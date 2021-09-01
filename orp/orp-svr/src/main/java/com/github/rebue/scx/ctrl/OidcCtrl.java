package com.github.rebue.scx.ctrl;

import java.net.URI;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rebue.scx.svc.OidcSvc;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/oidc")
public class OidcCtrl {

    @Autowired
    private OidcSvc oidcSvc;

    @GetMapping("/callback")
    public Mono<String> callback(
            ServerHttpRequest request, ServerHttpResponse response, String code)
    {
        return Mono.create(cb -> {
            Pair<String, String> pair = oidcSvc.callback(request, response, code);
            if (pair.getLeft() != null) {
                response.setStatusCode(HttpStatus.FOUND);
                response.getHeaders().setLocation(URI.create(pair.getLeft()));
                cb.success(null);
            }
            cb.success(pair.getRight());
        });
    }

}
