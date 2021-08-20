package com.github.rebue.scx.ctrl;

import com.github.rebue.scx.dto.LoginDto;
import com.github.rebue.scx.svc.OidcSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/login")
    public void login(LoginDto loginData)
    {
        System.out.println();
    }

}
