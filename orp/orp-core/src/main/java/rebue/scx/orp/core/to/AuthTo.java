package rebue.scx.orp.core.to;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
public class AuthTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id：对应各平台的appKey
     */
    private String            clientId;

    /**
     * 响应的类型
     */
    private String            responseType;

    /**
     * 支持自定义授权平台的 scope 内容
     */
    private List<String>      scopes;

    /**
     * 登录成功后的回调地址
     */
    private String            redirectUri;

    /**
     * state
     */
    private String            state;

}
