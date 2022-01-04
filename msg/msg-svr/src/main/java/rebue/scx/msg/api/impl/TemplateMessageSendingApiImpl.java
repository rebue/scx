package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.TemplateMessageSendingApi;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;

@DubboService
public class TemplateMessageSendingApiImpl implements TemplateMessageSendingApi {
    @Resource
    protected TemplateMessageSendingSvc svc;

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber, String code) {
        return svc.sendTemplateSMS(phoneNumber, code);
    }

}
