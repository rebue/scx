package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignUpByAccountNameTo;

/**
 * 账户注册API
 */
public interface RacSignUpApi {

    /**
     * 通过账户名称注册
     */
    Ro<SignUpOrInRa> signUpByAccountName(SignUpByAccountNameTo to);

}
