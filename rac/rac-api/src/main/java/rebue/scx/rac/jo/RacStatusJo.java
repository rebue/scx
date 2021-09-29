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
 * 身份
 *
 * The persistent class for the RAC_STATUS database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_STATUS")
@Getter
@Setter
@ToString
public class RacStatusJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 身份ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;
    /**
    * 身份名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 20)
    private String            name;
    /**
    * 首页URL
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = true)
    @Column(name = "HOME_URL", nullable = true, length = 100)
    private String            homeUrl;
    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "IS_ENABLED", nullable = false, length = 1)
    private Boolean           isEnabled;
    /**
    * 顺序号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "SEQ_NO", nullable = false, length = 3)
    private Byte              seqNo;
    /**
    * 身份备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = true)
    @Column(name = "REMARK", nullable = true, length = 50)
    private String            remark;

    /**
    * 领域
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "REALM_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacRealmJo        realm;

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
        RacStatusJo other = (RacStatusJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

}