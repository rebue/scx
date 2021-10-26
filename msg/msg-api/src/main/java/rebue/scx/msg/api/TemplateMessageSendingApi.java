package rebue.scx.msg.api;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.to.MsgSMSVerificationTo;

public interface TemplateMessageSendingApi {
    /**
     * 模板短信
     * 
     * @param phoneNumber
     */
    Ro<?> sendTemplateSMS(String phoneNumber);

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    Ro<?> sendTemplateSMS(String phoneNumber, String code);

    /**
     * 短信验证码校验
     * 
     */
    Ro<?> msgSMSVerification(MsgSMSVerificationTo to);

}
