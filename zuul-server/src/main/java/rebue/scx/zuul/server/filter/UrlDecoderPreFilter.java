package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

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

    /**
     * 将配置的signUrls放入字符串中作为判断是否匹配url请求正则表达式的字符串
     */
    private String  _sFilterUrls;

    /**
     * 启动标志，防止多次启动
     */
    private boolean bStartedFlag = false;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 防止多次启动
        if (bStartedFlag)
            return;
        bStartedFlag = true;

        _log.info("UrlDecoderPreFilter初始化");
        if (filterUrls != null && !filterUrls.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String url : filterUrls) {
                _log.info(url);
                sb.append(url).append("\r\n");
            }
            _sFilterUrls = sb.toString();
            _log.info("需要URLDecoder解码的url有:\r\n{}", _sFilterUrls);
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
        try {
            String url = req.getMethod() + ":" + req.getRequestURI();
            if (_sFilterUrls != null) {
                _log.debug("判断是否匹配需要过滤的url: {}", url);
                Pattern pattern = Pattern.compile(url);
                Matcher mathcer = pattern.matcher(_sFilterUrls);
                if (mathcer.find()) {
                    _log.debug("此url需要URLDecoder解码");
                    Map<String, String[]> oldParams = req.getParameterMap();
                    Map<String, List<String>> newParams = new HashMap<>();
                    if (oldParams != null && !oldParams.isEmpty()) {
                        _log.info("UrlDecoderPreFilter过滤器开始解码: {}", url);
                        for (Entry<String, String[]> entry : oldParams.entrySet()) {
                            String[] oldValues = entry.getValue();
                            if (oldValues != null && oldValues.length > 0) {
                                for (int i = 0; i < oldValues.length; i++) {
                                    _log.info("解码前: {}", oldValues[i]);
                                    oldValues[i] = URLDecoder.decode(oldValues[i], "utf-8");
                                    _log.info("解码后: {}", oldValues[i]);
                                }
                                newParams.put(entry.getKey(), Lists.newArrayList(oldValues));
                            }
                        }
                        ctx.setRequestQueryParams(newParams);
                    }
                } else {
                    _log.debug("此urlURLDecoder解码");
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}