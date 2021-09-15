package rebue.scx.rac.ra;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rebue.robotech.ra.IdRa;

/**
 * 账户注册或登录返回的结果
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class SignUpOrInRa extends IdRa<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 登录或注册成功后跳转的URL
     */
    private String            redirectUrl;

    /**
     * 签名(成功后可将签名放入Cookie中)
     */
    private String            sign;

    /**
     * 签名过期时间(成功后可将签名过期时间放入Cookie中)
     */
    private LocalDateTime     expirationTime;

    public SignUpOrInRa(final Long id, final String redirectUrl, final String sign, final LocalDateTime expirationTime) {
        super(id);
        this.redirectUrl    = redirectUrl;
        this.sign           = sign;
        this.expirationTime = expirationTime;
    }

}
