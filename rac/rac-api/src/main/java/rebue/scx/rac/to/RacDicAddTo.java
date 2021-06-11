package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 字典
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacDicAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @NotBlank(message = "字典ID不能为空")
    @Length(max = 32, message = "字典ID的长度不能大于32")
    private String            id;

    /**
     * 字典名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "字典名称不能为空")
    @Length(max = 200, message = "字典名称的长度不能大于200")
    private String            name;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String            sysId;

    /**
     * 字典备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "字典备注的长度不能大于50")
    private String            remark;

    /**
     * 字典Key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "字典Key不能为空")
    @Length(max = 32, message = "字典Key的长度不能大于32")
    private String            dicKey;
}
