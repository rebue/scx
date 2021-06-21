package rebue.wxx.jo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商户信息(微信支付账户信息)
 *
 * The persistent class for the WXX_MCH database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "WXX_MCH")
@Getter
@Setter
@ToString
public class WxxMchJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 商户号(MCH_ID)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 32)
    private String            id;
    /**
    * 商户名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 30)
    private String            name;
    /**
    * API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Basic(optional = false)
    @Column(name = "API_KEY", nullable = false, length = 50)
    private String            apiKey;

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
        WxxMchJo other = (WxxMchJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

}