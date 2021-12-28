
  package rebue.scx.msg.svc.impl;
  
  import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Component;

import com.alibaba.cloud.nacos.NacosConfigProperties.Config;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.log4j.Log4j;
import rebue.msg.fapi.msgFapi;
import rebue.scx.msg.config.EmailConfig;
import rebue.scx.msg.svc.EmailMessageSendingSvc;
  
  @Component 
  @Log4j
  public class EmailMessageSendingSvcImpl implements EmailMessageSendingSvc{
  
  @Resource 
  private msgFapi msgFapi;
  
  @Resource
  private EmailConfig conf;
  /**
   * 普通短信处理
   * @param title 标题
   * @param text 内容
   * @param datas 接收人
   * @return
   */
  public String encoderAndDecode(String text) {
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
  @Override
  public String SendEmailCustom(String title,String text,String[] datas){
	  
	  final Map<String, Object> map = new HashMap();
	  //自定义通道
	  map.put("instance", "email");
	  map.put("data",datas);
	  //邮箱发送msg
	  final Map<String, Object> map1 = new HashMap();
	  map1.put("subject", title);
	  map1.put("text", text);
	  final List<Object> list = new ArrayList<Object>();
	  final List<Object> list2 = new ArrayList<Object>();
	  list.add(map);
	  list2.add(map1);
	  final Map<String, Object> maps = new HashedMap();
	  maps.put("aud_email", list);
	  maps.put("msg_email", list2);
	  //把map对象转化为接口所能识别的类型
	  final String json = JSONObject.toJSONString(maps);  
	  //auth_string
	  String authString = conf.getAppKey();
	  authString = encoderAndDecode(authString);
	  String appKey = "Basic "+authString;
	  final String result = msgFapi.sendEmailCustom(json,conf.getEmailPlatform(),appKey);
	
     return result; 
        }
  /**
   * 模板邮箱信息处理
   * @param datas 接受人
   * @param var 验证码
   * @return
   */
  @Override
  public String SendEmailTemple(String[] datas,String var){
	  
	  final String daString = JSONObject.toJSONString(datas);
	  final String var1 = JSONObject.toJSONString(var);
      //模板类型
	  final String templateString = "{\"aud_email\": [{\"instance\": \"email\",\"data\": " + daString
	  + "}],\"template_id\":"+conf.getTempId()+",\"template_para\":{\"var\":" + var1 + "}}";
	  final JSONObject jo = JSONObject.parseObject(new String(templateString));
	  final String jsonString = JSONObject.toJSONString(jo);
	  //auth_string
	  String authString = conf.getAppKey();
	  authString = encoderAndDecode(authString);
	  String appKey = "Basic "+authString;
	  final String result = msgFapi.sendEmailTemplet(jsonString,conf.getEmailPlatform(),appKey);
	  return result; 
  }
  
  }
 