package rebue.scx.msg.api;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.to.EmailOrdinary;

public interface EmailMessageSendingApi {

    public String sendEmailOrdinary(EmailOrdinary emailOrdinary);

    public Ro<?> sendEmailTemple(String[] datas, String code);

    /**
     * 简易邮箱发送
     * 
     * @param mailAddrs 收件人,收件人2...
     * @param title     邮件标题
     * @param text      邮件正文
     * 
     */
    Ro<?> sendSimpleMail(String mailAddrs, String title, String text);

}
