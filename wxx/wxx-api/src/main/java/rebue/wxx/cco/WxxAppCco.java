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
     * 公众号的appid
     */
    private String        appId;

    /**
     * 公众号的app secret
     */
    private String        appSecret;

    /**
     * 微信的Access Token
     */
    private String        accessToken;

    /**
     * 下次请求时间
     */
    private LocalDateTime nextRequestTime;
}
