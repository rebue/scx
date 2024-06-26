package rebue.scx.orp.ctrl;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.orp.api.OrpApi;
import rebue.scx.orp.to.ForgetSignInPswdTo;
import rebue.scx.orp.to.OrpCodeTo;

/**
 * 忘记密码相关接口控制器
 * 请求路径orp-svr/forget
 *
 * @author yuanman
 *
 */
@RestController
@RequestMapping("/forget")
public class OrpForgetSignInPswdCtrl {

    @Autowired
    private OrpApi orpApi;

    /**
     * 根据账户ID校验微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @GetMapping("/verifiy-account/{orpType}/{clientId}/{accountId}")
    public Mono<Void> verifiyAccount(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @PathVariable("accountId") final Long accountId, final OrpCodeTo to, ServerHttpResponse response) {
        Ro<?>   ro   = orpApi.verifiyAccount(orpType, clientId, accountId, to);
        boolean flag = ro.getResult().getCode() == 1;
        return getResponse(response, orpType + "-verifiy", to.getCallbackUrl(), ro.getMsg(), flag);
    }

    /**
     * 忘记密码通过微信钉钉校验修改密码
     * 
     * @param orpType
     * @param clientId
     * @param to
     * 
     */
    @PostMapping("/sign-in-pswd/{orpType}/{clientId}")
    public Mono<Ro<?>> forgetSignInPswdTo(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @RequestBody final ForgetSignInPswdTo to) {
        return Mono.create(callback -> callback.success(orpApi.forgetSignInPswdTo(orpType, clientId, to)));
    }

    /**
     * @param response
     * @param orpType     （钉钉：ding-talk，微信：wechat-open）
     * @param callbackUrl 重定向的地址
     * @param flag        操作是否成功
     */
    private static Mono<Void> getResponse(ServerHttpResponse response, String orpType, String callbackUrl, String msg, boolean flag) {
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().setLocation(URI.create(getRedirectUrl(callbackUrl, orpType, msg, flag)));
        return response.setComplete();
    }

    private static String getRedirectUrl(String callbackUrl, String orpType, String msg, boolean flag) {
        if (flag) {
            return callbackUrl + "?u=1&event=" + orpType + "&result=success&msg=" + msg;
        }
        else {
            return callbackUrl + "?u=1&event=" + orpType + "&result=error&msg=" + msg;
        }

    }

    /**
     * URL 转码
     *
     * @return String
     */
    private static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 解码
     *
     * @return String
     */
    private static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
