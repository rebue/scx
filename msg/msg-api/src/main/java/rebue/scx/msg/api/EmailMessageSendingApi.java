package rebue.scx.msg.api;

public interface EmailMessageSendingApi {
	
	 public String SendEmailCustom(String title,String text,String[] datas);
	 
	  public String SendEmailTemple(String[] datas,String var);

}
