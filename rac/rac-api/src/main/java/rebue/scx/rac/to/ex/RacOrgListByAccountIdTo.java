package rebue.scx.rac.to.ex;

import java.io.Serializable;

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
public class RacOrgListByAccountIdTo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 账户ID
	 *
	 */
	private Long accountId;

	/**
	 * 组织ID
	 *
	 */
	private Long orgId;

}
