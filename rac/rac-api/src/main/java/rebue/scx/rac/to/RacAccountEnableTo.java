package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 启用或禁用账户
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountEnableTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

    /**
     * 启用或禁用
     */
    @NotNull(message = "是否启用不能为空")
    private Boolean           enabled;

}
