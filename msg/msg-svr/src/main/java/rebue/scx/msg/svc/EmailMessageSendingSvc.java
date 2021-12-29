package rebue.scx.msg.svc;

public interface EmailMessageSendingSvc {
	  //普通发送
	  public String SendEmailOrdinary(String title,String text,String[] datas);
	  
	  //模板发送
	  public String SendEmailTemple(String[] datas,String var);

}
