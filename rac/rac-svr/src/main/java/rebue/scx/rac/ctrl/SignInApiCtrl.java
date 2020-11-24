package rebue.scx.rac.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacSignInApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByUserNameTo;

/**
 * 用户登录的控制器
 */
@RestController
public class SignInApiCtrl {

    @Resource
    private RacSignInApi api;

    /**
     * 通过用户名称登录
     */
    @PostMapping("/sign-in/sign-in-by-user-name")
    public Mono<Ro<SignUpOrInRa>> signUpByUserName(@RequestBody final SignInByUserNameTo to) {
        return Mono.create(callback -> callback.success(api.signInByUserName(to)));
    }

}
