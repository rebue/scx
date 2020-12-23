package rebue.scx.gateway.server.filter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.scx.gateway.server.co.CachedKeyCo;
import rebue.scx.sgn.api.ex.SgnVerifyApi;

@Slf4j
@Component
public class SignPreGatewayFilterFactory extends AbstractGatewayFilterFactory<SignPreGatewayFilterFactory.Config> {

    @DubboReference
    private SgnVerifyApi                sgnVerifyApi;

    private final static AntPathMatcher _matcher = new AntPathMatcher();
    static {
        _matcher.setCaseSensitive(false);
    }

    public SignPreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
            log.info("\r\n============================= 运行SignPreFilter过滤器 =============================\r\n");
            try {
                final ServerHttpRequest request    = exchange.getRequest();
                final String            requestUrl = request.getMethod() + ":" + request.getPath();
                // TODO AntPathMatcher是不是线程安全的？
                if (config.getIgnoreUrls().stream().anyMatch(ignoreUrl -> _matcher.match(ignoreUrl, requestUrl))) {
                    log.debug("此URL根据配置被SignPreFilter过滤器忽略");
                    return chain.filter(exchange);
                }

                final Map<String, Object> paramMap = exchange.getAttribute(CachedKeyCo.REQUEST_PARAMS_MAP);
                if (!ResultDic.SUCCESS.equals(sgnVerifyApi.verify(paramMap).getResult())) {
                    log.warn("认证失败: paramMap-{}", paramMap);
                    final ServerHttpResponse response = exchange.getResponse();
                    // 401:认证失败，其实应该是UNAUTHENTICATED，Spring代码历史遗留问题
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                return chain.filter(exchange);
            } finally {
                log.info("\r\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 结束SignPreFilter过滤器 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
            }
        };

    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(NAME_KEY);
    }

    public static class Config {
        private List<String> ignoreUrls;

        public List<String> getIgnoreUrls() {
            return ignoreUrls;
        }

        public void setIgnoreUrls(final List<String> ignoreUrls) {
            this.ignoreUrls = ignoreUrls;
        }

    }

}
