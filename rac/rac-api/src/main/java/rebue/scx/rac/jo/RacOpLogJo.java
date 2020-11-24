package rebue.scx.rac.jo;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * 操作日志
 * 
 * The persistent class for the RAC_OP_LOG database table.
 * 
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_OP_LOG")
@Getter
@Setter
@ToString
public class RacOpLogJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 操作标题
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "OP_TITLE", nullable = false, length = 32)
    private String            opTitle;

    /**
     * 操作详情
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "OP_DETAIL", nullable = false, length = 300)
    private String            opDetail;

    /**
     * 操作时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "OP_DATETIME", nullable = false, length = 19)
    private LocalDateTime     opDatetime;

    /**
     * 系统
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "SYS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacSysJo          sys;

    /**
     * 用户
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacUserJo         user;

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
        RacOpLogJo other = (RacOpLogJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * 操作类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "OP_TYPE", nullable = false, length = 20)
    private String opType;
}
