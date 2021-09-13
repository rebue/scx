package rebue.scx.orp.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import rebue.scx.orp.core.cache.StateCache;
import rebue.scx.orp.core.config.StrategyConfig;
import rebue.scx.orp.core.dic.OrpTypeDic;
import rebue.scx.orp.core.factory.StrategyFactory;
import rebue.scx.orp.core.strategy.Strategy;
import rebue.wheel.net.httpclient.HttpClient;

@Configuration(proxyBeanMethods = false)
// XXX 启用属性类(也就是注入属性类，如果没有这一行，属性类要另外写注入，如在属性类上加注解@Compenent，或扫描)
@EnableConfigurationProperties(OrpProperties.class)
public class OrpConfig {

    @Bean
    public OrpBeans getOrpBeans(final OrpProperties orpProperties, final StringRedisTemplate stringRedisTemplate,
            final HttpClient httpClient) {

        final Map<String, String> apps = new HashMap<>();

        orpProperties.getApps().forEach((appid, secret) -> {
            apps.put(appid, secret);
        });

        final StateCache            stateCache = new StateCache(stringRedisTemplate, orpProperties.getStateCacheExpiration());

        final Map<String, Strategy> strategies = new HashMap<>();

        orpProperties.getStrategies().forEach((key, strategyProperies) -> {
            final StrategyConfig orpConfig = StrategyConfig.builder().isCheckState(strategyProperies.getIsCheckState()).build();
            strategies.put(key, StrategyFactory.getStrategy(OrpTypeDic.valueOf(key), orpConfig, stateCache, httpClient));

        });

        return OrpBeans.builder().apps(apps).strategies(strategies).build();
    }

}
