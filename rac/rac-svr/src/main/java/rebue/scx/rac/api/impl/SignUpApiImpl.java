package rebue.scx.rac.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.SignUpApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.ex.SignUpSvc;
import rebue.scx.rac.to.ex.SignUpByUserNameTo;

/**
 * 用户注册API的实现类
 */
@DubboService
public class SignUpApiImpl implements SignUpApi {

    @Resource
    private SignUpSvc svc;

    /**
     * 通过用户名称注册
     */
    @Override
    public Ro<SignUpOrInRa> signUpByUserName(final SignUpByUserNameTo to) {
        return svc.signUpByUserName(to);
    }

}
