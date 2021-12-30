package rebue.scx.msg.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class EmailTemplate {
	
	  private static final long serialVersionUID = 1L;

	/**
	 * 模板邮箱接受人
	 */
	private String[] datas;
	
	/**
	 * 验证码
	 */
	private String var;
}
