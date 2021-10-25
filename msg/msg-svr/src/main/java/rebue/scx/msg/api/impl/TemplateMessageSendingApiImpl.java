package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.TemplateMessageSendingApi;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
import rebue.scx.msg.to.MsgSMSTo;

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
    public Ro<?> sendTemplateSMS(String phoneNumber) {
        return svc.sendTemplateSMS(phoneNumber);
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(MsgSMSTo to) {
        return svc.msgSMSVerification(to);
    }

}
