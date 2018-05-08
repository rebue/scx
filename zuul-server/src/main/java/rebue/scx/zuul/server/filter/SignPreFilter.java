package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import rebue.wheel.turing.SignUtils;

@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.sign-pre-filter")
public class SignPreFilter extends ZuulFilter implements ApplicationListener<ApplicationStartedEvent> {
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

        _log.info("SignPreFilter初始化");
        if (filterUrls != null && !filterUrls.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String url : filterUrls) {
                _log.info(url);
                sb.append(url).append("\r\n");
            }
            _sFilterUrls = sb.toString();
            _log.info("需要签名的url有:\r\n{}", _sFilterUrls);
        }
    }

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
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        _log.debug("接收到请求：{}", url);
        if (_sFilterUrls != null) {
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            Pattern pattern = Pattern.compile(url);
            Matcher mathcer = pattern.matcher(_sFilterUrls);
            if (mathcer.find()) {
                _log.debug("此url需要验证签名");
                if (!SignUtils.verify1(ctx.getRequestQueryParams(), signKey)) {
                    ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                    ctx.setResponseStatusCode(403); // 返回错误码
                    // 响应错误信息
                    HttpServletResponse resp = ctx.getResponse();
                    try {
                        resp.setCharacterEncoding("utf-8");
                        PrintWriter writer = resp.getWriter();
                        writer.println("请求参数中的签名验证不正确");
                        writer.close();
                    } catch (IOException e) {
                        ReflectionUtils.rethrowRuntimeException(e);
                    }
                }
            } else {
                _log.debug("此url不需要验证签名");
            }
        }
        return null;
    }
}