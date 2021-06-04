package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 权限分组
 *
 * @mbg.removedMember seqNo,isEnabled
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermGroupAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 权限分组名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "权限分组名称不能为空")
    @Length(max = 20, message = "权限分组名称的长度不能大于20")
    private String            name;

    /**
     * 权限分组备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "权限分组备注的长度不能大于50")
    private String            remark;
}
