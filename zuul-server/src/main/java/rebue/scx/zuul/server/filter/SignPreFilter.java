package rebue.scx.zuul.server.filter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import rebue.scx.zuul.server.co.ZuulCo;
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
    @Value("${zuul.filter.signPreFilter.filterOrder:5}")
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

    private AntPathMatcher _matcher = new AntPathMatcher();

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
        _log.info("\r\n============================= 运行SignPreFilter过滤器 =============================\r\n");
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
            if (filterUrls != null && !filterUrls.isEmpty()) {
                _log.debug("判断是否匹配需要过滤的url: {}", url);
                if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                    _log.debug("此url需要验证签名");
                    @SuppressWarnings("unchecked")
                    Map<String, List<Object>> paramMap = (Map<String, List<Object>>) ctx.get(ZuulCo.REQUEST_PARAMS_MAP);
                    if (!SignUtils.verify1(paramMap, signKey)) {
                        String msg = "请求参数中的签名验证不正确";
                        _log.error(msg);
                        ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                        ctx.setResponseStatusCode(403); // 返回错误码
                        throw new RuntimeException(msg);
                    }
                } else {
                    _log.debug("此url不需要验证签名");
                }
            }
            return null;
        } finally {
            _log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束SignPreFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }

    }
}