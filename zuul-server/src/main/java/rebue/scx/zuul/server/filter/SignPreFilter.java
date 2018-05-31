package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

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

import rebue.wheel.MapUtils;
import rebue.wheel.turing.SignUtils;

/**
 * 签名过滤器
 * 检查签名是否正确
 */
@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.sign-pre-filter")
public class SignPreFilter extends ZuulFilter {
    private final static Logger _log = LoggerFactory.getLogger(SignPreFilter.class);

    @Value("${zuul.filter.signPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.signPreFilter.filterOrder:2147483647}")
    private Integer             filterOrder;
    @Value("${zuul.filter.signPreFilter.signKey}")
    private String              signKey;

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
        _log.info("设置SignPreFilter的过滤器类型为pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
        _log.info("设置SignPreFilter的排序号为{}(数字越大，优先级越高)", filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        _log.info("设置是否需要执行SignPreFilter过滤器: {}", shouldFilter);
        return shouldFilter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        _log.debug("接收到请求：{}", url);
        if (filterUrls != null && !filterUrls.isEmpty()) {
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                _log.debug("此url需要验证签名");
                try {
                    InputStream in = (InputStream) ctx.get("requestEntity");
                    if (in == null) {
                        in = req.getInputStream();
                    }
                    String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                    _log.debug("需要验证签名的body: {}", body);
                    Map<String, ?> paramMap = null;
                    if (body.charAt(0) == '{') {
                        paramMap = _objectMapper.readValue(body, Map.class);
                    } else {
                        paramMap = MapUtils.urlParams2Map(body);
                    }

                    if (!SignUtils.verify1(paramMap, signKey)) {
                        String msg = "请求参数中的签名验证不正确";
                        ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                        ctx.setResponseStatusCode(403); // 返回错误码
                        // 响应错误信息
                        HttpServletResponse resp = ctx.getResponse();
                        resp.setCharacterEncoding("utf-8");
                        PrintWriter writer = resp.getWriter();
                        writer.println(msg);
                        writer.close();
                        throw new RuntimeException(msg);
                    }

                    // 还原
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
                _log.debug("此url不需要验证签名");
            }
        }
        return null;
    }
}