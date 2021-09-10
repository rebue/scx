package rebue.scx.orp.core.to;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Builder
public class WechatGetUserInfoTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 调用凭证
     */
    private String            accessToken;

    /**
     * 普通用户的标识，对当前开发者帐号唯一
     */
    private String            openid;

    /**
     * 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为en
     */
    private String            lang;

}
