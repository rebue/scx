package com.github.rebue.third.party.demo.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.github.rebue.third.party.demo.Configurations;
import com.github.rebue.third.party.demo.Jwt;
import com.github.rebue.third.party.demo.utils.CookieUtil;
import com.github.rebue.third.party.demo.utils.UrlUtil;

@Component
public class CookieFilter implements Filter {

    @Resource
    private Jwt            jwt;

    @Resource
    private Configurations configurations;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String              path     = request.getServletPath();
        if (path.equals("/callback")
                || path.equals("/callback_error.html")) {
            chain.doFilter(req, res);
            return;
        }

        Boolean isLogin = CookieUtil.getFirstCookieValue(request, Configurations.LOGIN_COOKIE)
                .map(jwt::verify)
                .orElse(false);
        if (isLogin) {
            chain.doFilter(req, res);
            return;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scope", "openid");
        map.put("response_type", "code");
        map.put("client_id", configurations.getClientId());
        map.put("state", "第三方生成，redirect_uri回调时第三方自己校验");
        // 回调三方服务端地址
        map.put("redirect_uri", "http://localhost:30010/callback");
        String map2UrlParams = UrlUtil.map2UrlParams(map);
        // ?scope=openid&response_type=code&client_id=third-party-demo&state=tr&redirect_uri=http%3A%2F%2Flocalhost:30010%2Fcallback
        response.sendRedirect(configurations.getAuthUri() + "?" + map2UrlParams);
    }

    @Override
    public void destroy() {
    }

}
