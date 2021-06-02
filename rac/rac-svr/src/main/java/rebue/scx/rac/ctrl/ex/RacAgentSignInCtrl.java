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
import rebue.scx.rac.api.ex.RacAgentSignInApi;
import rebue.scx.rac.ra.AgentSignInRa;
import rebue.scx.rac.to.ex.AgentSignInTo;

/**
 * 代理登录的控制器
 */
@RestController
public class RacAgentSignInCtrl {

    @Resource
    private RacAgentSignInApi api;

    /**
     * 代理登录
     */
    @PostMapping("/rac/agent-sign-in/sign-in")
    public Mono<Ro<AgentSignInRa>> signIn(@RequestBody final AgentSignInTo to, final ServerHttpResponse resp) {
        return Mono.create(callback -> {
            final Ro<AgentSignInRa> ro = api.signIn(to);
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                jwtSignWithCookie(ro.getExtra(), to.getSysId(), resp);
            }
            callback.success(ro);
        });
    }

}
