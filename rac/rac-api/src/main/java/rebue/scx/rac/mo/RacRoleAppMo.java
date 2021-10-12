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
* 角色应用
*
* @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
*/
@JsonInclude(Include.NON_NULL)
public class RacRoleAppMo implements Serializable, Mo<Long> {
    /**
    * 角色应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = ModifyGroup.class, message = "角色应用ID不能为空")
    @PositiveOrZero(message = "角色应用ID不能为负数")
    private Long id;

    /**
    * 应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(groups = AddGroup.class, message = "应用ID不能为空")
    @Length(max = 32, message = "应用ID的长度不能大于32")
    private String appId;

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = AddGroup.class, message = "角色ID不能为空")
    @PositiveOrZero(message = "角色ID不能为负数")
    private Long roleId;

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    *
    * 应用
    *
    * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
    */
    @Getter
    @Setter
    private RacAppMo app;

    /**
    *
    * 角色
    *
    * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
    */
    @Getter
    @Setter
    private RacRoleMo role;

    /**
    * 角色应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getId() {
        return id;
    }

    /**
    * 角色应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * 应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getAppId() {
        return appId;
    }

    /**
    * 应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getRoleId() {
        return roleId;
    }

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        sb.append(", appId=").append(appId);
        sb.append(", roleId=").append(roleId);
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
        RacRoleAppMo other = (RacRoleAppMo) that;
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