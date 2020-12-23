package rebue.scx.gateway.server.filter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
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
public class CacheRequestBodyPreGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private JsonParser jsonParser;

    @Override
    public int getOrder() {
        return -1;
    }

    @SuppressWarnings({ "rawtypes", "unchecked"
    })
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        log.info("\r\n============================= 运行CacheRequestBodyFilter过滤器 =============================\r\n");
        try {
            // return ServerWebExchangeUtils.cacheRequestBody(exchange, (serverHttpRequest) -> chain.filter(exchange.mutate().request(serverHttpRequest).build()));
            log.info("{}: {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());

            final Map<String, Object>           paramMap    = new LinkedHashMap<>();
            final MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
            if (!queryParams.isEmpty()) {
                paramMap.putAll(queryParams);
            }
            // 重新构造request，参考ModifyRequestBodyGatewayFilterFactory
            final ServerRequest serverRequest = ServerRequest.create(exchange,
                HandlerStrategies.withDefaults().messageReaders());
            final MediaType     mediaType     = exchange.getRequest().getHeaders().getContentType();
            // 重点
            @SuppressWarnings("deprecation")
            final Mono<String>  modifiedBody  = serverRequest.bodyToMono(String.class).flatMap(body -> {
                                                  // TODO 因为约定了终端传参的格式，所以只考虑json的情况，如果是表单传参，请自行发挥
                                                  if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)
                                                      || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(mediaType)) {
                                                      // 将解析出来的字符串转成map
                                                      final Map<String, Object> bodyParmamMap = jsonParser.parseMap(body);
                                                      if (!bodyParmamMap.isEmpty()) {
                                                          paramMap.putAll(bodyParmamMap);
                                                      }
                                                  }

                                                  // 缓存
                                                  if (paramMap != null) {
                                                      exchange.getAttributes().put(CachedKeyCo.REQUEST_PARAMS_MAP, paramMap);
                                                      log.debug("params: {}", paramMap);
                                                  }

                                                  return Mono.just(body);
                                              });

            final BodyInserter  bodyInserter  = BodyInserters.fromPublisher(modifiedBody, String.class);
            final HttpHeaders   headers       = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            // 猜测这个就是之前报400错误的元凶，之前修改了body但是没有重新写content length
            headers.remove("Content-Length");
            // MyCachedBodyOutputMessage 这个类完全就是CachedBodyOutputMessage，只不过CachedBodyOutputMessage不是公共的
            final CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
            return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
                final ServerHttpRequest decorator = decorate(exchange, headers, outputMessage);
                return returnMono(chain, exchange.mutate().request(decorator).build());
            }));
        } finally {
            log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束CacheRequestBodyFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
        }
    }

    private Mono<Void> returnMono(final GatewayFilterChain chain, final ServerWebExchange exchange) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            final Long startTime = exchange.getAttribute("startTime");
            if (startTime != null) {
                final long executeTime = System.currentTimeMillis() - startTime;
                log.info("耗时：{}ms", executeTime);
                log.info("状态码：{}", Objects.requireNonNull(exchange.getResponse().getStatusCode()).value());
            }
        }));
    }

    ServerHttpRequestDecorator decorate(final ServerWebExchange exchange, final HttpHeaders headers, final CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                final long        contentLength = headers.getContentLength();
                final HttpHeaders httpHeaders   = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0L) {
                    httpHeaders.setContentLength(contentLength);
                }
                else {
                    httpHeaders.set("Transfer-Encoding", "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

}
