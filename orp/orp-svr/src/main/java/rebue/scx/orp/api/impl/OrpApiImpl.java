package rebue.scx.orp.api.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.server.reactive.ServerHttpResponse;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.orp.api.OrpApi;
import rebue.scx.orp.svc.OrpSvc;
import rebue.scx.orp.to.ForgetSignInPswdTo;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.ra.SignUpOrInRa;

@DubboService
public class OrpApiImpl implements OrpApi {

    @Resource
    private OrpSvc svc;

    @Override
    public Pair<String, String> callback(final String code, ServerHttpResponse response) {
        return svc.callback(code, response);
    }

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    @Override
    public Ro<?> getAuthUrl(final String orpType, final String clientId, final String redirectUri) {
        return new Ro<>(ResultDic.SUCCESS, "获取认证URL成功", svc.getAuthUrl(orpType, clientId, redirectUri));
    }

    /**
     * 根据账户ID校验微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @Override
    public Ro<?> verifiyAccount(String orpType, String clientId, Long accountId, OrpCodeTo to) {
        return svc.verifiyAccount(orpType, clientId, accountId, to);
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    @Override
    public Ro<?> authCode(final String orpType, final String clientId, final OrpCodeTo to) {
        return new Ro<>(ResultDic.SUCCESS, "获取用户信息成功", svc.authCode(orpType, clientId, to));
    }

    /**
     * 通过授权码登录
     */
    @Override
    public Ro<SignUpOrInRa> signInByCode(final String appId, final String orpType, final String clientId, final OrpCodeTo to) {
        return svc.signInByCode(appId, orpType, clientId, to);
    }

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    @Override
    public Ro<?> bindModify(String orpType, String clientId, Long accountId, OrpCodeTo to) {
        Ro<?>   bindModify = svc.bindModify(orpType, clientId, accountId, to);
        boolean flag       = bindModify.getResult().getCode() == 1;
        if (flag) {
            return new Ro<>(ResultDic.SUCCESS, "绑定成功", bindModify);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "绑定失败:" + bindModify.getMsg(), bindModify);
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
        Ro<?>   unbindModify = svc.unbindModify(orpType, clientId, accountId, to);
        boolean flag         = unbindModify.getResult().getCode() == 1;
        if (flag) {
            return new Ro<>(ResultDic.SUCCESS, "解除绑定成功", unbindModify);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "解除绑定失败:" + unbindModify.getMsg(), unbindModify);
        }
    }

    @Override
    public Ro<?> forgetSignInPswdTo(String orpType, String clientId, ForgetSignInPswdTo to) {
        return svc.forgetSignInPswdTo(orpType, clientId, to);
    }

}
