package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 角色信息
 *
 * 数据库表: RAC_ROLE
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "角色信息")
@JsonInclude(Include.NON_NULL)
public class RacRoleMo implements Serializable {

    /**
     *    角色ID
     *
     *    数据库字段: RAC_ROLE.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "角色ID")
    private String id;

    /**
     *    系统ID
     *
     *    数据库字段: RAC_ROLE.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统ID")
    private String sysId;

    /**
     *    角色名称
     *
     *    数据库字段: RAC_ROLE.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     *    首页路径
     *
     *    数据库字段: RAC_ROLE.HOME_PATH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "首页路径")
    private String homePath;

    /**
     *    是否启用
     *
     *    数据库字段: RAC_ROLE.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否启用")
    private Boolean isEnabled;

    /**
     *    顺序号
     *
     *    数据库字段: RAC_ROLE.ORDER_NO
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "顺序号")
    private Byte orderNo;

    /**
     *    角色备注
     *
     *    数据库字段: RAC_ROLE.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "角色备注")
    private String remark;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    角色ID
     *
     *    数据库字段: RAC_ROLE.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     *    角色ID
     *
     *    数据库字段: RAC_ROLE.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *    系统ID
     *
     *    数据库字段: RAC_ROLE.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    系统ID
     *
     *    数据库字段: RAC_ROLE.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    角色名称
     *
     *    数据库字段: RAC_ROLE.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    角色名称
     *
     *    数据库字段: RAC_ROLE.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    首页路径
     *
     *    数据库字段: RAC_ROLE.HOME_PATH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getHomePath() {
        return homePath;
    }

    /**
     *    首页路径
     *
     *    数据库字段: RAC_ROLE.HOME_PATH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    /**
     *    是否启用
     *
     *    数据库字段: RAC_ROLE.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: RAC_ROLE.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    顺序号
     *
     *    数据库字段: RAC_ROLE.ORDER_NO
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getOrderNo() {
        return orderNo;
    }

    /**
     *    顺序号
     *
     *    数据库字段: RAC_ROLE.ORDER_NO
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrderNo(Byte orderNo) {
        this.orderNo = orderNo;
    }

    /**
     *    角色备注
     *
     *    数据库字段: RAC_ROLE.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    角色备注
     *
     *    数据库字段: RAC_ROLE.REMARK
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
        sb.append(", sysId=").append(sysId);
        sb.append(", name=").append(name);
        sb.append(", homePath=").append(homePath);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", orderNo=").append(orderNo);
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
        RacRoleMo other = (RacRoleMo) that;
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
