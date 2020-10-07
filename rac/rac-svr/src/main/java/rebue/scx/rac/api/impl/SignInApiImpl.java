package rebue.scx.rac.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.SignInApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.ex.SignInSvc;
import rebue.scx.rac.to.ex.SignInByUserNameTo;

/**
 * 用户登录API的实现类
 */
@DubboService
public class SignInApiImpl implements SignInApi {

    @Resource
    private SignInSvc svc;

    /**
     * 通过用户名称注册
     */
    @Override
    public Ro<SignUpOrInRa> signInByUserName(final SignInByUserNameTo to) {
        return svc.signInByUserName(to);
    }

}
