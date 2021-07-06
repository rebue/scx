package rebue.wxx.ctrl.ex;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.wxx.api.ex.WxxReqApi;
import rebue.wxx.fto.WxxMessageTemplateSendFto;

/**
 * 向微信发送请求的服务的控制器
 */
@RestController
public class WxxReqCtrl {
	@Resource
	private WxxReqApi api;

	/**
	 * 刷新Access token
	 *
	 * @param appId
	 */
	@PostMapping(value = "/wxx/req/refresh-access-token/{appId}")
	Mono<Ro<?>> refreshAccessToken(@PathVariable(name = "appId") final String appId) {
		return Mono.create(callback -> callback.success(api.refreshAccessToken(appId)));
	}

	/**
	 * 发送模板类的消息
	 * 
	 * @param appId 微信的AppId
	 */
	@PostMapping(value = "/wxx/req/message/send-template-message/{appId}")
	Mono<Ro<?>> sendTemplateMessage(@PathVariable(name = "appId") final String appId, @RequestBody final WxxMessageTemplateSendFto to) {
		return Mono.create(callback -> callback.success(api.sendTemplateMessage(appId, to)));
	}

}
