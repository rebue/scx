package rebue.scx.cap.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapSMSSendingApi;
import rebue.scx.cap.svc.CapSMSSendingSvc;
import rebue.scx.cap.to.CapEmailTo;
import rebue.scx.cap.to.CapEmailVerificationTo;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.cap.to.CapSMSVerificationTo;

@DubboService
public class CapSMSSendingApiImpl implements CapSMSSendingApi {
    @Resource
    protected CapSMSSendingSvc svc;

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public Ro<?> sendTemplateSMS(CapSMSTo to) {
        return svc.sendTemplateSMS(to);
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(CapSMSVerificationTo to) {
        return svc.msgSMSVerification(to);
    }

    /**
     * 校验成功后删除手机验证码缓存
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public void deleteVerifiyCode(final CapSMSVerificationTo to) {
        svc.deleteVerifiyMobileCode(to);
    }

    @Override
    public Ro<?> sendTemplateEmail(CapEmailTo to) {
        return svc.sendTemplateEmail(to);
    }

    @Override
    public Ro<?> msgEmailVerification(CapEmailVerificationTo to) {
        return svc.msgEmailVerification(to);
    }

}
