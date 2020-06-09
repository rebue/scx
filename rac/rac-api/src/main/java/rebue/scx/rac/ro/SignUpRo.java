package rebue.scx.rac.ro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import rebue.robotech.ro.IdRo;

/**
 * 用户注册返回的结果
 */
@Schema(description = "通过用户名称注册要传递的参数")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SignUpRo extends IdRo<Long> {

    /**
     * 签名(成功后可将签名放入Cookie中)
     */
    private String sign;

    /**
     * 签名过期时间(成功后可将签名过期时间放入Cookie中)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expirationTime;

}
