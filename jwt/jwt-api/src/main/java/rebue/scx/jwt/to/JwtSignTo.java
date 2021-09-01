package rebue.scx.jwt.to;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 签名中储存的签名信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class JwtSignTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotBlank(message = "账户ID不能为空")
    @NotNull
    private String accountId;

    /**
     * appId
     */
    private String appId;

    /**
     * 账户的附加信息
     */
    private Map<String, Object> addition;

    public JwtSignTo(String accountId, String appId)
    {
        this.accountId = accountId;
        this.appId = appId;
    }

}
