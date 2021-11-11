package rebue.scx.oap.jo;

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
 * 第三方应用IP白名单
 *
 * The persistent class for the OAP_IP_WHITE_LIST database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "OAP_IP_WHITE_LIST")
@Getter
@Setter
@ToString
public class OapIpWhiteListJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 白名单IP
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IP_ADDR", nullable = false, length = 255)
    private String            ipAddr;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "CREATE_TIMESTAMP", nullable = false, length = 20)
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "UPDATE_TIMESTAMP", nullable = false, length = 20)
    private Long              updateTimestamp;

    /**
     * OAP_APP主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "APP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private OapAppJo          app;

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
        OapIpWhiteListJo other = (OapIpWhiteListJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
