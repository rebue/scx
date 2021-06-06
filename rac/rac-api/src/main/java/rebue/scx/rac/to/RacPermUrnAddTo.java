package rebue.scx.rac.to;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 权限URN
 *
 * @mbg.removedMember urn
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermUrnAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "权限ID不能为空")
    @PositiveOrZero(message = "权限ID不能为负数")
    private Long              permId;

    /**
     * URN
     */
    @NotNull(message = "URN不能为空")
    @Size(max = 100, message = "URN的长度不能大于100")
    private List<String>      urn;
}
