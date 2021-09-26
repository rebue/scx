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
 * 角色
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacRoleMo implements Serializable, Mo<Long> {

    /**
     * 角色ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "角色ID不能为空")
    @PositiveOrZero(message = "角色ID不能为负数")
    private Long              id;

    /**
     * 角色名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "角色名称不能为空")
    @Length(max = 20, message = "角色名称的长度不能大于20")
    private String            name;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean           isEnabled;

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "顺序号不能为空")
    @PositiveOrZero(message = "顺序号不能为负数")
    private Byte              seqNo;

    /**
     * 角色备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "角色备注的长度不能大于50")
    private String            remark;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 角色ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 角色名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getSeqNo() {
        return seqNo;
    }

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSeqNo(Byte seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 角色备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 角色备注
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
        sb.append(", realmId=").append(realmId);
        sb.append(", status=").append(status);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", seqNo=").append(seqNo);
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
        RacRoleMo other = (RacRoleMo) that;
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
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String realmId;

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

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacRealmMo realm;

    /**
     * 角色身份(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "角色身份的长度不能大于32")
    private String     status;

    /**
     * 角色身份(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getStatus() {
        return status;
    }

    /**
     * 角色身份(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
