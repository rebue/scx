package rebue.scx.rac.mo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 组织账户
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacOrgAccountMo implements Serializable, Mo<Long> {

    /**
     * 组织账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "组织账户ID不能为空")
    @PositiveOrZero(message = "组织账户ID不能为负数")
    private Long              id;

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "组织ID不能为空")
    @PositiveOrZero(message = "组织ID不能为负数")
    private Long              orgId;

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              accountId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组织
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacOrgMo          org;

    /**
     * 账户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      account;

    /**
     * 组织账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 组织账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
        sb.append(", orgId=").append(orgId);
        sb.append(", accountId=").append(accountId);
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
        RacOrgAccountMo other = (RacOrgAccountMo) that;
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
