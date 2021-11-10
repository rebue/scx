package rebue.scx.orp.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rebue.orp.core.OidcCore;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.TokenResponse;

import rebue.scx.orp.config.Configurations;
import rebue.scx.orp.utils.Jwt;

@RestController
public class DemoController {

    @Resource
    private Jwt            jwt;

    @Resource
    private Configurations configurations;

    // @Resource
    // private OrpStrategies strategy;

    @GetMapping("/callback")
    public String callback(HttpServletResponse response, String code) throws Exception {
        // strategy.getItems().get("oidc");
        // return "ssssssssssss";
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
