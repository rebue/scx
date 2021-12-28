package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.msg.api.EmailMessageSendingApi;
import rebue.scx.msg.svc.EmailMessageSendingSvc;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
@DubboService
public class EmailMessageSendingApiImpl implements EmailMessageSendingApi {
   
	@Resource
    protected EmailMessageSendingSvc svc;
    
	@Override
	public String SendEmailCustom(String title, String text, String[] datas) {
		// TODO Auto-generated method stub
		return svc.SendEmailCustom(title, text, datas);
	}

	@Override
	public String SendEmailTemple(String[] datas, String var) {
		// TODO Auto-generated method stub
		return svc.SendEmailTemple(datas, var);
	}

}
