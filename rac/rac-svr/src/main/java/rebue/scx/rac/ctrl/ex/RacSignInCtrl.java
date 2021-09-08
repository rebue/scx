package rebue.scx.rac.ctrl.ex;

import static rebue.scx.rac.ctrl.ex.SignUpOrInCtrlCommon.jwtSignWithCookie;

import javax.annotation.Resource;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;

/**
 * 账户登录的控制器
 */
@RestController
public class RacSignInCtrl {

    @Resource
    private RacSignInApi api;

    /**
     * 通过账户名称登录
     */
    @PostMapping("/rac/sign-in/sign-in-by-account-name")
    @RacOpLog(opType = "登录", opTitle = "通过账户名称登录: #{#p0.accountName}")
    public Mono<Ro<SignUpOrInRa>> signInByAccountName(@RequestBody final SignInByAccountNameTo to, final ServerHttpResponse resp) {
        return Mono.create(callback -> {
            final Ro<SignUpOrInRa> ro = api.signInByAccountName(to);
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                jwtSignWithCookie(ro.getExtra(), resp);
            }
            callback.success(ro);
        });
    }

    /**
     * 通过关键字获取输入密码错误而被锁定的账户记录
     * 
     * @param keywords
     * 
     * @return
     */
    @GetMapping("/rac/sign-in/sign-in-lock-record")
    public Mono<Ro<ListRa<RacAccountMo>>> getSignInLockRecord(@RequestParam("keywords") final String keywords) {
        return Mono.create(callback -> callback.success(api.getSignInLockRecord(keywords)));
    }

    /**
     * 手动删除输入登录密码错误次数账户解锁
     * 
     * @param id(账户ID)
     * 
     * @return
     */
    @PostMapping("/rac/sign-in/sign-in-lock-record")
    @RacOpLog(opType = "登录密码解锁", opTitle = "登录密码解锁: #{#p0}")
    public Mono<Ro<?>> handDelWrongPswdTimesOfSignIn(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.handDelWrongPswdTimesOfSignIn(id)));
    }

}
