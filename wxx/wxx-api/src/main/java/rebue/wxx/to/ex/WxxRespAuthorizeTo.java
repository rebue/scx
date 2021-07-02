package rebue.wxx.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class WxxRespAuthorizeTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "signature不能为空")
    private String            signature;
    @NotBlank(message = "timestamp不能为空")
    private String            timestamp;
    @NotBlank(message = "nonce不能为空")
    private String            nonce;
    @NotBlank(message = "echostr不能为空")
    private String            echostr;

}
