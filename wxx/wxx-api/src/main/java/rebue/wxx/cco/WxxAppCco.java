package rebue.wxx.cco;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 微信APP的缓存对象
 *
 * @author zbz
 *
 */
@Data
public class WxxAppCco {
    /**
     * app id
     */
    private String        appId;
    /**
     * app secret
     */
    private String        appSecret;
    /**
     * app token
     */
    private String        appToken;

    /**
     * 微信的Access Token
     */
    private String        accessToken;

    /**
     * 下次请求时间
     */
    private LocalDateTime nextRequestTime;
}
