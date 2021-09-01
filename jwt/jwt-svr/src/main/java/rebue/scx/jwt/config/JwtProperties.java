package rebue.scx.jwt.config;

import java.time.Duration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

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
