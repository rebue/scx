package rebue.scx.gateway.server.filter;

import java.net.URI;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;

/**
 * 重新构造Body
 *
 * 1. 因为在SpringCloudGateway中，Body是stream，只能读取一次，所以将其放入缓存中供其它过滤器使用，最后转发请求时再重构Body
 * 2. 与 CacheRequestBodyPreGlobalFilter 配合使用
 *
 * 参考:
 * spring 官方提供了：ReadBodyPredicateFactory 谓词工厂，和 ModifyRequestBodyGatewayFilterFactory 过滤器工厂
 * https://github.com/baobeidaodao/spring-cloud/blob/master/gateway/src/main/java/com/baobeidaodao/springcloud/gateway/filter/CacheRequestBodyFilter.java
 *
 * @author zbz
 */
@Slf4j
@Component
public class ModifyRequestBodyPreGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 加入参数中requestId的参数名称
     */
    private static final String REQUEST_ID_PARAM_NAME = "requestId";

    @Resource
    private ObjectMapper        objectMapper;

    /**
     * 注意我开始使用@Order注解没有起作用，所以以实现Ordered接口的方式设置最低的优先级
     */
    @Override
    public int getOrder() {
        return 99999;
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        log.info(StringUtils.rightPad("*** 进入 ModifyRequestBodyPreGlobalFilter 过滤器 ***", 100));
        try {
            final ServerHttpRequest request        = exchange.getRequest();
            final HttpHeaders       requestHeaders = request.getHeaders();

            // 缓存请求ID
            final Long                requestId         = (Long) exchange.getAttributes().get(CachedKeyCo.REQUEST_ID);

            final Map<String, Object> requestBodyParams = exchange.getAttribute(CachedKeyCo.REQUEST_BODY_PARAMS);

            // 没有Body的直接往下走
            if (requestBodyParams == null || requestBodyParams.size() == 0) {
                // 往查询参数中添加请求ID
                final URI    uri              = request.getURI();
                final String originalRawQuery = uri.getRawQuery();
                final String newRawQuery      = originalRawQuery + "&" + REQUEST_ID_PARAM_NAME + "=" + requestId;
                final URI    newUri           = UriComponentsBuilder.fromUri(uri).replaceQuery(newRawQuery.toString()).build(true).toUri();
                log.info("要转发的queryParams: {}", newRawQuery);
                final ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
                return chain.filter(exchange.mutate().request(newRequest).build());
            }

            // 有Body的重新构造新的Body，因为Body在CacheRequestBodyPreBolbalFilter过滤器中被出来，就要重新构建
            // 往Body中加入请求ID
            requestBodyParams.put(REQUEST_ID_PARAM_NAME, requestId);
            log.info("要转发的bodyParams: {}", requestBodyParams);
            // 重新构造request，参考ModifyRequestBodyGatewayFilterFactory
            // FIXME 这里默认构造JSON格式的Body，不知道后面会不会碰到其它格式的Body
            final Mono<String> modifiedBody;
            String             bodyString;
            try {
                bodyString = objectMapper.writeValueAsString(requestBodyParams);
                log.info("bodyString: {}", bodyString);
                modifiedBody = Mono.just(bodyString);
            } catch (final JsonProcessingException e) {
                e.printStackTrace();
                log.error("ModifyRequestBodyPreGlobalFilter 序列化JSON对象失败: requestParams-{}", requestBodyParams);
                final ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return response.setComplete();
            }

            // 删除headers中的content length，避免修改Body后Body的数据长度有变化被检测出来报400异常
            // 猜测这个就是之前报400错误的元凶，之前修改了body但是没有重新写content length
            final HttpHeaders newHeaders = new HttpHeaders();
            newHeaders.putAll(requestHeaders);
            // the new content type will be computed by bodyInserter
            // and then set in the request decorator
            newHeaders.remove(HttpHeaders.CONTENT_LENGTH);

            final BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter  = BodyInserters.fromPublisher(modifiedBody, String.class);
            final CachedBodyOutputMessage                               outputMessage = new CachedBodyOutputMessage(exchange, newHeaders);
            return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
                final ServerHttpRequest decorator = decorate(exchange, newHeaders, outputMessage);
                return chain.filter(exchange.mutate().request(decorator).build());
            }));
        } finally {
            log.info(StringUtils.rightPad("~~~ 结束 ModifyRequestBodyPreGlobalFilter 过滤器 ~~~", 100));
        }
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
