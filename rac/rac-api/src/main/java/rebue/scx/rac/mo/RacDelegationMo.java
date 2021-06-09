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
* 委托
*
* @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
*/
@JsonInclude(Include.NON_NULL)
public class RacDelegationMo implements Serializable, Mo<Long> {
    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = ModifyGroup.class, message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long id;

    /**
    * 委托人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = AddGroup.class, message = "委托人的账户ID不能为空")
    @PositiveOrZero(message = "委托人的账户ID不能为负数")
    private Long principalId;

    /**
    * 代理人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = AddGroup.class, message = "代理人的账户ID不能为空")
    @PositiveOrZero(message = "代理人的账户ID不能为负数")
    private Long agentId;

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    *
    * 代理人的账户
    *
    * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
    */
    @Getter
    @Setter
    private RacAccountMo agent;

    /**
    *
    * 委托人的账户
    *
    * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
    */
    @Getter
    @Setter
    private RacAccountMo principal;

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getId() {
        return id;
    }

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * 委托人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getPrincipalId() {
        return principalId;
    }

    /**
    * 委托人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    /**
    * 代理人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getAgentId() {
        return agentId;
    }

    /**
    * 代理人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
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
        sb.append(", principalId=").append(principalId);
        sb.append(", agentId=").append(agentId);
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
        RacDelegationMo other = (RacDelegationMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        ;
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