package rebue.scx.rac.api.ex.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.ex.RacSignInSvc;
import rebue.scx.rac.to.ex.SignInByUserNameTo;

/**
 * 用户登录API的实现类
 */
@DubboService
public class RacSignInApiImpl implements RacSignInApi {

    @Resource
    private RacSignInSvc svc;

    /**
     * 通过用户名称注册
     */
    @Override
    public Ro<SignUpOrInRa> signInByUserName(final SignInByUserNameTo to) {
        return svc.signInByUserName(to);
    }

}
