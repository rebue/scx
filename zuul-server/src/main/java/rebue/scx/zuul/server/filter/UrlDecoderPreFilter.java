package rebue.scx.zuul.server.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class UrlDecoderPreFilter extends ZuulFilter {
    private final static Logger _log = LoggerFactory.getLogger(UrlDecoderPreFilter.class);

    @Value("${zuul.filter.urlDecoderPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.urlDecoderPreFilter.filterOrder:2147483647}")
    private Integer             filterOrder;

    @Override
    public String filterType() {
        _log.info("设置UrlDecoderPreFilter的过滤器类型为pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
        _log.info("设置UrlDecoderPreFilter的排序号为{}(数字越大，优先级越高)", filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        _log.info("设置是否需要执行UrlDecoderPreFilter过滤器: {}", shouldFilter);
        return shouldFilter;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        Map<String, String[]> oldParams = req.getParameterMap();
        Map<String, List<String>> newParams = new HashMap<>();
        if (oldParams != null && !oldParams.isEmpty()) {
            _log.info("URLDecoder过滤器开始解码: {}", url);
            for (Entry<String, String[]> entry : oldParams.entrySet()) {
                String[] oldValues = entry.getValue();
                if (oldValues != null && oldValues.length > 0) {
                    List<String> newValues = new LinkedList<>();
                    for (String oldValue : oldValues) {
                        try {
                            String newValue = URLDecoder.decode(oldValue, "utf-8");
                            _log.debug(oldValue + ":" + newValue);
                            newValues.add(newValue);
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException("不支持utf-8编码(不可能的)");
                        }
                    }
                    newParams.put(entry.getKey(), newValues);
                }
            }
            ctx.setRequestQueryParams(newParams);
        }
        return null;
    }
}