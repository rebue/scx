package rebue.scx.msg.api;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.to.MsgSMSTo;

public interface TemplateMessageSendingApi {
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
    Ro<?> msgSMSVerification(MsgSMSTo to);

}
