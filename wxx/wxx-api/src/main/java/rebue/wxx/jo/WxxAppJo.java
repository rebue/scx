package rebue.wxx.jo;

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
 * The persistent class for the WXX_APP database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "WXX_APP")
@Getter
@Setter
@ToString
public class WxxAppJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 公众号的appid
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 32)
    private String            id;

    /**
     * APP名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 30)
    private String            name;

    /**
     * 公众号的app secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "APP_SECRET", nullable = false, length = 50)
    private String            appSecret;

    /**
     * 公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "TOKEN", nullable = false, length = 50)
    private String            token;

    /**
     * 微信加解密消息用的key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "ENCODEING_AES_KEY", nullable = true, length = 50)
    private String            encodeingAesKey;

    /**
     * 用户关注后自动回复的文本
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SUBSCRIBE_AUTO_REPLY", nullable = true, length = 100)
    private String            subscribeAutoReply;

    /**
     * 自定义菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "MENU", nullable = true, length = 1500)
    private String            menu;

    /**
     * 登录回调链接
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "LOGIN_CALLBACK_URL", nullable = false, length = 300)
    private String            loginCallbackUrl;

    /**
     * 登录回调方法类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "LOGIN_CALLBACK_METHOD_TYPE", nullable = false, length = 6)
    private String            loginCallbackMethodType;

    /**
     * 登录回调签名密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "LOGIN_CALLBACK_SIGNKEY", nullable = true, length = 64)
    private String            loginCallbackSignkey;

    /**
     * 微信支付完成通知的URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WXPAY_NOTIFY_URL", nullable = true, length = 250)
    private String            wxpayNotifyUrl;

    /**
     * 商户号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "MCH_ID", referencedColumnName = "ID")
    @ManyToOne()
    private WxxMchJo          mch;

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
        WxxAppJo other = (WxxAppJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
