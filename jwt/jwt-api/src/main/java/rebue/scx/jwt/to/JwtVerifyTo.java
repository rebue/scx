package rebue.scx.jwt.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证签名是否正确的传递参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class JwtVerifyTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 要验证的签名
     */
    @NotBlank(message = "要验证的签名不能为空")
    private String            sign;

}
