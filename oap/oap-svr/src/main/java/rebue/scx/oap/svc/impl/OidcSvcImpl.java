package rebue.scx.oap.svc.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.minidev.json.JSONObject;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignInfo;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.oap.config.OidcConfig;
import rebue.scx.oap.dic.OIDCAppDic;
import rebue.scx.oap.dto.CodeValue;
import rebue.scx.oap.dto.LoginDto;
import rebue.scx.oap.dto.OidcGetUserInfoTo;
import rebue.scx.oap.dto.RedirectUris;
import rebue.scx.oap.dto.UserInfoMo;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.oap.mo.OapGrantMo;
import rebue.scx.oap.oidc.AuthorizeInfo;
import rebue.scx.oap.oidc.CodeRepository;
import rebue.scx.oap.oidc.OidcHelper;
import rebue.scx.oap.oidc.OidcTokenError;
import rebue.scx.oap.oidc.TokenHelper;
import rebue.scx.oap.repository.OapAppRepository;
import rebue.scx.oap.repository.OapRedirectUriRepository;
import rebue.scx.oap.svc.AccessTokenService;
import rebue.scx.oap.svc.OapAuthLogSvc;
import rebue.scx.oap.svc.OapGrantSvc;
import rebue.scx.oap.svc.OidcSvc;
import rebue.scx.oap.to.OapAuthLogAddTo;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.wheel.core.util.OrikaUtils;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Service
public class OidcSvcImpl implements OidcSvc {

    @Autowired
    private CodeRepository           codeRepository;

    @Resource
    private OapAppRepository         oapAppRepository;
    @Resource
    private OapGrantSvc              OapGrantSvc;
    @Resource
    private OapRedirectUriRepository oapRedirectUriRepository;

    @Resource
    private AccessTokenService       accessTokenService;

    @Resource
    private OapAuthLogSvc            oapAuthLogSvc;

    @DubboReference
    private JwtApi                   jwtApi;

    @DubboReference
    private RacSignInApi             racSignInApi;
    @DubboReference
    private RacAccountApi            racAccountApi;
    @DubboReference
    private RacAppApi                racAppApi;

    /**
     * 映射工厂(用于不同类型的对象之间复制属性)
     */
    private MapperFactory            _mapperFactory = new DefaultMapperFactory.Builder().build();

    public OidcSvcImpl() {
        log.info(StringUtils.rightPad("*** 初始化 映射工厂(用于不同类型的对象之间复制属性)***", 100));
        _mapperFactory.classMap(RacAccountMo.class, UserInfoMo.class)
                .field("signInMobile", "mobile")
                .field("signInAvatar", "avatar")
                .field("signInEmail", "email")
                .field("signInNickname", "nickname")
                .byDefault().register();
    }

    /**
     * 根据accessToken idToken获取用户基础信息
     */
    @Override
    public Object getUserInfo(OidcGetUserInfoTo userInfoTo) {
        long       now         = System.currentTimeMillis();
        String     accessToken = userInfoTo.getAccessToken();
        OapGrantMo userInfoMo  = accessTokenService.getUserInfo(accessToken);
        String     idToken     = userInfoTo.getIdToken();
        Long       accountId   = JwtUtils.getJwtAccountIdFromSign(idToken);
        if (accountId == null) {
            return new Ro<>(ResultDic.PARAM_ERROR, "获取失败idToken不存在/已过期");
        }
        if (userInfoMo == null) {
            return new Ro<>(ResultDic.PARAM_ERROR, "获取失败accessToken不存在");
        }
        else if (userInfoMo.getAccessTokenExpireTimestamp() < now) {
            return new Ro<>(ResultDic.PARAM_ERROR, "获取失败accessToken已过期");
        }
        if (!userInfoMo.getAccountId().equals(accountId)) {
            return new Ro<>(ResultDic.PARAM_ERROR, "获取失败accessToken与idToken不对应");
        }
        RacAccountMo mo = racAccountApi.getAccountMoById(accountId);
        if (mo == null) {
            return new Ro<>(ResultDic.FAIL, "获取失败此用户不存在");
        }
        final UserInfoMo userMo = _mapperFactory.getMapperFacade().map(mo, UserInfoMo.class);
        userMo.setAccessToken(accessToken);
        userMo.setIdToken(idToken);
        return userMo;
    }

