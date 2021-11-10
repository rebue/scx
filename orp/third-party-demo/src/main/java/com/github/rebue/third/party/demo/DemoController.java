package com.github.rebue.third.party.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.rebue.orp.core.OidcCore;
import com.github.rebue.third.party.demo.utils.OkHttpClientImpl;
import com.github.rebue.third.party.demo.utils.UrlUtil;
import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.util.JSONObjectUtils;

import net.minidev.json.JSONObject;

@Controller
public class DemoController {

    @Resource
    private Jwt              jwt;

    @Resource
    private Configurations   configurations;
    @Resource
    private OkHttpClientImpl okHttpClientImpl;

    /**
     * 该控制器的地址为{统一认证服务器}/oap-svr/oap/authorize 中redirect_uri参数的值
     * 
     * @param response
     * @param code
     * 
     * @return
     * 
     * @throws Exception
     */
    @GetMapping("/callback")
    public String callback(HttpServletResponse response, String code) throws Exception {
        TokenResponse tokenResponse = OidcCore.tokenRequest(
                configurations.getTokenEndpoint(),
                configurations.getClientId(),
                configurations.getClientSecret(),
                code,
                configurations.getRedirect());
        if (tokenResponse.indicatesSuccess()) {
            JWT         idToken     = tokenResponse.toSuccessResponse().getTokens().toOIDCTokens().getIDToken();
            AccessToken accessToken = tokenResponse.toSuccessResponse().getTokens().toOIDCTokens().getAccessToken();
            // 获取账户信息
            String      userInfo    = okHttpClientImpl.get(getUrl(accessToken.getValue(), idToken.serialize()));
            JSONObject  parse       = JSONObjectUtils.parse(userInfo);
            Object      msg         = parse.get("msg");
            if (msg != null) {
                throw new RuntimeException(msg.toString());
            }
            String sign   = jwt.sign(idToken.getJWTClaimsSet().getSubject());
            // final SignedJWT jwt = SignedJWT.parse(sign);
            Cookie cookie = new Cookie(Configurations.LOGIN_COOKIE, sign);
            // -1关闭浏览器清除cookie
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:index.html";
        }
        return "redirect:callback_error.html";
    }

    private String getUrl(String accessToken, String idToken) {
        // String uri = "https://auth.nnxy.edu.cn/oap-svr/oap/get-user-info";
        String              uri = "http://172.20.11.244:13080/oap-svr/oap/get-user-info";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idToken", idToken);
        map.put("accessToken", accessToken);
        String map2UrlParams = UrlUtil.map2UrlParams(map);
        return uri + "?" + map2UrlParams;
    }

}
