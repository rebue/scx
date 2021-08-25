/*
 *Copyright © 2018 anji-plus
 *http://www.anji-plus.com
 *All rights reserved.
 */
package rebue.scx.cap.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 本地启动解决跨域问题
 *  打包发布请注释该类，可能会造成一定的影响，解决本地启动跨域问题
 * Created by raodeming on 2020/3/17.
 */
import org.springframework.context.annotation.Configuration;

@WebFilter(filterName = "CorsFilter")
@Configuration
public class CorsFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public  void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws ServletException, IOException {
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");//http请求方式
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
