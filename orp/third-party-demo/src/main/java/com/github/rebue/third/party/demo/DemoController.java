package com.github.rebue.third.party.demo;

import com.github.rebue.orp.core.OidcCore;
import com.github.rebue.third.party.demo.utils.CookieUtil;
import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.TokenResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {

    @Resource
    private Jwt jwt;

    @Resource
    private Configurations configurations;

    @GetMapping("/")
    public String index(HttpServletRequest request)
    {
        Boolean isLogin = CookieUtil.getFirstCookieValue(request, Configurations.LOGIN_COOKIE)
                .map(jwt::verify)
                .orElse(false);
        if (isLogin) {
            return "index.html";
        }
        return "redirect:" + configurations.getAuthUri();
    }

    @GetMapping("/callback")
    public String callback(HttpServletResponse response, String code) throws Exception
    {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                configurations.getTokenEndpoint(),
                configurations.getClientId(),
                configurations.getClientSecret(),
                code,
                configurations.getRedirect()
        );
        if (tokenResponse.indicatesSuccess()) {
            JWT idToken = tokenResponse.toSuccessResponse().getTokens().toOIDCTokens().getIDToken();
            String sign = jwt.sign(idToken.getJWTClaimsSet().getSubject());
            Cookie cookie = new Cookie(Configurations.LOGIN_COOKIE, sign);
            cookie.setDomain("the-localhost");
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:index.html";
        }
        return "redirect:callback_error.html";
    }

}
