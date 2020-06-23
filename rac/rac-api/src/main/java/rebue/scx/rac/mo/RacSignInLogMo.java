package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户登录日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacSignInLogMo implements Serializable {

    /**
     * 用户登录日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     * 用户ID(如为1则是散客)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long userId;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String sysId;

    /**
     * 登录类型(与注册类型一致)
     *              LOGIN_NAME: 登录名与密码
     *              EMAIL_PASSWORD: 电子邮箱与密码
     *              MOBILE_PASSWORD: 手机号与密码
     *              MOBILE_SMS. 手机短信验证
     *              WECHAT_OFFICIAL_ACCOUNTS: 微信公众号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String loginWay;

    /**
     * 登录时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loginTime;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户登录日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户登录日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID(如为1则是散客)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID(如为1则是散客)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     * 登录类型(与注册类型一致)
     *              LOGIN_NAME: 登录名与密码
     *              EMAIL_PASSWORD: 电子邮箱与密码
     *              MOBILE_PASSWORD: 手机号与密码
     *              MOBILE_SMS. 手机短信验证
     *              WECHAT_OFFICIAL_ACCOUNTS: 微信公众号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLoginWay() {
        return loginWay;
    }

    /**
     * 登录类型(与注册类型一致)
     *              LOGIN_NAME: 登录名与密码
     *              EMAIL_PASSWORD: 电子邮箱与密码
     *              MOBILE_PASSWORD: 手机号与密码
     *              MOBILE_SMS. 手机短信验证
     *              WECHAT_OFFICIAL_ACCOUNTS: 微信公众号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginWay(String loginWay) {
        this.loginWay = loginWay;
    }

    /**
     * 登录时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    /**
     * 登录时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", sysId=").append(sysId);
        sb.append(", loginWay=").append(loginWay);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RacSignInLogMo other = (RacSignInLogMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
