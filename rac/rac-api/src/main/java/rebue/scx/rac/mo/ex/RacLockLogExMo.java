package rebue.scx.rac.mo.ex;

import lombok.ToString;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.wheel.api.annotation.Desensitize;
import rebue.wheel.api.strategy.DesensitizeStrategy;

/**
 * 操作日志翻页查询使用
 *
 * @author yuanman
 *
 */
@ToString(callSuper = true)
public class RacLockLogExMo extends RacLockLogMo {

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
    @Desensitize(DesensitizeStrategy.MOBILE)
    private String            signInMobile;

    /**
     * 登录邮箱
     */
    @Desensitize(DesensitizeStrategy.EMAIL)
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

    // /**
    // * 锁定人登录名称
    // */
    // private String lockSignInName;
    // /**
    // * 锁定人登录手机号
    // */
    // private String lockSignInMobile;
    // /**
    // * 锁定人登录邮箱
    // */
    // private String lockSignInEmail;
    // /**
    // * 锁定人登录账户昵称
    // */
    // private String lockSignInNickname;
    // /**
    // * 锁定人微信昵称
    // */
    // private String lockwxNickname;
    // /**
    // * 锁定人QQ昵称
    // */
    // private String lockqqNickname;
    //
    // /**
    // * 代理锁定人登录名称
    // */
    // private String lockAgentSignInName;
    //
    // /**
    // * 代理锁定人登录账户昵称
    // */
    // private String lockAgentSignInNickname;
    //
    // /**
    // * 代理锁定人登录手机
    // *
    // */
    // private String lockAgentSignInMobile;
    //
    // /**
    // * 代理锁定人登录邮箱
    // *
    // */
    // private String lockAgentSignInEmail;
    //
    // /**
    // * 代理锁定人微信昵称
    // */
    // private String lockAgentwxNickname;
    //
    // /**
    // * 代理锁定人QQ昵称
    // */
    // private String lockAgentqqNickname;

    /**
     * 解锁人登录名称
     */
    private String unlockSignInName;

    /**
     * 解锁人登录手机号
     */
    @Desensitize(DesensitizeStrategy.MOBILE)
    private String unlockSignInMobile;

    /**
     * 解锁人登录邮箱
     */
    @Desensitize(DesensitizeStrategy.EMAIL)
    private String unlockSignInEmail;

    /**
     * 解锁人登录账户昵称
     */
    private String unlockSignInNickname;

    /**
     * 解锁人微信昵称
     */
    private String unlockwxNickname;

    /**
     * 解锁人QQ昵称
     */
    private String unlockqqNickname;

    /**
     * 代理解锁人登录名称
     */
    private String unlockAgentSignInName;

    /**
     * 代理解锁人登录账户昵称
     */
    private String unlockAgentSignInNickname;

    /**
     * 代理解锁人登录手机
     *
     */
    @Desensitize(DesensitizeStrategy.MOBILE)
    private String unlockAgentSignInMobile;

    /**
     * 代理解锁人登录邮箱
     *
     */
    @Desensitize(DesensitizeStrategy.EMAIL)
    private String unlockAgentSignInEmail;

    /**
     * 代理解锁人微信昵称
     */
    private String unlockAgentwxNickname;

    /**
     * 代理解锁人QQ昵称
     */
    private String unlockAgentqqNickname;

    public RacLockLogExMo() {
        super();
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(final String signInName) {
        this.signInName = signInName;
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

    public String getSignInNickname() {
        return signInNickname;
    }

    public void setSignInNickname(final String signInNickname) {
        this.signInNickname = signInNickname;
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

    public String getUnlockSignInName() {
        return unlockSignInName;
    }

    public void setUnlockSignInName(final String unlockSignInName) {
        this.unlockSignInName = unlockSignInName;
    }

    public String getUnlockSignInMobile() {
        return unlockSignInMobile;
    }

    public void setUnlockSignInMobile(final String unlockSignInMobile) {
        this.unlockSignInMobile = unlockSignInMobile;
    }

    public String getUnlockSignInEmail() {
        return unlockSignInEmail;
    }

    public void setUnlockSignInEmail(final String unlockSignInEmail) {
        this.unlockSignInEmail = unlockSignInEmail;
    }

    public String getUnlockSignInNickname() {
        return unlockSignInNickname;
    }

    public void setUnlockSignInNickname(final String unlockSignInNickname) {
        this.unlockSignInNickname = unlockSignInNickname;
    }

    public String getUnlockwxNickname() {
        return unlockwxNickname;
    }

    public void setUnlockwxNickname(final String unlockwxNickname) {
        this.unlockwxNickname = unlockwxNickname;
    }

    public String getUnlockqqNickname() {
        return unlockqqNickname;
    }

    public void setUnlockqqNickname(final String unlockqqNickname) {
        this.unlockqqNickname = unlockqqNickname;
    }

    public String getUnlockAgentSignInName() {
        return unlockAgentSignInName;
    }

    public void setUnlockAgentSignInName(final String unlockAgentSignInName) {
        this.unlockAgentSignInName = unlockAgentSignInName;
    }

    public String getUnlockAgentSignInNickname() {
        return unlockAgentSignInNickname;
    }

    public void setUnlockAgentSignInNickname(final String unlockAgentSignInNickname) {
        this.unlockAgentSignInNickname = unlockAgentSignInNickname;
    }

    public String getUnlockAgentSignInMobile() {
        return unlockAgentSignInMobile;
    }

    public void setUnlockAgentSignInMobile(final String unlockAgentSignInMobile) {
        this.unlockAgentSignInMobile = unlockAgentSignInMobile;
    }

    public String getUnlockAgentSignInEmail() {
        return unlockAgentSignInEmail;
    }

    public void setUnlockAgentSignInEmail(final String unlockAgentSignInEmail) {
        this.unlockAgentSignInEmail = unlockAgentSignInEmail;
    }

    public String getUnlockAgentwxNickname() {
        return unlockAgentwxNickname;
    }

    public void setUnlockAgentwxNickname(final String unlockAgentwxNickname) {
        this.unlockAgentwxNickname = unlockAgentwxNickname;
    }

    public String getUnlockAgentqqNickname() {
        return unlockAgentqqNickname;
    }

    public void setUnlockAgentqqNickname(final String unlockAgentqqNickname) {
        this.unlockAgentqqNickname = unlockAgentqqNickname;
    }

}
