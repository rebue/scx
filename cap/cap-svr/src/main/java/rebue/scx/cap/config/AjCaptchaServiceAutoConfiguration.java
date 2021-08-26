package rebue.scx.cap.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import rebue.scx.cap.commom.Const;
import rebue.scx.cap.properties.AjCaptchaProperties;
import rebue.scx.cap.svc.CaptchaService;
import rebue.scx.cap.svc.impl.CaptchaServiceFactory;
import rebue.scx.cap.util.ImageUtils;
import rebue.scx.cap.util.StringUtils;

@Configuration
public class AjCaptchaServiceAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(AjCaptchaServiceAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public CaptchaService captchaService(final AjCaptchaProperties prop) {
        logger.info("自定义配置项：{}", prop.toString());
        final Properties config = new Properties();
        config.put(Const.CAPTCHA_CACHETYPE, prop.getCacheType().name());
        config.put(Const.CAPTCHA_WATER_MARK, prop.getWaterMark());
        config.put(Const.CAPTCHA_FONT_TYPE, prop.getFontType());
        config.put(Const.CAPTCHA_TYPE, prop.getType().getCodeValue());
        config.put(Const.CAPTCHA_INTERFERENCE_OPTIONS, prop.getInterferenceOptions());
        config.put(Const.ORIGINAL_PATH_JIGSAW, prop.getJigsaw());
        config.put(Const.ORIGINAL_PATH_PIC_CLICK, prop.getPicClick());
        config.put(Const.CAPTCHA_SLIP_OFFSET, prop.getSlipOffset());
        config.put(Const.CAPTCHA_AES_STATUS, String.valueOf(prop.getAesStatus()));
        config.put(Const.CAPTCHA_WATER_FONT, prop.getWaterFont());
        config.put(Const.CAPTCHA_CACAHE_MAX_NUMBER, prop.getCacheNumber());
        config.put(Const.CAPTCHA_TIMING_CLEAR_SECOND, prop.getTimingClear());

        config.put(Const.HISTORY_DATA_CLEAR_ENABLE, prop.isHistoryDataClearEnable() ? "1" : "0");

        config.put(Const.REQ_FREQUENCY_LIMIT_ENABLE, prop.getReqFrequencyLimitEnable() ? "1" : "0");
        config.put(Const.REQ_GET_LOCK_LIMIT, prop.getReqGetLockLimit() + "");
        config.put(Const.REQ_GET_LOCK_SECONDS, prop.getReqGetLockSeconds() + "");
        config.put(Const.REQ_GET_MINUTE_LIMIT, prop.getReqGetMinuteLimit() + "");
        config.put(Const.REQ_CHECK_MINUTE_LIMIT, prop.getReqCheckMinuteLimit() + "");
        config.put(Const.REQ_VALIDATE_MINUTE_LIMIT, prop.getReqVerifyMinuteLimit() + "");

        config.put(Const.CAPTCHA_FONT_SIZE, prop.getFontSize() + "");
        config.put(Const.CAPTCHA_FONT_STYLE, prop.getFontStyle() + "");
        config.put(Const.CAPTCHA_WORD_COUNT, prop.getClickWordCount() + "");

        if ((StringUtils.isNotBlank(prop.getJigsaw()) && prop.getJigsaw().startsWith("classpath:"))
                || (StringUtils.isNotBlank(prop.getPicClick()) && prop.getPicClick().startsWith("classpath:"))) {
            //自定义resources目录下初始化底图
            config.put(Const.CAPTCHA_INIT_ORIGINAL, "true");
            initializeBaseMap(prop.getJigsaw(), prop.getPicClick());
        }
        final CaptchaService s = CaptchaServiceFactory.getInstance(config);
        return s;
    }

    private static void initializeBaseMap(final String jigsaw, final String picClick) {
        ImageUtils.cacheBootImage(getResourcesImagesFile(jigsaw + "/original/*.png"),
                getResourcesImagesFile(jigsaw + "/slidingBlock/*.png"),
                getResourcesImagesFile(picClick + "/*.png"));
    }

    public static Map<String, String> getResourcesImagesFile(final String path) {
        final Map<String, String> imgMap = new HashMap<>();
        final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            final Resource[] resources = resolver.getResources(path);
            for (final Resource resource : resources) {
                final byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
                final String string = Base64Utils.encodeToString(bytes);
                final String filename = resource.getFilename();
                imgMap.put(filename, string);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return imgMap;
    }
}