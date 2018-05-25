package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.url-decoder-pre-filter")
public class UrlDecoderPreFilter extends ZuulFilter implements ApplicationListener<ApplicationStartedEvent> {
    private final static Logger _log = LoggerFactory.getLogger(UrlDecoderPreFilter.class);

    @Value("${zuul.filter.urlDecoderPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.urlDecoderPreFilter.filterOrder:2147483647}")
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

    private AntPathMatcher _matcher;

    /**
     * 启动标志，防止多次启动
     */
    private boolean        bStartedFlag = false;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 防止多次启动
        if (bStartedFlag)
            return;
        bStartedFlag = true;

        _log.info("UrlDecoderPreFilter初始化");
        if (filterUrls != null && !filterUrls.isEmpty()) {
            _log.info("UrlDecoderPreFilter需要过滤的url有:\r\n{}", filterUrls);
            _matcher = new AntPathMatcher();
        }

    }

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
        _log.info("运行UrlDecoderPreFilter过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        _log.debug("接收到请求：{}", url);
        if (filterUrls != null && !filterUrls.isEmpty()) {
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                _log.debug("此url需要URLDecoder解码");
                try {
                    InputStream in = (InputStream) ctx.get("requestEntity");
                    if (in == null) {
                        in = req.getInputStream();
                    }
                    String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                    _log.debug("解码前body: {}", body);
                    body = URLDecoder.decode(body, "utf-8");
                    _log.debug("解码后body: {}", body);
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
                _log.debug("此url不需要URLDecoder解码");
            }
        }
        return null;
    }
}