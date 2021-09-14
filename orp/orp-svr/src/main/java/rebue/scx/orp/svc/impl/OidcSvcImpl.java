package rebue.scx.orp.svc.impl;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.github.rebue.orp.core.OidcCore;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.api.OapAppApi;
import rebue.scx.oap.config.OidcConfig;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.orp.config.OrpStrategies;
import rebue.scx.orp.core.ro.UserInfoRo;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.svc.OidcSvc;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.mo.RacAppMo;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Service
public class OidcSvcImpl implements OidcSvc {

    @Value("${oidc.client-id}")
    private String        clientId;

    @Value("${oidc.client-secret}")
    private String        clientSecret;

    @Value("${oidc.token-endpoint}")
    private String        tokenEndpoint;

    @Value("${oidc.public-key}")
    private String        publicKeyStr;

    @Value("${oidc.redirect-uri}")
    private String        redirectUri;

    private RSAPublicKey  publicKey;

    @DubboReference
    private RacAppApi     racAppApi;

    @DubboReference
    private OapAppApi     oapAppApi;

    @Resource
    private OrpStrategies strategy;

    @PostConstruct
    private void init() throws Exception {
        final byte[]             decoded = Base64.getDecoder().decode(publicKeyStr);
        final X509EncodedKeySpec spec    = new X509EncodedKeySpec(decoded);
        final KeyFactory         kf      = KeyFactory.getInstance("RSA");
        publicKey = (RSAPublicKey) kf.generatePublic(spec);
    }

    /**
     * @return [redirectUri, 错误信息, cookie]
     */
    @Override
    @SneakyThrows
    public Triple<String, String, ResponseCookie> callback(final String code) {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                tokenEndpoint,
                clientId,
                clientSecret,
                code,
                redirectUri);
        if (!tokenResponse.indicatesSuccess()) {
            log.info("111 callback");
            return Triple.of(null, tokenResponse.toErrorResponse().getErrorObject().getDescription(), null);
        }
        OIDCTokenResponse sr      = (OIDCTokenResponse) tokenResponse.toSuccessResponse();
        OIDCTokens        tokens  = sr.getOIDCTokens();
        JWT               idToken = tokens.getIDToken();
        if (!validateIdToken(idToken)) {
            return Triple.of(null, "服务器内部错误", null);
        }

        OapAppMo oapAppMo = oapAppApi.selectOneByClientId(clientId).orElse(null);
        if (oapAppMo == null) {
            return Triple.of(null, "应用不存在", null);
        }
        Ro<PojoRa<RacAppMo>> appRo = racAppApi.getById(oapAppMo.getAppId());
        RacAppMo             app;
        if (!appRo.isSuccess()
                || appRo.getExtra() == null
                || (app = appRo.getExtra().getOne()) == null) {
            return Triple.of(null, "应用不存在", null);
        }
        if (app.getUrl() == null) {
            return Triple.of(null, "应用url为空", null);
        }
        log.info("222 response appurl = " + app.getUrl());
        // todo 这里可以存储 accessToken refreshToken tokens.toJSONObject().toJSONString()
        return Triple.of(app.getUrl(), null, createCookie(idToken.serialize()));
    }

    private static ResponseCookie createCookie(final String value) {
        return ResponseCookie.from(JwtUtils.JWT_TOKEN_NAME, value)
                .path("/")
                .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                .build();
    }

    private boolean validateIdToken(final JWT idToken) throws Exception {
        final SignedJWT token = (SignedJWT) idToken;
        return token.verify(new RSASSAVerifier(publicKey));
    }

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    @Override
    public String getAuthUrl(final String orpType, final String clientId, final String redirectUri) {
        return strategy.getItems().get(orpType).getAuthUrl(AuthTo.builder()
                .clientId(clientId)
                .redirectUri(redirectUri)
                .build());
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    @Override
    public UserInfoRo authCode(final String orpType, final String clientId, final String code, final String state) {
        return strategy.getItems().get(orpType).authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .code(code)
                .state(state)
                .build());
    }

}
