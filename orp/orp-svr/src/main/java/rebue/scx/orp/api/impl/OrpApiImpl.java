package rebue.scx.orp.api.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.ResponseCookie;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.orp.api.OrpApi;
import rebue.scx.orp.svc.OrpSvc;

@DubboService
public class OrpApiImpl implements OrpApi {

    @Resource
    private OrpSvc oidcSvc;

    @Override
    public Triple<String, String, ResponseCookie> callback(final String code) {
        return oidcSvc.callback(code);
    }

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     */
    @Override
    public Ro<?> getAuthUrl(final String orpType, final String clientId, final String redirectUri) {
        return new Ro<>(ResultDic.SUCCESS, "获取认证URL成功", oidcSvc.getAuthUrl(orpType, clientId, redirectUri));
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    @Override
    public Ro<?> getUserInfo(final String orpType, final String clientId, final String code, final String state) {
        return new Ro<>(ResultDic.SUCCESS, "获取用户信息成功", oidcSvc.authCode(orpType, clientId, code, state));
    }

}
