package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织
 *
 */
@Data
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class RacOrgModifyDefaultOrgTo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 账户ID
	 *
	 */
	@NotNull(message = "账户ID不能为空")
	@PositiveOrZero(message = "账户ID不能为负数")
	private Long accountId;

	/**
	 * 组织ID
	 *
	 */
	@NotNull(message = "组织ID不能为空")
	@PositiveOrZero(message = "组织ID不能为负数")
	private Long orgId;

}
