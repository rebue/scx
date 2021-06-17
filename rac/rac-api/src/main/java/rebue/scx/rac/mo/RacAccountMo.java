package rebue.scx.rac.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 账户
 *
 * @mbg.dontOverWriteAnnotation
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
// 不进行序列化
@JsonIgnoreProperties({ "signInPswd", "signInPswdSalt", "payPswd", "payPswdSalt"
})
public class RacAccountMo implements Serializable, Mo<Long> {

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean           isEnabled;

    /**
     * 登录名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "登录名称的长度不能大于20")
    private String            signInName;

    /**
     * 登录手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 11, message = "登录手机的长度不能大于11")
    private String            signInMobile;

    /**
     * 登录邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "登录邮箱的长度不能大于50")
    private String            signInEmail;

    /**
     * 登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     * 注意：
     * 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     * 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "登录密码的长度不能大于32")
    private String            signInPswd;

    /**
     * 登录密码组合码(与密码组合加密用，详见登录密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "登录密码组合码的长度不能大于6")
    private String            signInPswdSalt;

    /**
     * 登录账户昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "登录账户昵称的长度不能大于20")
    private String            signInNickname;

    /**
     * 登录账户头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "登录账户头像的长度不能大于300")
    private String            signInAvatar;

    /**
     * 微信的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "微信的OpenId的长度不能大于64")
    private String            wxOpenId;

    /**
     * 微信的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "微信的UnionId的长度不能大于64")
    private String            wxUnionId;

    /**
     * 微信昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "微信昵称的长度不能大于100")
    private String            wxNickname;

    /**
     * 微信头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "微信头像的长度不能大于300")
    private String            wxAvatar;

    /**
     * QQ的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "QQ的OpenId的长度不能大于64")
    private String            qqOpenId;

    /**
     * QQ的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "QQ的UnionId的长度不能大于64")
    private String            qqUnionId;

    /**
     * QQ昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "QQ昵称的长度不能大于100")
    private String            qqNickname;

    /**
     * QQ头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "QQ头像的长度不能大于300")
    private String            qqAvatar;

    /**
     * 是否测试者
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否测试者不能为空")
    private Boolean           isTester;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "建立时间戳不能为空")
    @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "修改时间戳不能为空")
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long              updateTimestamp;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public void setId(final Long id) {
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
    public void setIsEnabled(final Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 登录名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInName() {
        return signInName;
    }

    /**
     * 登录名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInName(final String signInName) {
        this.signInName = signInName;
    }

    /**
     * 登录手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInMobile() {
        return signInMobile;
    }

    /**
     * 登录手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInMobile(final String signInMobile) {
        this.signInMobile = signInMobile;
    }

    /**
     * 登录邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInEmail() {
        return signInEmail;
    }

    /**
     * 登录邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInEmail(final String signInEmail) {
        this.signInEmail = signInEmail;
    }

    /**
     * 登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     * 注意：
     * 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     * 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInPswd() {
        return signInPswd;
    }

    /**
     * 登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     * 注意：
     * 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     * 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInPswd(final String signInPswd) {
        this.signInPswd = signInPswd;
    }

    /**
     * 登录密码组合码(与密码组合加密用，详见登录密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInPswdSalt() {
        return signInPswdSalt;
    }

    /**
     * 登录密码组合码(与密码组合加密用，详见登录密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInPswdSalt(final String signInPswdSalt) {
        this.signInPswdSalt = signInPswdSalt;
    }

    /**
     * 登录账户昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInNickname() {
        return signInNickname;
    }

    /**
     * 登录账户昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInNickname(final String signInNickname) {
        this.signInNickname = signInNickname;
    }

    /**
     * 登录账户头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInAvatar() {
        return signInAvatar;
    }

    /**
     * 登录账户头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInAvatar(final String signInAvatar) {
        this.signInAvatar = signInAvatar;
    }

    /**
     * 微信的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * 微信的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxOpenId(final String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     * 微信的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxUnionId() {
        return wxUnionId;
    }

    /**
     * 微信的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxUnionId(final String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    /**
     * 微信昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     * 微信昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxNickname(final String wxNickname) {
        this.wxNickname = wxNickname;
    }

    /**
     * 微信头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxAvatar() {
        return wxAvatar;
    }

    /**
     * 微信头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxAvatar(final String wxAvatar) {
        this.wxAvatar = wxAvatar;
    }

    /**
     * QQ的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqOpenId() {
        return qqOpenId;
    }

    /**
     * QQ的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqOpenId(final String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    /**
     * QQ的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqUnionId() {
        return qqUnionId;
    }

    /**
     * QQ的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqUnionId(final String qqUnionId) {
        this.qqUnionId = qqUnionId;
    }

    /**
     * QQ昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqNickname() {
        return qqNickname;
    }

    /**
     * QQ昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqNickname(final String qqNickname) {
        this.qqNickname = qqNickname;
    }

    /**
     * QQ头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqAvatar() {
        return qqAvatar;
    }

    /**
     * QQ头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqAvatar(final String qqAvatar) {
        this.qqAvatar = qqAvatar;
    }

    /**
     * 是否测试者
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsTester() {
        return isTester;
    }

    /**
     * 是否测试者
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsTester(final Boolean isTester) {
        this.isTester = isTester;
    }

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTimestamp(final Long createTimestamp) {
        this.createTimestamp = createTimestamp;
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
    public void setUpdateTimestamp(final Long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", remark=").append(remark);
        sb.append(", orgId=").append(orgId);
        sb.append(", domainId=").append(domainId);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", signInName=").append(signInName);
        sb.append(", signInMobile=").append(signInMobile);
        sb.append(", signInEmail=").append(signInEmail);
        sb.append(", signInPswd=").append(signInPswd);
        sb.append(", signInPswdSalt=").append(signInPswdSalt);
        sb.append(", payPswd=").append(payPswd);
        sb.append(", payPswdSalt=").append(payPswdSalt);
        sb.append(", signInNickname=").append(signInNickname);
        sb.append(", signInAvatar=").append(signInAvatar);
        sb.append(", wxOpenId=").append(wxOpenId);
        sb.append(", wxUnionId=").append(wxUnionId);
        sb.append(", wxNickname=").append(wxNickname);
        sb.append(", wxAvatar=").append(wxAvatar);
        sb.append(", qqOpenId=").append(qqOpenId);
        sb.append(", qqUnionId=").append(qqUnionId);
        sb.append(", qqNickname=").append(qqNickname);
        sb.append(", qqAvatar=").append(qqAvatar);
        sb.append(", isTester=").append(isTester);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", updateTimestamp=").append(updateTimestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        final RacAccountMo other = (RacAccountMo) that;
        return getId() == null ? other.getId() == null : getId().equals(other.getId());
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
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

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "组织ID不能为负数")
    private Long     orgId;

    /**
     * 组织
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacOrgMo org;

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrgId(final Long orgId) {
        this.orgId = orgId;
    }

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
    public void setPayPswd(final String payPswd) {
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
    public void setPayPswdSalt(final String payPswdSalt) {
        this.payPswdSalt = payPswdSalt;
    }

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long      userId;

    /**
     * 用户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacUserMo user;

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * 备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 150, message = "备注的长度不能大于150")
    private String      remark;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String      domainId;

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacDomainMo domain;

    /**
     * 备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(final String remark) {
        this.remark = remark;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDomainId(final String domainId) {
        this.domainId = domainId;
    }

}
