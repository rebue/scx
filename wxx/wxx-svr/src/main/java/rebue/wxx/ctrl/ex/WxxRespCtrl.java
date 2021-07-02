package rebue.wxx.ctrl.ex;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.wxx.api.ex.WxxRespApi;
import rebue.wxx.to.ex.WxxRespAuthorizeTo;

/**
 * 响应微信发过来请求的控制器
 */
@RestController
public class WxxRespCtrl {
    @Resource
    private WxxRespApi api;

    /**
     * 提供给微信验证本服务器身份的接口
     * 微信公众号绑定网站的域名时，会向此url发送GET请求进行校验，
     * 而要注意，微信通过同样的url，发出POST请求时却是推送消息过来，
     * 所以GET和POST要区分对待，本方法是处理GET的，下一个方法是处理POST的
     */
    @GetMapping(value = "/wxx/resp/{appId}", produces = MediaType.TEXT_PLAIN_VALUE)
    Mono<String> authorize(@PathVariable(name = "appId") final String appId, final WxxRespAuthorizeTo to) {
        return Mono.create(callback -> callback.success(api.authorize(appId, to)));
    }

}
