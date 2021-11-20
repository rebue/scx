package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/***
 * 短信消息校验
 * 
 * @author yuanman
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacSMSVerificationTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID为空")
    private Long              accountId;
    // /**
    // * 手机号
    // */
    // // @NotNull(message = "手机号不能为空")
    // private String phoneNumber;
    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String            code;
    /**
     * 图形验证码
     */
    private String            captchaVerification;

}
