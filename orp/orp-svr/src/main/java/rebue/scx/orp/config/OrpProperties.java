package rebue.scx.orp.config;

import java.time.Duration;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "orp")
public class OrpProperties {
    /**
     * 缓存state超时(默认5分钟)
     */
    private final Duration                  stateCacheExpiration = Duration.ofMinutes(5L);

    /**
     * 应用集合
     */
    private Map<String, String>             apps;

    /**
     * 策略集合
     */
    private Map<String, StrategyProperties> strategies;

    @Data
    class StrategyProperties {
        /**
         * 是否检查State参数
         */
        private Boolean isCheckState;
    }
}
