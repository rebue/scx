package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 权限信息
 *
 * 数据库表: rac_perm
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "权限信息")
@JsonInclude(Include.NON_NULL)
public class RacPermMo implements Serializable {

    /**
     *    权限ID
     *
     *    数据库字段: rac_perm.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "权限ID")
    private String id;

    /**
     *    权限分组ID
     *
     *    数据库字段: rac_perm.group_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "权限分组ID")
    private String groupId;

    /**
     *    系统ID
     *
     *    数据库字段: rac_perm.sys_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统ID")
    private String sysId;

    /**
     *    权限名称
     *
     *    数据库字段: rac_perm.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "权限名称")
    private String name;

    /**
     *    是否鉴权(不鉴权意味着放开访问权限)
     *
     *    数据库字段: rac_perm.is_authorize
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否鉴权(不鉴权意味着放开访问权限)")
    private Boolean isAuthorize;

    /**
     *    是否启用
     *
     *    数据库字段: rac_perm.is_enabled
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否启用")
    private Boolean isEnabled;

    /**
     *    顺序号
     *
     *    数据库字段: rac_perm.order_no
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "顺序号")
    private Byte orderNo;

    /**
     *    权限备注
     *
     *    数据库字段: rac_perm.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "权限备注")
    private String remark;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    权限ID
     *
     *    数据库字段: rac_perm.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     *    权限ID
     *
     *    数据库字段: rac_perm.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *    权限分组ID
     *
     *    数据库字段: rac_perm.group_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     *    权限分组ID
     *
     *    数据库字段: rac_perm.group_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     *    系统ID
     *
     *    数据库字段: rac_perm.sys_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    系统ID
     *
     *    数据库字段: rac_perm.sys_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    权限名称
     *
     *    数据库字段: rac_perm.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    权限名称
     *
     *    数据库字段: rac_perm.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    是否鉴权(不鉴权意味着放开访问权限)
     *
     *    数据库字段: rac_perm.is_authorize
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsAuthorize() {
        return isAuthorize;
    }

    /**
     *    是否鉴权(不鉴权意味着放开访问权限)
     *
     *    数据库字段: rac_perm.is_authorize
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsAuthorize(Boolean isAuthorize) {
        this.isAuthorize = isAuthorize;
    }

    /**
     *    是否启用
     *
     *    数据库字段: rac_perm.is_enabled
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: rac_perm.is_enabled
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    顺序号
     *
     *    数据库字段: rac_perm.order_no
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getOrderNo() {
        return orderNo;
    }

    /**
     *    顺序号
     *
     *    数据库字段: rac_perm.order_no
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrderNo(Byte orderNo) {
        this.orderNo = orderNo;
    }

    /**
     *    权限备注
     *
     *    数据库字段: rac_perm.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    权限备注
     *
     *    数据库字段: rac_perm.remark
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
        sb.append(", groupId=").append(groupId);
        sb.append(", sysId=").append(sysId);
        sb.append(", name=").append(name);
        sb.append(", isAuthorize=").append(isAuthorize);
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
        RacPermMo other = (RacPermMo) that;
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
