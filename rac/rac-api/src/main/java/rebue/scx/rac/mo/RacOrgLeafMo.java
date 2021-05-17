package rebue.scx.rac.mo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 组织(含isLeaf)
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RacOrgLeafMo extends RacOrgMo {

	private static final long serialVersionUID = 1L;

	/**
	 * 判断是否有子节点，有则为false
	 *
	 */
	@NotNull(message = "isLeaf不能为空")
	private Boolean isLeaf;

}
