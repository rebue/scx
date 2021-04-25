package rebue.scx.gateway.server.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.scx.gateway.server.pub.RrlPub;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.wheel.LocalDateUtils;
import rebue.wheel.idworker.IdWorker3;

/**
 * 缓存请求的Body
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

    protected IdWorker3              _idWorker;
    @Value("${robotech.appid:0}")
    private int                      _appid;

    @Resource
    private RrlPub                   rrlPub;

    @Resource
    private JsonParser               jsonParser;

    @Resource
    private ObjectMapper             objectMapper;

    private static DateTimeFormatter _dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @PostConstruct
    public void init() {
        _idWorker = new IdWorker3(_appid);
    }

    /**
     * 注意我开始使用@Order注解没有起作用，所以以实现Ordered接口的方式设置最高的优先级
     */
    @Override
    public int getOrder() {
        return -3;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        // final StopWatch stopWatch = new StopWatch();
        // stopWatch.start();
        // 缓存请求开始时间戳
        exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY, System.currentTimeMillis());

        // 获取请求信息
        final Long                              requestId        = _idWorker.getId();
        // 获取请求时间
        final long                              requestTimestamp = System.currentTimeMillis();
        final String                            requestTime      = _dateTimeFormatter.format(LocalDateUtils.getDateTimeOfTimestamp(requestTimestamp));
        final ServerHttpRequest                 request          = exchange.getRequest();
        final HttpMethod                        requestMethod    = request.getMethod();
        final URI                               requestUri       = request.getURI();
        final String                            requestScheme    = requestUri.getScheme();
        final String                            requestHost      = requestUri.getHost();
        final int                               requestPort      = requestUri.getPort();
        final String                            requestPath      = requestUri.getPath();
        final HttpHeaders                       requestHeaders   = request.getHeaders();
        final MediaType                         contentType      = requestHeaders.getContentType();
        final MultiValueMap<String, HttpCookie> requestCookies   = request.getCookies();
        final MultiValueMap<String, String>     queryParams      = request.getQueryParams();

        // 缓存请求ID
        exchange.getAttributes().put(CachedKeyCo.REQUEST_ID, requestId);
        // 缓存请求时间戳
        exchange.getAttributes().put(CachedKeyCo.REQUEST_TIMESTAMP, requestTimestamp);
        // 缓存请求查询参数
        exchange.getAttributes().put(CachedKeyCo.REQUEST_QUERY_PARAMS, queryParams);

        return DataBufferUtils.join(request.getBody()).doOnNext(dataBuffer -> {
            if (dataBuffer.readableByteCount() > 0) {
                // 将body读到字符串中
                final String bodyString = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer()).toString();
                if (StringUtils.isBlank(bodyString)) {
                    return;
                }
                // 缓存请求body
                exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY, bodyString);

                // FIXME 这里只判断了JSON格式的Body，不知道后面会不会碰到其它格式的Body
                if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)
                    || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(contentType)) {
                    final Map<String, Object> bodyParmams = new LinkedHashMap<>(jsonParser.parseMap(bodyString));
                    if (bodyParmams != null && !bodyParmams.isEmpty()) {
                        // 缓存请求Body中的参数
                        exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY_PARAMS, bodyParmams);
                    }
                }
            }
        }).doOnTerminate(() -> {
            // 上一步结束，在then之前，记录一下日志
            // 从缓存中获取Body
            final Object body = exchange.getAttributes().get(CachedKeyCo.REQUEST_BODY);

            // 记录文件日志
            logFile(requestId, requestTime, requestMethod, requestUri, requestHeaders, contentType, requestCookies, queryParams, body);

            logDb(requestId, requestTimestamp, requestMethod, requestUri, requestScheme, requestHost, requestPort, requestPath, requestHeaders, contentType, requestCookies,
                queryParams, body);
        }).then(chain.filter(exchange)).doFinally(signalType -> {
            // // 调用所有过滤器完成，又回到本优先级最高的过滤器
            // stopWatch.stop();
            //
            // final ServerHttpResponse response = exchange.getResponse();
            // final HttpStatus responseStatusCode = response.getStatusCode();
            // final HttpHeaders responseHeaders = response.getHeaders();
            //
            // // 文件日志
            // final StringBuilder sb3 = new StringBuilder();
            // sb3.append("结束CacheRequestBodyPreGlobalFilter过滤器!!!\r\n"
            // + "======================= CacheRequestBodyPreGlobalFilter过滤器被调用详情 =======================\r\n"
            // + "* 结束类型:\r\n* ");
            // sb3.append(signalType);
            // sb3.append("\r\n* 响应状态:\r\n* ");
            // sb3.append(responseStatusCode);
            // sb3.append("\r\n* 响应Headers:");
            // responseHeaders.forEach((name, values) -> {
            // values.forEach(value -> {
            // sb3.append("\r\n* ").append(name).append(":").append(value);
            // });
            // });
            // sb3.append("\r\n* 处理耗时:\r\n* ");
            // sb3.append(stopWatch.formatTime());
            // sb3.append(StringUtils.rightPad("\r\n===================================================================================", 100));
            // log.info(sb3.toString());
            //
            // // 数据库日志
            // // 构造消息对象
            // final RrlRespLogAddTo to = new RrlRespLogAddTo();
            // to.setId(requestId); // XXX 不自动生成ID，因为要让本次请求的请求ID等于响应ID
            // to.setHeaders(sjHeaders.toString());
            // to.setStatusCode(String.valueOf(responseStatusCode.value()));
            // rrlPub.addRespLog(to);
        });
    }

    /**
     * 记录文件日志
     */
    @SuppressWarnings("deprecation")
    private void logFile(final Long requestId, final String requestTime, final HttpMethod requestMethod, final URI requestUri, final HttpHeaders requestHeaders,
                         final MediaType contentType, final MultiValueMap<String, HttpCookie> requestCookies, final MultiValueMap<String, String> queryParams, final Object body) {
        final StringBuilder sb = new StringBuilder();
        sb.append("\r\n----------------------- 进入CacheRequestBodyPreGlobalFilter过滤器 -----------------------\r\n");
        sb.append("* 请求ID:\r\n*    ");
        sb.append(requestId);
        sb.append("\r\n* 请求时间:\r\n*    ");
        sb.append(requestTime);
        sb.append("\r\n* 请求链接:\r\n*    ");
        sb.append(requestMethod);
        sb.append(" ");
        sb.append(requestUri);
        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            sb.append("\r\n* 请求的Headers:");
            requestHeaders.forEach((name, values) -> {
                values.forEach(value -> {
                    sb.append("\r\n*    ").append(name).append(": ").append(value);
                });
            });
        }
        if (contentType != null) {
            sb.append("\r\n* 请求的contentType:\r\n*    ");
            sb.append(contentType);
        }
        if (requestCookies != null && !requestCookies.isEmpty()) {
            sb.append("\r\n* 请求的Cookies:");
            requestCookies.forEach((name, values) -> {
                values.forEach(value -> {
                    sb.append("\r\n*    ").append(name).append(": ").append(value);
                });
            });
        }
        if (queryParams != null && !queryParams.isEmpty()) {
            sb.append("\r\n* 请求的QueryParams:");
            queryParams.forEach((key, values) -> {
                values.forEach(value -> {
                    sb.append("\r\n*    ").append(key).append(": ").append(value);
                });
            });
        }
        if (body != null) {
            final String bodyString = body.toString();
            if (StringUtils.isNotBlank(bodyString)) {
                sb.append("\r\n* 请求的Body:\r\n");
                if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)
                    || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(contentType)) {
                    // 格式化JSON
                    String jsonText = null;
                    try {
                        jsonText = objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(objectMapper.readValue(bodyString, Object.class));
                        jsonText = "*    " + jsonText.replaceAll("\n", "\n*    ");
                    } catch (final JsonProcessingException e) {
                        jsonText = "*    JSON格式不正确: " + bodyString;
                    }
                    sb.append(jsonText);
                }
                else {
                    sb.append(bodyString.trim());
                }
            }
        }
        sb.append(StringUtils.rightPad("\r\n------------------------------------------------------------------------------", 100));
        log.info(sb.toString());
    }

    /**
     * 记录数据库日志
     */
    private void logDb(final Long requestId, final long requestTimestamp, final HttpMethod requestMethod, final URI requestUri, final String requestScheme,
                       final String requestHost, final int requestPort, final String requestPath, final HttpHeaders requestHeaders, final MediaType contentType,
                       final MultiValueMap<String, HttpCookie> requestCookies, final MultiValueMap<String, String> queryParams, final Object body) {
        // 记录数据库日志
        // 构造消息对象
        final RrlReqLogAddTo to = new RrlReqLogAddTo();
        to.setId(requestId);    // XXX 不自动生成ID，因为要让本次请求的请求ID等于响应ID
        to.setCreateTimestamp(requestTimestamp);
        to.setMethod(requestMethod.toString());
        to.setScheme(requestScheme);
        to.setHost(requestHost);
        to.setPort(requestPort);
        to.setPath(requestPath);
        to.setUri(requestUri.toString());
        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            to.setHeaders(requestHeaders.toString());
        }
        if (contentType != null) {
            to.setContentType(contentType.toString());
        }
        if (requestCookies != null && !requestCookies.isEmpty()) {
            to.setCookies(requestCookies.toString());
        }
        if (queryParams != null && !queryParams.isEmpty()) {
            to.setQueryParams(queryParams.toString());
        }
        if (body != null) {
            final String bodyString = body.toString();
            if (StringUtils.isNotBlank(bodyString)) {
                to.setBody(bodyString.trim());
            }
        }
        // 发送消息
        rrlPub.addReqLog(to);
    }

}
