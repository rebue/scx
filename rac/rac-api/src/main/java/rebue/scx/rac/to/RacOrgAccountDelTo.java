package rebue.scx.rac.to;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class RacOrgAccountDelTo implements Serializable {

	private static final long serialVersionUID = 1L;

//	/**
//	 * 组织账户ID
//	 *
//	 */
//	@NotNull(message = "组织账户ID不能为空")
//	@PositiveOrZero(message = "组织账户ID不能为负数")
//	private Long id;

	/**
	 * 组织ID
	 *
	 */
	@NotNull(message = "组织ID不能为空")
	@PositiveOrZero(message = "组织ID不能为负数")
	private Long orgId;

	/**
	 * 账户ID
	 *
	 */
	@NotNull(message = "账户ID不能为空")
	@PositiveOrZero(message = "账户ID不能为负数")
	private List<Long> accountIds;
}
