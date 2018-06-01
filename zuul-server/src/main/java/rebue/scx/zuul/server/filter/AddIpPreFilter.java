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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import rebue.scx.zuul.server.co.ZuulCo;
import rebue.scx.zuul.server.dic.RequestParamsTypeDic;
import rebue.wheel.MapUtils;

/**
 * 加入IP过滤器
 * 将浏览器客户端IP加入到请求参数中
 */
@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.add-ip-pre-filter")
public class AddIpPreFilter extends ZuulFilter {
    private final static Logger _log = LoggerFactory.getLogger(AddIpPreFilter.class);

    @Value("${zuul.filter.addIpPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.addIpPreFilter.filterOrder:10}")
    private Integer             filterOrder;

    /**
     * 需要过滤的URL
     * (读取yml配置文件中的属性)
     */
    private List<String>        filterUrls;

    public List<String> getFilterUrls() {
        return filterUrls;
    }

    public void setFilterUrls(List<String> filterUrls) {
        this.filterUrls = filterUrls;
    }

    private AntPathMatcher _matcher      = new AntPathMatcher();
    private ObjectMapper   _objectMapper = new ObjectMapper();

    @Override
    public String filterType() {
        _log.info("设置AddIpPreFilter的过滤器类型为pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
        _log.info("设置AddIpPreFilter的排序号为{}(数字越大，优先级越高)", filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        _log.info("设置是否需要执行AddIpPreFilter过滤器: {}", shouldFilter);
        return shouldFilter;
    }

    @Override
    public Object run() {
        _log.info("\r\n-----------------运行AddIpPreFilter过滤器-----------------\r\n");
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest req = ctx.getRequest();
            String url = req.getMethod() + ":" + req.getRequestURI();
            _log.debug("处理请求的URL：{}", url);
            if (filterUrls != null && !filterUrls.isEmpty()) {
                _log.debug("判断是否匹配需要过滤的url: {}", url);
                if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                    _log.debug("此url需要添加IP参数");
                    String ip = (String) ctx.get(ZuulCo.AGENT_IP);
                    String mac = "不再获取MAC地址";
                    switch ((RequestParamsTypeDic) ctx.get(ZuulCo.REQUEST_PARAMS_TYPE)) {
                    case BODY:
                        String body = (String) ctx.get(ZuulCo.REQUEST_PARAMS_STRING);
                        _log.debug("需要加入IP参数的body: {}", body);
                        if (body.charAt(0) == '{') {
                            try {
                                Map<String, Object> paramMap = _objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
                                });
                                List<Object> param = new ArrayList<>();
                                param.add(ip);
                                paramMap.put("ip", param);
                                param = new ArrayList<>();
                                param.add(mac);
                                paramMap.put("mac", param);
                                body = _objectMapper.writeValueAsString(paramMap);
                            } catch (IOException e) {
                                String msg = "按json格式解析参数失败";
                                ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                                ctx.setResponseStatusCode(403); // 返回错误码
                                _log.error(msg);
                                throw new RuntimeException(msg);
                            }
                        } else {
                            Map<String, List<Object>> paramMap = MapUtils.urlParams2Map(body);
                            List<Object> param = new ArrayList<>();
                            param.add(ip);
                            paramMap.put("ip", param);
                            param = new ArrayList<>();
                            param.add(mac);
                            paramMap.put("mac", param);
                            body = MapUtils.map2UrlParams(paramMap);
                        }
                        _log.debug("将新Body字符串加入到ctx中传递给其它过滤器");
                        ctx.set(ZuulCo.REQUEST_PARAMS_STRING, body);
                        _log.debug("将新Body请求加入到ctx中传递给其它过滤器");
                        try {
                            byte[] bodyBytes = body.getBytes("UTF-8");
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
                        } catch (UnsupportedEncodingException e) {
                            String msg = "不支持utf-8的编码";
                            _log.error(msg, e);
                            throw new RuntimeException(e);
                        }
                        return null;
                    case QUERY: {
                        Map<String, List<String>> paramMap = ctx.getRequestQueryParams();
                        List<String> param = new ArrayList<>();
                        param.add(ip);
                        paramMap.put("ip", param);
                        param = new ArrayList<>();
                        param.add(mac);
                        paramMap.put("mac", param);
                        return null;
                    }
                    default:
                        _log.info("没有请求参数，创建QueryParams");
                        Map<String, List<String>> paramMap = new HashMap<>();
                        List<String> param = new ArrayList<>();
                        param.add(ip);
                        paramMap.put("ip", param);
                        param = new ArrayList<>();
                        param.add(mac);
                        paramMap.put("mac", param);
                        ctx.set(ZuulCo.REQUEST_PARAMS_TYPE, RequestParamsTypeDic.QUERY);
                        _log.debug("将queryParam的Map加入到ctx中传递给其它过滤器");
                        ctx.setRequestQueryParams(paramMap);
                        return null;
                    }
                } else {
                    _log.debug("此url不需要添加IP参数");
                }
            }
            return null;

        } finally {
            _log.info("\r\n=================结束AddIpPreFilter过滤器=================\r\n");
        }
    }
}