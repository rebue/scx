package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;

/**
 * 账户登录API
 */
public interface RacSignInApi {

    /**
     * 通过账户名称登录
     */
    Ro<SignUpOrInRa> signInByAccountName(SignInByAccountNameTo to);

}
