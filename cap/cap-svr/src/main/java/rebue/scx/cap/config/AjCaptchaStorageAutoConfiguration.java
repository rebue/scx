package rebue.scx.cap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rebue.scx.cap.properties.AjCaptchaProperties;
import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.svc.impl.CaptchaServiceFactory;

/**
 * 存储策略自动配置.
 *
 */
@Configuration
public class AjCaptchaStorageAutoConfiguration {

    @Bean(name = "AjCaptchaCacheService")
    public CaptchaCacheService captchaCacheService(final AjCaptchaProperties ajCaptchaProperties){
        //缓存类型redis/local/....
        return CaptchaServiceFactory.getCache(ajCaptchaProperties.getCacheType().name());
    }
}
