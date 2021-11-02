package rebue.scx.rac.ctrl.ex;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.api.ex.RacForgetPasswordApi;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.to.ex.CheckAccountNumberTo;
import rebue.scx.rac.to.ex.ForgetSignInPswdToSetTo;
import rebue.wheel.api.exception.RuntimeExceptionX;

/**
 * 忘记密码相关接口控制器
 * 请求路径rac-svr/forget/**
 */
@RestController
@RequestMapping("/forget")
public class RacForgetPasswordCtrl {

    @Resource
    private RacForgetPasswordApi api;
    @Resource
    private RacAccountApi        accountApi;

    /**
     * 验证账户是否存在
     * 
     * @ignoreParams request
     * 
     * @param to
     * @param request
     * 
     * @return 账户信息
     */
    @PostMapping("/check-account-number")
    @SneakyThrows
    public Mono<Ro<?>> checkAccountNumber(@RequestBody final CheckAccountNumberTo to, final ServerHttpRequest request) {
        // 从Headers中获取应用ID
        final List<String> list  = request.getHeaders().get(RacCookieCo.HEADERS_APP_ID_KEY);
        final String       appId = list != null && list.size() > 0 ? list.get(0) : null;
        if (StringUtils.isBlank(appId)) {
            throw new RuntimeExceptionX("在Headers中找不到应用ID");
        }
        return Mono.create(callback -> callback.success(api.checkAccountNumber(to, appId)));
    }

    /**
     * 忘记密码通过手机号修改密码
     */
    @PostMapping("/sign-in-pswd-to-set")
    public Mono<Ro<?>> forgetSignInPswdToSetTo(@RequestBody final ForgetSignInPswdToSetTo to) {
        return Mono.create(callback -> callback.success(api.forgetSignInPswdToSetTo(to)));
    }

}
