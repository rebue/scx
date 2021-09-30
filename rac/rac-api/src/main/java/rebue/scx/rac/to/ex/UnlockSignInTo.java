package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 登录密码解锁可能要传递的参数
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UnlockSignInTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "ID不能为空")
    private Long              id;
    /**
     * 被锁定的账户ID
     */
    @NotNull(message = "被锁定的账户ID不能为空")
    private Long              accountId;

    /**
     * 解锁原因
     */
    @NotNull(message = "解锁原因不能为空")
    private String            unlockReason;
    /**
     * 操作员的账户ID
     */
    private Long              opAccountId;
    /**
     * 代理操作员的账户ID
     */
    private Long              agentAccountId;

}