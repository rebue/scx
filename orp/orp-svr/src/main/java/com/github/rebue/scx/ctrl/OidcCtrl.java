package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.svc.OidcSvc;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oidc")
public class OidcCtrl {

    @Autowired
    private OidcSvc oidcSvc;

    @GetMapping("/callback")
    public String callback(
            ServerHttpRequest request, ServerHttpResponse response, String code)
    {
        Pair<String, String> pair = oidcSvc.callback(request, response, code);
        if (pair.getLeft() != null) {
            return "redirect:" + pair.getLeft();
        }
        return pair.getRight();
    }

}
