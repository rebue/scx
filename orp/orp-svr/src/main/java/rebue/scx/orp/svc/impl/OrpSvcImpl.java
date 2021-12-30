package rebue.scx.orp.svc.impl;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.api.OapAppApi;
import rebue.scx.orp.config.OrpStrategies;
import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.to.AuthCodeTo;
import rebue.scx.orp.core.to.AuthTo;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.scx.orp.svc.OrpSvc;
import rebue.scx.orp.to.ForgetSignInPswdTo;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.api.ex.RacForgetPasswordApi;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.dic.SignUpOrInWayDic;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.ex.SignInByOidcTo;
import rebue.wheel.api.exception.RuntimeExceptionX;

@Slf4j
@Service
public class OrpSvcImpl implements OrpSvc {

    @Value("${oidc.public-key}")
    private String               publicKeyStr;

    private RSAPublicKey         publicKey;

    @DubboReference
    private RacAppApi            racAppApi;

    @DubboReference
    private RacAccountApi        racAccountApi;

    @DubboReference
    private RacSignInApi         racSignInApi;
    @DubboReference
    private RacForgetPasswordApi racForgetPasswordApi;

    @DubboReference
    private OapAppApi            oapAppApi;

    @Resource
    private OrpStrategies        strategy;

    @PostConstruct
    private void init() throws Exception {
        final byte[]             decoded = Base64.getDecoder().decode(publicKeyStr);
        final X509EncodedKeySpec spec    = new X509EncodedKeySpec(decoded);
        final KeyFactory         kf      = KeyFactory.getInstance("RSA");
        publicKey = (RSAPublicKey) kf.generatePublic(spec);
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
    @SneakyThrows
    public OrpUserInfoRa authCode(final String orpType, final String clientId, final OrpCodeTo to) {
        OrpUserInfoRa userInfoRa = strategy.getItems().get(orpType).authCode(AuthCodeTo.builder()
                .clientId(clientId)
                .code(to.getCode())
                .state(to.getState())
                .build());
        switch (orpType) {
        case "oidc":
            String sign = userInfoRa.getIdToken();
            final SignedJWT jwt = SignedJWT.parse(sign);
            if (!validateIdToken(jwt)) {
                throw new RuntimeExceptionX("*********** 服务器内部错误   ***************");
            }
        }
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
            RacAccountMo byId = racAccountApi.getAccountMoById(accountId);
            if (byId.getDdOpenId().equals(authCodeRa.getOpenId())) {
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
            RacAccountMo byIdMo = racAccountApi.getAccountMoById(accountId);
            if (byIdMo.getWxOpenId().equals(authCodeRa.getOpenId())) {
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
        OrpUserInfoRa authCodeRa = authCode(orpType, clientId, to);
        RacAccountMo  mo         = racAccountApi.getAccountMoById(accountId);
        switch (orpType) {
        case "ding-talk":
            if (mo.getDdOpenId().equals(authCodeRa.getOpenId())) {
                final String state      = UUID.randomUUID().toString();
                StateCache   stateCache = strategy.getItems().get(orpType).getSstateCache();
                stateCache.set(orpType, clientId, state);
                return new Ro<>(ResultDic.SUCCESS, state);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "扫码用户不对");
            }
        case "wechat-open":
            if (mo.getWxOpenId().equals(authCodeRa.getOpenId())) {
                final String state      = UUID.randomUUID().toString();
                StateCache   stateCache = strategy.getItems().get(orpType).getSstateCache();
                stateCache.set(orpType, clientId, state);
                return new Ro<>(ResultDic.SUCCESS, state);
            }
            else {
                return new Ro<>(ResultDic.FAIL, "扫码用户不对");
            }
        default:
            throw new RuntimeExceptionX("不支持此方式: " + orpType);
        }
    }

    @Override
    public Ro<?> forgetSignInPswdTo(String orpType, String clientId, ForgetSignInPswdTo to) {
        StateCache stateCache = strategy.getItems().get(orpType).getSstateCache();
        String     result     = stateCache.get(orpType, clientId, to.getVerifiy());
        if (result == null) {
            return new Ro<>(ResultDic.WARN, "参数错误或校验信息已经过期");
        }
        Ro<?> mo = racForgetPasswordApi.orpForgetSignInPswdToSetTo(to.getId(), to.getSignInPswd(), null);
        return mo;
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
