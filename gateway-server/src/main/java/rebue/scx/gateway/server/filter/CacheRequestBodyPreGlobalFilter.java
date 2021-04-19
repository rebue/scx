package rebue.scx.gateway.server.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;

/**
 * 缓存Body
 *
 * 1. 因为在SpringCloudGateway中，Body是stream，只能读取一次，所以将其放入缓存中供其它过滤器使用，最后转发请求时再重构Body
 * 2. 与 ModifyRequestBodyPreGlobalFilter 配合使用
 * 3. 因为是最优先的过滤器并解析了请求的各个参数，顺带做了请求和响应日志
 *
 * 参考:
 * spring 官方提供了：ReadBodyPredicateFactory 谓词工厂，和 ModifyRequestBodyGatewayFilterFactory 过滤器工厂
 * https://github.com/baobeidaodao/spring-cloud/blob/master/gateway/src/main/java/com/baobeidaodao/springcloud/gateway/filter/CacheRequestBodyFilter.java
 *
 * @author zbz
 */
@Slf4j
@Component
public class CacheRequestBodyPreGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private JsonParser   jsonParser;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 注意我开始使用@Order注解没有起作用，所以以实现Ordered接口的方式设置最高的优先级
     */
    @Override
    public int getOrder() {
        return -2;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final ServerHttpRequest request        = exchange.getRequest();
        final HttpMethod        requestMethod  = request.getMethod();
        final URI               requestUri     = request.getURI();
        final HttpHeaders       requestHeaders = request.getHeaders();
        final MediaType         contentType    = requestHeaders.getContentType();
        final StringBuilder     sb1            = new StringBuilder();
        sb1.append("\r\n----------------------- 进入CacheRequestBodyPreGlobalFilter过滤器 -----------------------\r\n");
        sb1.append("* 请求链接:\r\n*    ");
        sb1.append(requestMethod);
        sb1.append(" ");
        sb1.append(requestUri);
        sb1.append("\r\n* 请求的Headers:");
        final StringJoiner sjHeaders = new StringJoiner(";");
        requestHeaders.forEach((name, values) -> {
            final StringJoiner sjHeader = new StringJoiner(",");
            values.forEach(value -> {
                sjHeader.add(value);
                sb1.append("\r\n*    ").append(name).append(": ").append(value);
            });
            sjHeaders.add(name + ": " + sjHeader);
        });

        if (request.getQueryParams() != null && !request.getQueryParams().isEmpty()) {
            final Map<String, Object> requestQueryParams = new LinkedHashMap<>();
            // 缓存
            exchange.getAttributes().put(CachedKeyCo.REQUEST_QUERY_PARAMS, requestQueryParams);
            // 打印
            sb1.append("\r\n* 请求的QueryParams:");
            request.getQueryParams().forEach((key, values) -> {
                values.forEach(value -> {
                    requestQueryParams.put(key, value);
                    sb1.append("\r\n*    ").append(key).append(": ").append(value);
                });
            });
        }
        // log.info(sb1.toString());

        return DataBufferUtils.join(exchange.getRequest().getBody()).doOnNext(dataBuffer -> {
            if (dataBuffer.readableByteCount() > 0) {
                // 将body读到字符串中
                final String bodyString = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer()).toString();

                if (StringUtils.isBlank(bodyString)) {
                    return;
                }

                // 缓存body
                exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY, bodyString);

                // FIXME 这里只判断了JSON格式的Body，不知道后面会不会碰到其它格式的Body
                if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)
                    || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(contentType)) {
                    final Map<String, Object> bodyParmams = new LinkedHashMap<>(jsonParser.parseMap(bodyString));
                    if (bodyParmams != null && !bodyParmams.isEmpty()) {
                        // 缓存参数
                        exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY_PARAMS, bodyParmams);

                        sb1.append("\r\n* 请求的body:\r\n");

                        // 格式化JSON
                        String jsonText = null;
                        try {
                            jsonText = objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(objectMapper.readValue(bodyString, Object.class));
                            jsonText = "*    " + jsonText.replaceAll("\n", "\n*    ");
                        } catch (final JsonProcessingException e) {
                            jsonText = "*    JSON格式不正确: " + bodyString;
                        }
                        sb1.append(jsonText);
                    }
                    else {
                        sb1.append(bodyString).append("\r\n");
                    }
                }
                else {
                    sb1.append(bodyString).append("\r\n");
                }
            }
        }).doOnTerminate(() -> {
            // 上一步结束，在then之前，记录一下日志

            // TODO 数据库日志
            final Object body = exchange.getAttributes().get(CachedKeyCo.REQUEST_BODY);
            if (body != null) {
                final String bodyString = body.toString();
            }

            // 文件日志
            sb1.append(StringUtils.rightPad("\r\n------------------------------------------------------------------------------", 100));
            log.info(sb1.toString());
        }).doFinally(signalType -> {
            // 调用所有过滤器完成，又回到本优先级最高的过滤器
            stopWatch.stop();

            final ServerHttpResponse response           = exchange.getResponse();
            final HttpStatus         responseStatusCode = response.getStatusCode();
            final HttpHeaders        responseHeaders    = response.getHeaders();

            // TODO 数据库日志

            // 文件日志
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("结束CacheRequestBodyPreGlobalFilter过滤器!!!\r\n"
                + "======================= CacheRequestBodyPreGlobalFilter过滤器被调用详情 =======================\r\n"
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
            sb3.append(StringUtils.rightPad("\r\n===================================================================================", 100));
            log.info(sb3.toString());
        })
            // 往下传
            .then(chain.filter(exchange));
    }

}
