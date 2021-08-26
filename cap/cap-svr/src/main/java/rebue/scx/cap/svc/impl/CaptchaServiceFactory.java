package rebue.scx.cap.svc.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rebue.scx.cap.commom.Const;
import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.svc.CaptchaService;


public class CaptchaServiceFactory {

    private static Logger logger = LoggerFactory.getLogger(CaptchaServiceFactory.class);

    public static CaptchaService getInstance(final Properties config) {
        //先把所有CaptchaService初始化，通过init方法，实例字体等，add by lide1202@hotmail.com
        /*try{
            for(CaptchaService item: instances.values()){
                item.init(config);
            }
        }catch (Exception e){
            logger.warn("init captchaService fail:{}", e);
        }*/

        final String captchaType = config.getProperty(Const.CAPTCHA_TYPE, "default");
        final CaptchaService ret = instances.get(captchaType);
        if (ret == null) {
            throw new RuntimeException("unsupported-[captcha.type]=" + captchaType);
        }
        ret.init(config);
        return ret;
    }

    public static CaptchaCacheService getCache(final String cacheType) {
        return cacheService.get(cacheType);
    }

    public volatile static Map<String, CaptchaService> instances = new HashMap<String, CaptchaService>();
    public volatile static Map<String, CaptchaCacheService> cacheService = new HashMap<String, CaptchaCacheService>();

    static {
        final ServiceLoader<CaptchaCacheService> cacheServices = ServiceLoader.load(CaptchaCacheService.class);
        for (final CaptchaCacheService item : cacheServices) {
            cacheService.put(item.type(), item);
        }
        logger.info("supported-captchaCache-service:{}", cacheService.keySet().toString());
        final ServiceLoader<CaptchaService> services = ServiceLoader.load(CaptchaService.class);
        for (final CaptchaService item : services) {
            instances.put(item.captchaType(), item);
        }
        logger.info("supported-captchaTypes-service:{}", instances.keySet().toString());
    }
}
