package rebue.scx.rac.ctrl.ex;

import static rebue.scx.rac.ctrl.ex.SignUpOrInCtrlCommon.jwtSignWithCookie;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.api.ex.RacSignInApi;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.co.RacJwtSignCo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.UnifiedLoginTo;
import rebue.scx.rac.to.ex.SignInByAccountNameTo;
import rebue.scx.rac.to.ex.UnlockSignInTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.turing.JwtUtils;

/**
 * 账户登录的控制器
 */
@RestController
public class RacSignInCtrl {

    @Resource
    private RacSignInApi  api;
    @Resource
    private RacAccountApi accountApi;

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
     * 通过手机号验证登录
     * 
     * @ignoreParams request
     */
    @PostMapping("/rac/sign-in/sign-in-by-mobile-code")
    @RacOpLog(opType = "登录", opTitle = "通过手机号验证登录: #{#p0.accountName}")
    public Mono<Ro<SignUpOrInRa>> signInByMobileCode(@RequestBody final UnifiedLoginTo to, final ServerHttpRequest request, final ServerHttpResponse resp) {
        // 从Headers中获取应用ID
        final List<String> list  = request.getHeaders().get(RacCookieCo.HEADERS_APP_ID_KEY);
        final String       appId = list != null && list.size() > 0 ? list.get(0) : null;
        if (StringUtils.isBlank(appId)) {
            throw new RuntimeExceptionX("在Headers中找不到应用ID");
        }
        to.setAppId(appId);
        to.setLoginType((byte) 1);
        return Mono.create(callback -> {
            final Ro<SignUpOrInRa> ro = api.unifiedLogin(to);
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
    public Mono<Ro<PageRa<RacAccountMo>>> getSignInLockRecord(final RacAccountPageTo qo) {
        return Mono.create(callback -> callback.success(api.getSignInLockRecord(qo)));
    }

    /**
     * 手动删除输入登录密码错误次数账户解锁
     * 
     * @param to
     * 
     */
    @PostMapping("/rac/sign-in/sign-in-lock-record")
    @RacOpLog(opType = "登录密码解锁", opTitle = "登录密码解锁: #{#p0.accountId}")
    public Mono<Ro<?>> handDelWrongPswdTimesOfSignIn(@RequestBody final UnlockSignInTo to, @CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        final Long opAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (opAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到当前账户ID");
        }
        to.setOpAccountId(opAccountId);        // 从JWT签名中获取代理账户ID作为代理ID
        Object agentAccountIdObj = null;
        try {
            agentAccountIdObj = JwtUtils.getJwtAdditionItemFromSign(jwtToken, RacJwtSignCo.AGENT_ACCOUNT_ID);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (agentAccountIdObj != null) {
            final Long agentAccountId = Long.valueOf(agentAccountIdObj.toString());
            to.setAgentAccountId(agentAccountId);
        }
        return Mono.create(callback -> callback.success(api.handDelWrongPswdTimesOfSignIn(to)));

    }

}
