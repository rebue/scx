package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.msg.api.EmailMessageSendingApi;
import rebue.scx.msg.svc.EmailMessageSendingSvc;

@DubboService
public class EmailMessageSendingApiImpl implements EmailMessageSendingApi {

    @Resource
    protected EmailMessageSendingSvc svc;

    @Override
    public String SendEmailOrdinary(String title, String text, String[] datas) {
        return svc.sendEmailOrdinary(title, text, datas);
    }

    @Override
    public String SendEmailTemple(String[] datas, String var) {
        return svc.sendEmailTemple(datas, var);
    }

}
