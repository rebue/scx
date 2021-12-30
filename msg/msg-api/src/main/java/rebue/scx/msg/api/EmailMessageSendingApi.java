package rebue.scx.msg.api;

import rebue.scx.msg.to.EmailOrdinary;
import rebue.scx.msg.to.EmailTemplate;

public interface EmailMessageSendingApi {
	
	 public String SendEmailOrdinary(EmailOrdinary emailOrdinary);
	 
	  public String SendEmailTemple(EmailTemplate emailTemplate);

}
