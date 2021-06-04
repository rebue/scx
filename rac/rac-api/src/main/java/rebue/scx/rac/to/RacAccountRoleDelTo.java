package rebue.scx.rac.to;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountRoleDelTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     *
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              accountId;

    /**
     * 角色ID
     *
     */
    @NotNull(message = "角色ID不能为空")
    @PositiveOrZero(message = "角色ID不能为负数")
    private List<Long>        roleIds;
}
