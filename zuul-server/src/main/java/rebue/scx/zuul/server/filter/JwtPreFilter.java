package rebue.scx.zuul.server.filter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import rebue.jwt.svr.feign.JwtSvc;
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.dic.JwtVerifyResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.to.JwtUserInfoTo;
import rebue.wheel.turing.JwtUtils;

/**
 * JWT校验过滤器
 */
@Component
//让yml配置文件中的List类的节点自动注入本bean中相应的属性(注意如果配置文件里是小驼峰命名，这里却要对应写成小写并下划线隔开的规则)
@ConfigurationProperties(prefix = "zuul.filter.jwt-pre-filter")
public class JwtPreFilter extends ZuulFilter {
    private final static Logger _log = LoggerFactory.getLogger(JwtPreFilter.class);

    @Resource
    private JwtSvc              jwtSvc;

    @Value("${zuul.filter.jwtPreFilter.shouldFilter:false}")
    private Boolean             shouldFilter;
    @Value("${zuul.filter.jwtPreFilter.filterOrder:2}")
    private Integer             filterOrder;

    /**
     * 忽略的URL
     * (读取yml配置文件中的属性)
     */
    private List<String>        ignoreUrls;

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(final List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    private final AntPathMatcher _matcher = new AntPathMatcher();

    @Override
    public String filterType() {
        _log.info("设置JwtPreFilter的过滤器类型为pre");
        return "pre";
    }

    @Override
    public int filterOrder() {
        _log.info("设置JwtPreFilter的排序号为{}(数字越大，优先级越高)", filterOrder);
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        _log.info("设置是否需要执行JwtPreFilter过滤器: {}", shouldFilter);
        return shouldFilter;
    }

    @Override
    public Object run() throws ZuulException {
        _log.info("\r\n============================= 运行JwtPreFilter过滤器 =============================\r\n");
        try {
            final RequestContext ctx = RequestContext.getCurrentContext();
            final HttpServletRequest req = ctx.getRequest();
            final String url = req.getMethod() + ":" + req.getRequestURI();
            _log.debug("处理请求的URL：{}", url);
            final String contentType = req.getContentType();
            _log.debug("ContentType: {}", contentType);
            if (contentType != null && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                _log.debug("内容类型是文件上传，不解析参数");
                return null;
            }
            _log.debug("从请求的Cookie中获取JWT签名信息");
            final String sign = JwtUtils.getSignInCookies(req);
            _log.debug("判断是否匹配需要过滤的url: {}", url);
            _log.trace("需要忽略的URL: {}", ignoreUrls);
            if (ignoreUrls != null && !ignoreUrls.isEmpty() && //
                    ignoreUrls.stream().anyMatch((final String pattern) -> _matcher.match(pattern, url))) {
                _log.debug("此url不需要进行JWT校验");
                // 如果不带Cookie直接返回，否则继续以重新签名刷新过期时间
                if (sign == null) {
                    return null;
                }
            } else {
                _log.debug("此url需要进行JWT校验");
                if (sign == null) {
                    final String msg = "验证JWT签名错误-Cookie中并没有签名";
                    _log.error(msg);
                    ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                    ctx.setResponseStatusCode(401); // 返回错误码
                    throw new ZuulException(msg, 401, "可能Cookie已过期，重新登录即可");
                }
            }

            // 验证签名
            final JwtVerifyRo verifyRo = jwtSvc.verify(sign);
            if (JwtVerifyResultDic.SUCCESS.equals(verifyRo.getResult())) {
                // 重新签名刷新过期时间
                jwtSignWithCookie(verifyRo.getUserId(), verifyRo.getSysId(), verifyRo.getAddition(), ctx.getResponse());
            } else {
                final String msg = "验证JWT签名错误";
                _log.error(msg);
                ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                ctx.setResponseStatusCode(403); // 返回错误码
                throw new ZuulException(msg, 403, "可能是有人模仿浏览器恶意发出请求");
            }
            return null;
        } finally {
            _log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束JwtPreFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }

    /**
     * JWT签名并将其加入Cookie
     * 
     * @param userId
     *            用户ID
     */
    private void jwtSignWithCookie(final String userId, final String sysId, final Map<String, Object> addition, final HttpServletResponse resp) {
//        addition.put("wxOpenId", "oqTsm0gdD148UcBzibH4JTm2d9q4");
//        addition.put("wxUnionId", "oqTsm0gdD148UcBzibH4JTm2d9q4");
//        addition.put("orgId", 517928358546243584L);

        final JwtUserInfoTo to = new JwtUserInfoTo();
        to.setUserId(userId);
        to.setSysId(sysId);
        to.setAddition(addition);

        _log.info("调用jwt微服务签名的参数: {}", to);
        final JwtSignRo signRo = jwtSvc.sign(to);
        if (JwtSignResultDic.SUCCESS.equals(signRo.getResult())) {
            JwtUtils.addCookie(signRo.getSign(), signRo.getExpirationTime(), resp);
        }
    }

}