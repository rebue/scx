package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import rebue.scx.zuul.server.co.ZuulCo;

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
    @Value("${zuul.filter.addIpPreFilter.filterOrder:2147483647}")
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
        _log.info("运行AddIpPreFilter过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        _log.debug("接收到请求：{}", url);
        if (filterUrls != null && !filterUrls.isEmpty()) {
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                _log.debug("此url需要添加IP参数");
                try {
                    InputStream in = (InputStream) ctx.get("requestEntity");
                    if (in == null) {
                        in = req.getInputStream();
                    }
                    String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                    _log.debug("加入IP前的body: {}", body);
                    String ip = (String) ctx.get(ZuulCo.AGENT_IP);
                    String mac = "不再获取MAC地址";
                    if (body.charAt(0) == '{') {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> paramMap = _objectMapper.readValue(body, Map.class);
                        paramMap.put("ip", ip);
                        paramMap.put("mac", mac);
                        body = _objectMapper.writeValueAsString(paramMap);
                    } else {
                        body += "&ip=" + ip + "&mac=" + mac;
                    }
                    _log.debug("加入IP后的body: {}", body);
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
                } catch (IOException e) {
                    String msg = "获取请求body出现异常";
                    _log.error(msg, e);
                    throw new RuntimeException(e);
                }
            } else {
                _log.debug("此url不需要添加IP参数");
            }
        }
        return null;
    }
}