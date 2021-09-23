package rebue.scx.orp.ctrl;

import java.net.URI;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.config.OidcConfig;
import rebue.scx.orp.api.OrpApi;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.wheel.turing.JwtUtils;

@RestController
@RequestMapping("/orp")
public class OrpCtrl {

    @Resource
    private OrpApi api;

    @GetMapping("/callback")
    public Mono<String> callback(final ServerHttpResponse response, @RequestParam("code") final String code) {
        return Mono.create(cb -> {

            final Pair<String, String> pair = api.callback(code, response);
            if (pair.getLeft() != null) {
                response.setStatusCode(HttpStatus.FOUND);
                response.getHeaders().setLocation(URI.create(pair.getLeft()));
                cb.success(null);
            }
            else {
                cb.success(pair.getRight());
            }
        });
    }

    /**
     * 获取认证Url(获取认证Url后前端跳转此URL)
     * 
     * @param orpType     orp的类型（钉钉：ding-talk，微信：wechat-open）
     * @param clientId    客户端ID
     * @param redirectUri 扫码后回调的地址（/orp/get-user-info/{orpType}/{clientId}）
     * 
     * @return
     */
    @GetMapping("/get-auth-url/{orpType}/{clientId}")
    public Mono<Ro<?>> getAuthUrl(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @RequestParam("redirectUri") final String redirectUri) {
        return Mono.create(callback -> callback.success(api.getAuthUrl(orpType, clientId, redirectUri)));
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     * 
     * @param orpType  orp的类型（钉钉：ding-talk，微信：wechat-open）
     * @param clientId 客户端ID
     * @param to
     * 
     * @return
     */
    @GetMapping("/get-user-info/{orpType}/{clientId}")
    public Mono<Ro<?>> getUserInfo(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            final OrpCodeTo to) {
        return Mono.create(callback -> callback.success(api.getUserInfo(orpType, clientId, to)));
    }

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     * @return
     * 
     */
    @RacOpLog(opType = "绑定微信/钉钉", opTitle = "绑定微信/钉钉: #{#p0}")
    @GetMapping("/account-bind/{orpType}/{clientId}/{accountId}")
    public Mono<Void> bindModify(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @PathVariable("accountId") final Long accountId, final OrpCodeTo to, ServerHttpResponse response) {
        Ro<?>   ro   = api.bindModify(orpType, clientId, accountId, to);
        boolean flag = ro.getResult().getCode() == 1;

        return getResponse(response, orpType + "-bind", to.getCallbackUrl(), ro.getMsg(), flag);
    }

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     * 
     */
    @RacOpLog(opType = "解绑微信/钉钉", opTitle = "解绑微信/钉钉: #{#p0}")
    @GetMapping("/account-unbind/{orpType}/{clientId}/{accountId}")
    public Mono<Void> unbindModify(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @PathVariable("accountId") final Long accountId, final OrpCodeTo to, ServerHttpResponse response) {
        Ro<?>   ro   = api.unbindModify(orpType, clientId, accountId, to);
        boolean flag = ro.getResult().getCode() == 1;
        return getResponse(response, orpType + "-unbind", to.getCallbackUrl(), ro.getMsg(), flag);
    }

    /**
     * 
     * @param response
     * @param orpType     （钉钉：ding-talk-xx，微信：wechat-open）
     * @param callbackUrl 重定向的地址
     * @param flag        操作是否成功
     * 
     * @return
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
     * 通过授权码登录
     * 
     * @param appId    应用ID
     * @param orpType  orp的类型（钉钉：ding-talk，微信：wechat-open）
     * @param clientId 客户端ID
     * @param to
     * @param resp
     * 
     * @return
     */
    @GetMapping("/sign-in-by-code/{orpType}/{clientId}/{appId}")
    public Mono<Void> signInByCode(
            @PathVariable("orpType") final String orpType,
            @PathVariable("clientId") final String clientId, @PathVariable("appId") final String appId,
            final OrpCodeTo to, final ServerHttpResponse response) {
        final Ro<SignUpOrInRa> ro   = api.signInByCode(appId, orpType, clientId, to);
        final SignUpOrInRa     ra   = ro.getExtra();
        boolean                flag = ro.getResult().getCode() == 1;
        if (flag) {
            JwtUtils.addCookie(ra.getSign(), ra.getExpirationTime(), response);
            response.addCookie(ResponseCookie.from(RacCookieCo.APP_ID_KEY, appId)
                    .path("/")
                    .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                    .build());
            to.setCallbackUrl(ra.getRedirectUrl());
            return getResponse(response, orpType + "-sign-in", to.getCallbackUrl(), ro.getMsg(), flag);

        }
        to.setCallbackUrl(ra.getRedirectUrl());
        return getResponse(response, orpType + "-sign-in", to.getCallbackUrl(), ro.getMsg(), flag);
        // response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // return response.setComplete();
        // 401:认证失败，其实应该是UNAUTHENTICATED，HTTP协议历史遗留问题

    }

}