    @Override
    @SneakyThrows
    public String authorize(Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, List<String>> params       = paramMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue())));
        AuthenticationRequest     aRequest     = AuthenticationRequest.parse(params);
        ResponseType              responseType = aRequest.getResponseType();
        if (responseType.impliesCodeFlow()) {
            return codeFlowLoginPage(aRequest, request, response);
        }
        else {
            OapAuthLogAddTo add = new OapAuthLogAddTo();
            add.setIsSuccess(false);
            add.setOpDatetime(LocalDateTime.now());
            add.setReason("Invalid authentication request");
            oapAuthLogSvc.add(add);
            return "Invalid authentication request";
        }
    }

    @SneakyThrows
    private String codeFlowLoginPage(AuthenticationRequest aRequest, ServerHttpRequest hRequest, ServerHttpResponse hResponse) {
        JwtSignInfo jwtSignInfo;
        log.info(StringUtils.rightPad("*** 判断是否已登录***", 100));
        if ((jwtSignInfo = getAuthenticatedInfo(hRequest)) != null) {
            log.info(StringUtils.rightPad("*** 已登录***", 100));
            AuthorizationCode code         = codeRepository.createCode(aRequest, jwtSignInfo.getAccountId());
            HTTPResponse      redirect     = OidcHelper.authenticationSuccessUri(aRequest.getRedirectionURI(), aRequest.getState(), code);
            String            r            = redirect.getLocation().toString();
            RedirectUris      redirectUris = oapRedirectUriRepository.getRedirectUris(aRequest.getClientID().getValue());
            if (!redirectUris.match(r)) {
                OapAuthLogAddTo add = new OapAuthLogAddTo();
                add.setIsSuccess(false);
                add.setOpDatetime(LocalDateTime.now());
                add.setReason("codeFlowLoginPage重定向地址错误");
                oapAuthLogSvc.add(add);
                return "codeFlowLoginPage重定向地址错误";
            }
            OapAppMo oapAppMo    = oapAppRepository.selectByClientId(aRequest.getClientID().getValue());
            String   callbackUrl = "";
            try {
                String url = racAppApi.getById(oapAppMo.getAppId()).getExtra().getOne().getUrl();
                callbackUrl = getCallbackUrl(url);
            } catch (Exception e) {
                OapAuthLogAddTo add = new OapAuthLogAddTo();
                add.setIsSuccess(false);
                add.setOpDatetime(LocalDateTime.now());
                add.setReason("ClientID未配对App错误，rac不存在该应用/应用URL地址为空");
                oapAuthLogSvc.add(add);
                return "ClientID未配对App错误，rac不存在该应用/应用URL地址为空";
            }
            hResponse.setStatusCode(HttpStatus.FOUND);
            String url = getURLDecoderString(r);
            log.info("********* RedirectionURI*********:" + url + "\t");
            hResponse.getHeaders().setLocation(URI.create(url + callbackUrl));
            return null;
        }
        log.info(StringUtils.rightPad("*** 未登录***", 100));
        String cookieValue = new AuthorizeInfo(aRequest).toStr();
        log.info(StringUtils.rightPad("*** 向Cookie中添加编码后的ClientID/RedirectionURI 如下***", 100));
        log.info(StringUtils.rightPad("*** ClientID ***:" + aRequest.getClientID(), 100));
        log.info("********* RedirectionURI*********:" + aRequest.getRedirectionURI() + "\t\t");
        hResponse.addCookie(createCookie(cookieValue));
        hResponse.setStatusCode(HttpStatus.FOUND);
        hResponse.getHeaders().setLocation(URI.create(OidcConfig.getLoginUrl()));
        return null;
    }

    private Optional<AuthorizeInfo> getSessionInfo(ServerHttpRequest request) {
        HttpCookie cookie = request.getCookies().getFirst(OidcConfig.AUTH_INFO);
        if (cookie == null) {
            return Optional.empty();
        }
        return AuthorizeInfo.fromCookie(cookie.getValue());
    }

    /**
     * 获取帐号登录信息
     * 
     * @param appId
     * @param loginData
     */
    private Ro<SignUpOrInRa> getSignUpOrInRa(final String appId, LoginDto loginData) {
        UnifiedLoginTo oneTo = OrikaUtils.map(loginData, UnifiedLoginTo.class);
        oneTo.setAppId(appId);
        Ro<SignUpOrInRa> ra = racSignInApi.unifiedLogin(oneTo);
        return ra;
    }

    @Override
    @SneakyThrows
    public Ro<String> login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response) {
        AuthorizeInfo sessionInfo = getSessionInfo(request).orElse(null);
        if (sessionInfo == null) {
            OapAuthLogAddTo add = new OapAuthLogAddTo();
            add.setIsSuccess(false);
            add.setOpDatetime(LocalDateTime.now());
            add.setReason("未获取到session信息");
            oapAuthLogSvc.add(add);
            return Ro.fail("会话信息已失效,请刷新页面！");
        }

        String   uri      = sessionInfo.getRedirectUri();
        String   state    = sessionInfo.getState();
        String   clientId = sessionInfo.getClientId();
        String   scope    = sessionInfo.getScope();

        OapAppMo app      = oapAppRepository.selectByClientId(clientId);
        if (app == null) {
            OapAuthLogAddTo add = new OapAuthLogAddTo();
            add.setIsSuccess(false);
            add.setOpDatetime(LocalDateTime.now());
            add.setReason("clientId 不存在");
            oapAuthLogSvc.add(add);
            return Ro.fail("clientId 不存在");
        }
        Ro<SignUpOrInRa> ra = getSignUpOrInRa(OIDCAppDic.unified_auth.getDesc(), loginData);
        if (ra.getResult().getCode() != 1) {
            OapAuthLogAddTo add = new OapAuthLogAddTo();
            add.setIsSuccess(false);
            add.setOpDatetime(LocalDateTime.now());
            add.setReason(ra.getMsg());
            oapAuthLogSvc.add(add);
            return Ro.fail(ra.getMsg());
        }
        Ro<PojoRa<RacAppMo>> byId = racAppApi.getById(app.getAppId());
        RacAppMo             one  = byId.getExtra().getOne();
        ra.getExtra().setRedirectUrl(one.getUrl());
        if (ra.getExtra().getRedirectUrl() == null) {
            OapAuthLogAddTo add = new OapAuthLogAddTo();
            add.setIsSuccess(false);
            add.setOpDatetime(LocalDateTime.now());
            add.setReason("应用URL地址不能为空，请到平台管理设置应用主页地址");
            oapAuthLogSvc.add(add);
            return Ro.fail("应用URL地址不能为空，请到平台管理设置应用主页地址");
        }
        AuthorizationCode code         = codeRepository.createCode(clientId, new Scope(scope), ra.getExtra().getId());
        HTTPResponse      redirect     = OidcHelper.authenticationSuccessUri(new URI(uri), new State(state), code);
        String            r            = redirect.getLocation().toString();
        // 查询安全域名
        RedirectUris      redirectUris = oapRedirectUriRepository.getRedirectUris(clientId);
        if (!redirectUris.match(r)) {
            OapAuthLogAddTo add = new OapAuthLogAddTo();
            add.setIsSuccess(false);
            add.setOpDatetime(LocalDateTime.now());
            add.setReason("重定向地址错误");
            oapAuthLogSvc.add(add);
            return Ro.fail("重定向地址错误");
        }
        response.addCookie(
                ResponseCookie.from(OidcConfig.AUTH_INFO, "")
                        .path("/").sameSite("None").secure(true)
                        .maxAge(0)
                        .build());
        response.addCookie(
                ResponseCookie.from(JwtUtils.JWT_TOKEN_NAME, ra.getExtra().getSign())
                        .path("/")
                        .sameSite("None").secure(true)
                        .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                        .build());
        log.info("********* 登录login重定向地址*********:" + r + "\t\t");
        String          redirectUrl = redirect.getLocation().toString().toString() + getCallbackUrl(ra.getExtra().getRedirectUrl());
        OapAuthLogAddTo add         = new OapAuthLogAddTo();
        add.setIsSuccess(true);
        add.setOpDatetime(LocalDateTime.now());
        add.setReason("登录成功");
        oapAuthLogSvc.add(add);
        return Ro.success(URI.create(redirectUrl).toString());
    }

    /**
     * @return {@link com.nimbusds.oauth2.sdk.AccessTokenResponse}
     *         <p>
     *         或 {@link com.github.rebue.orp.core.dto.TokenError}
     */
    @Override
    public Object token(String authorization, URL url, String requestBody, ServerHttpResponse response) {
        TokenRequest               tokenRequest;
        Pair<TokenRequest, String> pair = tokenRequest(url, authorization, requestBody);
        if ((tokenRequest = pair.getLeft()) == null) {
            log.info(StringUtils.rightPad("*** token校验request请求错误 ***", 100));
            return tokenError(response, OidcTokenError.INVALID_REQUEST, pair.getRight());
        }
        String   clientId = tokenRequest.getClientAuthentication().getClientID().getValue();
        OapAppMo mo       = new OapAppMo();
        mo.setClientId(clientId);
        OapAppMo app = oapAppRepository.selectByClientId(clientId);
        if (app == null) {
            log.info(StringUtils.rightPad("*** token校验ClientId错误 ***", 100));
            return tokenError(response, OidcTokenError.INVALID_CLIENT, "invalid client : " + clientId);
        }
        if (!compareSecret(tokenRequest, app.getSecret())) {
            log.info(StringUtils.rightPad("*** token校验ClientSecret错误 ***", 100));
            return tokenError(response, OidcTokenError.UNAUTHORIZED_CLIENT, "unauthorized client : " + clientId);
        }
        AuthorizationGrant grant     = tokenRequest.getAuthorizationGrant();
        GrantType          grantType = grant.getType();
        if (grantType.equals(GrantType.AUTHORIZATION_CODE)) {
            return issueIdToken(tokenRequest, response);
        }
        else if (grantType.equals(GrantType.REFRESH_TOKEN)) {
            RefreshToken refreshToken = ((RefreshTokenGrant) grant).getRefreshToken();
            return refreshAccessToken(refreshToken, response);
        }
        return tokenError(response, OidcTokenError.UNSUPPORTED_GRANT_TYPE, "invalid grant_type : " + grantType.getValue());
    }

    /**
     * @return {@link com.nimbusds.openid.connect.sdk.OIDCTokenResponse} .toHTTPResponse().getContentAsJSONObject()
     *         <p>
     *         或 {@link com.github.rebue.orp.core.dto.TokenError}
     */
    @SneakyThrows
    private Object issueIdToken(TokenRequest tokenRequest, ServerHttpResponse response) {
        String code = getAuthorizationCode(tokenRequest).orElse(null);
        if (code == null) {
            log.info(StringUtils.rightPad("*** token校验code is empty ***", 100));
            return tokenError(response, OidcTokenError.INVALID_GRANT, "code is empty");
        }
        CodeValue codeValue = codeRepository.getAndRemoveCode(code).orElse(null);
        if (codeValue == null) {
            log.info(StringUtils.rightPad("*** token校验code 是无效的 ***", 100));
            return tokenError(response, OidcTokenError.INVALID_GRANT, "invalid code : " + code);
        }
        String clientId = tokenRequest.getClientAuthentication().getClientID().getValue();
        if (!codeValue.getClientId().equals(clientId)) {
            log.info(StringUtils.rightPad("*** token校验clientId 是无效的 ***", 100));
            return tokenError(response, OidcTokenError.INVALID_GRANT, "invalid clientId : " + clientId);
        }
        // if (!verifyRedirectionUri(tokenRequest, codeValue.getRedirectionUri())) {
        // log.info(StringUtils.rightPad("*** token校验redirection uri 是无效的 ***", 100));
        // return tokenError(response, OidcTokenError.INVALID_GRANT, "invalid redirection uri : " + codeValue.getRedirectionUri());
        // }
        JwtSignRa jwtSignRa = jwtApi.sign(new JwtSignTo(String.valueOf(codeValue.getAccountId()), codeValue.getClientId()));
        if (!jwtSignRa.isSuccess()) {
            return tokenError(response, OidcTokenError.SERVER_ERROR, "1111111111111111111111");
        }
        SignedJWT idToken = jwtApi.rawSign(new JwtSignTo(String.valueOf(codeValue.getAccountId()), codeValue.getClientId()));
        if (idToken == null) {
            return tokenError(response, OidcTokenError.SERVER_ERROR, "22222222222222222222222");
        }

        BearerAccessToken accessToken  = new BearerAccessToken(OidcConfig.ACCESS_TOKEN_LIFETIME, codeValue.getScope());
        RefreshToken      refreshToken = new RefreshToken();

        accessTokenService.saveToken(codeValue.getAccountId(), accessToken, refreshToken);

        OIDCTokens tokens = new OIDCTokens(idToken, accessToken, refreshToken);
        return makeTokenResponse(new OIDCTokenResponse(tokens), response);
    }

    @SneakyThrows
    private Object refreshAccessToken(RefreshToken refreshToken, ServerHttpResponse response) {
        if (refreshToken == null) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "Refresh token is null");
        }
        OapGrantMo info = accessTokenService.getByRefreshToken(refreshToken.getValue());
        if (info == null) {
            return tokenError(response, OidcTokenError.INVALID_GRANT, "Refresh token does not exist");
        }

        long         now   = System.currentTimeMillis();
        long         toEnd = info.getRefreshTokenExpiresTimestamp() - now;
        RefreshToken newRefreshToken;
        if (toEnd < 24 * 60 * 60) {
            // 距离 refresh token 结束时间少于一天，则颁发新的
            newRefreshToken = new RefreshToken();
            info.setRefreshToken(newRefreshToken.getValue());
        }
        else {
            newRefreshToken = null;
        }
        Scope             scope       = TokenHelper.strToAccessToken(info.getAccessToken()).getScope();
        BearerAccessToken accessToken = new BearerAccessToken(OidcConfig.ACCESS_TOKEN_LIFETIME, scope);
        info.setAccessToken(accessToken.getValue());
        accessTokenService.updateToken(info);

        Tokens              tokens        = new Tokens(accessToken, newRefreshToken);
        AccessTokenResponse tokenResponse = new AccessTokenResponse(tokens);
        return makeTokenResponse(tokenResponse, response);
    }

    @SneakyThrows
    private JSONObject makeTokenResponse(AccessTokenResponse tokenResponse, ServerHttpResponse response) {
        response.getHeaders().set("Cache-Control", "no-store");
        response.getHeaders().set("Pragma", "no-cache");
        return tokenResponse.toHTTPResponse().getContentAsJSONObject();
    }

    private static boolean verifyRedirectionUri(TokenRequest tokenRequest, String uri) {
        try {
            URI                uri2 = new URI(uri);
            AuthorizationGrant auth = tokenRequest.getAuthorizationGrant();
            if (auth.getType().equals(GrantType.AUTHORIZATION_CODE)) {
                AuthorizationCodeGrant codeAuth = (AuthorizationCodeGrant) auth;
                String                 decoded  = URLDecoder.decode(
                        codeAuth.getRedirectionURI().toString(), "UTF-8");
                return decoded.equalsIgnoreCase(uri2.toString());
            }
            return false;
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            return false;
        }
    }

    private static Optional<String> getAuthorizationCode(TokenRequest tokenRequest) {
        AuthorizationGrant auth = tokenRequest.getAuthorizationGrant();
        if (auth.getType().equals(GrantType.AUTHORIZATION_CODE)) {
            AuthorizationCodeGrant codeAuth = (AuthorizationCodeGrant) auth;
            String                 code     = codeAuth.getAuthorizationCode().getValue();
            return Optional.ofNullable(code);
        }
        return Optional.empty();
    }

    private boolean compareSecret(TokenRequest tokenRequest, String secret) {
        ClientAuthentication clientAuth = tokenRequest.getClientAuthentication();
        if (clientAuth == null) {
            return false;
        }
        if (clientAuth.getMethod().equals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)) {
            ClientSecretBasic basic = (ClientSecretBasic) clientAuth;
            String            value = basic.getClientSecret().getValue();
            return value.equals(secret);
        }
        return false;
    }

    public static TokenError tokenError(ServerHttpResponse response, String error, String errorDesc) {
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        TokenError e = new TokenError();
        e.setError(error);
        e.setError_description(errorDesc);
        return e;
    }

    private Pair<TokenRequest, String> tokenRequest(URL url, String authorization, String requestBody) {
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

    private static ResponseCookie createCookie(String value) {
        return ResponseCookie.from(OidcConfig.AUTH_INFO, value)
                .path("/")
                .sameSite("None").secure(true)
                .maxAge(30 * 60)
                .build();
    }

    private JwtSignInfo getAuthenticatedInfo(ServerHttpRequest hRequest) {
        HttpCookie cookie = hRequest.getCookies().getFirst(JwtUtils.JWT_TOKEN_NAME);
        if (cookie == null) {
            return null;
        }
        return jwtApi.verifyNotUpdate(cookie.getValue());
    }

    /**
     * 获取登录后重定向的地址
     *
     * @return String
     */
    private static String getCallbackUrl(String parameter) {
        String callbackUrl      = "&callbackUrl=";
        String urlEncoderString = getURLEncoderString(parameter);
        return callbackUrl + urlEncoderString;
    }

    /**
     * URL 转码
     *
     * @return String
     */
    private static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 解码
     *
     * @return str
     */
    private static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
