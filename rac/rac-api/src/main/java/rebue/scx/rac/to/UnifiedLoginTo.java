package rebue.scx.rac.to;

import lombok.Data;

@Data
public class UnifiedLoginTo {
    /**
     * 登录类型
     * 0：帐号密码登录
     * 1：手机验证码登录
     * 2：微信扫码登录
     * 3：钉钉扫码登录
     */
    private byte   loginType;
    /**
     * 应用ID
     */
    private String appId;

    /**
     * 登录名/邮箱/手机号
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 手机验证码登录手机号
     */
    private String phoneNumber;
    /**
     * 验证码
     */
    private String code;

}
