package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 系统信息
 *
 * 数据库表: rac_sys
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "系统信息")
@JsonInclude(Include.NON_NULL)
public class RacSysMo implements Serializable {

    /**
     *    系统ID
     *
     *    数据库字段: rac_sys.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统ID")
    private String id;

    /**
     *    系统名称
     *
     *    数据库字段: rac_sys.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统名称")
    private String name;

    /**
     *    系统备注
     *
     *    数据库字段: rac_sys.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统备注")
    private String remark;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    系统ID
     *
     *    数据库字段: rac_sys.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     *    系统ID
     *
     *    数据库字段: rac_sys.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *    系统名称
     *
     *    数据库字段: rac_sys.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    系统名称
     *
     *    数据库字段: rac_sys.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    系统备注
     *
     *    数据库字段: rac_sys.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    系统备注
     *
     *    数据库字段: rac_sys.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
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
        RacSysMo other = (RacSysMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
