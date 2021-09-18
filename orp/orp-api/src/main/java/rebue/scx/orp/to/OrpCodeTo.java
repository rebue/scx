package rebue.scx.orp.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权码参数
 * 
 * @author zbz
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrpCodeTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 授权码
     */
    @NotBlank(message = "授权码不能为空")
    private String            code;
    /**
     * 状态
     */
    private String            state;
    /**
     * 响应前端的重定向的地址
     */
    private String            callbackUrl;
}
