package rebue.msg.fapi;

import java.util.Map;

import com.dtflys.forest.annotation.Get;

public interface MsgFapi {

	/**
	 * 聪明的你一定看出来了@Get注解代表该方法专做GET请求 在url中的{0}代表引用第一个参数，{1}引用第二个参数
	 */
	@Get("http://ditu.amap.com/service/regeo?longitude={0}&latitude={1}")
	Map getLocation(String longitude, String latitude);

}