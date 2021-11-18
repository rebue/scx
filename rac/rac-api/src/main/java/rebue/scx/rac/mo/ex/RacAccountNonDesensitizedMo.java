package rebue.scx.rac.mo.ex;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.mo.RacUserMo;

/**
 * 账户
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class RacAccountNonDesensitizedMo implements Serializable {

    /**
     * 账户ID
     *
     */
    @NotNull(groups = ModifyGroup.class, message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

    /**
     * 登录名称
     *
     */
    @Length(max = 20, message = "登录名称的长度不能大于20")
    private String            signInName;

    /**
     * 登录手机
     *
     */
    @Length(max = 11, message = "登录手机的长度不能大于11")
    private String            signInMobile;

    /**
     * 登录邮箱
     *
     */
    @Length(max = 50, message = "登录邮箱的长度不能大于50")
    private String            signInEmail;

    /**
     * 登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     * 注意：
     * 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     * 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     */
    @Length(max = 32, message = "登录密码的长度不能大于32")
    private String            signInPswd;

    /**
     * 登录密码组合码(与密码组合加密用，详见登录密码备注)
     *
     */
    @Length(max = 6, message = "登录密码组合码的长度不能大于6")
    private String            signInPswdSalt;

    /**
     * 登录账户昵称
     *
     */
    @Length(max = 20, message = "登录账户昵称的长度不能大于20")
    private String            signInNickname;

    /**
     * 微信的OpenId
     *
     */
    @Length(max = 64, message = "微信的OpenId的长度不能大于64")
    private String            wxOpenId;

    /**
     * 微信的UnionId
     *
     */
    @Length(max = 64, message = "微信的UnionId的长度不能大于64")
    private String            wxUnionId;

    /**
     * 微信昵称
     *
     */
    @Length(max = 100, message = "微信昵称的长度不能大于100")
    private String            wxNickname;

    /**
     * 微信头像
     *
     */
    @Length(max = 300, message = "微信头像的长度不能大于300")
    private String            wxAvatar;

    /**
     * QQ的OpenId
     *
     */
    @Length(max = 64, message = "QQ的OpenId的长度不能大于64")
    private String            qqOpenId;

    /**
     * QQ的UnionId
     *
     */
    @Length(max = 64, message = "QQ的UnionId的长度不能大于64")
    private String            qqUnionId;

    /**
     * QQ昵称
     *
     */
    @Length(max = 100, message = "QQ昵称的长度不能大于100")
    private String            qqNickname;

    /**
     * QQ头像
     *
     */
    @Length(max = 300, message = "QQ头像的长度不能大于300")
    private String            qqAvatar;

    /**
     * 是否测试者
     *
     */
    @NotNull(groups = AddGroup.class, message = "是否测试者不能为空")
    private Boolean           isTester;

    /**
     * 建立时间戳
     *
     */
    @NotNull(groups = AddGroup.class, message = "建立时间戳不能为空")
    @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     */
    @NotNull(groups = AddGroup.class, message = "修改时间戳不能为空")
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long              updateTimestamp;

    private static final long serialVersionUID = 1L;

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
        RacAccountNonDesensitizedMo other = (RacAccountNonDesensitizedMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * 组织ID
     *
     */
    @PositiveOrZero(message = "组织ID不能为负数")
    private Long          orgId;

    /**
     * 组织
     *
     */
    @Getter
    @Setter
    private RacOrgMo      org;

    /**
     * 用户ID
     *
     */
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long          userId;

    /**
     * 用户
     *
     */
    @Getter
    @Setter
    private RacUserMo     user;

    /**
     * 备注
     *
     */
    @Length(max = 150, message = "备注的长度不能大于150")
    private String        remark;

    /**
     * 领域ID
     *
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String        realmId;

    /**
     * 领域
     *
     */
    @Getter
    @Setter
    private RacRealmMo    realm;

    /**
     * 账户编码
     *
     */
    @Length(max = 32, message = "账户编码的长度不能大于32")
    private String        code;

    /**
     * 钉钉的OpenId
     *
     */
    @Length(max = 64, message = "钉钉的OpenId的长度不能大于64")
    private String        ddOpenId;

    /**
     * 钉钉的UnionId
     *
     */
    @Length(max = 64, message = "钉钉的UnionId的长度不能大于64")
    private String        ddUnionId;

    /**
     * 钉钉的UserId
     *
     */
    @Length(max = 64, message = "钉钉的UserId的长度不能大于64")
    private String        ddUserId;

    /**
     * 钉钉昵称
     *
     */
    @Length(max = 100, message = "钉钉昵称的长度不能大于100")
    private String        ddNickname;

    /**
     * 钉钉头像
     *
     */
    @Length(max = 300, message = "钉钉头像的长度不能大于300")
    private String        ddAvatar;

    /**
     * 联合账户ID
     *
     */
    @PositiveOrZero(message = "联合账户ID不能为负数")
    private Long          unionId;

    /**
     * 过期时间
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expirationDatetime;

}
