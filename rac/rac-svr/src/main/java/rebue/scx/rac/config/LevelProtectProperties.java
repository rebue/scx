package rebue.scx.rac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 登保配置
 * 
 * @author yuanman
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "level-protect")
public class LevelProtectProperties {
    /**
     * 账号锁定时长/分钟
     */
    private String lockDuration;
    /**
     * 密码最小长度/位数
     */
    private String passwordMinLength;
    /**
     * 密码最少包含字符/种
     */
    private String passwordCharacter;
    /**
     * 是否强制修改密码
     */
    private String passwordTips;
    /**
     * 输入密码错误次数/次锁定
     */
    private String passwordErrors;
    /**
     * 密码过期时长/天
     */
    private String passwordDoverdue;
}
