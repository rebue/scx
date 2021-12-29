package rebue.scx.msg.util;

 import java.util.ArrayList;
 import java.util.Base64;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 import com.alibaba.fastjson.JSONObject;

 import lombok.extern.log4j.Log4j;
 @Log4j
 public class EmailUtil{
 	  public static String encoderAndDecode(String text) {
 		  final Base64.Decoder decoder = Base64.getDecoder();
 		  final Base64.Encoder encoder = Base64.getEncoder();
 		  byte[] textByte;
 		  String encodedText ="";
 		  try {
 		  	textByte = text.getBytes("UTF-8");
 		   
 		    // 编码
 		  	encodedText  = encoder.encodeToString(textByte);
 		    System.out.println(encodedText);
 		    // 解码
 		    System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
 		  }
 		catch (Exception e) {
 		 log.info("解码失败");
 		}
 		  return encodedText;
 	}
 	//极光普通短信发送的json字符串生成
 	public static String OrdinaryJson(String title,String text,String[] datas) {
 		 final Map<String, Object> map = new HashMap<String, Object>();
 		  //自定义通道
 		  map.put("instance", "email");
 		  map.put("data",datas);
 		  //邮箱发送msg
 		  final Map<String, Object> map1 = new HashMap<String, Object>();
 		  map1.put("subject", title);
 		  map1.put("text", text);
 		  final List<Object> list = new ArrayList<Object>();
 		  final List<Object> list2 = new ArrayList<Object>();
 		  list.add(map);
 		  list2.add(map1);
 		  final Map<String, Object> maps = new HashMap<String, Object>();
 		  maps.put("aud_email", list);
 		  maps.put("msg_email", list2);
 		  //把map对象转化为接口所能识别的类型
 		  final String json = JSONObject.toJSONString(maps);  
 		return json;
 	}
 	//极光模板json
 	 public static String TempleJson(String[] datas,String var,String templet){
 		  
 		  final String daString = JSONObject.toJSONString(datas);
 		  final String var1 = JSONObject.toJSONString(var);
 	      //模板类型
 		  final String templateString = "{\"aud_email\": [{\"instance\": \"email\",\"data\": " + daString
 		  + "}],\"template_id\":"+templet+",\"template_para\":{\"var\":" + var1 + "}}";
 		  final JSONObject jo = JSONObject.parseObject(new String(templateString));
 		  final String jsonString = JSONObject.toJSONString(jo);
 		  return jsonString;
 	 }
 	
 	
 	
 }
