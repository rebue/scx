package rebue.scx.gateway.server.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import rebue.scx.gateway.server.properties.MaxFileSizeProperties;
import rebue.scx.gateway.server.properties.MaxFileSizeProperties.Specify;
import rebue.wheel.core.spring.AntPathMatcherUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限制文件上传大小
 *
 * @author zbz
 */
@Slf4j
@Component
public class LimitFileSizePreGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private MaxFileSizeProperties maxFileSizeProperties;

    /**
     * 注意我开始使用@Order注解没有起作用，所以以实现Ordered接口的方式设置最高的优先级
     */
    @Override
    public int getOrder() {
        return 5;
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();

        log.info(StringUtils.rightPad("*** 进入 LimitFileSizePreGlobalFilter 过滤器 ***", 100));
        try {

            final HttpHeaders       headers     = request.getHeaders();
            final MediaType         contentType = headers.getContentType();

            // 如果是文件上传，进行限制文件大小的判断
            if (contentType != null && "multipart".equals(contentType.getType())) {
                final long contentLength = headers.getContentLength();
                if (contentLength != -1) {
                    final String        method    = request.getMethod().toString();
                    final String        path      = request.getPath().toString();
                    final String        url       = method + ":" + path;
                    final List<Specify> specifies = maxFileSizeProperties.getSpecifies();
                    log.info("判断是否有特别指定此URL-{}", url);
                    if (specifies != null && !specifies.isEmpty()) {
                        for (final Specify specify : specifies) {
                            if (AntPathMatcherUtils.match(method, path, specify.getUrl())) {
                                final DataSize limit = specify.getLimit();
                                if (contentLength > limit.toBytes()) {
                                    log.info("上传文件大小超出指定的范围: {}", limit);
                                    final ServerHttpResponse response = exchange.getResponse();
                                    response.setStatusCode(HttpStatus.PAYLOAD_TOO_LARGE);
                                    return response.setComplete();
                                }
                                else {
                                    log.info("上传文件大小未超出指定的范围: {}", limit);
                                    return chain.filter(exchange);
                                }
                            }
                        }
                    }
                    else {
                        final DataSize limit = maxFileSizeProperties.getDefaultLimit();
                        if (contentLength > limit.toBytes()) {
                            log.info("上传文件大小超出限定的范围: {}", limit);
                            final ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.PAYLOAD_TOO_LARGE);
                            return response.setComplete();
                        }
                        else {
                            log.info("上传文件大小未超出限定的范围: {}", limit);
                            return chain.filter(exchange);
                        }
                    }
                }
            }

            return chain.filter(exchange);
        } finally {
            log.info(StringUtils.rightPad("~~~ 结束 LimitFileSizePreGlobalFilter 过滤器 ~~~", 100));
        }
    }

}
