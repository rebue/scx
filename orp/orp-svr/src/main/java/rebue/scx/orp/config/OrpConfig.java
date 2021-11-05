package rebue.scx.orp.config;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.google.common.base.CaseFormat;

import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.factory.StrategyFactory;
import rebue.scx.orp.core.mo.ClientMo;
import rebue.scx.orp.core.strategy.Strategy;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.ApacheHttpClientImpl;

@Configuration(proxyBeanMethods = false)
// XXX 启用属性类(也就是注入属性类，如果没有这一行，属性类要另外写注入，如在属性类上加注解@Compenent，或扫描)
@EnableConfigurationProperties(OrpProperties.class)
public class OrpConfig {

    @Bean
    public OrpStrategies getOrpStategies(final OrpProperties orpProperties, final StringRedisTemplate stringRedisTemplate) {

        final HttpClient            httpClient = new ApacheHttpClientImpl();

        final StateCache            stateCache = new StateCache(stringRedisTemplate, orpProperties.getStateCacheExpiration());

        final Map<String, Strategy> strategies = new HashMap<>();
        orpProperties.getStrategies().forEach((strategyName, strategyProperies) -> {
            // 将配置策略的羊肉串格式转换为大驼峰格式，以获取ORP类型
            final OrpTypeDic            orpType   = OrpTypeDic.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, strategyName));
            final StrategyConfig        orpConfig = StrategyConfig.builder().isCheckState(strategyProperies.getIsCheckState())
                    .requestDomainName(strategyProperies.getRequestDomainName())
                    .build();
            final Map<String, ClientMo> clients   = strategyProperies.getClients().stream().collect(Collectors.toMap(ClientMo::getId, item -> item));
            strategies.put(strategyName, StrategyFactory.getStrategy(orpType, clients, orpConfig, stateCache, httpClient, strategyProperies.getExtras()));
        });
        return OrpStrategies.builder().items(strategies).build();
    }

}
