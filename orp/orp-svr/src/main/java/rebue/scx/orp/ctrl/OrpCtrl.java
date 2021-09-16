package rebue.scx.orp.ctrl;

import java.net.URI;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.orp.api.OrpApi;
import rebue.scx.orp.to.OrpCodeTo;
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

            final Triple<String, String, ResponseCookie> pair = api.callback(code);
            if (pair.getLeft() != null) {
                response.setStatusCode(HttpStatus.FOUND);
                response.getHeaders().setLocation(URI.create(pair.getLeft()));
                response.addCookie(pair.getRight());
                cb.success(null);
            }
            cb.success(pair.getMiddle());
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
    public Mono<Ro<?>> getUserInfo(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId, final OrpCodeTo to) {
        return Mono.create(callback -> callback.success(api.getUserInfo(orpType, clientId, to)));
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
    @GetMapping("/sign-in-by-code/{appId}/{orpType}/{clientId}")
    public Mono<Void> signInByCode(@PathVariable("appId") final String appId,
            @PathVariable("orpType") final String orpType,
            @PathVariable("clientId") final String clientId,
            final OrpCodeTo to, final ServerHttpResponse resp) {
        return Mono.create(callback -> {
            final Ro<SignUpOrInRa> ro = api.signInByCode(appId, orpType, clientId, to);
            final SignUpOrInRa     ra = ro.getExtra();
            if (ResultDic.SUCCESS.equals(ro.getResult())) {
                JwtUtils.addCookie(ra.getSign(), ra.getExpirationTime(), resp);
                resp.setStatusCode(HttpStatus.FOUND);
                resp.getHeaders().setLocation(URI.create(ra.getRedirectUrl()));
            }
            else {
                // 401:认证失败，其实应该是UNAUTHENTICATED，HTTP协议历史遗留问题
                resp.setStatusCode(HttpStatus.UNAUTHORIZED);
            }
            resp.setComplete();
            callback.success();
        });
    }

}
