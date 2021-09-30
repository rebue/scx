package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 重置账户登录密码
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountResetPasswordTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

}
