package com.github.rebue.scx.svc.impl;

import com.github.rebue.orp.core.dto.TokenError;
import com.github.rebue.scx.dto.CodeValue;
import com.github.rebue.scx.dto.LoginDto;
import com.github.rebue.scx.exception.OidcAuthenticationException;
import com.github.rebue.scx.oidc.AuthorisationCodeFlow;
import com.github.rebue.scx.oidc.AuthorizeInfo;
import com.github.rebue.scx.oidc.CodeRepository;
import com.github.rebue.scx.oidc.OidcNS;
import com.github.rebue.scx.svc.OidcSvc;
import com.nimbusds.common.contenttype.ContentType;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientAuthenticationMethod;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OidcSvcImpl implements OidcSvc {

    private static final String AUTH_INFO = "auth_info";

    private final Map<String, Map<String, String>> sessions = new HashMap<>();

    @Autowired
    private CodeRepository codeRepository;

    @Override
    @SneakyThrows
    public void authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response)
    {
        Map<String, List<String>> params = paramMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue())));
        AuthenticationRequest aRequest = AuthenticationRequest.parse(params);
        ResponseType responseType = aRequest.getResponseType();
        if (responseType.impliesCodeFlow()) {
            codeFlowLoginPage(aRequest, request, response);
        } else {
            throw new OidcAuthenticationException("Invalid authentication request");
        }
    }

    @SneakyThrows
    private void codeFlowLoginPage(AuthenticationRequest aRequest, ServerHttpRequest hRequest, ServerHttpResponse hResponse)
    {
        if (isAuthenticated(hRequest)) {
            String userId = getUserId(hRequest);
            AuthorizationCode code = codeRepository.createCode(aRequest, userId);
            // todo redirect 校验
            HTTPResponse redirect = AuthorisationCodeFlow.authenticationSuccessUri(aRequest.getRedirectionURI(), aRequest.getState(), code);
            hResponse.setStatusCode(HttpStatus.FOUND);
            hResponse.getHeaders().setLocation(URI.create(redirect.getLocation().toString()));
            return;
        }
        String cookie = new AuthorizeInfo(aRequest).toStr();
        hResponse.addCookie(createCookie(AUTH_INFO, cookie));
        hResponse.setStatusCode(HttpStatus.FOUND);
        hResponse.getHeaders().setLocation(URI.create("http://localhost:13080/admin-web#/unifiedLogin"));
    }

    private String getUserId(ServerHttpRequest hRequest)
    {
        return null; // todo
    }

    private Optional<AuthorizeInfo> getSessionInfo(ServerHttpRequest request)
    {
        HttpCookie cookie = request.getCookies().getFirst(AUTH_INFO);
        if (cookie == null) {
            return Optional.empty();
        }
        return Optional.of(AuthorizeInfo.fromCookie(cookie.getValue()));
    }

    @Override
    @SneakyThrows
    public void login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response)
    {
        Optional<AuthorizeInfo> sessionInfo1 = getSessionInfo(request);

        // todo 用户名密码校验
        loginData.getLoginName();
        loginData.getPassword();
        Map<String, String> sessionInfo = getSession(request).orElse(null);
        if (sessionInfo == null) {
            // todo 错误信息
            return;
        }
        sessionInfo.put("isLogin", "isLogin");
        String uri = sessionInfo.get(OidcNS.OIDC_SKEY_REDIRECT_URI);
        String state = sessionInfo.get(OidcNS.OIDC_SKEY_STATE);
        String clientId = sessionInfo.get(OidcNS.OIDC_SKEY_CLIENT_ID);
        String scope = sessionInfo.get(OidcNS.OIDC_SKEY_SCOPE);
        // todo userCode
        sessionInfo.put(OidcNS.USER_CODE, loginData.getLoginName());
        AuthorizationCode code = codeRepository.createCode(uri, state, clientId, new Scope(scope), loginData.getLoginName());
        HTTPResponse redirect = AuthorisationCodeFlow.authenticationSuccessUri(new URI(uri), new State(state), code);
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().setLocation(URI.create(redirect.getLocation().toString()));
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
            return tokenError(response, "invalid_request", pair.getRight());
        }
        // todo 从 "数据库" 找client
        String clientId = tokenRequest.getClientAuthentication().getClientID().getValue();
        if (false) { // todo 没有client
            return tokenError(response, "invalid_client", "invalid client : " + clientId);
        }
        if (!compareSecret(tokenRequest, "todo")) { // todo 从"数据库"获取的密钥
            return tokenError(response, "unauthorized_client", "unauthorized client : " + clientId);
        }
        AuthorizationGrant grant = tokenRequest.getAuthorizationGrant();
        GrantType grantType = grant.getType();
        if (grantType.equals(GrantType.AUTHORIZATION_CODE)) {
            return issueIdToken(tokenRequest, response);
        }
        // todo refresh token
        return tokenError(response, "unsupported_grant_type", "invalid grant_type : " + grantType.getValue());
    }

    /**
     * @return {@link com.nimbusds.oauth2.sdk.AccessTokenResponse}
     * <p> 或 {@link com.github.rebue.orp.core.dto.TokenError}
     */
    private Object issueIdToken(TokenRequest tokenRequest, ServerHttpResponse response)
    {
        String code = getAuthorizationCode(tokenRequest).orElse(null);
        if (code == null) {
            return tokenError(response, "invalid_grant", "code is empty");
        }
        CodeValue codeValue = codeRepository.getCode(code).orElse(null);
        if (codeValue == null) {
            return tokenError(response, "invalid_grant", "invalid code : " + code);
        }
        String clientId = tokenRequest.getClientAuthentication().getClientID().getValue();
        if (!codeValue.getClientId().equals(clientId)) {
            return tokenError(response, "invalid_grant", "invalid clientId : " + clientId);
        }
        if (!verifyRedirectionUri(tokenRequest, codeValue.getRedirectionUri())) {
            return tokenError(response, "invalid_grant", "invalid redirection uri : " + codeValue.getRedirectionUri());
        }
        String userCode = codeValue.getUserCode();
        // todo OidcNS.makeIdtoken()
        return null;
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

    /**
     * 对比密钥
     */
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

    private Optional<Map<String, String>> getSession(ServerHttpRequest hRequest)
    {
        for (Map.Entry<String, List<HttpCookie>> kv : hRequest.getCookies().entrySet()) {
            if (kv.getKey().equals("sessionId")) {
                for (HttpCookie cookie : kv.getValue()) {
                    Map<String, String> m = sessions.get(cookie.getValue());
                    if (m != null) {
                        return Optional.of(m);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static ResponseCookie createCookie(String key, String value)
    {
        return ResponseCookie.from(key, value)
                .domain("localhost") // todo
                .path("/")
                .maxAge(100000L)
                .build();
    }

    private boolean isAuthenticated(ServerHttpRequest hRequest)
    {
        return false; // todo
    }

}
