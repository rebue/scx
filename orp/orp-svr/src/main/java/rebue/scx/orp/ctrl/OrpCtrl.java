package rebue.scx.orp.ctrl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.config.OidcConfig;
import rebue.scx.orp.api.OrpApi;
import rebue.scx.orp.ra.OrpUserInfoRa;
import rebue.scx.orp.to.OrpCodeTo;
import rebue.scx.rac.api.RacOpLogApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.turing.JwtUtils;

/**
 * orp三方控制器
 * 
 * @author yuanman
 *
 */
@RestController
@RequestMapping("/orp")
public class OrpCtrl {

    @Resource
    private OrpApi      api;
    @DubboReference
    private RacOpLogApi opLogApi;

    /**
     * 回调
     * 
     * @param response
     * @param code
     * 
     */
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
     */
    @GetMapping("/get-auth-url/{orpType}/{clientId}")
    public Mono<Ro<?>> getAuthUrl(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @RequestParam(value = "redirectUri", required = false) final String redirectUri) {
        return Mono.create(callback -> callback.success(api.getAuthUrl(orpType, clientId, redirectUri)));
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     * 
     * @param orpType  orp的类型（钉钉：ding-talk，微信：wechat-open）
     * @param clientId 客户端ID
     * @param to
     * 
     */
    @GetMapping("/auth-code/{orpType}/{clientId}")
    public Mono<String> authCode(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            final OrpCodeTo to, final ServerHttpResponse response) {
        Ro<?>         authCode = api.authCode(orpType, clientId, to);
        OrpUserInfoRa extra    = (OrpUserInfoRa) authCode.getExtra();
        return Mono.create(cb -> {
            response.setStatusCode(HttpStatus.FOUND);
            response.addCookie(
                    ResponseCookie.from(JwtUtils.JWT_TOKEN_NAME, extra.getIdToken())
                            .path("/")
                            .maxAge(OidcConfig.CODE_FLOW_LOGIN_PAGE_COOKIE_AGE)
                            .build());
            response.getHeaders().setLocation(URI.create(to.getCallbackUrl()));
            cb.success(null);
        });
    }

    /**
     * 根据账户ID绑定微信钉钉的信息
     * 
     * @ignoreParams request
     * 
     * @param to 只需要上传微信/钉钉的信息
     */
    // @RacOpLog(opType = "绑定微信/钉钉", opTitle = "绑定微信/钉钉: #{#p0}")
    @GetMapping("/account-bind/{orpType}/{clientId}/{accountId}")
    public Mono<Void> bindModify(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @PathVariable("accountId") final Long accountId, final OrpCodeTo to, final ServerHttpRequest request, ServerHttpResponse response) {
        Ro<?>                             ro      = api.bindModify(orpType, clientId, accountId, to);
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        boolean                           flag    = ro.getResult().getCode() == 1;
        if (flag) {
            final RacOpLogAddTo appTo = new RacOpLogAddTo();
            switch (orpType) {
            case "ding-talk":
                appTo.setOpType("钉钉绑定");
                appTo.setOpTitle("钉钉绑定");
                appTo.setOpDetail("通过钉钉扫码绑定");
                break;
            case "wechat-open":
                appTo.setOpType("微信绑定");
                appTo.setOpTitle("微信绑定");
                appTo.setOpDetail("通过微信扫码绑定");
                break;
            default:
                throw new RuntimeExceptionX("不支持此解绑方式: " + orpType);
            }
            appTo.setAccountId(accountId);
            appTo.setAgentId(null);
            appTo.setAppId("unified-auth");
            appTo.setOpDatetime(LocalDateTime.now());
            opLogApi.add(appTo);
        }
        return getResponse(response, orpType + "-bind", to.getCallbackUrl(), ro.getMsg(), flag);
    }

    /**
     * 解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    // @RacOpLog(opType = "解绑微信/钉钉", opTitle = "解绑微信/钉钉: #{#p0}")
    @GetMapping("/account-unbind/{orpType}/{clientId}/{accountId}")
    public Mono<Void> unbindModify(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @PathVariable("accountId") final Long accountId, final OrpCodeTo to, ServerHttpResponse response) {
        Ro<?>   ro   = api.unbindModify(orpType, clientId, accountId, to);
        boolean flag = ro.getResult().getCode() == 1;
        if (flag) {
            final RacOpLogAddTo appTo = new RacOpLogAddTo();
            switch (orpType) {
            case "ding-talk":
                appTo.setOpType("钉钉解绑");
                appTo.setOpTitle("钉钉解绑");
                appTo.setOpDetail("通过钉钉扫码解绑");
                break;
            case "wechat-open":
                appTo.setOpType("微信解绑");
                appTo.setOpTitle("微信解绑");
                appTo.setOpDetail("通过微信扫码解绑");
                break;
            default:
                throw new RuntimeExceptionX("不支持此解绑方式: " + orpType);
            }
            appTo.setAccountId(accountId);
            appTo.setAgentId(null);
            appTo.setAppId("unified-auth");
            appTo.setOpDatetime(LocalDateTime.now());
            opLogApi.add(appTo);
        }
        return getResponse(response, orpType + "-unbind", to.getCallbackUrl(), ro.getMsg(), flag);
    }

    /**
     * 
     * @param response
     * @param orpType     （钉钉：ding-talk-xx，微信：wechat-open）
     * @param callbackUrl 重定向的地址
     * @param flag        操作是否成功
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
     * @ignoreParams request
     * 
     * @param appId    应用ID
     * @param orpType  orp的类型（钉钉：ding-talk，微信：wechat-open）
     * @param clientId 客户端ID
     * @param to
     * @param resp
     */
    // @RacOpLog(opType = "扫码登录", opTitle = "微信/钉钉扫码登录: #{#p0}")
    @GetMapping("/sign-in-by-code/{orpType}/{clientId}/{appId}")
    public Mono<Void> signInByCode(
            @PathVariable("orpType") final String orpType,
            @PathVariable("clientId") final String clientId, @PathVariable("appId") final String appId,
            final OrpCodeTo to, final ServerHttpRequest request, final ServerHttpResponse response) {
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();

        final Ro<SignUpOrInRa>            ro      = api.signInByCode(appId, orpType, clientId, to);
        final SignUpOrInRa                ra      = ro.getExtra();
        boolean                           flag    = ro.getResult().getCode() == 1;
        if (flag) {
            final RacOpLogAddTo appTo = new RacOpLogAddTo();
            switch (orpType) {
            case "ding-talk":
                appTo.setOpType("钉钉扫码登录");
                appTo.setOpTitle("钉钉扫码登录");
                appTo.setOpDetail("通过钉钉扫码登录");
                break;
            case "wechat-open":
                appTo.setOpType("微信扫码登录");
                appTo.setOpTitle("微信扫码登录");
                appTo.setOpDetail("通过微信扫码登录");
                break;
            default:
                throw new RuntimeExceptionX("不支持此登录方式: " + orpType);
            }
            appTo.setAccountId(JwtUtils.getJwtAccountIdFromSign(ra.getSign()));
            appTo.setAgentId(null);
            appTo.setAppId("unified-auth");
            appTo.setOpDatetime(LocalDateTime.now());
            opLogApi.add(appTo);
            JwtUtils.addCookie(ra.getSign(), ra.getExpirationTime(), response);
            // to.setCallbackUrl(ra.getRedirectUrl());
            return getResponse(response, orpType + "-sign-in" + "&url=" + getURLEncoderString(ra.getRedirectUrl()), to.getCallbackUrl(), ro.getMsg(), flag);

        }
        // to.setCallbackUrl(ra.getRedirectUrl());
        return getResponse(response, orpType + "-sign-in", to.getCallbackUrl(), ro.getMsg(), flag);
        // response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // return response.setComplete();
        // 401:认证失败，其实应该是UNAUTHENTICATED，HTTP协议历史遗留问题
    }

    /**
     * URL 转码
     *
     * @return String
     */
    private static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 解码
     *
     * @return String
     */
    private static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
