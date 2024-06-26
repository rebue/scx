package rebue.scx.oap.jo;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 认证记录
 *
 * The persistent class for the OAP_AUTH_LOG database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "OAP_AUTH_LOG")
@Getter
@Setter
@ToString
public class OapAuthLogJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 19)
    private Long              id;

    /**
     * 是否成功认证：0失败，1成功
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_SUCCESS", nullable = false, length = 1)
    private Boolean           isSuccess;

    /**
     * 操作时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "OP_DATETIME", nullable = false, length = 19)
    private LocalDateTime     opDatetime;

    /**
     * 记录原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "REASON", nullable = true, length = 100)
    private String            reason;

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
        OapAuthLogJo other = (OapAuthLogJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
