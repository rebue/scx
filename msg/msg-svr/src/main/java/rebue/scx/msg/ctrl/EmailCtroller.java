 package rebue.scx.msg.ctrl;

 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.ws.rs.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.cloud.sentinel.SentinelProperties.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;
import ma.glasnost.orika.impl.generator.specification.Convert;
import rebue.scx.msg.api.EmailMessageSendingApi;
import rebue.scx.msg.svc.impl.EmailMessageSendingSvcImpl;




@RestController
@Log4j
 public class EmailCtroller {

// @Resource
 //private EmailUtil emailUtil;
 @Resource
 private EmailMessageSendingApi api;
 @Resource
 private EmailMessageSendingSvcImpl impl;
 
 final String[] data = {"2530364891@qq.com"};
 /**
  * @title 邮箱标题
  * @text 邮箱内容
  * 普通短信发送
  * @return
  */
 @PostMapping("/email/ordinary")
 public String sendEmailOrdinary(@RequestParam("title")String title,@RequestParam("text")String text,@RequestParam("datas")String[] datas) {
   return  impl.SendEmailOrdinary(title, text, datas);
 }
 /**
  * 邮箱的模板发送
  * @return
  */
 @PostMapping("/email/tempale")
 public String sendEmailTempale(@RequestParam("datas")String[] datas) {
    Random random = new Random();
    String varString = random.nextInt(9)+0+"";
    log.info("var"+varString);
    return api.SendEmailTemple(datas,varString);
 }
}

