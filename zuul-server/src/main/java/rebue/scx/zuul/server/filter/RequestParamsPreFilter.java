package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import rebue.scx.zuul.server.co.ZuulCo;
import rebue.scx.zuul.server.dic.RequestParamsTypeDic;

/**
 * 请求参数过滤器(获取请求的参数及参数体类型，在之后的过滤器中共享，以免多次读取耗费性能)
 *
 */
@Component
public class RequestParamsPreFilter extends ZuulFilter {
    private final static Logger _log = LoggerFactory.getLogger(RequestParamsPreFilter.class);

    @Value("${zuul.filter.requestParamsPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.requestParamsPreFilter.filterOrder:2}")
    private Integer             filterOrder;

    @Override
    public String filterType() {
        _log.info("设置RequestParamsPreFilter的过滤器类型为pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
        _log.info("设置RequestParamsPreFilter的排序号为{}(数字越大，优先级越高)", filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        _log.info("设置是否需要执行RequestParamsPreFilter过滤器: {}", shouldFilter);
        return shouldFilter;
    }

    @Override
    public Object run() {
        _log.info("运行RequestParamsPreFilter过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        _log.debug("处理请求的URL：{}", url);
        _log.debug("判断请求参数是否是Body");
        try {
            InputStream in = (InputStream) ctx.get("requestEntity");
            if (in == null) {
                in = req.getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            if (!StringUtils.isBlank(body)) {
                _log.debug("请求参数类型是Body，将其加入到ctx中传递给其它过滤器");
                ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.BODY);
                _log.debug("将请求参数加入到ctx中传递给其它过滤器");
                ctx.set(ZuulCo.REQUEST_PARAMS_STRING, body);
                return null;
            }
        } catch (IOException e) {
            String msg = "获取请求body出现异常";
            _log.error(msg, e);
            throw new RuntimeException(e);
        }

        _log.debug("判断请求参数是否是Query");
        Map<String, String[]> oldParams = req.getParameterMap();
        if (oldParams != null && !oldParams.isEmpty()) {
            Map<String, List<String>> newParams = new HashMap<>();
            for (Entry<String, String[]> entry : oldParams.entrySet()) {
                String[] oldValues = entry.getValue();
                if (oldValues != null && oldValues.length > 0) {
                    List<String> newValues = new LinkedList<>();
                    for (String oldValue : oldValues) {
                        newValues.add(oldValue);
                    }
                    newParams.put(entry.getKey(), newValues);
                }
            }
            _log.debug("请求参数类型是Query，将其加入到ctx中传递给其它过滤器");
            ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.QUERY);
            _log.debug("将queryParam的Map加入到ctx中传递给其它过滤器");
            ctx.setRequestQueryParams(newParams);
            return null;
        }

        _log.debug("没有请求参数");
        _log.debug("请求参数类型是NONE，将其加入到ctx中传递给其它过滤器");
        ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.NONE);
        return null;
    }
}