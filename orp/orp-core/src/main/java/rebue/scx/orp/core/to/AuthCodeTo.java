package rebue.scx.orp.core.to;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Builder
public class AuthCodeTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id：对应各平台的appKey
     */
    private String            clientId;

    /**
     * 客户端密钥
     */
    private String            clientSecret;

    /**
     * code
     */
    private String            code;

    /**
     * state
     */
    private String            state;

    /**
     * 授权类型
     */
    private String            grantType;
}
