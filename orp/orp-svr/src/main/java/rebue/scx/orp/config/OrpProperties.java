package rebue.scx.orp.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import rebue.scx.orp.core.mo.ClientMo;

@Data
@ConfigurationProperties(prefix = "orp")
public class OrpProperties {
    /**
     * 缓存state超时(默认5分钟)
     */
    private Duration                        stateCacheExpiration = Duration.ofMinutes(5L);

    /**
     * 策略集合
     */
    private Map<String, StrategyProperties> strategies           = new HashMap<>();

    @Data
    public static class StrategyProperties {
        /**
         * 是否检查State参数
         */
        private Boolean        isCheckState;

        /**
         * 应用集合
         */
        private List<ClientMo> clients;
    }
}
