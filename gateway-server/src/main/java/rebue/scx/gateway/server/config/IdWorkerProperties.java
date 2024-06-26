package rebue.scx.gateway.server.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "idworker")
public class IdWorkerProperties {
    /**
     * 节点ID二进制的位数
     */
    private Integer  nodeIdBits = 5;

    /**
     * 服务列表
     */
    Map<String, Svc> svces      = new LinkedHashMap<>();

    @Data
    public class Svc {
        /**
         * 节点ID二进制的位数
         */
        private Integer nodeIdBits;
    }
}
