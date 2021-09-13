package rebue.scx.orp.ctrl;

import java.net.URI;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import rebue.scx.orp.api.OidcApi;

import reactor.core.publisher.Mono;
import rebue.scx.orp.core.dic.OrpTypeDic;

@RestController
@RequestMapping("/oidc")
public class OidcCtrl {

    @Resource
    private OidcApi oidcApi;

    @GetMapping("/callback")
    public Mono<String> callback(ServerHttpResponse response, @RequestParam("code") String code) {
        return Mono.create(cb -> {

            Triple<String, String, ResponseCookie> pair = oidcApi.callback(code);
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
     * 认证授权码(OP服务器收到认证请求后重定向redirectUrl，通过此方法向OP服务器发出获取access_token的请求)
     */
    @GetMapping("/auth-code/{orpType}")
    public Mono<String> authCode(ServerHttpResponse response,
            @PathVariable OrpTypeDic orpType,
            @RequestParam("code") String code,
            @RequestParam("state") String state) {
        return Mono.create(cb -> {
            // oidcApi.authCode(code,state);

            Triple<String, String, ResponseCookie> pair = oidcApi.callback(code);
            if (pair.getLeft() != null) {
                response.setStatusCode(HttpStatus.FOUND);
                response.getHeaders().setLocation(URI.create(pair.getLeft()));
                response.addCookie(pair.getRight());
                cb.success(null);
            }
            cb.success(pair.getMiddle());
        });
    }

}
