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
 * The persistent class for the rac_role_perm database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "rac_role_perm")
@Getter
@Setter
@ToString
public class RacRolePermJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 20)
    private Long id;

    /**
     * 权限
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "perm_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RacPermJo perm;

    /**
     * 角色
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RacRoleJo role;

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
        RacRolePermJo other = (RacRolePermJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
