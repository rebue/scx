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
* 权限命令
*
* @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
*/
@JsonInclude(Include.NON_NULL)
public class RacPermCommandMo implements Serializable, Mo<Long> {
    /**
    * 权限命令的ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = ModifyGroup.class, message = "权限命令的ID不能为空")
    @PositiveOrZero(message = "权限命令的ID不能为负数")
    private Long id;

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(groups = AddGroup.class, message = "权限ID不能为空")
    @PositiveOrZero(message = "权限ID不能为负数")
    private Long permId;

    /**
    * 命令KEY
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(groups = AddGroup.class, message = "命令KEY不能为空")
    @Length(max = 50, message = "命令KEY的长度不能大于50")
    private String commandKey;

    /**
    * 命令备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 200, message = "命令备注的长度不能大于200")
    private String remark;

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    *
    * 权限
    *
    * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
    */
    @Getter
    @Setter
    private RacPermMo perm;

    /**
    * 权限命令的ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getId() {
        return id;
    }

    /**
    * 权限命令的ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getPermId() {
        return permId;
    }

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setPermId(Long permId) {
        this.permId = permId;
    }

    /**
    * 命令KEY
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getCommandKey() {
        return commandKey;
    }

    /**
    * 命令KEY
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setCommandKey(String commandKey) {
        this.commandKey = commandKey;
    }

    /**
    * 命令备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getRemark() {
        return remark;
    }

    /**
    * 命令备注
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
        sb.append(", permId=").append(permId);
        sb.append(", commandKey=").append(commandKey);
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
        RacPermCommandMo other = (RacPermCommandMo) that;
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