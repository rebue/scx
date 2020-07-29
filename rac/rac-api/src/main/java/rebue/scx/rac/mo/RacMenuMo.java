package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 菜单信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacMenuMo implements Serializable, Mo<String> {

    /**
     * 菜单ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = ModifyGroup.class, message = "菜单ID不能为空")
    @Length(max = 32, message = "菜单ID的长度不能大于32")
    private String id;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "系统ID不能为空")
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String sysId;

    /**
     * 菜单编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "菜单编码不能为空")
    @Length(max = 20, message = "菜单编码的长度不能大于20")
    private String code;

    /**
     * 菜单名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称的长度不能大于20")
    private String name;

    /**
     * 标题(点击菜单后显示在内容页面的标题)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "标题的长度不能大于30")
    private String title;

    /**
     * 路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "路径不能为空")
    @Length(max = 20, message = "路径的长度不能大于20")
    private String path;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean isEnabled;

    /**
     * 菜单图标
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "菜单图标的长度不能大于20")
    private String icon;

    /**
     * 菜单备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "菜单备注的长度不能大于50")
    private String remark;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacSysMo sys;

    /**
     * 菜单ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     * 菜单ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     * 菜单编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getCode() {
        return code;
    }

    /**
     * 菜单编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 菜单名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 标题(点击菜单后显示在内容页面的标题)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题(点击菜单后显示在内容页面的标题)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPath() {
        return path;
    }

    /**
     * 路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPath(String path) {
        this.path = path;
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
     * 菜单图标
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 菜单图标
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 菜单备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 菜单备注
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
        RacMenuMo other = (RacMenuMo) that;
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
        return "String";
    }
}
