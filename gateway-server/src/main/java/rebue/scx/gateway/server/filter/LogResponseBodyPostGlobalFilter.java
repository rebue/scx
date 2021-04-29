package rebue.scx.gateway.server.filter;

import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.scx.gateway.server.co.GatewayServerCo;
import rebue.scx.gateway.server.pub.RrlPub;
import rebue.scx.rrl.to.RrlRespLogAddTo;
import rebue.wheel.core.LocalDateTimeUtils;

/**
 * 记录响应包括Body在内的详细信息
 * 虽然排序是-2，但是是在响应时才进行处理
 *
 * @author zbz
 */
@Slf4j
@Component
public class LogResponseBodyPostGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private ModifyResponseBodyGatewayFilterFactory modifyResponseBodyGatewayFilterFactory;

    @Resource
    private RrlPub                                 rrlPub;

    @Resource
    private JsonParser                             jsonParser;

    @Resource
    private ObjectMapper                           objectMapper;

    private static DateTimeFormatter               _dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 注意我开始使用@Order注解没有起作用，所以以实现Ordered接口的方式设置最高的优先级
     */
    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -2;
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        log.info(StringUtils.rightPad("*** 进入 LogResponseBodyPostGlobalFilter 过滤器 ***", 100));
        try {
            final ServerHttpResponse          originalResponse  = exchange.getResponse();

            final DataBufferFactory           bufferFactory     = originalResponse.bufferFactory();
            final ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                                                                    @Override
                                                                    public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                                                                        if (body instanceof Flux) {
                                                                            log.debug("准备响应，要解析Body记录日志，Body是Flux");
                                                                            final Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                                                                            return super.writeWith(fluxBody.map(//
                                                                                dataBuffer -> {
                                                                                    // probably should reuse buffers
                                                                                    final byte[] content = new byte[dataBuffer.readableByteCount()];
                                                                                    dataBuffer.read(content);
                                                                                    // 释放掉内存
                                                                                    DataBufferUtils.release(dataBuffer);

                                                                                    String bodyString = null;
                                                                                    if (content != null && content.length > 0) {
                                                                                        bodyString = new String(content, Charset.forName("UTF-8"));
                                                                                    }
                                                                                    log.debug("Body解析完成");

                                                                                    final HttpStatus                        responseStatusCode = originalResponse.getStatusCode();
                                                                                    final HttpHeaders                       responseHeaders    = originalResponse.getHeaders();
                                                                                    final MultiValueMap<String, ResponseCookie> responseCookies = originalResponse.getCookies();

                                                                                    // 获取请求ID
                                                                                    final Long      requestId          = exchange.getAttribute(CachedKeyCo.REQUEST_ID);
                                                                                    // 获取请求时间
                                                                                    final LocalDateTime requestTime    = exchange.getAttribute(CachedKeyCo.REQUEST_TIME);
                                                                                    final String    requestTimeString  = _dateTimeFormatter.format(requestTime);
                                                                                    // 当前响应时间
                                                                                    final LocalDateTime responseTime   = LocalDateTime.now();
                                                                                    final String    responseTimeString = _dateTimeFormatter.format(responseTime);
                                                                                    // 处理耗时(毫秒)
                                                                                    final Long      spendMillis        = Duration.between(requestTime, responseTime).toMillis();

                                                                                    // 记录文件日志
                                                                                    logFile(responseStatusCode, responseHeaders, requestId, requestTimeString, responseTimeString,
                                                                                        spendMillis, responseCookies, bodyString);

                                                                                    // 记录数据库日志
                                                                                    logRrl(responseStatusCode, responseHeaders, requestId, responseTime, responseCookies,
                                                                                        bodyString);

                                                                                    return bufferFactory.wrap(content);
                                                                                }));
                                                                        }
                                                                        // if body is not a flux. never got there.
                                                                        log.warn("Body不是Flux，有这个可能吗？");
                                                                        return super.writeWith(body);
                                                                    }

                                                                };
            // replace response with decorator
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        } finally {
            log.info(StringUtils.rightPad("~~~ 结束 LogResponseBodyPostGlobalFilter 过滤器 ~~~", 100));
        }
    }

    /**
     * 记录文件日志
     */
    private void logFile(final HttpStatus responseStatusCode, final HttpHeaders responseHeaders, final Long requestId, final String requestTimeString,
                         final String responseTimeString, final Long spendMillis, final MultiValueMap<String, ResponseCookie> responseCookies, final String bodyString) {
        // 文件日志
        final StringBuilder sb = new StringBuilder();
        sb.append("请求处理完成，准备响应!!!\r\n======================= 请求及响应详情 =======================\r\n");
        sb.append("* 请求ID:\r\n*    ");
        sb.append(requestId);
        sb.append("\r\n* 请求时间:\r\n*    ");
        sb.append(requestTimeString);
        sb.append("\r\n* 响应时间:\r\n*    ");
        sb.append(responseTimeString);
        sb.append("\r\n* 处理耗时:\r\n*    ");
        sb.append(spendMillis);
        sb.append("毫秒");
        sb.append("\r\n* 响应状态:\r\n*    ");
        sb.append(responseStatusCode);
        if (responseHeaders != null && !responseHeaders.isEmpty()) {
            sb.append("\r\n* 响应Headers:");
            responseHeaders.forEach(
                (name, values) -> {
                    values.forEach(value -> sb.append("\r\n*    ").append(name).append(":").append(value));
                });
        }
        if (responseCookies != null && !responseCookies.isEmpty()) {
            sb.append("\r\n* 响应Cookies:");
            responseCookies.forEach(
                (name, values) -> {
                    values.forEach(value -> sb.append("\r\n*    ").append(name).append(":").append(value));
                });
        }
        if (StringUtils.isNotBlank(bodyString)) {
            sb.append("\r\n* 响应主体:\r\n");
            // 格式化JSON
            String jsonText = null;
            try {
                jsonText = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(objectMapper.readValue(bodyString, Object.class));
                jsonText = "*    " + jsonText.replaceAll("\n", "\n*    ");
            } catch (final JsonProcessingException e) {
                jsonText = "*    " + bodyString;
            }
            sb.append(jsonText);
        }
        sb.append(StringUtils.rightPad(
            "\r\n===================================================================================",
            100));
        log.info(sb.toString());
    }

    /**
     * 记录数据库日志
     */
    private void logRrl(final HttpStatus responseStatusCode, final HttpHeaders responseHeaders, final Long requestId,
                        final LocalDateTime responseTime, final MultiValueMap<String, ResponseCookie> responseCookies, final String bodyString) {
        // 数据库日志
        // 构造消息对象
        final RrlRespLogAddTo to = new RrlRespLogAddTo();
        to.setEventId(GatewayServerCo.RRL_EVENT_ID);
        to.setId(requestId); // XXX 不自动生成ID，因为要让本次请求的请求ID等于响应ID
        to.setHeaders(responseHeaders.toString());
        to.setStatusCode(String.valueOf(responseStatusCode.value()));
        if (responseCookies != null && responseCookies.size() > 0) {
            to.setCookies(responseCookies.toString());
        }
        if (StringUtils.isNotBlank(bodyString)) {
            to.setBody(bodyString);
        }
        to.setCreateTimestamp(LocalDateTimeUtils.getMillis(responseTime));
        rrlPub.addRespLog(to);
    }
}
