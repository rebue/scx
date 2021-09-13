package rebue.scx.rac.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 账户
 *
 * @mbg.removedMember isEnabled,createTimestamp,updateTimestamp
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              id;

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
     *             注意：
     *             1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     *             2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
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
    private Boolean           isTester;

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "组织ID不能为负数")
    private Long              orgId;

    /**
     * 支付密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "支付密码的长度不能大于32")
    private String            payPswd;

    /**
     * 支付密码组合码(与支付密码组合加密用，详见支付密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "支付密码组合码的长度不能大于6")
    private String            payPswdSalt;

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long              userId;

    /**
     * 备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 150, message = "备注的长度不能大于150")
    private String            remark;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            realmId;

    /**
     * 账户编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "账户编码的长度不能大于32")
    private String            code;

    /**
     * 钉钉的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "钉钉的OpenId的长度不能大于64")
    private String            ddOpenId;

    /**
     * 钉钉的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "钉钉的UnionId的长度不能大于64")
    private String            ddUnionId;

    /**
     * 钉钉的UserId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "钉钉的UserId的长度不能大于64")
    private String            ddUserId;

    /**
     * 钉钉昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "钉钉昵称的长度不能大于100")
    private String            ddNickname;

    /**
     * 钉钉头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "钉钉头像的长度不能大于300")
    private String            ddAvatar;
}
