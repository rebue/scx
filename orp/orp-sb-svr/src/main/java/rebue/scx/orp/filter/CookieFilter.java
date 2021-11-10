package rebue.scx.orp.filter;

import java.io.IOException;

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

import rebue.scx.orp.config.Configurations;
import rebue.scx.orp.utils.CookieUtil;
import rebue.scx.orp.utils.Jwt;

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
        response.sendRedirect(configurations.getAuthUri());
    }

    @Override
    public void destroy() {
    }

}
