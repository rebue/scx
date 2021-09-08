package com.github.rebue.scx.svc.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

import com.github.rebue.orp.core.dto.TokenError;
import com.github.rebue.scx.config.OidcConfig;
import com.github.rebue.scx.dto.CodeValue;
import com.github.rebue.scx.dto.LoginDto;
import com.github.rebue.scx.dto.RedirectUris;
import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.mo.OapGrantMo;
import com.github.rebue.scx.oidc.AuthorizeInfo;
import com.github.rebue.scx.oidc.CodeRepository;
import com.github.rebue.scx.oidc.OidcHelper;
import com.github.rebue.scx.oidc.OidcTokenError;
import com.github.rebue.scx.oidc.TokenHelper;
import com.github.rebue.scx.repository.OapAppRepository;
import com.github.rebue.scx.repository.OapRedirectUriRepository;
import com.github.rebue.scx.svc.AccessTokenService;
import com.github.rebue.scx.svc.OidcSvc;
import com.nimbusds.common.contenttype.ContentType;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant;
import com.nimbusds.oauth2.sdk.AuthorizationGrant;
import com.nimbusds.oauth2.sdk.GrantType;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.RefreshTokenGrant;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientAuthenticationMethod;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import com.nimbusds.oauth2.sdk.token.Tokens;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;

import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignInfo;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.wheel.turing.JwtUtils;

@Service
public class OidcSvcImpl implements OidcSvc {

    @Autowired
    private CodeRepository codeRepository;

    @Resource
    private OapAppRepository oapAppRepository;

    @Resource
    private OapRedirectUriRepository oapRedirectUriRepository;

    @Resource
    private AccessTokenService accessTokenService;

    @DubboReference
    private JwtApi jwtApi;

    @DubboReference
    private RacSignInApi racSignInApi;

