package rebue.scx.rac.mo;import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;



/**
 * 个人
 * 
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacPersonMo implements Serializable, Mo<Long> {

    /**
     * 个人ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "个人ID不能为空") @PositiveOrZero(message = "个人ID不能为负数")
    private Long id;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean isEnabled;

    /**
     * 支付密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "支付密码的长度不能大于32")
    private String payPswd;

    /**
     * 支付密码组合码(与支付密码组合加密用，详见支付密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "支付密码组合码的长度不能大于6")
    private String payPswdSalt;

    /**
     * 手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 11, message = "手机的长度不能大于11")
    private String mobile;

    /**
     * 是否已验证手机号码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedMobile;

    /**
     * 电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "电子邮箱的长度不能大于50")
    private String email;

    /**
     * 是否已验证电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedEmail;

    /**
     * 用户实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "用户实名的长度不能大于100")
    private String realName;

    /**
     * 是否已验证实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedRealname;

    /**
     * 身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 18, message = "身份证号的长度不能大于18")
    private String idCard;

    /**
     * 是否已验证身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedIdcard;

    /**
     * 性别
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "性别不能为负数")
    private Byte sex;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "建立时间戳不能为空") @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long createrTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "修改时间戳不能为空") @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long updateTimestamp;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 个人ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 个人ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 支付密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPayPswd() {
        return payPswd;
    }

    /**
     * 支付密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPayPswd(String payPswd) {
        this.payPswd = payPswd;
    }

    /**
     * 支付密码组合码(与支付密码组合加密用，详见支付密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPayPswdSalt() {
        return payPswdSalt;
    }

    /**
     * 支付密码组合码(与支付密码组合加密用，详见支付密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPayPswdSalt(String payPswdSalt) {
        this.payPswdSalt = payPswdSalt;
    }

    /**
     * 手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 是否已验证手机号码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedMobile() {
        return isVerifiedMobile;
    }

    /**
     * 是否已验证手机号码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedMobile(Boolean isVerifiedMobile) {
        this.isVerifiedMobile = isVerifiedMobile;
    }

    /**
     * 电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 是否已验证电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    /**
     * 是否已验证电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    /**
     * 用户实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 用户实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 是否已验证实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedRealname() {
        return isVerifiedRealname;
    }

    /**
     * 是否已验证实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedRealname(Boolean isVerifiedRealname) {
        this.isVerifiedRealname = isVerifiedRealname;
    }

    /**
     * 身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 是否已验证身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedIdcard() {
        return isVerifiedIdcard;
    }

    /**
     * 是否已验证身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedIdcard(Boolean isVerifiedIdcard) {
        this.isVerifiedIdcard = isVerifiedIdcard;
    }

    /**
     * 性别
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 性别
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreaterTimestamp() {
        return createrTimestamp;
    }

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreaterTimestamp(Long createrTimestamp) {
        this.createrTimestamp = createrTimestamp;
    }

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUpdateTimestamp(Long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
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
    sb.append(", isEnabled=").append(isEnabled);
    sb.append(", payPswd=").append(payPswd);
    sb.append(", payPswdSalt=").append(payPswdSalt);
    sb.append(", mobile=").append(mobile);
    sb.append(", isVerifiedMobile=").append(isVerifiedMobile);
    sb.append(", email=").append(email);
    sb.append(", isVerifiedEmail=").append(isVerifiedEmail);
    sb.append(", realName=").append(realName);
    sb.append(", isVerifiedRealname=").append(isVerifiedRealname);
    sb.append(", idCard=").append(idCard);
    sb.append(", isVerifiedIdcard=").append(isVerifiedIdcard);
    sb.append(", sex=").append(sex);
    sb.append(", createrTimestamp=").append(createrTimestamp);
    sb.append(", updateTimestamp=").append(updateTimestamp);
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
    RacPersonMo other = (RacPersonMo) that;
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

    /**
     * 获取ID的类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
public String getIdType() {
    return "Long";
}
}
