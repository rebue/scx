package rebue.scx.msg.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.to.MsgSMSVerificationTo;

@Validated
public interface TemplateMessageSendingSvc {
    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    // Ro<?> sendTemplateSMS(String phoneNumber);

    /**
     * 短信验证码校验
     * 
     */
    Ro<?> msgSMSVerification(MsgSMSVerificationTo to);

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    Ro<?> sendTemplateSMS(String phoneNumber, String code);

}
