package rebue.scx.msg.to;

import lombok.Data;

@Data
public class EmailOrdinary {
	/**
	 * 邮箱标题
	 */
	private String title;
	
      /**
       * 邮箱文本内容
       */
	private String text;
	/**
	 * 接受邮箱人
	 */
	private String[] datas;
	
}
