package rebue.msg.fapi;

import java.util.List;
import java.util.Map;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.JSONQuery;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Request;
import com.dtflys.forest.annotation.Var;

public interface msgFapi {

	
	/**
	 * 普通短信发送
	 * @param json
	 * @return
	 */
	@Post(url="https://api.ums.jiguang.cn/v1/sent",headers = {"Accept-Charset: UTF-8",
			"Content-Type: application/json",
			"Authorization: ${appKey}",
	})
	String SendEmailOrdinary(@JSONBody() Object json,@Var("appKey")String appKey);


	/**
	 * 邮箱模板调用
	 * @param json
	 * @return
	 */
	@Post(url="https://api.ums.jiguang.cn/v1/template/sent",headers = {"Accept-Charset: UTF-8",
            "Content-Type: application/json",
            "Authorization:${appKey}",
              })
String sendEmailTemplet(@JSONBody() Object json,@Var("appKey")String appKey);
}
