package rebue.scx.rac.jo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单
 * 
 * The persistent class for the RAC_MENU database table.
 * 
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_MENU")
@Getter
@Setter
@ToString
public class RacMenuJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 菜单编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "CODE", nullable = false, length = 20)
    private String            code;

    /**
     * 菜单名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 20)
    private String            name;

    /**
     * 标题(点击菜单后显示在内容页面的标题)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "TITLE", nullable = true, length = 30)
    private String            title;

    /**
     * 路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "PATH", nullable = false, length = 20)
    private String            path;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_ENABLED", nullable = false, length = 1)
    private Boolean           isEnabled;

    /**
     * 菜单图标
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "ICON", nullable = true, length = 20)
    private String            icon;

    /**
     * 菜单备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "REMARK", nullable = true, length = 50)
    private String            remark;

    /**
     * 系统
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "SYS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacSysJo          sys;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RacMenuJo other = (RacMenuJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
