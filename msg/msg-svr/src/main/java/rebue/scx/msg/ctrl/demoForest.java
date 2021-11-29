package rebue.scx.msg.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rebue.scx.msg.svc.MyService;
import rebue.scx.msg.util.EmailUtil;

@RestController
public class demoForest {

	@Resource
	private EmailUtil emailUtil;
	@Resource
	private MyService myService;

	@GetMapping("/hello")
	public void testFo() {
		final String urlParam = "https://api.ums.jiguang.cn/v1/sent";
		final String templateUrl = "https://api.ums.jiguang.cn/v1/template/sent";
		final String datas[] = { "2530364891@qq.com" };
		emailUtil.sendTemplet(templateUrl, datas, "55555");
		System.out.println(myService.SendEmail());
	}

	@GetMapping("/hello1")
	public String testFo1() {
		return "测试";
	}

}
