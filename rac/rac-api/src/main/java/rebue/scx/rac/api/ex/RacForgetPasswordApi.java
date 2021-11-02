package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.to.ex.CheckAccountNumberTo;
import rebue.scx.rac.to.ex.ForgetSignInPswdToSetTo;

/**
 * 账户忘记密码API
 */
public interface RacForgetPasswordApi {
    /**
     * 校验帐号存在
     */
    Ro<?> checkAccountNumber(CheckAccountNumberTo to, String appId);

    /**
     * 忘记密码修改
     */
    Ro<?> forgetSignInPswdToSetTo(ForgetSignInPswdToSetTo to);

}
