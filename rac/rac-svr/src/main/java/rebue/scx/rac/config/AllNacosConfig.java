package rebue.scx.rac.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.CaseFormat;

import lombok.Data;
import rebue.scx.rac.dic.NacosTypeDic;
import rebue.scx.rac.factory.NacosStrategyFactory;
import rebue.scx.rac.strategy.nacos.NacosStrategy;

/**
 * 
 * @author yuanman
 *
 */
@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "nacos")
public class AllNacosConfig {
    /**
     * 活动配置文件dev/prod
     */
    private String                               active;
    /**
     * nacos服务IP
     */
    private String                               serverAddr;
    /**
     * 策略集合
     */
    private Map<String, NacosStrategyProperties> serverName = new HashMap<>();

    @Data
    public static class NacosStrategyProperties {
        /**
         * 配置文件格式
         */
        private String fileExtension;
        /**
         * nacos分组名
         */
        private String group;

    }

    @Bean
    public NacosStrategies getOrpStategies(final AllNacosConfig allNacosConfig) {
        // final String serverAddr = allNacosConfig.getServerAddr();
        final Map<String, NacosStrategy> strategies = new HashMap<>();
        allNacosConfig.getServerName().forEach((ServerName, strategyProperies) -> {
            // 将配置策略的羊肉串格式转换为大驼峰格式，以获取ORP类型
            final NacosTypeDic nameType = NacosTypeDic.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, ServerName));
            strategies.put(ServerName, NacosStrategyFactory.getStrategy(nameType, serverAddr, active, strategyProperies));
        });
        return NacosStrategies.builder().items(strategies).build();
    }
}