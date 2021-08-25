package rebue.scx.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Data
@Validated
@ConfigurationProperties("jwt")
public class JwtProperties {

    /**
     * key-pair文件相对路径
     */
    @NotNull
    private String keyPair;

    /**
     * 签发者
     */
    @NotBlank
    private String issuer;

    /**
     * 过期时间间隔
     */
    private Duration expirationDuration;

}
