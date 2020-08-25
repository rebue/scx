package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
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
public class RacUserModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Long id;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String nickname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "的长度不能大于300")
    private String avatar;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String signInName;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String signInPswd;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String payPswd;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 6, message = "的长度不能大于6")
    private String salt;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 11, message = "的长度不能大于11")
    private String mobile;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedMobile;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "的长度不能大于50")
    private String email;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedEmail;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "的长度不能大于64")
    private String wxOpenId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "的长度不能大于64")
    private String wxUnionId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "的长度不能大于100")
    private String wxNickname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "的长度不能大于300")
    private String wxAvatar;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "的长度不能大于64")
    private String qqOpenId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 64, message = "的长度不能大于64")
    private String qqUnionId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "的长度不能大于100")
    private String qqNickname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "的长度不能大于300")
    private String qqAvatar;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "的长度不能大于100")
    private String realName;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedRealname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 18, message = "的长度不能大于18")
    private String idCard;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedIdcard;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Byte sex;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Byte age;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isTester;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Long updateTimestamp;
}
