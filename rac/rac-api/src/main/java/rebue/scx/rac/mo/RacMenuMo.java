package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 菜单信息
 *
 * 数据库表: rac_menu
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "菜单信息")
@JsonInclude(Include.NON_NULL)
public class RacMenuMo implements Serializable {

    /**
     *    菜单ID
     *
     *    数据库字段: rac_menu.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "菜单ID")
    private String id;

    /**
     *    系统ID
     *
     *    数据库字段: rac_menu.sys_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统ID")
    private String sysId;

    /**
     *    菜单编码
     *
     *    数据库字段: rac_menu.code
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "菜单编码")
    private String code;

    /**
     *    菜单名称
     *
     *    数据库字段: rac_menu.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "菜单名称")
    private String name;

    /**
     *    标题(点击菜单后显示在内容页面的标题)
     *
     *    数据库字段: rac_menu.title
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "标题(点击菜单后显示在内容页面的标题)")
    private String title;

    /**
     *    路径
     *
     *    数据库字段: rac_menu.path
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "路径")
    private String path;

    /**
     *    是否启用
     *
     *    数据库字段: rac_menu.is_enabled
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否启用")
    private Boolean isEnabled;

    /**
     *    菜单图标
     *
     *    数据库字段: rac_menu.icon
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     *    菜单备注
     *
     *    数据库字段: rac_menu.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "菜单备注")
    private String remark;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    菜单ID
     *
     *    数据库字段: rac_menu.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     *    菜单ID
     *
     *    数据库字段: rac_menu.id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *    系统ID
     *
     *    数据库字段: rac_menu.sys_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    系统ID
     *
     *    数据库字段: rac_menu.sys_id
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    菜单编码
     *
     *    数据库字段: rac_menu.code
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getCode() {
        return code;
    }

    /**
     *    菜单编码
     *
     *    数据库字段: rac_menu.code
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *    菜单名称
     *
     *    数据库字段: rac_menu.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    菜单名称
     *
     *    数据库字段: rac_menu.name
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    标题(点击菜单后显示在内容页面的标题)
     *
     *    数据库字段: rac_menu.title
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getTitle() {
        return title;
    }

    /**
     *    标题(点击菜单后显示在内容页面的标题)
     *
     *    数据库字段: rac_menu.title
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *    路径
     *
     *    数据库字段: rac_menu.path
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPath() {
        return path;
    }

    /**
     *    路径
     *
     *    数据库字段: rac_menu.path
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *    是否启用
     *
     *    数据库字段: rac_menu.is_enabled
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: rac_menu.is_enabled
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    菜单图标
     *
     *    数据库字段: rac_menu.icon
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIcon() {
        return icon;
    }

    /**
     *    菜单图标
     *
     *    数据库字段: rac_menu.icon
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *    菜单备注
     *
     *    数据库字段: rac_menu.remark
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    菜单备注
     *
     *    数据库字段: rac_menu.remark
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", path=").append(path);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", icon=").append(icon);
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
        RacMenuMo other = (RacMenuMo) that;
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
