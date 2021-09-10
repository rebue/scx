package rebue.scx.orp.core.ro;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserInfoRo {

    /**
     * 用户ID
     */
    private String       id;

    /**
     * OpenId
     */
    private String       openId;

    /**
     * unionId
     */
    private String       unionId;

    /**
     * AccessToken
     */
    private String       accessToken;

    /**
     * IdToken
     */
    private String       idToken;

    /**
     * 授权范围
     */
    private List<String> scopes;

    /**
     * 用户昵称
     */
    private String       nickname;

    /**
     * 用户头像
     */
    private String       avatar;

    /**
     * 用户邮箱
     */
    private String       email;

    /**
     * 用户备注（各平台中的用户个人介绍）
     */
    private String       remark;

    /**
     * 位置
     */
    private String       location;
}