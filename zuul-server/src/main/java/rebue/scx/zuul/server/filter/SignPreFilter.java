package rebue.scx.zuul.server.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import rebue.scx.zuul.server.co.ZuulCo;
import rebue.scx.zuul.server.dic.RequestParamsTypeDic;
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
    @Value("${zuul.filter.signPreFilter.filterOrder:4}")
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
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String url = req.getMethod() + ":" + req.getRequestURI();
        _log.debug("处理请求的URL：{}", url);
        if (filterUrls != null && !filterUrls.isEmpty()) {
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            if (filterUrls.stream().anyMatch((String pattern) -> _matcher.match(pattern, url))) {
                _log.debug("此url需要验证签名");
                Map<String, ?> paramMap = null;
                switch ((RequestParamsTypeDic) ctx.get(ZuulCo.REQUEST_PARAMS_TYPE)) {
                case BODY:
                    String body = (String) ctx.get(ZuulCo.REQUEST_PARAMS_STRING);
                    _log.debug("需要验证签名的body: {}", body);
                    if (body.charAt(0) == '{') {
                        try {
                            paramMap = _objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
                            });
                        } catch (IOException e) {
                            String msg = "按json格式解析参数失败";
                            ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                            ctx.setResponseStatusCode(403); // 返回错误码
                            _log.error(msg);
                            throw new RuntimeException(msg);
                        }
                    } else {
                        paramMap = MapUtils.urlParams2Map(body);
                    }
                    break;
                case QUERY:
                    paramMap = ctx.getRequestQueryParams();
                    break;
                default:
                    String msg = "没有请求参数，不能验证签名";
                    ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                    ctx.setResponseStatusCode(403); // 返回错误码
                    throw new RuntimeException(msg);
                }
                if (!SignUtils.verify1(paramMap, signKey)) {
                    String msg = "请求参数中的签名验证不正确";
                    ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                    ctx.setResponseStatusCode(403); // 返回错误码
                    throw new RuntimeException(msg);
                }
            } else {
                _log.debug("此url不需要验证签名");
            }
        }
        return null;
    }
}