package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Data;

/**
 * 通过OIDC登录要传递的参数
 */
@Data
@Builder
public class SignInByOidcTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @NotBlank(message = "应用ID不能为空")
    @Length(max = 20, message = "应用ID长度不能超过20")
    private String            appId;

    /**
     * openid
     */
    private String            openId;
    /**
     * unionid
     */
    private String            unionId;

}
