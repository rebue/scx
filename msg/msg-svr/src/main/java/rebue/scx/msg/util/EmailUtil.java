package rebue.scx.msg.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.log4j.Log4j;
import rebue.scx.msg.to.EmailOrdinary;
import rebue.scx.msg.to.EmailTemplate;
import rebue.wheel.api.exception.RuntimeExceptionX;

@Log4j
public class EmailUtil {

    /**
     * 极光邮箱作者appkey-secret的编码
     * 
     * @param text
     * 
     * @return
     */
    public static String getEncoderToString(String text) {
        final Base64.Encoder encoder     = Base64.getEncoder();
        byte[]               textByte;
        String               encodedText = "";
        try {
            textByte    = text.getBytes("UTF-8");
            // 编码
            encodedText = encoder.encodeToString(textByte);
        } catch (Exception e) {
            log.info("编码失败");
            throw new RuntimeExceptionX("       编码失败        ");
        }
        return encodedText;
    }

    /**
     * 极光普通邮箱发送的json字符串生成
     * 
     * @param title
     * @param text
     * @param datas
     * 
     * @return
     */
    public static String getOrdinaryJson(EmailOrdinary emailOrdinary) {
        final Map<String, Object> map = new HashMap<String, Object>();
        // 自定义通道
        map.put("instance", "email");
        map.put("data", emailOrdinary.getDatas());
        // 邮箱发送msg
        final Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("subject", emailOrdinary.getTitle());
        map1.put("text", emailOrdinary.getText());
        final List<Object> list  = new ArrayList<Object>();
        final List<Object> list2 = new ArrayList<Object>();
        list.add(map);
        list2.add(map1);
        final Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("aud_email", list);
        maps.put("msg_email", list2);
        // 把map对象转化为接口所能识别的类型
        final String json = JSONObject.toJSONString(maps);
        return json;
    }

    /**
     * 极光模板生成json
     * 
     * @param datas
     * @param var
     * @param templet
     * 
     * @return
     */
    public static String getTempleJson(EmailTemplate emailTemplate, Integer templet) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("instance", "email");
        map.put("data", emailTemplate.getDatas());
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("var", emailTemplate.getVar());
        List<Object> list = new ArrayList<Object>();
        list.add(map);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("aud_email", list);
        map3.put("template_id", templet);
        map3.put("template_para", map2);

        String json = JSONObject.toJSONString(map3);

        return json;
    }

}
