package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSignUpApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.ex.RacSignUpSvc;
import rebue.scx.rac.to.ex.SignUpByAccountNameTo;

/**
 * 账户注册API的实现类
 */
@DubboService
public class RacSignUpApiImpl implements RacSignUpApi {

    @Resource
    private RacSignUpSvc svc;

    /**
     * 通过账户名称注册
     */
    @Override
    public Ro<SignUpOrInRa> signUpByAccountName(final SignUpByAccountNameTo to) {
        return svc.signUpByAccountName(to);
    }

}
