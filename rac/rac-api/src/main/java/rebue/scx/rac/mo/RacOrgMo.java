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
     * 关键字
     */
    @Getter
    @Setter
    @Length(max = 256, message = "搜索关键字不能超过20位数")
    private String            keywords;

    /**
     * 组织名称
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
     * 组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "组织类型不能为空")
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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

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
     * 组织名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 组织名称
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
     * 组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getOrgType() {
        return orgType;
    }

    /**
     * 组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrgType(Byte orgType) {
        this.orgType = orgType;
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
        sb.append(", realmId=").append(realmId);
        sb.append(", orgType=").append(orgType);
        sb.append(", treeCode=").append(treeCode);
        sb.append(", fullName=").append(fullName);
        sb.append(", introduction=").append(introduction);
        sb.append(", remark=").append(remark);
        sb.append(", attrType=").append(attrType);
        sb.append(", addr=").append(addr);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", contactWay=").append(contactWay);
        sb.append(", email=").append(email);
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

    /**
     * 树编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "树编码不能为空")
    @Length(max = 50, message = "树编码的长度不能大于50")
    private String treeCode;

    /**
     * 树编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getTreeCode() {
        return treeCode;
    }

    /**
     * 树编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    /**
     * 组织属性类型(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "组织属性类型的长度不能大于32")
    private String attrType;

    /**
     * 组织属性类型(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * 组织属性类型(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    /**
     * 地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 500, message = "地址的长度不能大于500")
    private String addr;

    /**
     * 联系人
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "联系人的长度不能大于30")
    private String contactPerson;

    /**
     * 联系方式
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "联系方式的长度不能大于30")
    private String contactWay;

    /**
     * 邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "邮箱的长度不能大于50")
    private String email;

    /**
     * 地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 联系人
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * 联系人
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * 联系方式
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getContactWay() {
        return contactWay;
    }

    /**
     * 联系方式
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    /**
     * 邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String     realmId;

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacRealmMo realm;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRealmId() {
        return realmId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }
}
