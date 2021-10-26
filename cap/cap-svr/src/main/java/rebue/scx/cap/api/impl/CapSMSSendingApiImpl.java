package rebue.scx.cap.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapSMSSendingApi;
import rebue.scx.cap.svc.CapSMSSendingSvc;
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
    public Ro<?> sendTemplateSMS(String phoneNumber) {
        return svc.sendTemplateSMS(phoneNumber);
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(CapSMSVerificationTo to) {
        return svc.msgSMSVerification(to);
    }

}
