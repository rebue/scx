package rebue.scx.jwt.to;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 签名中储存的签名信息
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
public class JwtSignTo implements Serializable {
    private static final long   serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotBlank(message = "账户ID不能为空")
    @NotNull
    private String              accountId;

    /**
     * 账户的附加信息
     */
    private Map<String, Object> addition;

    /**
     * FIXME 不知道为何@RequiredArgsConstructor未生效
     */
    public JwtSignTo(String accountId) {
        this.accountId = accountId;
    }

}
