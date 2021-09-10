package rebue.scx.gateway.server.filter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.scx.sgn.api.ex.SgnVerifyApi;
import rebue.wheel.core.spring.AntPathMatcherUtils;

@Slf4j
@Component
public class SignPreGatewayFilterFactory extends AbstractGatewayFilterFactory<SignPreGatewayFilterFactory.Config> {

    @DubboReference
    private SgnVerifyApi sgnVerifyApi;

    public SignPreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            log.info(StringUtils.rightPad("*** 进入SignPreFilter过滤器 ***", 100));
            try {
                final ServerHttpRequest request = exchange.getRequest();
                final String            method  = request.getMethod().toString();
                final String            path    = request.getPath().toString();
                final String            url     = method + ":" + path;

                log.info("判断是否要过滤此URL-{}", url);
                if (config.getFilterUrls() != null && !config.getFilterUrls().isEmpty()
                    && AntPathMatcherUtils.noneMatch(method, path, config.getFilterUrls())) {
                    log.debug("经判断不过滤此URL");
                    return returnFilter(chain, exchange);
                }
                if (config.getIgnoreUrls() != null && !config.getIgnoreUrls().isEmpty()
                    && AntPathMatcherUtils.anyMatch(method, path, config.getIgnoreUrls())) {
                    log.debug("经判断忽略此URL");
                    return returnFilter(chain, exchange);
                }
                log.info("经判断要过滤此URL");

                // 获取请求参数
                Map<String, Object>                 requestParams;
                final MultiValueMap<String, String> requestQueryParams = request.getQueryParams();
                if (requestQueryParams.size() > 0) {
                    requestParams = new LinkedHashMap<>();
                    requestQueryParams.forEach((key, values) -> {
                        // XXX 签名的请求不允许有数组参数
                        requestParams.put(key, values.get(0));
                    });
                }
                else {
                    requestParams = exchange.getAttribute(CachedKeyCo.REQUEST_BODY_PARAMS);
                }

                if (!ResultDic.SUCCESS.equals(sgnVerifyApi.verify(requestParams).getResult())) {
                    log.warn("认证失败: url-{}, requestParams-{}", url, requestParams);
                    final ServerHttpResponse response = exchange.getResponse();
                    // 401:认证失败，其实应该是UNAUTHENTICATED，Spring代码历史遗留问题
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                log.info("认证成功");

                return returnFilter(chain, exchange);
            } finally {
                log.info(StringUtils.rightPad("~~~ 结束 SignPreFilter 过滤器 ~~~", 100));
            }
        }, 5);
    }

    private Mono<Void> returnFilter(final GatewayFilterChain chain, final ServerWebExchange exchange) {
        return chain.filter(exchange);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(NAME_KEY);
    }

    static class Config {
        /**
         * 要进行过滤的链接
         */
        private List<String> filterUrls;
        /**
         * 要忽略过滤的链接
         */
        private List<String> ignoreUrls;

        public List<String> getFilterUrls() {
            return filterUrls;
        }

        public void setFilterUrls(final List<String> filterUrls) {
            this.filterUrls = filterUrls;
        }

        public List<String> getIgnoreUrls() {
            return ignoreUrls;
        }

        public void setIgnoreUrls(final List<String> ignoreUrls) {
            this.ignoreUrls = ignoreUrls;
        }

    }

}
