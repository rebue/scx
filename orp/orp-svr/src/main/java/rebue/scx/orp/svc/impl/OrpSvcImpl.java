package rebue.scx.orp.svc.impl;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
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
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.api.OapAppApi;
import rebue.scx.oap.config.OidcConfig;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.orp.config.OrpStrategies;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.scx.orp.svc.OrpSvc;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.dic.SignUpOrInWayDic;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.ex.SignInByOidcTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Service
public class OrpSvcImpl implements OrpSvc {

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
    private RacAccountApi racAccountApi;

    @DubboReference
    private RacSignInApi  racSignInApi;

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
     * @return [redirectUri, 错误信息]
     */
    @Override
    @SneakyThrows
    public Pair<String, String> callback(final String code, ServerHttpResponse response) {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                tokenEndpoint,
                clientId,
                clientSecret,
                code,
                redirectUri);
        if (!tokenResponse.indicatesSuccess()) {
            log.info("111 callback");
            return Pair.of(null, tokenResponse.toErrorResponse().getErrorObject().getDescription());
        }
        OIDCTokenResponse sr      = (OIDCTokenResponse) tokenResponse.toSuccessResponse();
        OIDCTokens        tokens  = sr.getOIDCTokens();
        JWT               idToken = tokens.getIDToken();
        if (!validateIdToken(idToken)) {
            return Pair.of(null, "服务器内部错误");
        }

        OapAppMo oapAppMo = oapAppApi.selectOneByClientId(clientId).orElse(null);
        if (oapAppMo == null) {
            return Pair.of(null, "应用不存在");
        }
        Ro<PojoRa<RacAppMo>> appRo = racAppApi.getById(oapAppMo.getAppId());
        RacAppMo             app;
        if (!appRo.isSuccess()
                || appRo.getExtra() == null
                || (app = appRo.getExtra().getOne()) == null) {
            return Pair.of(null, "应用不存在");
        }
        if (app.getUrl() == null) {
            return Pair.of(null, "应用url为空");
        }
        log.info("222 response appurl = " + app.getUrl());
        // todo 这里可以存储 accessToken refreshToken tokens.toJSONObject().toJSONString()
        response.addCookie(
                ResponseCookie.from(JwtUtils.JWT_TOKEN_NAME, idToken.serialize())
                        .path("/")
                        .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                        .build());

        // response.addCookie(
        // ResponseCookie.from(RacCookieCo.APP_ID_KEY, app.getId())
        // .path("/")
        // .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
        // .build());
        return Pair.of(app.getUrl(), null);
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
    public OrpUserInfoRa authCode(final String orpType, final String clientId, final OrpCodeTo to) {
        OrpUserInfoRa userInfoRa = strategy.getItems().get(orpType).authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .code(to.getCode())
                .state(to.getState())
                .build());

