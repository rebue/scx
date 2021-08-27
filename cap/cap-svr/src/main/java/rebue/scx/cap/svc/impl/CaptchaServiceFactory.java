package rebue.scx.cap.svc.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rebue.scx.cap.commom.Const;
import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.svc.CaptchaService;
import rebue.wheel.api.exception.RuntimeExceptionX;

@Component
public class CaptchaServiceFactory {

    @Autowired
    private  CaptchaCacheServiceMemImpl   captchaCacheServiceMemImpl;
    @Autowired
    private  CaptchaCacheServiceRedisImpl captchaCacheServiceRedisImpl;

    private static CaptchaServiceFactory self;
    @PostConstruct
    void Init() {
        //初始化注入缓存实例
        self = this;
    }
    
    private static Logger                logger = LoggerFactory.getLogger(CaptchaServiceFactory.class);

    public static CaptchaService getInstance(final Properties config) {
        final String         captchaType = config.getProperty(Const.CAPTCHA_TYPE, "default");
        final CaptchaService ret         = instances.get(captchaType);
        if (ret == null) {
            throw new RuntimeException("unsupported-[captcha.type]=" + captchaType);
        }
        ret.init(config);
        return ret;
    }

    public static CaptchaCacheService getCache(final String cacheType) {
        if ("redis".equals(cacheType)) {
            return self.captchaCacheServiceRedisImpl;
        }
        if ("local".equals(cacheType)) {
            return self.captchaCacheServiceMemImpl;
        } 
        throw new RuntimeExceptionX("尚未配置验证缓存类型，请联系管理员");
    }

    public volatile static Map<String, CaptchaService>      instances    = new HashMap<String, CaptchaService>();

    static {
        //将验证码类型加入Map，根据key来使用对应的验证类型
        final ServiceLoader<CaptchaService> services = ServiceLoader.load(CaptchaService.class);
        for (final CaptchaService item : services) {
            instances.put(item.captchaType(), item);
        }
        logger.info("supported-captchaTypes-service:{}", instances.keySet().toString());
    }
}
