package rebue.scx.oap.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
public class LoginDto {
    /**
     * 登录类型默认为0
     * 0：帐号密码登录
     * 1：手机验证码登录
     * 2：邮箱验证码登录
     * 3：微信扫码登录
     * 4：钉钉扫码登录
     */
    @NotNull(message = "登录类型为空")
    @PositiveOrZero(message = "登录类型不能为负数")
    private byte   loginType;

    /**
     * 登录名/邮箱/手机号
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 图形验证码
     */
    private String captchaVerification;

    /**
     * 手机验证码登录的手机号
     */
    private String phoneNumber;
    /**
     * 邮箱验证码登录的邮箱
     */
    private String email;
    /**
     * 消息验证码
     */
    private String code;

}