    @Override
    @SneakyThrows
    public String authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response)
    {
        Map<String, List<String>> params = paramMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue())));
        AuthenticationRequest aRequest = AuthenticationRequest.parse(params);
        ResponseType responseType = aRequest.getResponseType();
        if (responseType.impliesCodeFlow()) {
            return codeFlowLoginPage(aRequest, request, response);
        } else {
            return "Invalid authentication request";
        }
    }

    @SneakyThrows
    private String codeFlowLoginPage(AuthenticationRequest aRequest, ServerHttpRequest hRequest, ServerHttpResponse hResponse)
    {
        JwtSignInfo jwtSignInfo;
        if ((jwtSignInfo = getAuthenticatedInfo(hRequest)) != null) {
            AuthorizationCode code = codeRepository.createCode(aRequest, jwtSignInfo.getAccountId());
            HTTPResponse redirect = OidcHelper.authenticationSuccessUri(aRequest.getRedirectionURI(), aRequest.getState(), code);
            String r = redirect.getLocation().toString();
            RedirectUris redirectUris = oapRedirectUriRepository.getRedirectUris(aRequest.getClientID().getValue());
            if (!redirectUris.match(r)) {
                return "重定向地址错误";
            }
            hResponse.setStatusCode(HttpStatus.FOUND);
            hResponse.getHeaders().setLocation(URI.create(r));
            return null;
        }
        String cookieValue = new AuthorizeInfo(aRequest).toStr();
        hResponse.addCookie(createCookie(cookieValue));
        hResponse.setStatusCode(HttpStatus.FOUND);
        hResponse.getHeaders().setLocation(URI.create(OidcConfig.LOGIN_URL));
        return null;
    }

    private Optional<AuthorizeInfo> getSessionInfo(ServerHttpRequest request)
    {
        HttpCookie cookie = request.getCookies().getFirst(OidcConfig.AUTH_INFO);
        if (cookie == null) {
            return Optional.empty();
        }
        return AuthorizeInfo.fromCookie(cookie.getValue());
    }


    @Override
    @SneakyThrows
    public Ro<String> login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response)
    {
        AuthorizeInfo sessionInfo = getSessionInfo(request).orElse(null);
        if (sessionInfo == null) {
            return Ro.fail("未获取到session信息");
        }

        String uri = sessionInfo.getRedirectUri();
        String state = sessionInfo.getState();
        String clientId = sessionInfo.getClientId();
        String scope = sessionInfo.getScope();

        OapAppMo app = oapAppRepository.selectByClientId(clientId);
        if (app == null) {
            return Ro.fail("clientId 不存在");
        }
        UnifiedLoginTo to = new UnifiedLoginTo();
        to.setAppId(app.getAppId());
        to.setUsername(loginData.getLoginName());
        to.setPassword(loginData.getPassword());
        RacAccountMo ra = racSignInApi.unifiedLogin(to).orElse(null);
        if (ra == null) {
            // region 测试
            to.setPassword(DigestUtils.md5Hex(loginData.getPassword()));
            ra = racSignInApi.unifiedLogin(to).orElse(null);
            if (ra == null) {
                return Ro.fail("账户或密码错误");
            }
            // endregion
        }
        AuthorizationCode code = codeRepository.createCode(uri, clientId, new Scope(scope), ra.getId());
        HTTPResponse redirect = OidcHelper.authenticationSuccessUri(new URI(uri), new State(state), code);
        String r = redirect.getLocation().toString();
        RedirectUris redirectUris = oapRedirectUriRepository.getRedirectUris(clientId);
        if (!redirectUris.match(r)) {
            return Ro.fail("重定向地址错误");
        }
        return Ro.success(URI.create(redirect.getLocation().toString()).toString());
    }

    /**
     * @return {@link com.nimbusds.oauth2.sdk.AccessTokenResponse}
     * <p> 或 {@link com.github.rebue.orp.core.dto.TokenError}
     */
    @Override
    public Object token(String authorization, URL url, String requestBody, ServerHttpResponse response)
    {
        TokenRequest tokenRequest;
        Pair<TokenRequest, String> pair = tokenRequest(url, authorization, requestBody);
        if ((tokenRequest = pair.getLeft()) == null) {
            return tokenError(response, OidcTokenError.INVALID_REQUEST, pair.getRight());
        }
        String clientId = tokenRequest.getClientAuthentication().getClientID().getValue();
        OapAppMo mo = new OapAppMo();
        mo.setClientId(clientId);
        OapAppMo app = oapAppRepository.selectByClientId(clientId);
        if (app == null) {
            return tokenError(response, OidcTokenError.INVALID_CLIENT, "invalid client : " + clientId);
        }
        if (!compareSecret(tokenRequest, app.getSecret())) {
            return tokenError(response, OidcTokenError.UNAUTHORIZED_CLIENT, "unauthorized client : " + clientId);
        }
        AuthorizationGrant grant = tokenRequest.getAuthorizationGrant();
        GrantType grantType = grant.getType();
        if (grantType.equals(GrantType.AUTHORIZATION_CODE)) {
            return issueIdToken(tokenRequest, response);
        } else if (grantType.equals(GrantType.REFRESH_TOKEN)) {
            RefreshToken refreshToken = ((RefreshTokenGrant) grant).getRefreshToken();
            return refreshAccessToken(refreshToken, response);
        }
        return tokenError(response, OidcTokenError.UNSUPPORTED_GRANT_TYPE, "invalid grant_type : " + grantType.getValue());
    }

    /**
     * @return {@link com.nimbusds.openid.connect.sdk.OIDCTokenResponse} .toHTTPResponse().getContentAsJSONObject()
     * <p> 或 {@link com.github.rebue.orp.core.dto.TokenError}
     */
    @SneakyThrows
    private Object issueIdToken(TokenRequest tokenRequest, ServerHttpResponse response)
    {
        String code = getAuthorizationCode(tokenRequest).orElse(null);
        if (code == null) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "code is empty");
        }
        CodeValue codeValue = codeRepository.getAndRemoveCode(code).orElse(null);
        if (codeValue == null) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "invalid code : " + code);
        }
        String clientId = tokenRequest.getClientAuthentication().getClientID().getValue();
        if (!codeValue.getClientId().equals(clientId)) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "invalid clientId : " + clientId);
        }
        if (!verifyRedirectionUri(tokenRequest, codeValue.getRedirectionUri())) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "invalid redirection uri : " + codeValue.getRedirectionUri());
        }
        JwtSignRa jwtSignRa = jwtApi.sign(new JwtSignTo(String.valueOf(codeValue.getAccountId()), codeValue.getClientId()));
        if (!jwtSignRa.isSuccess()) {
            return tokenError(response, OidcTokenError.SERVER_ERROR, "1111111111111111111111");
        }
        SignedJWT idToken = jwtApi.rawSign(new JwtSignTo(String.valueOf(codeValue.getAccountId()), codeValue.getClientId()));
        if (idToken == null) {
            return tokenError(response, OidcTokenError.SERVER_ERROR, "22222222222222222222222");
        }

        BearerAccessToken accessToken = new BearerAccessToken(OidcConfig.ACCESS_TOKEN_LIFETIME, codeValue.getScope());
        RefreshToken refreshToken = new RefreshToken();

        accessTokenService.saveToken(codeValue.getAccountId(), accessToken, refreshToken);

        OIDCTokens tokens = new OIDCTokens(idToken, accessToken, refreshToken);
        return makeTokenResponse(new OIDCTokenResponse(tokens), response);
    }

    @SneakyThrows
    private Object refreshAccessToken(RefreshToken refreshToken, ServerHttpResponse response)
    {
        if (refreshToken == null) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "Refresh token is null");
        }
        OapGrantMo info = accessTokenService.getByRefreshToken(refreshToken.getValue());
        if (info == null) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "Refresh token does not exist");
        }

        long now = System.currentTimeMillis();
        long toEnd = info.getRefreshTokenExpiresTimestamp() - now;
        RefreshToken newRefreshToken;
        if (toEnd < 24 * 60 * 60) {
            // 距离 refresh token 结束时间少于一天，则颁发新的
            newRefreshToken = new RefreshToken();
            info.setRefreshToken(newRefreshToken.getValue());
        } else {
            newRefreshToken = null;
        }
        Scope scope = TokenHelper.strToAccessToken(info.getAccessToken()).getScope();
        BearerAccessToken accessToken = new BearerAccessToken(OidcConfig.ACCESS_TOKEN_LIFETIME, scope);
        info.setAccessToken(accessToken.getValue());
        accessTokenService.updateToken(info);

        Tokens tokens = new Tokens(accessToken, newRefreshToken);
        AccessTokenResponse tokenResponse = new AccessTokenResponse(tokens);
        return makeTokenResponse(tokenResponse, response);
    }

    @SneakyThrows
    private JSONObject makeTokenResponse(AccessTokenResponse tokenResponse, ServerHttpResponse response)
    {
        response.getHeaders().set("Cache-Control", "no-store");
        response.getHeaders().set("Pragma", "no-cache");
        return tokenResponse.toHTTPResponse().getContentAsJSONObject();
    }


    private static boolean verifyRedirectionUri(TokenRequest tokenRequest, String uri)
    {
        try {
            URI uri2 = new URI(uri);
            AuthorizationGrant auth = tokenRequest.getAuthorizationGrant();
            if (auth.getType().equals(GrantType.AUTHORIZATION_CODE)) {
                AuthorizationCodeGrant codeAuth = (AuthorizationCodeGrant) auth;
                String decoded = URLDecoder.decode(
                        codeAuth.getRedirectionURI().toString(), "UTF-8");
                return decoded.equalsIgnoreCase(uri2.toString());
            }
            return false;
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            return false;
        }
    }

    private static Optional<String> getAuthorizationCode(TokenRequest tokenRequest)
    {
        AuthorizationGrant auth = tokenRequest.getAuthorizationGrant();
        if (auth.getType().equals(GrantType.AUTHORIZATION_CODE)) {
            AuthorizationCodeGrant codeAuth = (AuthorizationCodeGrant) auth;
            String code = codeAuth.getAuthorizationCode().getValue();
            return Optional.ofNullable(code);
        }
        return Optional.empty();
    }

    private boolean compareSecret(TokenRequest tokenRequest, String secret)
    {
        ClientAuthentication clientAuth = tokenRequest.getClientAuthentication();
        if (clientAuth == null) {
            return false;
        }
        if (clientAuth.getMethod().equals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)) {
            ClientSecretBasic basic = (ClientSecretBasic) clientAuth;
            String value = basic.getClientSecret().getValue();
            return value.equals(secret);
        }
        return false;
    }

    public static TokenError tokenError(ServerHttpResponse response, String error, String errorDesc)
    {
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        TokenError e = new TokenError();
        e.setError(error);
        e.setError_description(errorDesc);
        return e;
    }

    private Pair<TokenRequest, String> tokenRequest(URL url, String authorization, String requestBody)
    {
        try {
            HTTPRequest httpRequest = new HTTPRequest(HTTPRequest.Method.POST, url);
            httpRequest.setEntityContentType(ContentType.APPLICATION_URLENCODED);
            httpRequest.setQuery(requestBody);
            httpRequest.setAuthorization(authorization);
            TokenRequest tr = TokenRequest.parse(httpRequest);
            return Pair.of(tr, null);
        } catch (ParseException e) {
            return Pair.of(null, e.toString());
        }
    }

    private static ResponseCookie createCookie(String value)
    {
        return ResponseCookie.from(OidcConfig.AUTH_INFO, value)
                .domain(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_DOMAIN)
                .path("/")
                .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                .build();
    }

    private JwtSignInfo getAuthenticatedInfo(ServerHttpRequest hRequest)
    {
        HttpCookie cookie = hRequest.getCookies().getFirst(JwtUtils.JWT_TOKEN_NAME);
        if (cookie == null) {
            return null;
        }
        return jwtApi.verifyNotUpdate(cookie.getValue());
    }

}
