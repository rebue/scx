package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.api.OidcApi;
import com.github.rebue.scx.svc.OidcSvc;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;

@RestController
@RequestMapping("/oidc")
public class OidcCtrl {

    @Resource
    private OidcApi oidcApi;

    @GetMapping("/callback")
    public Mono<String> callback(ServerHttpResponse response, String code)
    {
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

}
