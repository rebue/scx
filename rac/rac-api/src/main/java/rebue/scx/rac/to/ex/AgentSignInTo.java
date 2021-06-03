package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 代理登录要传递的参数
 */
@Data
@JsonInclude(Include.NON_NULL)
public class AgentSignInTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不能为空")
    private Long              accountId;

    /**
     * 系统ID
     */
    @NotBlank(message = "系统ID不能为空")
    @Length(max = 20, message = "系统ID长度不能超过20")
    private String            sysId;

}
