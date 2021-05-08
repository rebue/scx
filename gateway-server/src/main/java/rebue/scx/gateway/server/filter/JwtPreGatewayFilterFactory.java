package rebue.scx.gateway.server.filter;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.to.JwtVerifyTo;
import rebue.wheel.core.spring.AntPathMatcherUtils;
import rebue.wheel.turing.JwtUtils;

@Slf4j
@Component
public class JwtPreGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtPreGatewayFilterFactory.Config> {

    @DubboReference
    private JwtApi jwtApi;

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
                final String sign = JwtUtils.getSignInCookies(request);
                if (StringUtils.isBlank(sign)) {
                    log.warn("JWT签名校验失败: 在Cookie中并没有找到签名");
                    final ServerHttpResponse response = exchange.getResponse();
                    // 401:认证失败，其实应该是UNAUTHENTICATED，Spring代码历史遗留问题
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                if (!ResultDic.SUCCESS.equals(jwtApi.verify(new JwtVerifyTo(sign)).getResult())) {
                    log.warn("JWT签名校验失败: url-{}", url);
                    final ServerHttpResponse response = exchange.getResponse();
                    // 401:认证失败，其实应该是UNAUTHENTICATED，Spring代码历史遗留问题
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                log.info("JWT签名校验成功");

                return returnFilter(chain, exchange);
            } finally {
                log.info(StringUtils.rightPad("~~~ 结束 JwtPreFilter 过滤器 ~~~", 100));
            }
        }, 7);
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
