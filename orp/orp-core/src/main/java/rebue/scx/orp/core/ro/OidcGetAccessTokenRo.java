package rebue.scx.orp.core.ro;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nimbusds.jwt.JWT;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OidcGetAccessTokenRo {
    /**
     * jwt签名
     */
    private JWT     idToken;
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
     * 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段
     */
    private String  unionid;

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
