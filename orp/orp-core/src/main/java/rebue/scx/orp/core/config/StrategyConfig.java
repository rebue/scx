package rebue.scx.orp.core.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StrategyConfig {
    /**
     * 是否检查State参数
     */
    private Boolean isCheckState;

}
