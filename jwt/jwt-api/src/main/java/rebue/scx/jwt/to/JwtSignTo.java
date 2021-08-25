package rebue.scx.jwt.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * 签名中储存的签名信息
 */
@Data
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
    private final String accountId;

    /**
     * appId
     */
    private final String appId;

    /**
     * 账户的附加信息
     */
    private Map<String, Object> addition;

}
