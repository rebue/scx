package rebue.scx.oap.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OidcGetUserInfoTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 调用凭证
     */
    @NotBlank(message = "accessToken不能为空")
    private String            accessToken;

    /**
     * 普通用户的标识，对当帐号唯一
     */
    @NotBlank(message = "idToken不能为空")
    private String            idToken;

    /**
     * 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为en
     */
    private String            lang;

}
