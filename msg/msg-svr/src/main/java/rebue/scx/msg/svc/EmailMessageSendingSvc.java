package rebue.scx.msg.svc;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.to.EmailOrdinary;

public interface EmailMessageSendingSvc {
    /**
     * 普通邮箱发送
     * 
     * @param title
     * @param text
     * @param datas
     * 
     * @return
     */
    public String sendEmailOrdinary(EmailOrdinary emailOrdinary);

    /**
     * 模板邮箱发送
     * 
     * @param datas
     * @param var
     * 
     * @return
     */
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
