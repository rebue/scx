package rebue.scx.rac.ctrl.ex;

import static rebue.scx.rac.ctrl.ex.SignUpOrInCtrlCommon.jwtSignWithCookie;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.ex.RacAgentSignOutApi;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.co.RacJwtSignCo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.wheel.turing.JwtUtils;

/**
 * 退出代理登录的控制器
 */
@RestController
public class RacAgentSignOutCtrl {

    @Resource
    private RacAgentSignOutApi api;

    /**
     * 退出代理登录
     */
    @PostMapping("/rac/agent-sign-out/sign-out")
    @RacOpLog(opType = "登录", opTitle = "登录: 从代理登录退出", opDetail = "")
    @SneakyThrows
    public Mono<Ro<SignUpOrInRa>> signOut(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken,
            final ServerHttpResponse resp) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }

        // 从JWT签名中获取代理账户ID作为代理ID
        final Object agentAccountIdObj = JwtUtils.getJwtAdditionItemFromSign(jwtToken, RacJwtSignCo.AGENT_ACCOUNT_ID);
        if (agentAccountIdObj == null) {
            throw new IllegalArgumentException("在JWT签名中找不到代理账户ID");
        }
        final Long agentAccountId = Long.valueOf(agentAccountIdObj.toString());

        // 从JWT签名中获取代理应用ID
        final Object agentAppIdObj = JwtUtils.getJwtAdditionItemFromSign(jwtToken, RacJwtSignCo.AGENT_APP_ID);
        if (agentAppIdObj == null) {
            throw new IllegalArgumentException("在JWT签名中找不到代理应用ID");
        }
        final String agentAppId = agentAppIdObj.toString();

        // 从JWT签名中获取代理之前的URL
        final Object urlBeforeAgentObj = JwtUtils.getJwtAdditionItemFromSign(jwtToken, RacJwtSignCo.URL_BEFORE_AGENT);
        if (urlBeforeAgentObj == null) {
            throw new IllegalArgumentException("在JWT签名中找不到代理之前的URL");
        }
        final String urlBeforeAgent = urlBeforeAgentObj.toString();

        return Mono.create(callback -> {
            final Ro<SignUpOrInRa> ro = api.signOut(agentAccountId, agentAppId, urlBeforeAgent);
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                final ResponseCookie responseCookie = ResponseCookie.from(RacCookieCo.APP_ID_KEY, agentAppId).path("/").build();
                resp.addCookie(responseCookie);
                jwtSignWithCookie(ro.getExtra(), resp);
            }
            callback.success(ro);
        });
    }

}
