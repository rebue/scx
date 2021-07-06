package rebue.wxx.fto;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 发送模板类的消息的参数
 *
 * @author zbz
 *
 */
@Data
public class WxxMessageTemplateSendFto {
	/**
	 * 接收者openid
	 */
	@NotBlank(message = "touser不能为空")
	private String                 touser;
	/**
	 * 模板ID
	 */
	@NotBlank(message = "模板ID不能为空")
	private String                 template_id;
	/**
	 * 模板跳转链接（海外帐号没有跳转能力）
	 */
	private String                 url;
	/**
	 * 跳小程序所需数据，不需跳小程序可不用传该数据
	 */
	private Miniprogram            miniprogram;
	/**
	 * 模板数据
	 *
	 * @mock {"first":{"value":"恭喜你购买成功！","color":"#173177"},"keyword1":{"value":"巧克力","color":"#173177"},"remark":{"value":"欢迎再次购买！","color":"#173177"}}
	 */
	private Map<String, ParamItem> data;
}

/**
 * 跳小程序所需数据，不需跳小程序可不用传该数据
 */
@Data
class Miniprogram {
	/**
	 * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
	 */
	private String appid;
	/**
	 * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
	 */
	private String pagepath;
}

/**
 * 参数项
 */
@Data
class ParamItem {
	/**
	 * 参数项的值
	 */
	private String value;
	/**
	 * 参数项的颜色
	 */
	private String color;
}