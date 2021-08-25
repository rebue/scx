package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.dto.LoginDto;
import com.github.rebue.scx.svc.OidcSvc;
import com.github.rebue.scx.svc.impl.OidcSvcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/oidc")
public class OidcCtrl {

    @Autowired
    private OidcSvc oidcSvc;

    /**
     * 重定向到前端登录页面(未登录)
     * 重定向到前端登录页面(未登录)
     * https://openid.net/specs/openid-connect-core-1_0.html#AuthorizationEndpoint
     * <p>
     * scope           openid
     * response_type   code
     * client_id       test1
     * redirect_uri    https://www.baidu.com
     * state           RECOMMENDED
     */
    @RequestMapping(value = "/authorize", method = {RequestMethod.GET, RequestMethod.POST})
    public void authorize(@RequestParam Map<String, String> paramMap, ServerHttpRequest request, ServerHttpResponse response)
    {
        oidcSvc.authorize(paramMap, request, response);
    }

    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public void login(LoginDto loginData, ServerHttpRequest request, ServerHttpResponse response)
    {
        oidcSvc.login(loginData, request, response);
    }

    @ResponseBody
    @PostMapping(value = "/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Mono<Object> token(ServerHttpRequest request, ServerHttpResponse response)
    {
        List<String> authorizations = request.getHeaders().get("Authorization");
        if (CollectionUtils.isEmpty(authorizations)) {
            return Mono.just(OidcSvcImpl.tokenError(response, "auth", "missing Authorization header"));
        }
        URL url;
        try {
            url = new URL(request.getURI().toString());
        } catch (Exception e) {
            return Mono.just(OidcSvcImpl.tokenError(response, "url", e.getMessage()));
        }
        return DataBufferUtils.join(request.getBody())
                .map(buf -> {
                    String requestBody = StandardCharsets.UTF_8.decode(buf.asByteBuffer()).toString();
                    return oidcSvc.token(authorizations.get(0), url, requestBody, response);
                });
    }

}
