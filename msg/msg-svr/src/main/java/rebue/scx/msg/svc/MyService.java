package rebue.scx.msg.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rebue.msg.fapi.msgFapi;

@Component
public class MyService {
	@Autowired
	private msgFapi msgFapi;

	public void testClient() {
		final String result = msgFapi.helloForest();
		System.out.println(result);
	}

	public String SendEmail() {
		final String result = msgFapi.helloForest();
		System.out.println(result);
		return result;
	}

}