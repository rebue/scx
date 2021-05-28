package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 更改组织与账户的关系的参数
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacModifyOrgAccountTo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 账户ID
	 *
	 */
	@NotNull(message = "账户ID不能为空")
	@PositiveOrZero(message = "账户ID不能为负数")
	private Long accountId;

	/**
	 * 更改成该组织ID
	 *
	 */
	@NotNull(message = "组织ID不能为空")
	@PositiveOrZero(message = "组织ID不能为负数")
	private Long orgId;

	/**
	 * 被修改组织ID
	 *
	 */
	@NotNull(message = "被修改组织ID不能为空")
	@PositiveOrZero(message = "被修改组织ID不能为负数")
	private Long modifyOrgId;
}
