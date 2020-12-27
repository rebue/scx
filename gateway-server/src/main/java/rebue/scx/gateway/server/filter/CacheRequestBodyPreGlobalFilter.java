package rebue.scx.gateway.server.filter;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private JsonParser   jsonParser;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public int getOrder() {
        return -1;
    }

    @SuppressWarnings({ "rawtypes", "unchecked"
    })
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final ServerHttpRequest             request            = exchange.getRequest();
        final HttpMethod                    requestMethod      = request.getMethod();
        final URI                           requestUri         = request.getURI();
        final HttpHeaders                   requestHeaders     = request.getHeaders();
        final MultiValueMap<String, String> requestQueryParams = request.getQueryParams();

        final StringBuilder                 sb1                = new StringBuilder();
        sb1.append("\r\n----------------------- 进入CacheRequestBodyFilter过滤器 -----------------------\r\n");
        sb1.append("* 请求链接:\r\n*    ");
        sb1.append(requestMethod);
        sb1.append(" ");
        sb1.append(requestUri);
        sb1.append("\r\n* 请求的Headers:");
        requestHeaders.forEach((name, values) -> {
            values.forEach(value -> {
                sb1.append("\r\n*    ").append(name).append(":").append(value);
            });
        });
        sb1.append("\r\n* 请求的QueryParams:\r\n*    ");
        sb1.append(requestQueryParams).append("\r\n");
        log.info(sb1.toString());

        final Map<String, Object> paramMap = new LinkedHashMap<>();
        if (!requestQueryParams.isEmpty()) {
            requestQueryParams.forEach((key, value) -> paramMap.put(key, value.get(0)));
        }
        // 缓存
        if (paramMap != null) {
            exchange.getAttributes().put(CachedKeyCo.REQUEST_PARAMS_MAP, paramMap);
        }

        // 重新构造request，参考ModifyRequestBodyGatewayFilterFactory
        final ServerRequest serverRequest = ServerRequest.create(exchange,
                HandlerStrategies.withDefaults().messageReaders());
        final MediaType     mediaType     = exchange.getRequest().getHeaders().getContentType();
        // 重点
        @SuppressWarnings("deprecation")
        final Mono<String>  modifiedBody  = serverRequest.bodyToMono(String.class).flatMap(body -> {
                                              final StringBuilder sb2 = new StringBuilder();
                                              sb2.append("\r\n* 请求的body:\r\n");
                                              // TODO 因为约定了终端传参的格式，所以只考虑json的情况，如果是表单传参，请自行发挥
                                              if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)
                                                      || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(mediaType)) {
                                                  String jsonText = null;
                                                  try {
                                                      jsonText = objectMapper.writerWithDefaultPrettyPrinter()
                                                              .writeValueAsString(objectMapper.readValue(body, Object.class));
                                                      jsonText = "*    " + jsonText.replaceAll("\n", "\n*    ");
                                                  } catch (final JsonProcessingException e) {
                                                      jsonText = "*    JSON格式不正确: " + body;
                                                  }
                                                  sb2.append(jsonText);
                                                  // 将解析出来的字符串转成map
                                                  final Map<String, Object> bodyParmamMap = jsonParser.parseMap(body);
                                                  if (!bodyParmamMap.isEmpty()) {
                                                      paramMap.putAll(bodyParmamMap);
                                                  }
                                              }
                                              else {
                                                  sb2.append(body).append("\r\n");
                                              }
                                              sb2.append("\r\n");
                                              sb2.append(StringUtils.rightPad("------------------------------------------------------------------------------", 100));
                                              log.info(sb2.toString());

                                              // // 缓存
                                              // if (paramMap != null) {
                                              // exchange.getAttributes().put(CachedKeyCo.REQUEST_PARAMS_MAP, paramMap);
                                              // }

                                              return Mono.just(body);
                                          });

        final BodyInserter  bodyInserter  = BodyInserters.fromPublisher(modifiedBody, String.class);
        final HttpHeaders   newHeaders    = new HttpHeaders();
        newHeaders.putAll(exchange.getRequest().getHeaders());
        // 猜测这个就是之前报400错误的元凶，之前修改了body但是没有重新写content length
        newHeaders.remove("Content-Length");
        final CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, newHeaders);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            final ServerHttpRequest decorator = decorate(exchange, requestHeaders, outputMessage);
            return chain.filter(exchange.mutate().request(decorator).build()).doFinally(signalType -> {
                // 调用完成
                stopWatch.stop();

                final ServerHttpResponse response           = exchange.getResponse();
                final HttpStatus         responseStatusCode = response.getStatusCode();
                final HttpHeaders        responseHeaders    = response.getHeaders();

                final StringBuilder      sb3                = new StringBuilder();
                sb3.append("结束CacheRequestBodyFilter过滤器!!!\r\n"
                        + "======================= CacheRequestBodyFilter过滤器被调用详情 =======================\r\n"
                        + "* 结束类型:\r\n*    ");
                sb3.append(signalType);
                sb3.append("\r\n* 响应状态:\r\n*    ");
                sb3.append(responseStatusCode);
                sb3.append("\r\n* 响应Headers:");
                responseHeaders.forEach((name, values) -> {
                    values.forEach(value -> {
                        sb3.append("\r\n*    ").append(name).append(":").append(value);
                    });
                });
                sb3.append("\r\n* 处理耗时:\r\n*    ");
                sb3.append(stopWatch.formatTime());
                sb3.append("\r\n");
                sb3.append(StringUtils.rightPad("===================================================================================", 100));
                log.info(sb3.toString());
            });
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
