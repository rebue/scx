package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/***
 * 短信消息
 * 
 * @author yuanman
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacSMSTo implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     * 账户ID
     */
    @NotNull(message = "账户ID为空")
    private Long              accountId;
    /**
     * 图形验证码
     */
    @NotNull(message = "图形验证码不能为空")
    private String            captchaVerification;

}
