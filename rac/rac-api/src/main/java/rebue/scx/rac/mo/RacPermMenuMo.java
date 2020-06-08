package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 权限菜单
 *
 * 数据库表: RAC_PERM_MENU
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "权限菜单")
@JsonInclude(Include.NON_NULL)
public class RacPermMenuMo implements Serializable {

    /**
     *    权限菜单ID
     *
     *    数据库字段: RAC_PERM_MENU.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "权限菜单ID")
    private Long id;

    /**
     *    权限ID
     *
     *    数据库字段: RAC_PERM_MENU.PERM_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "权限ID")
    private String permId;

    /**
     *    菜单ID
     *
     *    数据库字段: RAC_PERM_MENU.MENU_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "菜单ID")
    private String menuId;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    权限菜单ID
     *
     *    数据库字段: RAC_PERM_MENU.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    权限菜单ID
     *
     *    数据库字段: RAC_PERM_MENU.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    权限ID
     *
     *    数据库字段: RAC_PERM_MENU.PERM_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPermId() {
        return permId;
    }

    /**
     *    权限ID
     *
     *    数据库字段: RAC_PERM_MENU.PERM_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPermId(String permId) {
        this.permId = permId;
    }

    /**
     *    菜单ID
     *
     *    数据库字段: RAC_PERM_MENU.MENU_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     *    菜单ID
     *
     *    数据库字段: RAC_PERM_MENU.MENU_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
        sb.append(", permId=").append(permId);
        sb.append(", menuId=").append(menuId);
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
        RacPermMenuMo other = (RacPermMenuMo) that;
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
