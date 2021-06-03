package rebue.scx.rac.ctrl.ex;

import static rebue.scx.rac.ctrl.ex.SignUpOrInCtrlCommon.jwtSignWithCookie;

import javax.annotation.Resource;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.ex.RacSignUpApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignUpByAccountNameTo;

/**
 * API账户注册的控制器
 */
@RestController
public class RacSignUpCtrl {

    @Resource
    private RacSignUpApi api;

    /**
     * 通过账户名称注册
     */
    @PostMapping("/rac/sign-up/sign-up-by-account-name")
    @RacOpLog(opType = "登录", opTitle = "通过账户名称注册: #{#p0.accountName}")
    public Mono<Ro<SignUpOrInRa>> signUpByAccountName(@RequestBody final SignUpByAccountNameTo to, final ServerHttpResponse resp) {
        return Mono.create(callback -> {
            final Ro<SignUpOrInRa> ro = api.signUpByAccountName(to);
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                jwtSignWithCookie(ro.getExtra(), resp);
            }
            callback.success(ro);
        });
    }

}
