package rebue.scx.rac.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 启用或禁用账户并记录日志
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountEnableTo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 启用或禁用
	 */
	@NotNull(message = "是否启用不能为空")
	private Boolean isEnabled;

	/**
	 * 领域ID
	 *
	 */
	private String realmId;
	
	/**
	 * 被锁定账户的账户ID
	 *
	 */
	@PositiveOrZero(message = "锁定账户的账户ID不能为负数")
	private Long lockAccountId;

	/**
	 * 锁定操作员的账户ID
	 *
	 */
	@PositiveOrZero(message = "锁定操作员的账户ID不能为负数")
	private Long lockOpId;

	/**
	 * 锁定原因
	 *
	 */
	@Length(max = 100, message = "锁定原因的长度不能大于100")
	private String lockReason;

	/**
	 * 锁定时间
	 *
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime lockDatetime;

	/**
	 * 解锁原因
	 */
	@Length(max = 100, message = "解锁原因的长度不能大于100")
	private String unlockReason;

	/**
	 * 解锁时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime unlockDatetime;

	/**
	 * 解锁操作员的账户ID
	 */
	@PositiveOrZero(message = "解锁操作员的账户ID不能为负数")
	private Long unlockOpId;

}
