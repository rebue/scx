package rebue.scx.rac.mo.ex;

import lombok.ToString;
import rebue.scx.rac.mo.RacOpLogMo;
import rebue.wheel.api.annotation.Desensitize;
import rebue.wheel.api.strategy.DesensitizeStrategy;

/**
 * 操作日志翻页查询使用
 * 
 * @author yuanman
 *
 */

@ToString(callSuper = true)
public class RacOpLogExMo extends RacOpLogMo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    private String            appName;
    /**
     * 应用备注
     */
    private String            remark;
    /**
     * 菜单
     */
    private String            menu;
    /**
     * 领域ID
     */
    private String            realmId;

    /**
     * 登录名称
     */
    private String            signInName;

    /**
     * 登录账户昵称
     */
    private String            signInNickname;

    /**
     * 登录手机
     *
     */
    @Desensitize(DesensitizeStrategy.MOBILE)
    private String            signInMobile;

    /**
     * 登录邮箱
     *
     */
    @Desensitize(DesensitizeStrategy.EMAIL)
    private String            signInEmail;

    /**
     * 微信昵称
     */
    private String            wxNickname;

    /**
     * QQ昵称
     */
    private String            qqNickname;

    /**
     * 代理人登录名称
     */
    private String            agentSignInName;

    /**
     * 代理人登录账户昵称
     */
    private String            agentSignInNickname;

    /**
     * 代理人登录手机
     *
     */
    @Desensitize(DesensitizeStrategy.MOBILE)
    private String            agentSignInMobile;

    /**
     * 代理人登录邮箱
     *
     */
    @Desensitize(DesensitizeStrategy.EMAIL)
    private String            agentSignInEmail;

    /**
     * 代理人微信昵称
     */
    private String            agentwxNickname;

    /**
     * 代理人QQ昵称
     */
    private String            agentqqNickname;

    public RacOpLogExMo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RacOpLogExMo(final String appName, final String remark, final String menu, final String realmId, final String signInName, final String signInNickname,
            final String signInMobile, final String signInEmail,
            final String wxNickname, final String qqNickname, final String agentSignInName, final String agentSignInNickname, final String agentSignInMobile,
            final String agentSignInEmail, final String agentwxNickname,
            final String agentqqNickname) {
        super();
        this.appName             = appName;
        this.remark              = remark;
        this.menu                = menu;
        this.realmId             = realmId;
        this.signInName          = signInName;
        this.signInNickname      = signInNickname;
        this.signInMobile        = signInMobile;
        this.signInEmail         = signInEmail;
        this.wxNickname          = wxNickname;
        this.qqNickname          = qqNickname;
        this.agentSignInName     = agentSignInName;
        this.agentSignInNickname = agentSignInNickname;
        this.agentSignInMobile   = agentSignInMobile;
        this.agentSignInEmail    = agentSignInEmail;
        this.agentwxNickname     = agentwxNickname;
        this.agentqqNickname     = agentqqNickname;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(final String appName) {
        this.appName = appName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(final String menu) {
        this.menu = menu;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(final String realmId) {
        this.realmId = realmId;
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(final String signInName) {
        this.signInName = signInName;
    }

    public String getSignInNickname() {
        return signInNickname;
    }

    public void setSignInNickname(final String signInNickname) {
        this.signInNickname = signInNickname;
    }

    public String getSignInMobile() {
        return signInMobile;
    }

    public void setSignInMobile(final String signInMobile) {
        this.signInMobile = signInMobile;
    }

    public String getSignInEmail() {
        return signInEmail;
    }

    public void setSignInEmail(final String signInEmail) {
        this.signInEmail = signInEmail;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(final String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public String getQqNickname() {
        return qqNickname;
    }

    public void setQqNickname(final String qqNickname) {
        this.qqNickname = qqNickname;
    }

    public String getAgentSignInName() {
        return agentSignInName;
    }

    public void setAgentSignInName(final String agentSignInName) {
        this.agentSignInName = agentSignInName;
    }

    public String getAgentSignInNickname() {
        return agentSignInNickname;
    }

    public void setAgentSignInNickname(final String agentSignInNickname) {
        this.agentSignInNickname = agentSignInNickname;
    }

    public String getAgentSignInMobile() {
        return agentSignInMobile;
    }

    public void setAgentSignInMobile(final String agentSignInMobile) {
        this.agentSignInMobile = agentSignInMobile;
    }

    public String getAgentSignInEmail() {
        return agentSignInEmail;
    }

    public void setAgentSignInEmail(final String agentSignInEmail) {
        this.agentSignInEmail = agentSignInEmail;
    }

    public String getAgentwxNickname() {
        return agentwxNickname;
    }

    public void setAgentwxNickname(final String agentwxNickname) {
        this.agentwxNickname = agentwxNickname;
    }

    public String getAgentqqNickname() {
        return agentqqNickname;
    }

    public void setAgentqqNickname(final String agentqqNickname) {
        this.agentqqNickname = agentqqNickname;
    }

}
