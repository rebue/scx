package rebue.scx.rac.ctrl.ex;

import static rebue.scx.rac.ctrl.ex.SignUpOrInCtrlCommon.jwtSignWithCookie;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.ex.RacAgentSignInApi;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.ex.AgentSignInTo;
import rebue.wheel.turing.JwtUtils;

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
    @RacOpLog(opType = "登录", opTitle = "代理登录: #{#p0.accountId}")
    public Mono<Ro<SignUpOrInRa>> signIn(@RequestBody final AgentSignInTo to,
            @CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken,
            @CookieValue(RacCookieCo.APP_ID_KEY) final String appId,
            final ServerHttpResponse resp) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        // 从JWT签名中获取当前账户ID作为代理ID
        final Long agentAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (agentAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到当前账户ID");
        }
        // 从JWT签名中获取应用ID
        if (StringUtils.isBlank(appId)) {
            throw new IllegalArgumentException("在Cookie中找不到应用ID");
        }

        return Mono.create(callback -> {
            final Ro<SignUpOrInRa> ro = api.signIn(to.getAccountId(), agentAccountId, to.getAppId(), appId, to.getCurUrl());
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                final ResponseCookie responseCookie = ResponseCookie.from(RacCookieCo.APP_ID_KEY, to.getAppId()).path("/").build();
                resp.addCookie(responseCookie);
                jwtSignWithCookie(ro.getExtra(), resp);
            }
            callback.success(ro);
        });
    }

}
