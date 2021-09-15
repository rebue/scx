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
import rebue.robotech.ro.Ro;
import rebue.scx.orp.api.OrpApi;

@RestController
@RequestMapping("/orp")
public class OrpCtrl {

    @Resource
    private OrpApi oidcApi;

    @GetMapping("/callback")
    public Mono<String> callback(final ServerHttpResponse response, @RequestParam("code") final String code) {
        return Mono.create(cb -> {

            final Triple<String, String, ResponseCookie> pair = oidcApi.callback(code);
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
     */
    @GetMapping("/get-auth-url/{orpType}/{clientId}")
    public Mono<Ro<?>> getAuthUrl(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @RequestParam("redirectUri") final String redirectUri) {
        return Mono.create(callback -> callback.success(oidcApi.getAuthUrl(orpType, clientId, redirectUri)));
    }

    /**
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    @GetMapping("/get-user-info/{orpType}/{clientId}")
    public Mono<Ro<?>> getUserInfo(@PathVariable("orpType") final String orpType, @PathVariable("clientId") final String clientId,
            @RequestParam("code") final String code, @RequestParam("state") final String state) {
        return Mono.create(callback -> callback.success(oidcApi.getUserInfo(orpType, clientId, code, state)));
    }

}
