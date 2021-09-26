package rebue.scx.etl.jo;

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
 * 同步策略
 *
 * The persistent class for the ETL_SYNC_STRATEGY database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "ETL_SYNC_STRATEGY")
@Getter
@Setter
@ToString
public class EtlSyncStrategyJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 策略名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 32)
    private String            name;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_ENABLED", nullable = false, length = 1)
    private Boolean           isEnabled;

    /**
     * 来源名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "SRC_NAME", nullable = false, length = 32)
    private String            srcName;

    /**
     * 目的名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "DST_NAME", nullable = false, length = 32)
    private String            dstName;

    /**
     * 策略备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "REMARK", nullable = true, length = 32)
    private String            remark;

    /**
     * 目的的连接器
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "DST_CONN_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private EtlConnJo         dstConn;

    /**
     * 来源的连接器
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "SRC_CONN_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private EtlConnJo         srcConn;

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
        EtlSyncStrategyJo other = (EtlSyncStrategyJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
