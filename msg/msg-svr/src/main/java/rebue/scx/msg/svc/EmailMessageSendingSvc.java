package rebue.scx.msg.svc;

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
    public String sendEmailOrdinary(String title, String text, String[] datas);

    /**
     * 模板邮箱发送
     * 
     * @param datas
     * @param var
     * 
     * @return
     */
    public String sendEmailTemple(String[] datas, String var);

}
