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
 * 锁定日志
 *
 * The persistent class for the RAC_LOCK_LOG database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_LOCK_LOG")
@Getter
@Setter
@ToString
public class RacLockLogJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 锁定日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 锁定原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "LOCK_REASON", nullable = false, length = 100)
    private String            lockReason;

    /**
     * 锁定时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "LOCK_DATETIME", nullable = false, length = 19)
    private LocalDateTime     lockDatetime;

    /**
     * 解锁原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "UNLOCK_REASON", nullable = true, length = 100)
    private String            unlockReason;

    /**
     * 解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "UNLOCK_DATETIME", nullable = true, length = 19)
    private LocalDateTime     unlockDatetime;

    /**
     * 锁定操作员的账户
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "LOCK_OP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacAccountJo      lockOp;

    /**
     * 锁定账户的账户
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "LOCK_ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacAccountJo      lockAccount;

    /**
     * 解锁操作员的账户
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "UNLOCK_OP_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacAccountJo      unlockOp;

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
        RacLockLogJo other = (RacLockLogJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * 锁定操作的代理人的账户
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "LOCK_OP_AGENT_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacAccountJo lockOpAgent;

    /**
     * 解锁操作的代理人的账户
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "UNLOCK_OP_AGENT_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacAccountJo unlockOpAgent;

    /**
     * 领域
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "REALM_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacRealmJo   realm;
}
