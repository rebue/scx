package rebue.scx.oap.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 返回的用户信息
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserInfoMo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     *
     */
    private Long              id;

    /**
     * 登录名称
     *
     */
    private String            signInName;

    /**
     * 登录手机
     *
     */
    private String            signInMobile;

    /**
     * 登录邮箱
     *
     */
    private String            signInEmail;

    /**
     * 登录账户昵称
     *
     */
    private String            signInNickname;

    /**
     * 登录账户头像
     *
     */
    private String            signInAvatar;

    /**
     * 微信的OpenId
     *
     */
    private String            wxOpenId;

    /**
     * 微信的UnionId
     *
     */
    private String            wxUnionId;

    /**
     * 微信昵称
     *
     */
    private String            wxNickname;

    /**
     * 微信头像
     *
     */
    private String            wxAvatar;

    /**
     * QQ的OpenId
     *
     */
    private String            qqOpenId;

    /**
     * QQ的UnionId
     *
     */
    private String            qqUnionId;

    /**
     * QQ昵称
     *
     */
    private String            qqNickname;

    /**
     * QQ头像
     *
     */
    private String            qqAvatar;

    /**
     * 用户ID
     *
     */
    private Long              userId;

    /**
     * 备注
     *
     */
    private String            remark;

    /**
     * 账户编码
     *
     */
    private String            code;

    /**
     * 钉钉的OpenId
     *
     */
    private String            ddOpenId;

    /**
     * 钉钉的UnionId
     *
     */
    private String            ddUnionId;

    /**
     * 钉钉的UserId
     *
     */
    private String            ddUserId;

    /**
     * 钉钉昵称
     */
    private String            ddNickname;

    /**
     * 钉钉头像
     *
     */
    private String            ddAvatar;

    /**
     * 联合账户ID
     *
     */
    private Long              unionId;

}
