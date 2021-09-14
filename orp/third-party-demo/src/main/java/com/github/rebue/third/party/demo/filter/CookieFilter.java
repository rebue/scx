package com.github.rebue.third.party.demo.filter;

import com.github.rebue.third.party.demo.Configurations;
import com.github.rebue.third.party.demo.Jwt;
import com.github.rebue.third.party.demo.utils.CookieUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CookieFilter implements Filter {

    @Resource
    private Jwt jwt;

    @Resource
    private Configurations configurations;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getServletPath();
        if (path.equals("/callback")
                || path.equals("/callback_error.html")
        ) {
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
        response.sendRedirect(configurations.getAuthUri());
    }

    @Override
    public void destroy()
    {
    }

}
