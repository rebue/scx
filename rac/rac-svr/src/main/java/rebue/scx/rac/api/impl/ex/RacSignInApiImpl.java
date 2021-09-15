package rebue.scx.rac.api.impl.ex;

import java.util.Optional;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.dic.SignUpOrInWayDic;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.ex.RacSignInSvc;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;
import rebue.scx.rac.to.ex.SignInByOidcTo;
import rebue.scx.rac.to.ex.UnlockSignInTo;

/**
 * 账户登录API的实现类
 */
@DubboService
public class RacSignInApiImpl implements RacSignInApi {

    @Resource
    private RacSignInSvc svc;

    @Override
    public Optional<RacAccountMo> unifiedLogin(final UnifiedLoginTo to) {
        return svc.unifiedLogin(to);
    }

    /**
     * 通过账户名称注册
     */
    @Override
    public Ro<SignUpOrInRa> signInByAccountName(final SignInByAccountNameTo to) {
        return svc.signInByAccountName(to);
    }

    /**
     * 通过OIDC登录
     */
    @Override
    public Ro<SignUpOrInRa> signInByOidc(final SignUpOrInWayDic signUpOrInWay, final SignInByOidcTo to) {
        return svc.signInByOidc(signUpOrInWay, to);
    }

    /**
     * 通过关键字获取输入密码错误而被锁定的账户记录
     */
    @Override
    public Ro<PageRa<RacAccountMo>> getSignInLockRecord(final RacAccountPageTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new PageRa<>(svc.getSignInLockRecord(qo)));
    }

    /**
     * 手动删除输入登录密码错误次数
     */
    @Override
    public Ro<?> handDelWrongPswdTimesOfSignIn(final UnlockSignInTo to) {
        final Boolean boo = svc.handDelWrongPswdTimesOfSignIn(to);
        if (boo) {
            return new Ro<>(ResultDic.SUCCESS, "解锁密码锁定成功");
        }
        else {
            return new Ro<>(ResultDic.FAIL, "解锁密码锁定失败，可能已经解锁或者已经被改变");
        }
    }

}
