package rebue.scx.rac.to;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 添加权限和角色关系的参数
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacRoleAppAddTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    @PositiveOrZero(message = "角色ID不能为负数")
    private Long              roleId;

    /**
     * 应用ID
     */
    private List<String>      appIds;
}
