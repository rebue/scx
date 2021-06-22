package rebue.wxx.to;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class WxxAppDelTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * APP名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
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
    @Length(max = 50, message = "公众号的app的长度不能大于50")
    private String            appSecret;

    /**
     * 公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
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
}
