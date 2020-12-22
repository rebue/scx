package rebue.scx.gateway.server.filter;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;

/**
 * spring 官方提供了预言类：一个 ReadBodyPredicateFactory 谓词工厂，和 ModifyRequestBodyGatewayFilterFactory 过滤器工厂
 * 而且目前 2019-04-20 仍然是 bate 版
 * 并没有直接实现获取 request body 的 filter
 * 此 filter 为参考以上预言类作出的记录 request body 的全局过滤器工厂
 * https://github.com/baobeidaodao/spring-cloud/blob/master/gateway/src/main/java/com/baobeidaodao/springcloud/gateway/filter/CacheRequestBodyFilter.java
 * 
 * @author DaoDao
 */
@Slf4j
@Component
@Order(value = 3)
public class CacheRequestBodyFilter implements GatewayFilter {

    @Resource
    private JsonParser jsonParser;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        log.info("\r\n============================= 运行CacheRequestBodyFilter过滤器 =============================\r\n");
        try {
            // return ServerWebExchangeUtils.cacheRequestBody(exchange, (serverHttpRequest) -> chain.filter(exchange.mutate().request(serverHttpRequest).build()));
            log.info("{}: {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());
            return DataBufferUtils.join(exchange.getRequest().getBody()).doOnNext(dataBuffer -> {
                Map<String, Object>                 paramMap    = null;
                final MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
                if (!queryParams.isEmpty()) {
                    paramMap = new LinkedHashMap<>(queryParams);
                }

                if (dataBuffer.readableByteCount() > 0) {
                    // 将body读到字符串中
                    final String              bodyString    = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer()).toString();
                    // 将解析出来的字符串转成map(FIXME 这里把所有传过来的Body的内容都认为是json格式的，不知道后面会不会碰到什么例外)
                    final Map<String, Object> bodyParmamMap = jsonParser.parseMap(bodyString);
                    if (paramMap == null) {
                        paramMap = bodyParmamMap;
                    }
                    else {
                        paramMap.putAll(bodyParmamMap);
                    }

                    // 缓存
                    if (paramMap != null) {
                        exchange.getAttributes().put(CachedKeyCo.REQUEST_PARAMS_MAP, paramMap);
                        log.debug("params: {}", paramMap);
                    }
                }
            })
                // 往下传
                .then(chain.filter(exchange));
        } finally {
            log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束CacheRequestBodyFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }
}
