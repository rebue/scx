package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.TemplateMessageSendingApi;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
import rebue.scx.msg.to.MsgSMSVerificationTo;

@DubboService
public class TemplateMessageSendingApiImpl implements TemplateMessageSendingApi {
    @Resource
    protected TemplateMessageSendingSvc svc;

    /**
     * 模板短信已弃用
     * 
     * @param phoneNumber
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber) {
        return null;
        // return svc.sendTemplateSMS(phoneNumber);
    }

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

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(MsgSMSVerificationTo to) {
        return svc.msgSMSVerification(to);
    }

}
