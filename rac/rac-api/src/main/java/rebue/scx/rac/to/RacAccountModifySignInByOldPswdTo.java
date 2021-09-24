package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 根据旧登录密码更新新登录密码
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountModifySignInByOldPswdTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

    /**
     * 旧登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    @Length(max = 32, message = "登录密码的长度不能大于32")
    private String            signInPswd;
    /**
     * 新登录密码
     */
    @NotBlank(message = "新登录密码不能为空")
    @Length(max = 32, message = "登录密码的长度不能大于32")
    private String            newSignInPswd;

}
