package rebue.scx.msg.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;

@Validated
public interface TemplateMessageSendingSvc {

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    Ro<?> sendTemplateSMS(String phoneNumber, String code);

}
