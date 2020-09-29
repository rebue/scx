package rebue.scx.rac.ra;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import rebue.robotech.ra.IdRa;

/**
 * 用户注册或登录返回的结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SignUpOrInRa extends IdRa<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 签名(成功后可将签名放入Cookie中)
     */
    @NonNull
    private String            sign;

    /**
     * 签名过期时间(成功后可将签名过期时间放入Cookie中)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NonNull
    private LocalDateTime     expirationTime;

    public SignUpOrInRa(final Long id, final String sign, final LocalDateTime expirationTime) {
        super(id);
        this.sign           = sign;
        this.expirationTime = expirationTime;
    }

}
