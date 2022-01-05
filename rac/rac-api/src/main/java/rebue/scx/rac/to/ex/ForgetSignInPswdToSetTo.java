package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 修改账户登录密码
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ForgetSignInPswdToSetTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @Length(max = 32, message = "新密码的长度不能大于32")
    private String            signInPswd;
    /**
     * 找回方式类型默认为0
     * 0：通过手机号找回
     * 1：通过邮箱找回
     */
    @NotNull(message = "找回方式类型不能为空")
    @PositiveOrZero(message = "登录类型不能为负数")
    private byte              forgetTpye;
    /**
     * 手机验证码
     */
    @NotNull(message = "验证码不能为空")
    private String            code;
    /**
     * 图形验证码
     */
    @NotNull(message = "图形验证码不能为空")
    private String            captchaVerification;

}
