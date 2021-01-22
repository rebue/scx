package rebue.scx.rac.mo;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 组织
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacOrgMo implements Serializable, Mo<Long> {

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "组织ID不能为空")
    @PositiveOrZero(message = "组织ID不能为负数")
    private Long              id;

    /**
     * 组织名称(简称)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "组织名称不能为空")
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
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 组织类型(1.集团;2.公司;99.部门)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "组织类型不能为空")
    @PositiveOrZero(message = "组织类型不能为负数")
    private Byte              orgType;

    /**
     * 左值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "左值不能为空")
    @PositiveOrZero(message = "左值不能为负数")
    private Integer           leftValue;

    /**
     * 右值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "右值不能为空")
    @PositiveOrZero(message = "右值不能为负数")
    private Integer           rightValue;

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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacDomainMo       domain;

    /**
     * 上级组织
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacOrgMo          parent;

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 组织名称(简称)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 组织名称(简称)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 上级组织ID(根组织填0)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级组织ID(根组织填0)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    /**
     * 组织类型(1.集团;2.公司;99.部门)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getOrgType() {
        return orgType;
    }

    /**
     * 组织类型(1.集团;2.公司;99.部门)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrgType(Byte orgType) {
        this.orgType = orgType;
    }

    /**
     * 左值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Integer getLeftValue() {
        return leftValue;
    }

    /**
     * 左值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLeftValue(Integer leftValue) {
        this.leftValue = leftValue;
    }

    /**
     * 右值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Integer getRightValue() {
        return rightValue;
    }

    /**
     * 右值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRightValue(Integer rightValue) {
        this.rightValue = rightValue;
    }

    /**
     * 组织全名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 组织全名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 组织简介
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 组织简介
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 组织备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 组织备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", domainId=").append(domainId);
        sb.append(", orgType=").append(orgType);
        sb.append(", leftValue=").append(leftValue);
        sb.append(", rightValue=").append(rightValue);
        sb.append(", fullName=").append(fullName);
        sb.append(", introduction=").append(introduction);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RacOrgMo other = (RacOrgMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * 获取ID的类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String getIdType() {
        return "Long";
    }
}
