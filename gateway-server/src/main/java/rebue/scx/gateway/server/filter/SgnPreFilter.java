package rebue.scx.gateway.server.filter;

import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.scx.sgn.api.SgnApi;

/**
 * 签名过滤器
 * 检查签名是否正确
 */
@Slf4j
@Order(5)
@Component
public class SgnPreFilter implements GlobalFilter {

    @DubboReference
    private SgnApi sgnApi;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        log.info("\r\n============================= 运行SgnPreFilter过滤器 =============================\r\n");
        try {
            final Map<String, Object> paramMap = exchange.getAttribute(CachedKeyCo.REQUEST_PARAMS_MAP);
            if (!sgnApi.verify(paramMap)) {
                log.warn("认证失败: paramMap-{}", paramMap);
                final ServerHttpResponse response = exchange.getResponse();
                // 401:认证失败，其实应该是UNAUTHENTICATED，Spring代码历史遗留问题
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        } finally {
            log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束SgnPreFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }

}
