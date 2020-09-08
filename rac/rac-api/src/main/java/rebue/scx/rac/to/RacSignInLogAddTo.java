package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户登录日志
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacSignInLogAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID(如为1则是散客)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "用户ID不能为空")
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long userId;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "系统ID不能为空")
    @Length(max = 32, message = "系统ID的长度不能大于32")
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
    @NotBlank(message = "登录类型不能为空")
    @Length(max = 32, message = "登录类型的长度不能大于32")
    private String loginWay;

    /**
     * 登录时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "登录时间不能为空")
    private LocalDateTime loginDatetime;
}