        return userInfoRa;
    }

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    @Override
    public Ro<?> bindModify(String orpType, String clientId, Long accountId, OrpCodeTo to) {
        OrpUserInfoRa authCodeRa = authCode(orpType, clientId, to);
        switch (orpType) {
        case "ding-talk":
            RacAccountModifyTo bindDingTalk = new RacAccountModifyTo();
            bindDingTalk.setId(accountId);
            bindDingTalk.setDdAvatar(authCodeRa.getAvatar());
            bindDingTalk.setDdNickname(authCodeRa.getNickname());
            bindDingTalk.setDdOpenId(authCodeRa.getOpenId());
            bindDingTalk.setDdUnionId(authCodeRa.getUnionId());
            bindDingTalk.setDdUserId(authCodeRa.getId());
            return racAccountApi.bindModify(bindDingTalk);
        case "wechat-open":
            RacAccountModifyTo wechatOpen = new RacAccountModifyTo();
            wechatOpen.setId(accountId);
            wechatOpen.setWxAvatar(authCodeRa.getAvatar());
            wechatOpen.setWxNickname(authCodeRa.getNickname());
            wechatOpen.setWxOpenId(authCodeRa.getOpenId());
            wechatOpen.setWxUnionId(authCodeRa.getUnionId());
            return racAccountApi.bindModify(wechatOpen);
        default:
            throw new RuntimeExceptionX("不支持此绑定方式: " + orpType);
        }
    }

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    @Override
    public Ro<?> unbindModify(String orpType, String clientId, Long accountId, OrpCodeTo to) {
        OrpUserInfoRa authCodeRa = authCode(orpType, clientId, to);
        switch (orpType) {
        case "ding-talk":
            Ro<PojoRa<RacAccountMo>> byId = racAccountApi.getById(accountId);
            if (byId.getExtra().getOne().getDdOpenId().equals(authCodeRa.getOpenId())) {
                RacAccountModifyTo bindDingTalk = new RacAccountModifyTo();
                bindDingTalk.setId(accountId);
                bindDingTalk.setDdAvatar(authCodeRa.getAvatar());
                bindDingTalk.setDdNickname(authCodeRa.getNickname());
                bindDingTalk.setDdOpenId(authCodeRa.getOpenId());
                bindDingTalk.setDdUnionId(authCodeRa.getUnionId());
                bindDingTalk.setDdUserId(authCodeRa.getId());
                return racAccountApi.unbindModify(bindDingTalk);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "扫码用户不对");
            }
        case "wechat-open":
            Ro<PojoRa<RacAccountMo>> byIdMo = racAccountApi.getById(accountId);
            if (byIdMo.getExtra().getOne().getWxOpenId().equals(authCodeRa.getOpenId())) {
                RacAccountModifyTo wechatOpen = new RacAccountModifyTo();
                wechatOpen.setId(accountId);
                wechatOpen.setWxAvatar(authCodeRa.getAvatar());
                wechatOpen.setWxNickname(authCodeRa.getNickname());
                wechatOpen.setWxOpenId(authCodeRa.getOpenId());
                wechatOpen.setWxUnionId(authCodeRa.getUnionId());
                return racAccountApi.unbindModify(wechatOpen);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "扫码用户不对");
            }
        default:
            throw new RuntimeExceptionX("不支持此解绑方式: " + orpType);
        }
    }

    /**
     * 根据账户ID校验微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @Override
    public Ro<?> verifiyAccount(String orpType, String clientId, Long accountId, OrpCodeTo to) {
        OrpUserInfoRa            authCodeRa = authCode(orpType, clientId, to);
        Ro<PojoRa<RacAccountMo>> byId       = racAccountApi.getById(accountId);
        RacAccountMo             mo         = byId.getExtra().getOne();
        switch (orpType) {
        case "ding-talk":
            if (mo.getDdOpenId().equals(authCodeRa.getOpenId())) {
                final String state = UUID.randomUUID().toString();
                return new Ro<>(ResultDic.SUCCESS, state);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "扫码用户不对");
            }
        case "wechat-open":
            if (mo.getWxOpenId().equals(authCodeRa.getOpenId())) {
                final String state = UUID.randomUUID().toString();
                return new Ro<>(ResultDic.SUCCESS, state);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "扫码用户不对");
            }
        default:
            throw new RuntimeExceptionX("不支持此方式: " + orpType);
        }
    }

    /**
     * 通过授权码登录
     */
    @Override
    public Ro<SignUpOrInRa> signInByCode(final String appId, final String orpType, final String clientId, final OrpCodeTo to) {
        final OrpUserInfoRa    userInfo = authCode(orpType, clientId, to);
        final SignUpOrInWayDic signUpOrInWay;
        switch (orpType) {
        case "ding-talk":
            signUpOrInWay = SignUpOrInWayDic.DINGTALK;
            break;
        case "wechat-open":
            signUpOrInWay = SignUpOrInWayDic.WECHAT;
            break;
        default:
            throw new RuntimeExceptionX("不支持此登录方式: " + orpType);
        }
        return racSignInApi.signInByOidc(signUpOrInWay, SignInByOidcTo.builder()
                .appId(appId)
                .unionId(userInfo.getUnionId())
                .openId(userInfo.getOpenId())
                .build());
    }

}
