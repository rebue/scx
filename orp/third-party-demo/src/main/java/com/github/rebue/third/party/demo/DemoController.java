package com.github.rebue.third.party.demo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.rebue.orp.core.OidcCore;
import com.github.rebue.third.party.demo.utils.CookieUtil;
import com.nimbusds.oauth2.sdk.TokenResponse;

@Controller
public class DemoController {

    @Resource
    private Jwt jwt;

    @Resource
    private Configurations configurations;

    @GetMapping("/")
    public String index(HttpServletRequest request, String code)
    {
        if (code != null) {
            return "index.html";
        }
        Boolean isLogin = CookieUtil.getFirstCookieValue(request, Configurations.LOGIN_COOKIE)
                .map(jwt::verify)
                .orElse(false);
        if (isLogin) {
            return "index.html";
        }
        return "redirect:" + configurations.getAuthUri();
    }

    @GetMapping("/callback")
    public String callback(String code) throws Exception
    {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                configurations.getTokenEndpoint(),
                configurations.getClientId(),
                configurations.getClientSecret(),
                code,
                configurations.getRedirect()
        );
        if (tokenResponse.indicatesSuccess()) {
            return "redirect:index.html";
        }
        return "redirect:callback_error.html";
    }

}
