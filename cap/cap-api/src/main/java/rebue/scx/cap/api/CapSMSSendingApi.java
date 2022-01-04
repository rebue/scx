package rebue.scx.cap.api;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.to.CapEmailTo;
import rebue.scx.cap.to.CapEmailVerificationTo;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.cap.to.CapSMSVerificationTo;

public interface CapSMSSendingApi {
    /**
     * 模板短信
     * 
     * @param to
     */
    Ro<?> sendTemplateSMS(CapSMSTo to);

    /**
     * 短信验证码校验
     * 
     */
    Ro<?> msgSMSVerification(CapSMSVerificationTo to);

    /**
     * 校验成功后删除手机验证码缓存
     * 
     * @param phoneNumber
     * @param code
     */
    void deleteVerifiyCode(CapSMSVerificationTo to);

    /**
     * 发送模板邮箱
     * 
     * @param to
     * 
     * @return
     */
    Ro<?> sendTemplateEmail(CapEmailTo to);

    /**
     * 邮箱验证码校验
     * 
     * @param to
     * 
     * @return
     */
    Ro<?> msgEmailVerification(CapEmailVerificationTo to);
}
