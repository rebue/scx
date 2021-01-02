package rebue.scx.rac.ctrl.ex;

import static rebue.scx.rac.ctrl.ex.SignUpOrInCommon.jwtSignWithCookie;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacSignUpApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignUpByUserNameTo;

/**
 * API用户注册的控制器
 */
@RestController
public class RacSignUpCtrl {

    @Resource
    private RacSignUpApi api;

    /**
     * 通过用户名称注册
     */
    @PostMapping("/rac/sign-up/sign-up-by-user-name")
    public Mono<Ro<SignUpOrInRa>> signUpByUserName(@RequestBody final SignUpByUserNameTo to, HttpServletResponse resp) {
        return Mono.create(callback -> {
            Ro<SignUpOrInRa> ro = api.signUpByUserName(to);
            if (ResultDic.SUCCESS.equals(ro.getResult()))
                jwtSignWithCookie(ro.getExtra(), to.getSysId(), resp);
            callback.success(ro);
        });
    }

}
