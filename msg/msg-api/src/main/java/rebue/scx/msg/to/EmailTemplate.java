package rebue.scx.msg.to;

import lombok.Data;

@Data
public class EmailTemplate {

	/**
	 * 模板邮箱接受人
	 */
	private String[] datas;
	
	/**
	 * 验证码
	 */
	private String var;
}
