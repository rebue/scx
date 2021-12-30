package rebue.scx.msg.svc;

import rebue.scx.msg.to.EmailOrdinary;
import rebue.scx.msg.to.EmailTemplate;

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
    public String sendEmailTemple(EmailTemplate emailTemplate);

}
