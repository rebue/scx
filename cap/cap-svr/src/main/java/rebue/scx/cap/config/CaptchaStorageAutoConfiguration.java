package rebue.scx.cap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rebue.scx.cap.properties.CaptchaProperties;
import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.svc.impl.CaptchaServiceFactory;

/**
 * 存储策略自动配置.
 *
 */
@Configuration
public class CaptchaStorageAutoConfiguration {

    @Bean(name = "CaptchaCacheService")
    public CaptchaCacheService captchaCacheService(final CaptchaProperties CaptchaProperties) {
        // 缓存类型redis/local/....
        return CaptchaServiceFactory.getCache(CaptchaProperties.getCacheType().name());
    }
}
