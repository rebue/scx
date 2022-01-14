package rebue.scx.msg.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.log4j.Log4j;
import rebue.scx.msg.config.SmtpConfig;
import rebue.scx.msg.to.EmailOrdinary;
import rebue.scx.msg.to.EmailTemplate;
import rebue.wheel.api.exception.RuntimeExceptionX;

@Log4j
public class JGEmailUtil {

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

    /**
     * 发送邮箱的key，
     * 收件人mailAddr
     * 邮件标题subject
     * 邮件内容content
     * 
     * @author yuanman
     */
    enum MailKey {
        mailAddr, subject, content
    }

    /**
     * 生成邮件消息
     * 
     * @param mailAddrs 收件人直接用String多个地址用逗号","隔开
     * @param title     邮件标题
     * @param content   邮件内容
     */
    public static Map<String, String> genMailMap(String mailAddrs, String title, String content) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(MailKey.mailAddr.name(), mailAddrs);
        map.put(MailKey.subject.name(), title);
        map.put(MailKey.content.name(), content);
        return map;
    }

    /**
     * 发送模板邮件(可多条多内容)
     * 
     * @param list   ist.add(genMailMap(?,?,?))
     * @param config
     */
    public static void sendMail(List<Map<String, String>> list, SmtpConfig config) {
        sendMail(list, config, true);
    }

    /**
     * 发送邮件(可多条多内容)
     * 
     * @param list   list.add(genMailMap(?,?,?))
     * @param config 配置
     * @param boo    是否使用模板内容
     */
    public static void sendMail(List<Map<String, String>> list, SmtpConfig config, boolean boo) {
        Session     mailSession = getEmailSession(config);
        // 创建邮件消息
        MimeMessage message     = new MimeMessage(mailSession);
        try {
            // 设置发送时间
            message.setSentDate(new Date());
            // 设置发件人
            InternetAddress form = new InternetAddress(
                    config.getUser());
            message.setFrom(form);
            for (Map<String, String> map : list) {
                // 验证码
                String content = map.get(MailKey.content.name());
                if (boo) {
                    content = config.getTempleContent().replaceFirst("{{code}}", content);
                }
                // 设置收件人
                // InternetAddress to = new InternetAddress("1782271387@qq.com");
                // 支持群发，多地址用逗号分隔
                message.setRecipients(RecipientType.TO, map.get(MailKey.mailAddr.name()));
                // 设置邮件标题
                message.setSubject(map.get(MailKey.subject.name()));
                // 设置邮件的内容体
                message.setContent(content, "text/html;charset=UTF-8");
                // 发送邮件
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取创建邮件会话
     * 
     * @param config
     */
    public static Session getEmailSession(SmtpConfig config) {
        Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        // props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", config.getHost());
        // smtp登陆的账号、密码 ；需开启smtp登陆
        props.put("mail.user", config.getUser());
        // 访问SMTP服务时需要提供的密码,不是邮箱登陆密码，一般都有独立smtp的登陆密码
        props.put("mail.password", config.getPassword());
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator auth        = new Authenticator() {
                                      @Override
                                      protected PasswordAuthentication getPasswordAuthentication() {
                                          // 用户名、密码
                                          String userName = props.getProperty("mail.user");
                                          String password = props.getProperty("mail.password");
                                          return new PasswordAuthentication(userName, password);
                                      }
                                  };
        // 使用配置属性和授权信息，创建邮件会话
        Session       mailSession = Session.getInstance(props, auth);
        mailSession.setDebug(true);
        return mailSession;
    }

}
