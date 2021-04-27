package rebue.scx.gateway.server.filter;

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
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.wheel.serialization.jackson.JacksonUtils;

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

    @Resource
    private ObjectMapper objectMapper;

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
            final ServerHttpRequest             request        = exchange.getRequest();
            final HttpHeaders                   requestHeaders = request.getHeaders();
            final MultiValueMap<String, String> queryParams    = request.getQueryParams();

            if (queryParams != null && queryParams.size() > 0) {
                log.info("要转发的queryParams: {}", queryParams);
            }

            final Map<String, Object> requestBodyParams = exchange.getAttribute(CachedKeyCo.REQUEST_BODY_PARAMS);
            if (requestBodyParams == null) {
                return chain.filter(exchange);
            }

            if (requestBodyParams != null && requestBodyParams.size() > 0) {
                log.info("要转发的bodyParams: {}", requestBodyParams);
            }

            // 重新构造request，参考ModifyRequestBodyGatewayFilterFactory
            // FIXME 这里默认构造JSON格式的Body，不知道后面会不会碰到其它格式的Body
            Mono<String> modifiedBody;
            String       bodyString;
            try {
                bodyString = JacksonUtils.serialize(requestBodyParams);
                // final String json = "{\"algorithm\":2,\"requestId\":861869767961608194}";
                log.info("json: {}", bodyString);
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
            newHeaders.putAll(exchange.getRequest().getHeaders());
            // the new content type will be computed by bodyInserter
            // and then set in the request decorator
            newHeaders.remove(HttpHeaders.CONTENT_LENGTH);
            // final List<String> a = new ArrayList<>();
            // a.add(String.valueOf(bodyString.length()));
            // newHeaders.put(HttpHeaders.CONTENT_LENGTH, a);

            final BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter  = BodyInserters.fromPublisher(modifiedBody, String.class);
            final CachedBodyOutputMessage                               outputMessage = new CachedBodyOutputMessage(exchange, newHeaders);
            return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
                final ServerHttpRequest decorator = decorate(exchange, requestHeaders, outputMessage);
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
