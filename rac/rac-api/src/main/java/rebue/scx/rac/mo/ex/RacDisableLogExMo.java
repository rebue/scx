package rebue.scx.rac.mo.ex;

import lombok.ToString;
import rebue.scx.rac.mo.RacDisableLogMo;

/**
 * 账户启用/禁用日志分页查询使用
 *
 * @author yuanman
 *
 */
@ToString(callSuper = true)
public class RacDisableLogExMo extends RacDisableLogMo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录名称
     */
    private String            signInName;

    /**
     * 登录手机号
     */
    private String            signInMobile;

    /**
     * 登录邮箱
     */
    private String            signInEmail;

    /**
     * 登录账户昵称
     */
    private String            signInNickname;
    /**
     * 微信昵称
     */
    private String            wxNickname;
    /**
     * QQ昵称
     */
    private String            qqNickname;

    /**
     * 禁用人登录名称
     */
    private String            disableSignInName;
    /**
     * 禁用人登录手机号
     */
    private String            disableSignInMobile;
    /**
     * 禁用人登录邮箱
     */
    private String            disableSignInEmail;
    /**
     * 禁用人登录账户昵称
     */
    private String            disableSignInNickname;
    /**
     * 禁用人微信昵称
     */
    private String            disablewxNickname;
    /**
     * 禁用人QQ昵称
     */
    private String            disableqqNickname;

    /**
     * 代理禁用人登录名称
     */
    private String            disableAgentSignInName;

    /**
     * 代理禁用人登录账户昵称
     */
    private String            disableAgentSignInNickname;

    /**
     * 代理禁用人登录手机
     *
     */
    private String            disableAgentSignInMobile;

    /**
     * 代理禁用人登录邮箱
     *
     */
    private String            disableAgentSignInEmail;

    /**
     * 代理禁用人微信昵称
     */
    private String            disableAgentwxNickname;

    /**
     * 代理禁用人QQ昵称
     */
    private String            disableAgentqqNickname;

    /**
     * 启用人登录名称
     */
    private String            enableSignInName;

    /**
     * 启用人登录手机号
     */
    private String            enableSignInMobile;

    /**
     * 启用人登录邮箱
     */
    private String            enableSignInEmail;

    /**
     * 启用人登录账户昵称
     */
    private String            enableSignInNickname;

    /**
     * 启用人微信昵称
     */
    private String            enablewxNickname;

    /**
     * 启用人QQ昵称
     */
    private String            enableqqNickname;

    /**
     * 代理启用人登录名称
     */
    private String            enableAgentSignInName;

    /**
     * 代理启用人登录账户昵称
     */
    private String            enableAgentSignInNickname;

    /**
     * 代理启用人登录手机
     *
     */
    private String            enableAgentSignInMobile;

    /**
     * 代理启用人登录邮箱
     *
     */
    private String            enableAgentSignInEmail;

    /**
     * 代理启用人微信昵称
     */
    private String            enableAgentwxNickname;

    /**
     * 代理启用人QQ昵称
     */
    private String            enableAgentqqNickname;

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    public String getSignInMobile() {
        return signInMobile;
    }

    public void setSignInMobile(String signInMobile) {
        this.signInMobile = signInMobile;
    }

    public String getSignInEmail() {
        return signInEmail;
    }

    public void setSignInEmail(String signInEmail) {
        this.signInEmail = signInEmail;
    }

    public String getSignInNickname() {
        return signInNickname;
    }

    public void setSignInNickname(String signInNickname) {
        this.signInNickname = signInNickname;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public String getQqNickname() {
        return qqNickname;
    }

    public void setQqNickname(String qqNickname) {
        this.qqNickname = qqNickname;
    }

    public String getDisableSignInName() {
        return disableSignInName;
    }

    public void setDisableSignInName(String disableSignInName) {
        this.disableSignInName = disableSignInName;
    }

    public String getDisableSignInMobile() {
        return disableSignInMobile;
    }

    public void setDisableSignInMobile(String disableSignInMobile) {
        this.disableSignInMobile = disableSignInMobile;
    }

    public String getDisableSignInEmail() {
        return disableSignInEmail;
    }

    public void setDisableSignInEmail(String disableSignInEmail) {
        this.disableSignInEmail = disableSignInEmail;
    }

    public String getDisableSignInNickname() {
        return disableSignInNickname;
    }

    public void setDisableSignInNickname(String disableSignInNickname) {
        this.disableSignInNickname = disableSignInNickname;
    }

    public String getDisablewxNickname() {
        return disablewxNickname;
    }

    public void setDisablewxNickname(String disablewxNickname) {
        this.disablewxNickname = disablewxNickname;
    }

    public String getDisableqqNickname() {
        return disableqqNickname;
    }

    public void setDisableqqNickname(String disableqqNickname) {
        this.disableqqNickname = disableqqNickname;
    }

    public String getDisableAgentSignInName() {
        return disableAgentSignInName;
    }

    public void setDisableAgentSignInName(String disableAgentSignInName) {
        this.disableAgentSignInName = disableAgentSignInName;
    }

    public String getDisableAgentSignInNickname() {
        return disableAgentSignInNickname;
    }

    public void setDisableAgentSignInNickname(String disableAgentSignInNickname) {
        this.disableAgentSignInNickname = disableAgentSignInNickname;
    }

    public String getDisableAgentSignInMobile() {
        return disableAgentSignInMobile;
    }

    public void setDisableAgentSignInMobile(String disableAgentSignInMobile) {
        this.disableAgentSignInMobile = disableAgentSignInMobile;
    }

    public String getDisableAgentSignInEmail() {
        return disableAgentSignInEmail;
    }

    public void setDisableAgentSignInEmail(String disableAgentSignInEmail) {
        this.disableAgentSignInEmail = disableAgentSignInEmail;
    }

    public String getDisableAgentwxNickname() {
        return disableAgentwxNickname;
    }

    public void setDisableAgentwxNickname(String disableAgentwxNickname) {
        this.disableAgentwxNickname = disableAgentwxNickname;
    }

    public String getDisableAgentqqNickname() {
        return disableAgentqqNickname;
    }

    public void setDisableAgentqqNickname(String disableAgentqqNickname) {
        this.disableAgentqqNickname = disableAgentqqNickname;
    }

    public String getEnableSignInName() {
        return enableSignInName;
    }

    public void setEnableSignInName(String enableSignInName) {
        this.enableSignInName = enableSignInName;
    }

    public String getEnableSignInMobile() {
        return enableSignInMobile;
    }

    public void setEnableSignInMobile(String enableSignInMobile) {
        this.enableSignInMobile = enableSignInMobile;
    }

    public String getEnableSignInEmail() {
        return enableSignInEmail;
    }

    public void setEnableSignInEmail(String enableSignInEmail) {
        this.enableSignInEmail = enableSignInEmail;
    }

    public String getEnableSignInNickname() {
        return enableSignInNickname;
    }

    public void setEnableSignInNickname(String enableSignInNickname) {
        this.enableSignInNickname = enableSignInNickname;
    }

    public String getEnablewxNickname() {
        return enablewxNickname;
    }

    public void setEnablewxNickname(String enablewxNickname) {
        this.enablewxNickname = enablewxNickname;
    }

    public String getEnableqqNickname() {
        return enableqqNickname;
    }

    public void setEnableqqNickname(String enableqqNickname) {
        this.enableqqNickname = enableqqNickname;
    }

    public String getEnableAgentSignInName() {
        return enableAgentSignInName;
    }

    public void setEnableAgentSignInName(String enableAgentSignInName) {
        this.enableAgentSignInName = enableAgentSignInName;
    }

    public String getEnableAgentSignInNickname() {
        return enableAgentSignInNickname;
    }

    public void setEnableAgentSignInNickname(String enableAgentSignInNickname) {
        this.enableAgentSignInNickname = enableAgentSignInNickname;
    }

    public String getEnableAgentSignInMobile() {
        return enableAgentSignInMobile;
    }

    public void setEnableAgentSignInMobile(String enableAgentSignInMobile) {
        this.enableAgentSignInMobile = enableAgentSignInMobile;
    }

    public String getEnableAgentSignInEmail() {
        return enableAgentSignInEmail;
    }

    public void setEnableAgentSignInEmail(String enableAgentSignInEmail) {
        this.enableAgentSignInEmail = enableAgentSignInEmail;
    }

    public String getEnableAgentwxNickname() {
        return enableAgentwxNickname;
    }

    public void setEnableAgentwxNickname(String enableAgentwxNickname) {
        this.enableAgentwxNickname = enableAgentwxNickname;
    }

    public String getEnableAgentqqNickname() {
        return enableAgentqqNickname;
    }

    public void setEnableAgentqqNickname(String enableAgentqqNickname) {
        this.enableAgentqqNickname = enableAgentqqNickname;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
