package com.github.rebue.third.party.demo;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.rebue.orp.core.OidcCore;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.TokenResponse;

@Controller
public class DemoController {

    @Resource
    private Jwt            jwt;

    @Resource
    private Configurations configurations;

    @GetMapping("/callback")
    public String callback(HttpServletResponse response, String code) throws Exception {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                configurations.getTokenEndpoint(),
                configurations.getClientId(),
                configurations.getClientSecret(),
                code,
                configurations.getRedirect());
        if (tokenResponse.indicatesSuccess()) {
            JWT             idToken = tokenResponse.toSuccessResponse().getTokens().toOIDCTokens().getIDToken();
            String          sign    = jwt.sign(idToken.getJWTClaimsSet().getSubject());
            final SignedJWT jwt     = SignedJWT.parse(sign);
            Cookie          cookie  = new Cookie(Configurations.LOGIN_COOKIE, sign);
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:index.html";
        }
        return "redirect:callback_error.html";
    }

}
