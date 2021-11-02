package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接受Post请求只有一个参数的实体
 * 
 * @author yuanman
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CheckAccountNumberTo implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
     * 登录名
     */
    @NotNull(message = "登录名不能为空")
    private String            signInName;
    /**
     * 图形验证码
     */
    @NotNull(message = "图形验证码不能为空")
    private String            captchaVerification;

}
