package rebue.scx.orp.core.ro;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WechatGetAccessTokenRo {
    /**
     * 接口调用凭证
     */
    private String  accessToken;

    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expiresIn;

    /**
     * 用户刷新access_token
     */
    private String  refreshToken;

    /**
     * 授权用户唯一标识
     */
    private String  openid;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String  scope;

    /**
     * 错误的编码
     */
    private Long    errcode;

    /**
     * 错误的消息
     */
    private String  errmsg;
}
