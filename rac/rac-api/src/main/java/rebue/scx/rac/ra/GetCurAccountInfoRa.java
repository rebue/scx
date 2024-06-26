package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacPermCommandMo;

/**
 * 当前账户信息
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @RequiredArgsConstructor // 不知道@Data中默认包含的@RequiredArgsConstructor为何没起效
@JsonInclude(Include.NON_NULL)
public class GetCurAccountInfoRa extends RacAccountMo implements Serializable {

    private static final long      serialVersionUID = 1L;

    /**
     * 账户ID
     */
    private Long                   id;

    /**
     * 账户昵称
     */
    private String                 nickname;

    /**
     * 账户头像
     */
    private String                 avatar;

    /**
     * 代理账户ID
     */
    private Long                   agentAccountId;

    /**
     * 代理账户昵称
     */
    private String                 agentNickname;

    /**
     * 代理账户头像
     */
    private String                 agentAvatar;

    /**
     * 账户组织ID
     */
    private Long                   orgId;

    /**
     * 账户组织全名
     */
    private String                 orgFullName;
    /**
     * 账户组织名称
     */
    private String                 orgName;

    /**
     * 是否测试者
     */
    private Boolean                isTester;

    /**
     * 菜单列表
     */
    private List<String>           menus;
    /**
     * 权限命令
     */
    private List<RacPermCommandMo> permCommands;

}
