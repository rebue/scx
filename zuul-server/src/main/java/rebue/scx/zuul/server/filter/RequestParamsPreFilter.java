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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import rebue.scx.zuul.server.co.ZuulCo;
import rebue.scx.zuul.server.dic.RequestParamsTypeDic;
import rebue.wheel.MapUtils;

/**
 * 请求参数过滤器(获取请求的参数及参数体类型，在之后的过滤器中共享，以免多次读取耗费性能)
 *
 */
@Component
public class RequestParamsPreFilter extends ZuulFilter {
    private final static Logger _log          = LoggerFactory.getLogger(RequestParamsPreFilter.class);

    @Value("${zuul.filter.requestParamsPreFilter.shouldFilter:true}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.requestParamsPreFilter.filterOrder:3}")
    private Integer             filterOrder;

    private ObjectMapper        _objectMapper = new ObjectMapper();

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
        _log.info("\r\n============================= 运行RequestParamsPreFilter过滤器 =============================\r\n");
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest req = ctx.getRequest();
            String url = req.getMethod() + ":" + req.getRequestURI();
            _log.debug("处理请求的URL：{}", url);
            String contentType = req.getContentType();
            _log.debug("ContentType: {}", contentType);
            if (contentType != null && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                _log.debug("内容类型是文件上传，不解析参数");
                return null;
            }

            _log.debug("判断请求参数是否是Body");
            try {
                InputStream in = (InputStream) ctx.get("requestEntity");
                if (in == null) {
                    in = req.getInputStream();
                }
                String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                if (!StringUtils.isBlank(body)) {
                    _log.debug("请求参数类型是Body，将其加入到ctx中传递给其它过滤器: {}", body);
                    ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.BODY);
                    ctx.set(ZuulCo.REQUEST_PARAMS_STRING, body);
                    Map<String, ?> paramMap = null;
                    if (body.charAt(0) == '{') {
                        try {
                            paramMap = _objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
                            });
                        } catch (IOException e) {
                            String msg = "按json格式解析参数失败";
                            _log.error(msg);
                            ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                            ctx.setResponseStatusCode(403); // 返回错误码
                            throw new RuntimeException(msg);
                        }
                    } else {
                        paramMap = MapUtils.urlParams2Map(body);
                    }
                    ctx.set(ZuulCo.REQUEST_PARAMS_MAP, paramMap);
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
                _log.debug("请求参数类型是Query，将其加入到ctx中传递给其它过滤器: {}", newParams);
                ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.QUERY);
                ctx.set(ZuulCo.REQUEST_PARAMS_MAP, newParams);
                _log.debug("将queryParam的Map加入到ctx中传递给其它过滤器");
                ctx.setRequestQueryParams(newParams);
                return null;
            }

            _log.debug("没有请求参数");
            _log.debug("请求参数类型是NONE，将其加入到ctx中传递给其它过滤器");
            ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.NONE);
            return null;
        } finally {
            _log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束RequestParamsPreFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }
}