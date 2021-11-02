package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacForgetPasswordApi;
import rebue.scx.rac.svc.ex.RacForgetPasswordSvc;
import rebue.scx.rac.to.ex.CheckAccountNumberTo;
import rebue.scx.rac.to.ex.ForgetSignInPswdToSetTo;

/**
 * 账户忘记密码API的实现类
 */
@DubboService
public class RacForgetPasswordApiImpl implements RacForgetPasswordApi {

    @Resource
    private RacForgetPasswordSvc svc;

    /**
     * 校验帐号存在
     */
    @Override
    public Ro<?> checkAccountNumber(CheckAccountNumberTo to, String appId) {
        return svc.checkAccountNumber(to, appId);
    }

    /**
     * 忘记密码修改
     */
    @Override
    public Ro<?> forgetSignInPswdToSetTo(ForgetSignInPswdToSetTo to) {
        return svc.forgetSignInPswdToSetTo(to);
    }

}
