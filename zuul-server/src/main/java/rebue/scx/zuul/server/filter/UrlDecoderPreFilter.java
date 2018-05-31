package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import rebue.scx.zuul.server.co.ZuulCo;
import rebue.scx.zuul.server.dic.RequestParamsTypeDic;

@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.url-decoder-pre-filter")
public class UrlDecoderPreFilter extends ZuulFilter {
    private final static Logger _log = LoggerFactory.getLogger(UrlDecoderPreFilter.class);

    @Value("${zuul.filter.urlDecoderPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.urlDecoderPreFilter.filterOrder:3}")
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

    private AntPathMatcher _matcher = new AntPathMatcher();

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
        _log.debug("处理请求的URL：{}", url);
        if (filterUrls != null && !filterUrls.isEmpty()) {
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                _log.debug("此url需要URLDecoder解码");
                switch ((RequestParamsTypeDic) ctx.get(ZuulCo.REQUEST_PARAMS_TYPE)) {
                case BODY:
                    _log.debug("开始解码Body类型的参数");
                    byte[] bodyBytes;
                    String body = (String) ctx.get(ZuulCo.REQUEST_PARAMS_STRING);
                    try {
                        _log.debug("解码前queryString: {}", body);
                        body = URLDecoder.decode(body, "utf-8");
                        _log.debug("解码后queryString: {}", body);
                        bodyBytes = body.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        String msg = "不支持utf-8的编码";
                        _log.error(msg, e);
                        throw new RuntimeException(e);
                    }
                    _log.debug("将新Body字符串加入到ctx中传递给其它过滤器");
                    ctx.set(ZuulCo.REQUEST_PARAMS_STRING, body);
                    _log.debug("将新Body请求加入到ctx中传递给其它过滤器");
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
                    return null;
                case QUERY:
                    _log.debug("开始解码Query类型的参数");
                    Map<String, List<String>> newParams = new HashMap<>();
                    for (Entry<String, List<String>> entry : ctx.getRequestQueryParams().entrySet()) {
                        List<String> oldValues = entry.getValue();
                        if (oldValues != null && !oldValues.isEmpty()) {
                            List<String> newValues = new LinkedList<>();
                            for (String oldValue : oldValues) {
                                try {
                                    String newValue = URLDecoder.decode(oldValue, "utf-8");
                                    _log.debug(oldValue + ":" + newValue);
                                    newValues.add(newValue);
                                } catch (UnsupportedEncodingException e) {
                                    String msg = "不支持utf-8的编码";
                                    _log.error(msg, e);
                                    throw new RuntimeException(e);
                                }
                            }
                            newParams.put(entry.getKey(), newValues);
                        }
                    }
                    _log.debug("将queryParam的Map加入到ctx中传递给其它过滤器");
                    ctx.setRequestQueryParams(newParams);
                    return null;
                default:
                    _log.warn("没有参数，不需要解码");
                    return null;
                }
            } else {
                _log.debug("此url不需要URLDecoder解码");
            }
        }
        return null;
    }
}