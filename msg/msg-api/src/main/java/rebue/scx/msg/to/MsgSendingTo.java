package rebue.scx.msg.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/***
 * 消息
 * 
 * @author yuanman
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class MsgSendingTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * App：app
     */
    private String            msgApp;
    /**
     * 微信公众号：wechatoa
     */
    private String            msgWechatoa;

    /**
     * 微信小程序：wechatmp
     */
    private String            msgWechatmp;
    /**
     * 短信：sms
     */
    private String            msgSms;
    /***
     * 邮箱：email
     */
    private String            msgEmail;
    /**
     * 支付宝生活号：alipayLife
     */
    private String            msgAlipayLife;
    /**
     * 钉钉：dingtalk
     */
    private String            msgDingtalk;
    /**
     * 企业微信：wechatwk
     */
    private String            msgWechatwk;

}
