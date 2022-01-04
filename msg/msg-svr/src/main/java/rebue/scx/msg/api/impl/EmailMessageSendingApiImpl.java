package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.api.EmailMessageSendingApi;
import rebue.scx.msg.svc.EmailMessageSendingSvc;
import rebue.scx.msg.to.EmailOrdinary;

@DubboService
public class EmailMessageSendingApiImpl implements EmailMessageSendingApi {

    @Resource
    protected EmailMessageSendingSvc svc;

    @Override
    public String sendEmailOrdinary(EmailOrdinary emailOrdinary) {
        return svc.sendEmailOrdinary(emailOrdinary);
    }

    @Override
    public Ro<?> sendEmailTemple(String[] datas, String code) {
        return svc.sendEmailTemple(datas, code);
    }

}
