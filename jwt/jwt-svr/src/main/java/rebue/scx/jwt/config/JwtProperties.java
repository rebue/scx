package rebue.scx.jwt.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Validated
@ConfigurationProperties("jwt")
public class JwtProperties {

    /**
     * key-pair文件相对路径
     */
    @NotNull
    @Getter
    private String keyPair;

    /**
     * 签发者
     */
    @NotBlank
    @Getter
    private String issuer;

    /**
     * 过期时间间隔
     */
    private Duration expirationDuration;

    private long _exp;

    @PostConstruct
    private void init()
    {
        _exp = expirationDuration.toMillis();
    }

    public long exp()
    {
        return _exp;
    }

}
