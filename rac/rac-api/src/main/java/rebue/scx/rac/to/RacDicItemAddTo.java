package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 字典项
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacDicItemAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "字典ID不能为空")
    @Length(max = 32, message = "字典ID的长度不能大于32")
    private String            dicId;

    /**
     * 字典项名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "字典项名称不能为空")
    @Length(max = 32, message = "字典项名称的长度不能大于32")
    private String            name;

    /**
     * 字典备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "字典备注的长度不能大于50")
    private String            remark;

    /**
     * 树编码(每三位为一级)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "树编码不能为空")
    @Length(max = 50, message = "树编码的长度不能大于50")
    private String            treeCode;
}
