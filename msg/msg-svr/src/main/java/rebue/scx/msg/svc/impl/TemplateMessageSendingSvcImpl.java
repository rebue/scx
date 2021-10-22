package rebue.scx.msg.svc.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
import rebue.scx.msg.util.SmsUtil;

@Service
public class TemplateMessageSendingSvcImpl implements TemplateMessageSendingSvc {
    @Resource
    private SmsUtil sms;

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber, String code) {
        // sms.sendSMSCode(phoneNumber, code);
        return new Ro<>(ResultDic.SUCCESS, "发送成功");
    }

}
