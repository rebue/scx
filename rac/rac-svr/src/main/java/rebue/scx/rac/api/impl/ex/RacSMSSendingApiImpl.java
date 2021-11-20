package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSMSSendingApi;
import rebue.scx.rac.svc.ex.RacSMSSendingSvc;
import rebue.scx.rac.to.ex.RacSMSTo;
import rebue.scx.rac.to.ex.RacSMSVerificationTo;

/**
 * 发短信API的实现类
 * 
 * @author yuanman
 *
 */
@DubboService
public class RacSMSSendingApiImpl implements RacSMSSendingApi {

    @Resource
    private RacSMSSendingSvc svc;

    @Override
    public Ro<?> sendTemplateSMS(RacSMSTo to) {
        return svc.sendTemplateSMS(to);
    }

    @Override
    public Ro<?> msgSMSVerification(RacSMSVerificationTo to) {
        return svc.msgSMSVerification(to);
    }

}
