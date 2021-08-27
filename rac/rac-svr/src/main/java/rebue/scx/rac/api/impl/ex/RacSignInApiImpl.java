package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.ex.RacSignInSvc;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;

import java.util.Optional;

/**
 * 账户登录API的实现类
 */
@DubboService
public class RacSignInApiImpl implements RacSignInApi {

    @Resource
    private RacSignInSvc svc;
    @DubboReference
    private CapApi   capApi;

    @Override
    public Optional<RacAccountMo> unifiedLogin(UnifiedLoginTo to)
    {
        return svc.unifiedLogin(to);
    }

    /**
     * 通过账户名称注册
     */
    @Override
    public Ro<SignUpOrInRa> signInByAccountName(final SignInByAccountNameTo to) {
        final Ro<?> verifyVo = capApi.verifyVo(to.getVerification());
        if (verifyVo.getResult().getCode() == 1) {
           return svc.signInByAccountName(to);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "验证码二次校验失败！");
        }
    }

}
