package rebue.scx.zuul.server.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.context.RequestContext;

/**
 * 打印响应时的Cookie
 *
 */
public class CookiePostFilter {
    private final static Logger _log = LoggerFactory.getLogger(CookiePostFilter.class);

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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                _log.info("Cookie ---> {}: {}", cookie.getName(), cookie.getValue());
            }
        }
        return null;
    }
}
