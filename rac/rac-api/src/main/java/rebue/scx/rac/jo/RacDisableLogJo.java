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
 * 账户启/禁用日志
 *
 * The persistent class for the RAC_DISABLE_LOG database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_DISABLE_LOG")
@Getter
@Setter
@ToString
public class RacDisableLogJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 日志ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;
    /**
    * 禁用原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "DISABLE_REASON", nullable = false, length = 100)
    private String            disableReason;
    /**
    * 禁用时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "DISABLE_DATETIME", nullable = false, length = 19)
    private LocalDateTime     disableDatetime;
    /**
    * 启用原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = true)
    @Column(name = "ENABLE_REASON", nullable = true, length = 100)
    private String            enableReason;
    /**
    * 启用时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = true)
    @Column(name = "ENABLE_DATETIME", nullable = true, length = 19)
    private LocalDateTime     enableDatetime;

    /**
    * 禁用账户
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacAccountJo      account;
    /**
    * 代理禁用操作员
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "DISABLE_OP_AGENT_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacAccountJo      disableOpAgent;
    /**
    * 领域
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "REALM_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacRealmJo        realm;
    /**
    * 禁用操作员
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "DISABLE_OP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacAccountJo      disableOp;
    /**
    * 代理启用操作员
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "ENABLE_OP_AGENT_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacAccountJo      enableOpAgent;
    /**
    * 启用操作员
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @JoinColumn(name = "ENABLE_OP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacAccountJo      enableOp;

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
        RacDisableLogJo other = (RacDisableLogJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

}