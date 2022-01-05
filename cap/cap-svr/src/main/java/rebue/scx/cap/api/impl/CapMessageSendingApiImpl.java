package rebue.scx.cap.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapMessageSendingApi;
import rebue.scx.cap.svc.CapMessageSendingSvc;
import rebue.scx.cap.to.CapEmailTo;
import rebue.scx.cap.to.CapEmailVerificationTo;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.cap.to.CapSMSVerificationTo;

@DubboService
public class CapMessageSendingApiImpl implements CapMessageSendingApi {
    @Resource
    protected CapMessageSendingSvc svc;

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
    public void deleteVerifiyMobilCode(final CapSMSVerificationTo to) {
        svc.deleteVerifiyMobileCode(to);
    }

    /**
     * 发送模板邮箱
     */
    @Override
    public Ro<?> sendTemplateEmail(CapEmailTo to) {
        return svc.sendTemplateEmail(to);
    }

    /**
     * 校验邮箱
     */
    @Override
    public Ro<?> msgEmailVerification(CapEmailVerificationTo to) {
        return svc.msgEmailVerification(to);
    }

    /**
     * 校验成功后删除邮箱验证码缓存
     * 
     * @param email
     * @param code
     */
    @Override
    public void deleteVerifiyEmailCode(CapEmailVerificationTo to) {
        svc.deleteVerifiyEmailCode(to);
    }

}
