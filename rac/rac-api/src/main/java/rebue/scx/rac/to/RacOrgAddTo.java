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
 * 组织
 *
 * @mbg.removedMember treeCode
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacOrgAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组织名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "组织名称不能为空")
    @Length(max = 30, message = "组织名称的长度不能大于30")
    private String            name;

    /**
     * 上级组织ID(根组织填0)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "上级组织ID不能为负数")
    private Long              parentId;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "组织类型不能为空")
    @PositiveOrZero(message = "组织类型不能为负数")
    private Byte              orgType;

    /**
     * 组织全名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 80, message = "组织全名的长度不能大于80")
    private String            fullName;

    /**
     * 组织简介
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 200, message = "组织简介的长度不能大于200")
    private String            introduction;

    /**
     * 组织备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "组织备注的长度不能大于100")
    private String            remark;

    /**
     * 组织属性类型(字典项ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "组织属性类型的长度不能大于32")
    private String            attrType;
}
