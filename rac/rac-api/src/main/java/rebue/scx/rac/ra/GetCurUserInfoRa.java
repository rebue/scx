package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前用户信息
 *
 */
@Data
@NoArgsConstructor
// @RequiredArgsConstructor // 不知道@Data中默认包含的@RequiredArgsConstructor为何没起效
@JsonInclude(Include.NON_NULL)
public class GetCurUserInfoRa implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long              id;

    /**
     * 登录用户昵称
     */
    private String            signInNickname;

    /**
     * 登录用户头像
     */
    private String            signInAvatar;

    /**
     * 是否测试者
     */
    private Boolean           isTester;

    /**
     * 菜单列表
     */
    private List<String>      menus;

}
