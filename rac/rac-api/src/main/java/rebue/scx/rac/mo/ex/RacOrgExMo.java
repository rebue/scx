package rebue.scx.rac.mo.ex;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rebue.scx.rac.mo.RacOrgMo;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RacOrgExMo extends RacOrgMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 关键字
	 */
	@Getter
	@Setter
	@Length(max = 20, message = "搜索关键字不能超过20位数")
	private String keywords;

}
