package rebue.scx.cap.api;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.to.CapSMSVerificationTo;

public interface CapSMSSendingApi {
    /**
     * 模板短信
     * 
     * @param phoneNumber
     */
    Ro<?> sendTemplateSMS(String phoneNumber);

    /**
     * 短信验证码校验
     * 
     */
    Ro<?> msgSMSVerification(CapSMSVerificationTo to);
}
