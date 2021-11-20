package rebue.scx.rac.api.ex;

import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.dic.SignUpOrInWayDic;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;
import rebue.scx.rac.to.ex.SignInByOidcTo;
import rebue.scx.rac.to.ex.UnlockSignInTo;

/**
 * 账户登录API
 */
public interface RacSignInApi {

    Ro<SignUpOrInRa> unifiedLogin(UnifiedLoginTo to);

    /**
     * 通过账户名称登录
     */
    Ro<SignUpOrInRa> signInByAccountName(SignInByAccountNameTo to);

    /**
     * 通过关键字获取输入密码错误而被锁定的账户记录
     */
    Ro<PageRa<RacAccountMo>> getSignInLockRecord(RacAccountPageTo qo);

    /**
     * 手动删除输入登录密码错误次数
     * 
     * @param to
     */
    Ro<?> handDelWrongPswdTimesOfSignIn(UnlockSignInTo to);

    /**
     * 通过OIDC登录
     */
    Ro<SignUpOrInRa> signInByOidc(SignUpOrInWayDic signUpOrInWay, SignInByOidcTo to);

}
