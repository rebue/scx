package rebue.scx.msg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Builder;
import lombok.Data;

/**
 * Smtp邮箱配置
 * 
 * @author yuanman
 *
 */
@Data
@Builder
@Configuration
@ConfigurationProperties(prefix = "msg.smtp")
public class SmtpConfig {

    /**
     * smtp服务地址
     */
    private String host;
    /**
     * 帐号
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 内容模板验证码专用
     */
    private String templeContent;

    public SmtpConfig(String host, String user, String password, String templeContent) {
        super();
        this.host          = host;
        this.user          = user;
        this.password      = password;
        this.templeContent = templeContent;
    }

    public SmtpConfig() {
        super();
    }

}
