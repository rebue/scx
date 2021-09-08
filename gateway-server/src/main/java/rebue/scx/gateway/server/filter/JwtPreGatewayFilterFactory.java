package rebue.scx.gateway.server.filter;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.rac.api.RacPermUrnApi;
import rebue.wheel.core.spring.AntPathMatcherUtils;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Component
public class JwtPreGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtPreGatewayFilterFactory.Config> {

    @Value("${oidc.redirect-uri}")
    private String redirectUri;

    @DubboReference
    private JwtApi        jwtApi;

    @DubboReference
    private RacPermUrnApi racPermUrnApi;

    public JwtPreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            log.info(StringUtils.rightPad("*** 进入JwtPreFilter过滤器 ***", 100));
            try {
                final ServerHttpRequest request = exchange.getRequest();
                final String            method  = request.getMethod().toString();
                final String            path    = request.getPath().toString();
                final String            url     = method + ":" + path;

                if (isStaticResource(path)) {
                    return returnFilter(chain, exchange);
                }

                // 登录页面
                if (path.startsWith("/admin-web")
                    && "u=1".equals(request.getURI().getQuery())) {
                    return returnFilter(chain, exchange);
                }

                log.info("判断是否要过滤此URL-{}", url);
                if (config.getFilterUrls() != null && !config.getFilterUrls().isEmpty()
                    && AntPathMatcherUtils.noneMatch(method, path, config.getFilterUrls())) {
                    log.debug("经判断不过滤此URL");
                    return returnFilter(chain, exchange);
                }
                if (config.getIgnoreUrls() != null && !config.getIgnoreUrls().isEmpty()
                    && AntPathMatcherUtils.anyMatch(method, path, config.getIgnoreUrls())) {
                    log.debug("经判断忽略此URL");
                    return returnFilter(chain, exchange);
                }
                log.info("经判断要过滤此URL");

                // 获取签名
                final String sign = JwtUtils.getSignFromCookies(request);
                if (StringUtils.isBlank(sign)) {
                    log.warn("JWT签名校验失败: 在Cookie中并没有找到签名");
                    final ServerHttpResponse response = exchange.getResponse();

                    response.setStatusCode(HttpStatus.FOUND);
                    response.getHeaders().setLocation(URI.create(redirectUri));

                    return response.setComplete();
                }

                final JwtSignRa verifyRo = jwtApi.verify(sign);
                if (!verifyRo.isSuccess()) {
                    log.warn("JWT签名校验失败: url-{}", url);
                    final ServerHttpResponse response = exchange.getResponse();

                    response.setStatusCode(HttpStatus.FOUND);
                    response.getHeaders().setLocation(URI.create(redirectUri));

                    return response.setComplete();
                }
                log.info("JWT签名校验成功");

                boolean skip = path.equals("/") || path.startsWith("/admin-web");
                if (!skip) {
                    log.info("获取可访问的链接列表");
                    final Long               accountId = JwtUtils.getJwtAccountIdFromSign(sign);
                    final Ro<ListRa<String>> urnsRo    = racPermUrnApi.getUrnsOfAccount(accountId);
                    if (!ResultDic.SUCCESS.equals(urnsRo.getResult())) {
                        log.warn("获取可访问的链接列表失败: url-{}", url);
                        final ServerHttpResponse response = exchange.getResponse();
                        // 503:服务失效
                        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
                        return response.setComplete();
                    }
                    log.info("判断账户是否有该链接的访问权限: accountId-{}, url-{}", accountId, url);
                    if (AntPathMatcherUtils.noneMatch(method, path, urnsRo.getExtra().getList())) {
                        log.warn("账户没有访问该链接的权限: accountId-{}, url-{}", accountId, url);
                        final ServerHttpResponse response = exchange.getResponse();
                        // 403:没有访问该资源的权限
                        response.setStatusCode(HttpStatus.FORBIDDEN);
                        return response.setComplete();
                    }
                    log.info("账户有访问该链接的权限");
                }

                log.info("延长JWT签名时效");
                final ServerHttpResponse                    response        = exchange.getResponse();
                final MultiValueMap<String, ResponseCookie> responseCookies = response.getCookies();
                final ResponseCookie                        jwtTokenCookie  = ResponseCookie.from(JwtUtils.JWT_TOKEN_NAME, verifyRo.getSign()).maxAge(
                    Duration.between(LocalDateTime.now(), verifyRo.getExpirationTime())).path("/").build();
                responseCookies.add(JwtUtils.JWT_TOKEN_NAME, jwtTokenCookie);
                log.info("完成延长JWT签名时效");

                return returnFilter(chain, exchange);
            } finally {
                log.info(StringUtils.rightPad("~~~ 结束 JwtPreFilter 过滤器 ~~~", 100));
            }
        }, 7);
    }

    private static boolean isStaticResource(String path)
    {
        // 拦截html
        return path.matches(".*[.](jpg|css|svg|ttf|ddf|png|js|woff|txt|ico|json|map)$");
    }

    private Mono<Void> returnFilter(final GatewayFilterChain chain, final ServerWebExchange exchange) {
        return chain.filter(exchange);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(NAME_KEY);
    }

    static class Config {
        /**
         * 要进行过滤的链接
         */
        private List<String> filterUrls;
        /**
         * 要忽略过滤的链接
         */
        private List<String> ignoreUrls;

        public List<String> getFilterUrls() {
            return filterUrls;
        }

        public void setFilterUrls(final List<String> filterUrls) {
            this.filterUrls = filterUrls;
        }

        public List<String> getIgnoreUrls() {
            return ignoreUrls;
        }

        public void setIgnoreUrls(final List<String> ignoreUrls) {
            this.ignoreUrls = ignoreUrls;
        }

    }

}
