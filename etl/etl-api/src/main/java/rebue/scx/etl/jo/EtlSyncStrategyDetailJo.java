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
 * 同步策略详情
 *
 * The persistent class for the ETL_SYNC_STRATEGY_DETAIL database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "ETL_SYNC_STRATEGY_DETAIL")
@Getter
@Setter
@ToString
public class EtlSyncStrategyDetailJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 来源表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SRC_TABLE_NAME", nullable = true, length = 32)
    private String            srcTableName;

    /**
     * 来源字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "SRC_FIELD_NAME", nullable = false, length = 32)
    private String            srcFieldName;

    /**
     * 来源字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "SRC_FIELD_TYPE", nullable = false, length = 32)
    private String            srcFieldType;

    /**
     * 来源字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SRC_FIELD_LENGTH", nullable = true, length = 3)
    private Byte              srcFieldLength;

    /**
     * 来源字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SRC_FIELD_PRECISION", nullable = true, length = 3)
    private Byte              srcFieldPrecision;

    /**
     * 目的表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "DST_TABLE_NAME", nullable = false, length = 32)
    private String            dstTableName;

    /**
     * 目的字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "DST_FIELD_NAME", nullable = false, length = 32)
    private String            dstFieldName;

    /**
     * 目的字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "DST_FIELD_TYPE", nullable = false, length = 32)
    private String            dstFieldType;

    /**
     * 目的字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "DST_FIELD_LENGTH", nullable = true, length = 3)
    private Byte              dstFieldLength;

    /**
     * 目的字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "DST_FIELD_PRECISION", nullable = true, length = 3)
    private Byte              dstFieldPrecision;

    /**
     * 策略
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "STRATEGY_ID", referencedColumnName = "ID")
    @ManyToOne()
    private EtlSyncStrategyJo strategy;

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
        EtlSyncStrategyDetailJo other = (EtlSyncStrategyDetailJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * 策略详情ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long id;
}
