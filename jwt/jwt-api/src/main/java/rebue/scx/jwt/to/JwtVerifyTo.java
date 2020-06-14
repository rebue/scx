package rebue.scx.jwt.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 验证签名是否正确的传递参数
 */
@Schema(description = "验证签名是否正确的传递参数")
@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class JwtVerifyTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String            userId;

    /**
     * 要验证的签名
     */
    @Schema(description = "要验证的签名")
    @NotBlank(message = "要验证的签名不能为空")
    private String            sign;

}
