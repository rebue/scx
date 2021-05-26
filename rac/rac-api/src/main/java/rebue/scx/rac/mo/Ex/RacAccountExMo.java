package rebue.scx.rac.mo.Ex;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rebue.scx.rac.mo.RacAccountMo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RacAccountExMo extends RacAccountMo {

	private static final long serialVersionUID = 1L;
	/**
	 * 关键字
	 */
	@Length(max = 256, message = "搜索关键字不能超过20位数")
	private String keywords;
}
