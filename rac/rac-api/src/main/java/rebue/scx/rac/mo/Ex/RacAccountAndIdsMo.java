package rebue.scx.rac.mo.Ex;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.robotech.valid.ModifyGroup;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacOrgAccountMo;

/**
 * 账户和ids key
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountAndIdsMo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 组织账户下的所有用户关系的orgId
	 *
	 */
	private List<Long> orgIds;
	/**
	 * 组织账户下的所有用户关系ids
	 *
	 */
	@NotNull(groups = ModifyGroup.class, message = "ids是key不能为空")
	private List<RacOrgAccountMo> ids;
	/**
	 * 账户
	 */
	private List<RacAccountMo> list;

}
