package rebue.scx.cap.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
public class CapEmailVerificationTo extends CapBaseTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String            email;
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
