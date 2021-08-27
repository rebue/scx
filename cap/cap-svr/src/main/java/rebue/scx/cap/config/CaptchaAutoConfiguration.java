package rebue.scx.cap.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rebue.scx.cap.properties.CaptchaProperties;

@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
@ComponentScan("rebue.scx.cap")
@Import({CaptchaServiceAutoConfiguration.class, CaptchaStorageAutoConfiguration.class})
public class CaptchaAutoConfiguration {
}
