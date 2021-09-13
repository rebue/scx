package rebue.scx.rac.mo.ex;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rebue.robotech.valid.AddGroup;
import rebue.scx.rac.mo.RacAccountMo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RacLockLogAndAccountMo extends RacAccountMo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 锁定日志ID
     */
    private Long              lockLogId;

    /**
     * 锁定账户的账户ID
     *
     */
    @NotNull(groups = AddGroup.class, message = "锁定账户的账户ID不能为空")
    @PositiveOrZero(message = "锁定账户的账户ID不能为负数")
    private Long              lockAccountId;

    /**
     * 锁定原因
     *
     */
    @NotBlank(groups = AddGroup.class, message = "锁定原因不能为空")
    @Length(max = 100, message = "锁定原因的长度不能大于100")
    private String            lockReason;

    /**
     * 锁定时间
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(groups = AddGroup.class, message = "锁定时间不能为空")
    private LocalDateTime     lockDatetime;

    /**
     * 解锁原因
     *
     */
    @Length(max = 100, message = "解锁原因的长度不能大于100")
    private String            unlockReason;

    /**
     * 解锁时间
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     unlockDatetime;

    /**
     * 解锁操作员的账户ID
     *
     */
    @PositiveOrZero(message = "解锁操作员的账户ID不能为负数")
    private Long              unlockOpId;

    /**
     * 解锁操作的代理人的账户ID
     *
     */
    @PositiveOrZero(message = "解锁操作的代理人的账户ID不能为负数")
    private Long              unlockOpAgentId;

    /**
     * 自动解锁时间
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     autoUnlockDatetime;
}
