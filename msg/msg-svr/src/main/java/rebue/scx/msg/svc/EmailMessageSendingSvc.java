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

}
