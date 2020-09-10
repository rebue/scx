package rebue.scx.jwt.config;

import java.time.Duration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties("jwt")
public class JwtProperties {

    /**
     * JWT签名的密钥。由于采用的是HS512算法，所以密钥的字节长度要大于64Byte
     */
    @NotNull
    private byte[] signKey;

    /**
     * 签发者
     */
    @NotBlank
    private String issuer;

    /**
     * 过期时间间隔(默认是30m)
     */
    private Duration expirationDuration = Duration.ofMinutes(30L);

}
