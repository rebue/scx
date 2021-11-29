package rebue.msg.fapi;

import com.dtflys.forest.annotation.Request;

public interface msgFapi {

	@Request("https://api.ums.jiguang.cn/v1/template/sent")
	String helloForest();

}
