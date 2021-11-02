package rebue.scx.cap.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.cap.to.CapSMSVerificationTo;

@Validated
public interface CapSMSSendingSvc {
    /**
     * 模板短信
     * 
     * @param to
     * @param code
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

}
