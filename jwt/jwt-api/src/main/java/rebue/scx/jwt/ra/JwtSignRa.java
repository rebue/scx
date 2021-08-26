package rebue.scx.jwt.ra;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带有签名的附加内容
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class JwtSignRa implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success = true;

    public static JwtSignRa success(final String sign, final LocalDateTime expirationTime)
    {
        final JwtSignRa ra = new JwtSignRa();
        ra.sign = sign;
        ra.expirationTime = expirationTime;
        return ra;
    }

    public static JwtSignRa error()
    {
        final JwtSignRa ra = new JwtSignRa();
        ra.success = false;
        return ra;
    }

    /**
     * 签名
     */
    @NonNull
    private String sign;

    /**
     * 超时时间
     */
    @NonNull
    private LocalDateTime expirationTime;

}
