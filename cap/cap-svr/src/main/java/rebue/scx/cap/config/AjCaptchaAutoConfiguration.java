package rebue.scx.cap.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rebue.scx.cap.properties.AjCaptchaProperties;

@Configuration
@EnableConfigurationProperties(AjCaptchaProperties.class)
@ComponentScan("rebue.scx.cap")
@Import({AjCaptchaServiceAutoConfiguration.class, AjCaptchaStorageAutoConfiguration.class})
public class AjCaptchaAutoConfiguration {
}
