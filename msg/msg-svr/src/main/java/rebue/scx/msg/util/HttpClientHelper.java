//package rebue.scx.msg.util;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections.map.HashedMap;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.StringRequestEntity;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.http.HttpException;
//
//import com.alibaba.fastjson.JSONObject;
//
//public class HttpClientHelper {
//	public static String sendPost(final String urlParam) throws HttpException, IOException {
//		// 创建httpClient实例对象
//		final HttpClient httpClient = new HttpClient();
//		// 设置httpClient连接主机服务器超时时间：15000毫秒
//		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
//		// 创建post请求方法实例对象
//		final PostMethod postMethod = new PostMethod(urlParam);
//		// 设置post请求超时时间
//		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
//		postMethod.addRequestHeader("Content-Type", "application/json");
//		// getMethod.addRequestHeader("Basic",
//		// "10001275A644497:c3a12648939a4cde9ca2e4a5");
//		final Base64.Decoder decoder = Base64.getDecoder();
//		final Base64.Encoder encoder = Base64.getEncoder();
//		final String text = "10001275A644497:c3a12648939a4cde9ca2e4a5";
//		final byte[] textByte = text.getBytes("UTF-8");
//		// 编码
//		final String encodedText = encoder.encodeToString(textByte);
//		System.out.println(encodedText);
//		// 解码
//		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
//
//		postMethod.addRequestHeader("Authorization", "Basic " + encodedText);
//	final String templateString = "{\"aud_email\": [{\"instance\": \"email\",\"data\": [\"1782271387@qq.com\"]}],\"template_id\":10522,\"template_para\":{\"var\":\"556655\"}}";
//		// final String string = "{\"aud_email\": [{\"instance\": \"email\",\"data\":
//		// [\"1782271387@qq.com\"]}],\"msg_email\": [{\"subject\": \"hello, ums
//		// email!\",\"text\": \"ums emailtest.reply\"}],\"rule_id\": 0}";
////		final JSONObject jo = JSONObject.parseObject(new String(string));
////		final String jsonString = JSONObject.toJSONString(jo);
//// Part[] templateParts = {
//// new StringPart("aud_email", "[{\"instance\": \"email\",\"data\": [\"1782271387@qq.com\"]}]"),
//// new StringPart("template_id", "10522"),
//// new StringPart("template_para", "{\"var\":\"556655\"}")
//// };
//		final String[] strings = { "2530364891@qq.com" };
//		final Map<String, Object> map = new HashMap();
//		map.put("instance", "email");
//		map.put("data", strings);
//		final Map<String, Object> map1 = new HashMap();
//		map1.put("subject", "迈越软件测试测试邮箱");
//		map1.put("text", "测试est");
//		final List<Object> list = new ArrayList<Object>();
//		final List<Object> list2 = new ArrayList<Object>();
//		list.add(map);
//		list2.add(map1);
//		final Map<String, Object> maps = new HashedMap();
//		maps.put("aud_email", list);
//		maps.put("msg_email", list2);
//		final String json = JSONObject.toJSONString(maps);
////		final NameValuePair[] parts = {
////				new NameValuePair("aud_email", "[{\"instance\": \"email\",\"data\": [\"2530364891@qq.com\"]}]"),
////				new NameValuePair("msg_email",
////						"[{\\\"subject\\\": \\\"迈越软件测试测试邮箱\\\",\\\"text\\\": \\\"ums email test. reply\\\"}]"),
////				new NameValuePair("rule_id", "0") };
//
//		// postMethod.setRequestBody(jo);
//		// postMethod.setRequestBody(string);
//		postMethod.setRequestEntity(new StringRequestEntity(json, "text/html", "UTF-8"));
//		httpClient.executeMethod(postMethod);
//
//		final String result = postMethod.getResponseBodyAsString();
//		postMethod.releaseConnection(); // 出错
//		return result;
//	}
//
//	/*
//	 * public static String sendGet(final String urlParam) throws HttpException,
//	 * IOException { // 创建httpClient实例对象 final HttpClient httpClient = new
//	 * HttpClient(); // 设置httpClient连接主机服务器超时时间：15000毫秒
//	 * httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000)
//	 * ; // 创建GET请求方法实例对象 final GetMethod getMethod = new GetMethod(urlParam); //
//	 * 设置post请求超时时间 getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
//	 * 60000); getMethod.addRequestHeader("Content-Type", "application/json");
//	 * httpClient.executeMethod(getMethod);
//	 * 
//	 * final String result = getMethod.getResponseBodyAsString();
//	 * getMethod.releaseConnection(); return result; }
//	 */
//
//	public static void main(final String[] args) throws HttpException, IOException {
//		final String templateUrl = "https://api.ums.jiguang.cn/v1/template/sent";
//		// System.out.println(sendPost(templateUrl));
//		final String url = "https://api.ums.jiguang.cn/v1/sent";
//		System.out.println(sendPost(url));
//		// System.out.println(sendGet(url));
//	}
//}