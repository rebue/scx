package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacUserAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "用户昵称的长度不能大于20")
    private String nickname;

    /**
     * 用户头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "用户头像的长度不能大于300")
    private String avatar;

    /**
     * 登录名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "登录名称的长度不能大于20")
    private String signInName;

    /**
     * 登录密码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "登录密码的长度不能大于32")
    private String signInPswd;

    /**
     * 支付密码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "支付密码的长度不能大于32")
    private String payPswd;

    /**
     * 密码组合码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "密码组合码的长度不能大于6")
    private String salt;

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
     * 微信的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "微信的OpenId的长度不能大于64")
    private String wxOpenId;

    /**
     * 微信的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "微信的UnionId的长度不能大于64")
    private String wxUnionId;

    /**
     * 微信昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "微信昵称的长度不能大于100")
    private String wxNickname;

    /**
     * 微信头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "微信头像的长度不能大于300")
    private String wxAvatar;

    /**
     * QQ的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "QQ的OpenId的长度不能大于64")
    private String qqOpenId;

    /**
     * QQ的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "QQ的UnionId的长度不能大于64")
    private String qqUnionId;

    /**
     * QQ昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "QQ昵称的长度不能大于100")
    private String qqNickname;

    /**
     * QQ头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "QQ头像的长度不能大于300")
    private String qqAvatar;

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
     * 年龄
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "年龄不能为负数")
    private Byte age;

    /**
     * 是否测试者
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "是否测试者不能为空")
    private Boolean isTester;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "是否启用不能为空")
    private Boolean isEnabled;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "修改时间戳不能为空")
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long updateTimestamp;
}
