package rebue.scx.rac.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacSignUpApi;
import rebue.scx.rac.ra.SignUpRa;
import rebue.scx.rac.svc.RacSignUpSvc;
import rebue.scx.rac.to.SignUpByUserNameTo;

/**
 * API用户注册服务的实现类
 */
@DubboService
public class RacSignUpApiImpl implements RacSignUpApi {

    @Resource
    private RacSignUpSvc svc;

    /**
     * 通过用户名称注册
     */
    @Override
    public Ro<SignUpRa> signUpByUserName(final SignUpByUserNameTo to) {
        return svc.signUpByUserName(to);
    }

}
