package rebue.scx.msg.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 发送邮箱
 * 
 * @author yuanman
 *
 */

@Component
public class MailUtil {

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    /**
     * 发送邮箱
     * 
     * 示例
     * <p>
     * 
     * <pre class="code">
     * String title = "标题";
     * String mailAddrs = "收件人1,收件人1...";
     * Map<String, String> mapImg = new HashMap<String, String>();
     * String cid = "imgCid";
     * String imgPath = "bg1.png";
     * mapImg.put(cid, imgPath);
     * Map<String, String> mapAttachment = new HashMap<String, String>();
     * String fileName = "附件全名称";
     * String filePath = "文件地址";
     * mapAttachment.put(fileName, filePath);
     * // 正文
     * StringBuilder sb = new StringBuilder();
     * sb.append("<html><body>");
     * sb.append("这是有图片有附件的邮件：");
     * sb.append("<img src=\'cid:" + cid + "\'>");
     * sb.append("</body></html>");
     * sendMail(mailAddrs, title, sb.toString(), mapImg, mapAttachment);
     * </pre>
     * 
     * @param mailAddrs     收件人
     * @param title         标题
     * @param text          正文
     * @param mapImg        图片
     * @param mapAttachment 附件
     * 
     * @throws MessagingException
     */
    public void sendMail(String mailAddrs, String title, String text,
            Map<String, String> mapImg, Map<String, String> mapAttachment) throws MessagingException {
        MimeMessage       mimeMessage = javaMailSenderImpl.createMimeMessage();
        // FIXME 多数据源未配置
        String            username    = javaMailSenderImpl.getUsername();
        MimeMessageHelper message     = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setFrom(username);
        message.setTo(mailAddrs.split(","));
        message.setSubject(title);
        message.setText(text, true);
        if (mapImg != null) {
            for (Entry<String, String> entry : mapImg.entrySet()) {
                String key   = entry.getKey();
                String value = entry.getValue();
                message.addInline(key, new ClassPathResource(value));
            }
        }
        if (mapAttachment != null) {
            for (Entry<String, String> entry : mapAttachment.entrySet()) {
                String key   = entry.getKey();
                String value = entry.getValue();
                message.addAttachment(key, new ClassPathResource(value));
            }
        }
        javaMailSenderImpl.send(mimeMessage);
    }

    /**
     * 发送简易邮箱
     * 
     * @param mailAddrs 收件人
     * @param title     标题
     * @param text      正文
     * 
     * @throws MessagingException
     */
    public void sendMail(String mailAddrs, String title, String text) throws MessagingException {
        sendMail(mailAddrs, title, text, null, null);
    }
}
