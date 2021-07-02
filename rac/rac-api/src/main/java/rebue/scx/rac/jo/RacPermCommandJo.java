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
 * 权限命令
 *
 * The persistent class for the RAC_PERM_COMMAND database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_PERM_COMMAND")
@Getter
@Setter
@ToString
public class RacPermCommandJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 权限命令的ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;
    /**
    * 命令KEY
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "COMMAND_KEY", nullable = false, length = 50)
    private String            commandKey;
    /**
    * 命令备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = true)
    @Column(name = "REMARK", nullable = true, length = 200)
    private String            remark;

    /**
    * 权限
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "PERM_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacPermJo         perm;

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
        RacPermCommandJo other = (RacPermCommandJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

}