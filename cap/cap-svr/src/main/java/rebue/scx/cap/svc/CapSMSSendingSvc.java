package rebue.scx.cap.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.to.CapSMSVerificationTo;

@Validated
public interface CapSMSSendingSvc {
    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    Ro<?> sendTemplateSMS(String phoneNumber);

    /**
     * 短信验证码校验
     * 
     */
    Ro<?> msgSMSVerification(CapSMSVerificationTo to);

}
