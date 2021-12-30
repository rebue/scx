package rebue.scx.msg.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.msg.api.EmailMessageSendingApi;
import rebue.scx.msg.svc.EmailMessageSendingSvc;
import rebue.scx.msg.to.EmailOrdinary;
import rebue.scx.msg.to.EmailTemplate;

@DubboService
public class EmailMessageSendingApiImpl implements EmailMessageSendingApi {

    @Resource
    protected EmailMessageSendingSvc svc;


	@Override
	public String SendEmailOrdinary(EmailOrdinary emailOrdinary) {
		 return svc.sendEmailOrdinary(emailOrdinary);
	}

	@Override
	public String SendEmailTemple(EmailTemplate emailTemplate) {
		// TODO Auto-generated method stub
		 return svc.sendEmailTemple(emailTemplate);
	}

}
