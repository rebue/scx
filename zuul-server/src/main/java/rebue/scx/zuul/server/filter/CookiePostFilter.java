package rebue.scx.zuul.server.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 打印响应时的Cookie
 *
 */
@Slf4j
public class CookiePostFilter {
    public String filterType() {
        return "post";
    }

    public int filterOrder() {
        return 999;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest req = ctx.getRequest();
        final Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                log.info("Cookie ---> {}: {}", cookie.getName(), cookie.getValue());
            }
        }
        return null;
    }
}
