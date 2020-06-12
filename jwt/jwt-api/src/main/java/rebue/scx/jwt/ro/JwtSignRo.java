package rebue.scx.jwt.ro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rebue.robotech.ro.Ro;

/**
 * 签名的返回结果
 */
@Schema(description = "签名的返回结果")
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class JwtSignRo extends Ro {
    private static final long serialVersionUID = 1L;

    /**
     * 签名
     */
    @Schema(description = "签名")
    private String            sign;

    /**
     * 超时时间
     */
    @Schema(description = "超时时间")
    private Date              expirationTime;

}
