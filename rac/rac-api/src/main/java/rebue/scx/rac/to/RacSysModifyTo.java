package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 系统信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacSysModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "系统ID不能为空")
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String id;

    /**
     * 系统名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "系统名称的长度不能大于20")
    private String name;

    /**
     * 系统备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "系统备注的长度不能大于50")
    private String remark;
}
