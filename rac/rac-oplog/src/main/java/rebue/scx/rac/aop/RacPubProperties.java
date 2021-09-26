package rebue.scx.rac.aop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "rac")
public class RacPubProperties {
    /**
     * 连接地址
     */
    private Long sendTimeout = 5000L;
}
