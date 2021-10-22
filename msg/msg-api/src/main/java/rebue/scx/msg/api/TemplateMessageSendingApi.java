package rebue.scx.msg.api;

import rebue.robotech.ro.Ro;

public interface TemplateMessageSendingApi {
    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    Ro<?> sendTemplateSMS(String phoneNumber, String code);

}
