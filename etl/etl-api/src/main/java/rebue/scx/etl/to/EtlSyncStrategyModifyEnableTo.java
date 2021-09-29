package rebue.scx.etl.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 同步策略
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class EtlSyncStrategyModifyEnableTo implements Serializable {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 策略ID
     *
     */
    @NotNull(message = "策略ID不能为空")
    @PositiveOrZero(message = "策略ID不能为负数")
    private Long              id;

    /**
     * 是否启用
     *
     */
    @NotNull(message = "策略ID不能为空")
    private Boolean           isEnabled;

}
