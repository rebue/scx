package rebue.wxx.api.ex;

import rebue.wxx.to.ex.WxxRespAuthorizeTo;

/**
 * 响应微信发过来请求的API
 */
public interface WxxRespApi {

    /**
     * 提供给微信验证本服务器身份的接口
     * 微信公众号绑定网站的域名时，会向此url发送GET请求进行校验，
     * 而要注意，微信通过同样的url，发出POST请求时却是推送消息过来，
     * 所以GET和POST要区分对待，本方法是处理GET的，下一个方法是处理POST的
     */
    String authorize(String appId, WxxRespAuthorizeTo to);
}
