package rebue.scx.gateway.server.filter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.scx.sgn.api.ex.SgnVerifyApi;

@Slf4j
@Component
public class SignPreGatewayFilterFactory extends AbstractGatewayFilterFactory<SignPreGatewayFilterFactory.Config> {

    @DubboReference
    private SgnVerifyApi                sgnVerifyApi;

    private final static AntPathMatcher _matcher = new AntPathMatcher();
    static {
        _matcher.setCaseSensitive(false);
    }

    public SignPreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            log.info(StringUtils.rightPad("*** 进入SignPreFilter过滤器 ***", 100));
            final ServerHttpRequest request    = exchange.getRequest();
            final String            requestUrl = request.getMethod() + ":" + request.getPath();
            // TODO AntPathMatcher是不是线程安全的？
            if (config.getIgnoreUrls() != null
                && !config.getIgnoreUrls().isEmpty()
                && config.getIgnoreUrls().stream().anyMatch(
                    ignoreUrl -> _matcher.match(ignoreUrl, requestUrl))) {
                log.debug("此URL根据配置被SignPreFilter过滤器忽略");
                return returnFilter(chain, exchange);
            }

            Map<String, Object> requestParams = exchange.getAttribute(CachedKeyCo.REQUEST_QUERY_PARAMS);
            if (requestParams == null) {
                requestParams = exchange.getAttribute(CachedKeyCo.REQUEST_BODY_PARAMS);
            }
            if (!ResultDic.SUCCESS.equals(sgnVerifyApi.verify(requestParams).getResult())) {
                log.warn("认证失败: requestParams-{}", requestParams);
                final ServerHttpResponse response = exchange.getResponse();
                // 401:认证失败，其实应该是UNAUTHENTICATED，Spring代码历史遗留问题
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            return returnFilter(chain, exchange);
        }, 5);
    }

    private Mono<Void> returnFilter(final GatewayFilterChain chain, final ServerWebExchange exchange) {
        return chain.filter(exchange).doFinally(signalType -> {
            log.info(StringUtils.rightPad("~~~ 结束SignPreFilter过滤器(结束类型: " + signalType + ") ~~~", 100));
        });
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(NAME_KEY);
    }

    public static class Config {
        private List<String> ignoreUrls;

        public List<String> getIgnoreUrls() {
            return ignoreUrls;
        }

        public void setIgnoreUrls(final List<String> ignoreUrls) {
            this.ignoreUrls = ignoreUrls;
        }

    }

}
