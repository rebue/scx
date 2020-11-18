package rebue.scx.rac.to;import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;



/**
 * 用户
 * 
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacUserModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "用户ID不能为空") @PositiveOrZero(message = "用户ID不能为负数")
    private Long id;

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
     * 是否测试者
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isTester;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long updateTimestamp;

    /**
     * 个人ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "个人ID不能为负数")
    private Long personId;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long createrTimestamp;

    /**
     * 登录名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "登录名称的长度不能大于20")
    private String signInName;

    /**
     * 登录手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 11, message = "登录手机的长度不能大于11")
    private String signInMobile;

    /**
     * 登录邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "登录邮箱的长度不能大于50")
    private String signInEmail;

    /**
     * 登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     *              注意：
     *              1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     *              2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "登录密码的长度不能大于32")
    private String signInPswd;

    /**
     * 登录密码组合码(与密码组合加密用，详见登录密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "登录密码组合码的长度不能大于6")
    private String signInPswdSalt;

    /**
     * 登录用户昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "登录用户昵称的长度不能大于20")
    private String signInNickname;

    /**
     * 登录用户头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "登录用户头像的长度不能大于300")
    private String signInAvatar;
}
