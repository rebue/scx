package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import lombok.extern.slf4j.Slf4j;
import rebue.scx.zuul.server.co.ZuulCo;
import rebue.scx.zuul.server.dic.RequestParamsTypeDic;
import rebue.wheel.MapUtils;

/**
 * 加入IP过滤器
 * 将浏览器客户端IP加入到请求参数中
 */
@Slf4j
@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.add-ip-pre-filter")
public class AddIpPreFilter extends ZuulFilter {

    @Value("${zuul.filter.addIpPreFilter.shouldFilter:false}")
    private Boolean      shouldFilter;
    @Value("${zuul.filter.addIpPreFilter.filterOrder:6}")
    private Integer      filterOrder;

    /**
     * 需要过滤的URL
     * (读取yml配置文件中的属性)
     */
    private List<String> filterUrls;

    public List<String> getFilterUrls() {
        return filterUrls;
    }

    public void setFilterUrls(final List<String> filterUrls) {
        this.filterUrls = filterUrls;
    }

    private final AntPathMatcher _matcher      = new AntPathMatcher();
    private final ObjectMapper   _objectMapper = new ObjectMapper();

    @Override
    public String filterType() {
        log.info("设置AddIpPreFilter的过滤器类型为pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
        log.info("设置AddIpPreFilter的排序号为{}(数字越大，优先级越高)", filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        log.info("设置是否需要执行AddIpPreFilter过滤器: {}", shouldFilter);
        return shouldFilter;
    }

    @Override
    public Object run() {
        log.info("\r\n============================= 运行AddIpPreFilter过滤器 =============================\r\n");
        try {
            final RequestContext ctx = RequestContext.getCurrentContext();
            final HttpServletRequest req = ctx.getRequest();
            final String url = req.getMethod() + ":" + req.getRequestURI();
            log.debug("处理请求的URL：{}", url);
            if (filterUrls != null && !filterUrls.isEmpty()) {
                log.debug("判断是否匹配需要过滤的url: {}", url);
                if (filterUrls.stream().anyMatch((final String pattern) -> _matcher.match(pattern, url))) {
                    log.debug("此url需要添加IP参数");
                    final String ip = (String) ctx.get(ZuulCo.AGENT_IP);
                    final String mac = "不再获取MAC地址";
                    final String userAgent = (String) ctx.get(ZuulCo.USER_AGENT);
                    switch ((RequestParamsTypeDic) ctx.get(ZuulCo.REQUEST_PARAMS_TYPE)) {
                    case BODY:
                        String body = (String) ctx.get(ZuulCo.REQUEST_PARAMS_STRING);
                        log.debug("需要加入IP参数的body: {}", body);
                        if (body.charAt(0) == '{') {
                            try {
                                final Map<String, Object> paramMap = _objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
                                });
                                List<Object> param = new ArrayList<>();
                                param.add(ip);
                                paramMap.put("ip", param);
                                param = new ArrayList<>();
                                param.add(mac);
                                paramMap.put("mac", param);
                                param = new ArrayList<>();
                                param.add(userAgent);
                                paramMap.put("userAgent", param);
                                body = _objectMapper.writeValueAsString(paramMap);
                            } catch (final IOException e) {
                                final String msg = "按json格式解析参数失败";
                                ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                                ctx.setResponseStatusCode(403); // 返回错误码
                                log.error(msg);
                                throw new RuntimeException(msg);
                            }
                        } else {
                            final Map<String, List<Object>> paramMap = MapUtils.urlParams2Map(body);
                            List<Object> param = new ArrayList<>();
                            param.add(ip);
                            paramMap.put("ip", param);
                            param = new ArrayList<>();
                            param.add(mac);
                            paramMap.put("mac", param);
                            param = new ArrayList<>();
                            param.add(userAgent);
                            paramMap.put("userAgent", param);
                            body = MapUtils.map2UrlParams(paramMap);
                        }
                        log.debug("将新Body字符串加入到ctx中传递给其它过滤器");
                        ctx.set(ZuulCo.REQUEST_PARAMS_STRING, body);
                        log.debug("将新Body请求加入到ctx中传递给其它过滤器");
                        try {
                            final byte[] bodyBytes = body.getBytes("UTF-8");
                            ctx.setRequest(new HttpServletRequestWrapper(req) {
                                @Override
                                public ServletInputStream getInputStream() throws IOException {
                                    return new ServletInputStreamWrapper(bodyBytes);
                                }

                                @Override
                                public int getContentLength() {
                                    return bodyBytes.length;
                                }

                                @Override
                                public long getContentLengthLong() {
                                    return bodyBytes.length;
                                }
                            });
                        } catch (final UnsupportedEncodingException e) {
                            final String msg = "不支持utf-8的编码";
                            log.error(msg, e);
                            throw new RuntimeException(e);
                        }
                        return null;
                    case QUERY: {
                        final Map<String, List<String>> paramMap = ctx.getRequestQueryParams();
                        List<String> param = new ArrayList<>();
                        param.add(ip);
                        paramMap.put("ip", param);
                        param = new ArrayList<>();
                        param.add(mac);
                        paramMap.put("mac", param);
                        param = new ArrayList<>();
                        param.add(userAgent);
                        paramMap.put("userAgent", param);
                        return null;
                    }
                    default:
                        log.info("没有请求参数，创建QueryParams");
                        final Map<String, List<String>> paramMap = new HashMap<>();
                        List<String> param = new ArrayList<>();
                        param.add(ip);
                        paramMap.put("ip", param);
                        param = new ArrayList<>();
                        param.add(mac);
                        paramMap.put("mac", param);
                        param = new ArrayList<>();
                        param.add(userAgent);
                        paramMap.put("userAgent", param);
                        ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.QUERY);
                        log.debug("将queryParam的Map加入到ctx中传递给其它过滤器");
                        ctx.setRequestQueryParams(paramMap);
                        return null;
                    }
                } else {
                    log.debug("此url不需要添加IP参数");
                }
            }
            return null;

        } finally {
            log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束AddIpPreFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }
}