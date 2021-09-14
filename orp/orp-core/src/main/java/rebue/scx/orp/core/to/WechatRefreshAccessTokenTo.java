package rebue.scx.orp.core.to;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class WechatRefreshAccessTokenTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id：对应各平台的appKey
     */
    private String            clientId;

    /**
     * 授权类型，填refresh_token
     */
    private String            grantType;

    /**
     * 刷新token
     */
    private String            refreshToken;

}
