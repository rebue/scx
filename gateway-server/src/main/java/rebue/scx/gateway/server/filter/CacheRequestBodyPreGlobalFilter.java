package rebue.scx.gateway.server.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
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
import rebue.scx.gateway.server.co.GatewayServerCo;
import rebue.scx.gateway.server.config.IdWorkerProperties;
import rebue.scx.gateway.server.config.IdWorkerProperties.Svc;
import rebue.scx.gateway.server.pub.RrlPub;
import rebue.scx.rrl.to.RrlReqLogAddTo;
import rebue.wheel.core.LocalDateTimeUtils;
import rebue.wheel.core.idworker.IdWorker3;

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

    @Resource
    private RrlPub                   rrlPub;

    @Resource
    private CuratorFramework         _zkClient;
    @Resource
    private IdWorkerProperties       _idWorkerProperties;
    /**
     * ID生成器
     */
    protected IdWorker3              _idWorker;

    @Resource
    private JsonParser               jsonParser;

    @Resource
    private ObjectMapper             objectMapper;

    private static DateTimeFormatter _dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @PostConstruct
    public void init() throws Exception {
        final String zkNodePath      = "/idworker/rebue.scx.gateway/CacheRequestBodyPreGlobalFilter";
        final String reduceClassName = "CacheRequestBodyPreGlobalFilter";
        // 从配置中读取节点ID的二进制的位数
        int          nodeIdBits      = _idWorkerProperties.getNodeIdBits();
        final Svc    svcProperties   = _idWorkerProperties.getSvces().get(reduceClassName);
        if (svcProperties != null && svcProperties.getNodeIdBits() != null) {
            nodeIdBits = svcProperties.getNodeIdBits();
        }

        Integer nodeId;
        LOOP: while (true) {
            final String zkNodeFullName   = _zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(zkNodePath + "/id_");
            final String zkNodeSimpleName = zkNodeFullName.substring(zkNodeFullName.lastIndexOf("/") + 1);
            nodeId = getNodeId(zkNodeFullName, nodeIdBits);
            final List<String> zkNodes = _zkClient.getChildren().forPath(zkNodePath);
            for (final String zkNodeSimpleNameTemp : zkNodes) {
                if (zkNodeSimpleNameTemp.equals(zkNodeSimpleName)) {
                    continue;
                }
                final Integer nodeIdTemp = getNodeId(zkNodeSimpleNameTemp, nodeIdBits);
                if (nodeIdTemp.equals(nodeId)) {
                    _zkClient.delete().forPath(zkNodeFullName);
                    continue LOOP;
                }
            }
            break;
        }
        _idWorker = new IdWorker3(nodeId, nodeIdBits);
    }

    private Integer getNodeId(final String path, final int nodeIdBits) {
        return Integer.valueOf(StringUtils.right(path, 10)) % (2 << nodeIdBits - 1);
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
        // 缓存请求开始时间戳
        exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY_STRING, System.currentTimeMillis());

        // 获取请求信息
        // 会话ID
        final Long                              sessionId         = _idWorker.getId();
        // 获取请求时间
        final LocalDateTime                     requestTime       = LocalDateTime.now();
        final String                            requestTimeString = _dateTimeFormatter.format(requestTime);
        final ServerHttpRequest                 request           = exchange.getRequest();
        final HttpMethod                        requestMethod     = request.getMethod();
        final URI                               requestUri        = request.getURI();
        final String                            requestScheme     = requestUri.getScheme();
        final String                            requestHost       = requestUri.getHost();
        final int                               requestPort       = requestUri.getPort();
        final String                            requestPath       = requestUri.getPath();
        final HttpHeaders                       requestHeaders    = request.getHeaders();
        final MediaType                         contentType       = requestHeaders.getContentType();
        final MultiValueMap<String, HttpCookie> requestCookies    = request.getCookies();
        final MultiValueMap<String, String>     queryParams       = request.getQueryParams();

        // 缓存请求ID
        exchange.getAttributes().put(CachedKeyCo.SESSION_ID, sessionId);
        // 缓存请求时间戳
        exchange.getAttributes().put(CachedKeyCo.REQUEST_TIME, requestTime);

        // 如果是文件上传，不要读取Body
        if (contentType != null && "multipart".equals(contentType.getType())) {
            // 记录文件日志
            logFile(sessionId, requestTimeString, requestMethod, requestUri, requestHeaders, contentType, requestCookies, queryParams, null);

            logDb(sessionId, requestTime, requestMethod, requestUri, requestScheme, requestHost, requestPort, requestPath, requestHeaders, contentType, requestCookies,
                queryParams, null);
            return chain.filter(exchange);
        } else if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)) {
            return chain.filter(exchange);
        }

        return DataBufferUtils.join(request.getBody()).doOnNext(dataBuffer -> {
            if (dataBuffer.readableByteCount() > 0) {
                // 将body读到字符串中
                final String bodyString = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer()).toString();
                if (StringUtils.isBlank(bodyString)) {
                    return;
                }
                // 缓存请求body
                exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY_STRING, bodyString);

                // FIXME 这里只判断了JSON格式的Body，不知道后面会不会碰到其它格式的Body
                if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)
                    || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(contentType)) {
                    final Map<String, Object> bodyParmams = new LinkedHashMap<>(jsonParser.parseMap(bodyString));
                    // 缓存请求Body中的参数
                    exchange.getAttributes().put(CachedKeyCo.REQUEST_BODY_PARAMS, bodyParmams);
                }
            }
        }).doOnTerminate(() -> {
            // 上一步结束，在then之前，记录一下日志
            // 从缓存中获取Body
            final Object body = exchange.getAttributes().get(CachedKeyCo.REQUEST_BODY_STRING);

            // 记录文件日志
            logFile(sessionId, requestTimeString, requestMethod, requestUri, requestHeaders, contentType, requestCookies, queryParams, body);

            logDb(sessionId, requestTime, requestMethod, requestUri, requestScheme, requestHost, requestPort, requestPath, requestHeaders, contentType, requestCookies,
                queryParams, body);
        }).then(chain.filter(exchange));
    }

    /**
     * 记录文件日志
     */
    @SuppressWarnings("deprecation")
    private void logFile(final Long sessionId, final String requestTimeString, final HttpMethod requestMethod, final URI requestUri, final HttpHeaders requestHeaders,
                         final MediaType contentType, final MultiValueMap<String, HttpCookie> requestCookies, final MultiValueMap<String, String> queryParams, final Object body) {
        final StringBuilder sb = new StringBuilder();
        sb.append("接收到新的请求!!!\r\n----------------------- 请求的详情 -----------------------\r\n");
        sb.append("* 会话ID:\r\n*    ");
        sb.append(sessionId);
        sb.append("\r\n* 请求时间:\r\n*    ");
        sb.append(requestTimeString);
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
    private void logDb(final Long sessionId, final LocalDateTime requestTime, final HttpMethod requestMethod, final URI requestUri, final String requestScheme,
                       final String requestHost, final int requestPort, final String requestPath, final HttpHeaders requestHeaders, final MediaType contentType,
                       final MultiValueMap<String, HttpCookie> requestCookies, final MultiValueMap<String, String> queryParams, final Object body) {
        // 记录数据库日志
        // 构造消息对象
        final RrlReqLogAddTo to = new RrlReqLogAddTo();
        to.setEventId(GatewayServerCo.RRL_EVENT_ID);
        to.setSessionId(sessionId);    // XXX 本次请求的会话ID与响应的会话ID相同
        to.setCreateTimestamp(LocalDateTimeUtils.getMillis(requestTime));
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
