package rebue.scx.jwt.ra;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 带有签名的附加内容
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor // 不知道@Data中默认包含的@RequiredArgsConstructor为何没起效
@JsonInclude(Include.NON_NULL)
public class JwtSignRa implements Serializable {
    private static final long serialVersionUID = 1L;

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
