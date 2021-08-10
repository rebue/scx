package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 权限命令
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermCommandAddTo implements Serializable {

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
     * 命令KEY
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "命令KEY不能为空")
    @Length(max = 50, message = "命令KEY的长度不能大于50")
    private String            commandKey;

    /**
     * 命令备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 200, message = "命令备注的长度不能大于200")
    private String            remark;
}
