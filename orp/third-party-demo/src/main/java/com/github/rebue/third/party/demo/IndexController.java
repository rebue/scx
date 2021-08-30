package com.github.rebue.third.party.demo;

import com.github.rebue.third.party.demo.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

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
        return "redirect:" + configurations.getRedirect();
    }

    @ResponseBody
    @GetMapping("/callback")
    public String callback()
    {
        return "";
    }

}
