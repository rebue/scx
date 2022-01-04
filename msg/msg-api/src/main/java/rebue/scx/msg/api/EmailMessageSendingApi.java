package rebue.scx.msg.api;

import rebue.robotech.ro.Ro;
import rebue.scx.msg.to.EmailOrdinary;

public interface EmailMessageSendingApi {

    public String sendEmailOrdinary(EmailOrdinary emailOrdinary);

    public Ro<?> sendEmailTemple(String[] datas, String code);

}
