package rebue.wxx.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * APP
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class WxxAppMo implements Serializable, Mo<String> {

    /**
     * 公众号的appid
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = ModifyGroup.class, message = "公众号的appid不能为空")
    @Length(max = 32, message = "公众号的appid的长度不能大于32")
    private String            id;

    /**
     * APP名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "APP名称不能为空")
    @Length(max = 30, message = "APP名称的长度不能大于30")
    private String            name;

    /**
     * 商户号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "商户号的长度不能大于32")
    private String            mchId;

    /**
     * 公众号的app secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "公众号的app不能为空")
    @Length(max = 50, message = "公众号的app的长度不能大于50")
    private String            appSecret;

    /**
     * 公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "公众号的token不能为空")
    @Length(max = 50, message = "公众号的token的长度不能大于50")
    private String            token;

    /**
     * 微信加解密消息用的key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "微信加解密消息用的key的长度不能大于50")
    private String            encodeingAesKey;

    /**
     * 用户关注后自动回复的文本
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "用户关注后自动回复的文本的长度不能大于100")
    private String            subscribeAutoReply;

    /**
     * 自定义菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 1500, message = "自定义菜单的长度不能大于1500")
    private String            menu;

    /**
     * 登录回调链接
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "登录回调链接的长度不能大于300")
    private String            loginCallbackUrl;

    /**
     * 登录回调方法类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "登录回调方法类型的长度不能大于6")
    private String            loginCallbackMethodType;

    /**
     * 登录回调签名密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "登录回调签名密钥的长度不能大于64")
    private String            loginCallbackSignkey;

    /**
     * 微信支付完成通知的URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 250, message = "微信支付完成通知的URL的长度不能大于250")
    private String            wxpayNotifyUrl;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private WxxMchMo          mch;

    /**
     * 公众号的appid
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     * 公众号的appid
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * APP名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * APP名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商户号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * 商户号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    /**
     * 公众号的app secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * 公众号的app secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * 公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getToken() {
        return token;
    }

    /**
     * 公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 微信加解密消息用的key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEncodeingAesKey() {
        return encodeingAesKey;
    }

    /**
     * 微信加解密消息用的key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEncodeingAesKey(String encodeingAesKey) {
        this.encodeingAesKey = encodeingAesKey;
    }

    /**
     * 用户关注后自动回复的文本
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSubscribeAutoReply() {
        return subscribeAutoReply;
    }

    /**
     * 用户关注后自动回复的文本
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSubscribeAutoReply(String subscribeAutoReply) {
        this.subscribeAutoReply = subscribeAutoReply;
    }

    /**
     * 自定义菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMenu() {
        return menu;
    }

    /**
     * 自定义菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMenu(String menu) {
        this.menu = menu;
    }

    /**
     * 登录回调链接
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLoginCallbackUrl() {
        return loginCallbackUrl;
    }

    /**
     * 登录回调链接
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginCallbackUrl(String loginCallbackUrl) {
        this.loginCallbackUrl = loginCallbackUrl;
    }

    /**
     * 登录回调方法类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLoginCallbackMethodType() {
        return loginCallbackMethodType;
    }

    /**
     * 登录回调方法类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginCallbackMethodType(String loginCallbackMethodType) {
        this.loginCallbackMethodType = loginCallbackMethodType;
    }

    /**
     * 登录回调签名密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLoginCallbackSignkey() {
        return loginCallbackSignkey;
    }

    /**
     * 登录回调签名密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginCallbackSignkey(String loginCallbackSignkey) {
        this.loginCallbackSignkey = loginCallbackSignkey;
    }

    /**
     * 微信支付完成通知的URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxpayNotifyUrl() {
        return wxpayNotifyUrl;
    }

    /**
     * 微信支付完成通知的URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxpayNotifyUrl(String wxpayNotifyUrl) {
        this.wxpayNotifyUrl = wxpayNotifyUrl;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", mchId=").append(mchId);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", token=").append(token);
        sb.append(", encodeingAesKey=").append(encodeingAesKey);
        sb.append(", subscribeAutoReply=").append(subscribeAutoReply);
        sb.append(", menu=").append(menu);
        sb.append(", loginCallbackUrl=").append(loginCallbackUrl);
        sb.append(", loginCallbackMethodType=").append(loginCallbackMethodType);
        sb.append(", loginCallbackSignkey=").append(loginCallbackSignkey);
        sb.append(", wxpayNotifyUrl=").append(wxpayNotifyUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WxxAppMo other = (WxxAppMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * 获取ID的类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String getIdType() {
        return "String";
    }
}
