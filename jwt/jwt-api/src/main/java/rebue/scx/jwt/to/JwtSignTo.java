package rebue.scx.jwt.to;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 签名中储存的签名信息
 */
@Schema(description = "签名中储存的用户信息")
@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class JwtSignTo implements Serializable {
    private static final long   serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String              userId;

    /**
     * 用户的附加信息
     */
    @Schema(description = "签名中的附加信息")
    private Map<String, Object> addition;

}
