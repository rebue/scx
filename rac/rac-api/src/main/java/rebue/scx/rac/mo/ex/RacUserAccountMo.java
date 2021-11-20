package rebue.scx.rac.mo.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rebue.robotech.valid.ModifyGroup;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.wheel.api.annotation.Desensitize;
import rebue.wheel.api.strategy.DesensitizeStrategy;

/**
 * 带有用户的账户表
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "signInPswd", "signInPswdSalt"
})
@Getter
@Setter
@ToString(callSuper = true)
public class RacUserAccountMo extends RacAccountMo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(groups = ModifyGroup.class, message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              accountId;

    /**
     * 手机
     */
    @Length(max = 11, message = "手机的长度不能大于11")
    @Desensitize(DesensitizeStrategy.MOBILE)
    private String            mobile;

    /**
     * 是否已验证手机号码
     */
    private Boolean           isVerifiedMobile;

    /**
     * 电子邮箱
     */
    @Length(max = 50, message = "电子邮箱的长度不能大于50")
    @Desensitize(DesensitizeStrategy.EMAIL)
    private String            email;

    /**
     * 是否已验证电子邮箱
     */
    private Boolean           isVerifiedEmail;

    /**
     * 用户实名
     */
    @Length(max = 100, message = "用户实名的长度不能大于100")
    @Desensitize(DesensitizeStrategy.USERNAME)
    private String            realName;

    /**
     * 是否已验证实名
     */
    private Boolean           isVerifiedRealname;

    /**
     * 身份证号
     */
    @Length(max = 18, message = "身份证号的长度不能大于18")
    @Desensitize(DesensitizeStrategy.ID_CARD)
    private String            idCard;

    /**
     * 是否已验证身份证号
     */
    private Boolean           isVerifiedIdcard;

    /**
     * 性别
     */
    @PositiveOrZero(message = "性别不能为负数")
    private Byte              sex;

}
