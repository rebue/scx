package rebue.scx.msg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "msg.email")
public class EmailConfig {
	 /**
     * 短信平台
     */
    //private String  emailPlatform;
    /**
     * 是否模拟短信
     */
    private Boolean simulation = true;
    /**
     * appKey
     */
    private String  appKey;
    /**
     * 短信模板ID
     */
    private Integer  tempId;
    /**
     * appSecret
     */
    private String  appSecret;
    
    private String emailTempletEndpoint;
    private String emailOrdinaryEndpoint;
    

}
