package rebue.scx.msg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;

import cn.jsms.api.common.SMSClient;
import lombok.Data;

/**
 * 短信配置
 * 
 * @author yuanman
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "msg.sms")
public class SmsConfig {
    /**
     * 短信平台
     */
    private String  smsPlatform;
    /**
     * 是否模拟短信
     */
    private Boolean simulation = true;
    /**
     * appKey
     */
    private String  appKey;
    /**
     * appSecret
     */
    private String  appSecret;
    /**
     * 短信模板ID
     */
    private String  tempId;
    /**
     * 签名id
     */
    private String  signId;

    /**
     * 获取极光客户端
     */
    public SMSClient getJgSMSClient() {
        return new SMSClient(appSecret, appKey);
    }

    /**
     * 获取百度客户端
     */
    public SmsClient getBaiduSMSClient() {
        // String ACCESS_KEY_ID = "your access key";
        // String SECRET_ACCESS_KEY = "your secret key";
        String                 ENDPOINT = "http://smsv3.bj.baidubce.com";
        SmsClientConfiguration config   = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(appKey, appSecret));
        config.setEndpoint(ENDPOINT);
        return new SmsClient(config);
    }
}
