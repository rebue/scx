package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.svc.OidcSvc;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;

@RestController
@RequestMapping("/oidc")
public class OidcCtrl {

    @Autowired
    private OidcSvc oidcSvc;

    @GetMapping("/callback")
    public Mono<Ro<String>> callback(
            ServerHttpRequest request, ServerHttpResponse response,
            String code, String redirectUri)
    {
        return Mono.create(cb -> {
            Pair<String, String> pair = oidcSvc.callback(request, response, code, redirectUri);
            if (pair.getLeft() != null) {
                cb.success(Ro.success(pair.getLeft()));
                return;
            }
            cb.success(Ro.fail(pair.getRight()));
        });
    }

}
