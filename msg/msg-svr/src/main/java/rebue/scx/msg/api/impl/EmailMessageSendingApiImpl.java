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

    /**
     * 简易邮箱发送
     * 
     * @param mailAddrs 收件人,收件人2...
     * @param title     邮件标题
     * @param text      邮件正文
     * 
     */
    @Override
    public Ro<?> sendSimpleMail(String mailAddrs, String title, String text) {
        return svc.sendSimpleMail(mailAddrs, title, text);
    }

}
