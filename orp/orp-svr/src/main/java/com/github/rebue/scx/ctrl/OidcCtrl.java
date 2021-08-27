package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.svc.OidcSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
    public Mono<Ro<String>> callback(ServerHttpRequest request, String code)
    {
        return Mono.create(cb -> {
            Ro<String> ro = oidcSvc.callback(request, code).map(Ro::success)
                    .orElse(Ro.fail("获取token失败"));
            cb.success(ro);
        });
    }

}
