/*
 *Copyright © 2018 anji-plus
 *http://www.anji-plus.com
 *All rights reserved.
 */
package rebue.scx.cap.svc.impl;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.commom.Const;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.svc.CaptchaService;
import rebue.scx.cap.util.AESUtil;
import rebue.scx.cap.util.CacheUtil;
import rebue.scx.cap.util.ImageUtils;
import rebue.scx.cap.util.MD5Util;
import rebue.scx.cap.util.StringUtils;

public abstract class AbstractCaptchaService implements CaptchaService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String IMAGE_TYPE_PNG = "png";

    protected static int HAN_ZI_SIZE = 25;

    protected static int HAN_ZI_SIZE_HALF = HAN_ZI_SIZE / 2;
    //check校验坐标
    protected static String REDIS_CAPTCHA_KEY = "RUNNING:CAPTCHA:%s";

    //后台二次校验坐标
    protected static String REDIS_SECOND_CAPTCHA_KEY = "RUNNING:CAPTCHA:second-%s";

    protected static Long EXPIRESIN_SECONDS = 2 * 60L;

    protected static Long EXPIRESIN_THREE = 3 * 60L;

    protected static String waterMark = "我的水印";

    protected static String waterMarkFontStr = "WenQuanZhengHei.ttf";

    protected Font waterMarkFont;//水印字体

    protected static String slipOffset = "5";

    protected static Boolean captchaAesStatus = true;

    protected static String clickWordFontStr = "WenQuanZhengHei.ttf";

    protected Font clickWordFont;//点选文字字体

    protected static String cacheType = "redis";

    protected static int captchaInterferenceOptions = 0;

    //判断应用是否实现了自定义缓存，没有就使用内存
    @Override
    public void init(final Properties config) {
        //初始化底图
        final boolean aBoolean = Boolean.parseBoolean(config.getProperty(Const.CAPTCHA_INIT_ORIGINAL));
        if (!aBoolean) {
            ImageUtils.cacheImage(config.getProperty(Const.ORIGINAL_PATH_JIGSAW),
                    config.getProperty(Const.ORIGINAL_PATH_PIC_CLICK));
        }
        logger.info("--->>>初始化验证码底图<<<---" + captchaType());
        waterMark = config.getProperty(Const.CAPTCHA_WATER_MARK, "我的水印");
        slipOffset = config.getProperty(Const.CAPTCHA_SLIP_OFFSET, "5");
        waterMarkFontStr = config.getProperty(Const.CAPTCHA_WATER_FONT, "WenQuanZhengHei.ttf");
        captchaAesStatus = Boolean.parseBoolean(config.getProperty(Const.CAPTCHA_AES_STATUS, "true"));
        clickWordFontStr = config.getProperty(Const.CAPTCHA_FONT_TYPE, "WenQuanZhengHei.ttf");
        //clickWordFontStr = config.getProperty(Const.CAPTCHA_FONT_TYPE, "SourceHanSansCN-Normal.otf");
        cacheType = config.getProperty(Const.CAPTCHA_CACHETYPE, "local");
        captchaInterferenceOptions = Integer.parseInt(
                config.getProperty(Const.CAPTCHA_INTERFERENCE_OPTIONS, "0"));

        // 部署在linux中，如果没有安装中文字段，水印和点选文字，中文无法显示，
        // 通过加载resources下的font字体解决，无需在linux中安装字体
        loadWaterMarkFont();

        if (cacheType.equals("local")) {
            logger.info("初始化local缓存...");
            CacheUtil.init(Integer.parseInt(config.getProperty(Const.CAPTCHA_CACAHE_MAX_NUMBER, "1000")),
                    Long.parseLong(config.getProperty(Const.CAPTCHA_TIMING_CLEAR_SECOND, "180")));
        }
        if (config.getProperty(Const.HISTORY_DATA_CLEAR_ENABLE, "0").equals("1")) {
            logger.info("历史资源清除开关...开启..." + captchaType());
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    destroy(config);
                }
            }));
        }
        if (config.getProperty(Const.REQ_FREQUENCY_LIMIT_ENABLE, "0").equals("1")) {
            if (limitHandler == null) {
                logger.info("接口分钟内限流开关...开启...");
                limitHandler = new FrequencyLimitHandler.DefaultLimitHandler(config, getCacheService(cacheType));
            }
        }
    }

    protected CaptchaCacheService getCacheService(final String cacheType) {
        return CaptchaServiceFactory.getCache(cacheType);
    }

    @Override
    public void destroy(final Properties config) {

    }

    private static FrequencyLimitHandler limitHandler;

    @Override
    public Ro<?> get(final CaptchaVO captchaVO) {
        if (limitHandler != null) {
            captchaVO.setClientUid(getValidateClientId(captchaVO));
            //final ResponseModel validateGet = limitHandler.validateGet(captchaVO);
            //return new Ro<>(ResultDic.SUCCESS, "成功获取model",validateGet);
            return limitHandler.validateGet(captchaVO);
        }
        return null;
    }

    @Override
    public Ro<?> check(final CaptchaVO captchaVO) {
        if (limitHandler != null) {
            // 验证客户端
           /* ResponseModel ret = limitHandler.validateCheck(captchaVO);
            if(!validatedReq(ret)){
                return ret;
            }
            // 服务端参数验证*/
            captchaVO.setClientUid(getValidateClientId(captchaVO));
            return limitHandler.validateCheck(captchaVO);
        }
        return null;
    }

    @Override
    public Ro<?> verification(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return new Ro<>(ResultDic.FAIL, "参数对象不能为空");
            //return RepCodeEnum.NULL_ERROR.parseError("captchaVO");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaVerification())) {
            return new Ro<>(ResultDic.FAIL, "校验参数不能为空");
            //return RepCodeEnum.NULL_ERROR.parseError("captchaVerification");
        }
        if (limitHandler != null) {
            return limitHandler.validateVerify(captchaVO);
        }
        return null;
    }

//    protected boolean validatedReq(final ResponseModel resp) {
//        return resp == null || resp.isSuccess();
//    }
    protected boolean validatedReq(final Ro<?> r) {
        return r == null || r.isSuccess();
    }

    protected String getValidateClientId(final CaptchaVO req){
        // 以服务端获取的客户端标识 做识别标志
        if(StringUtils.isNotEmpty(req.getBrowserInfo())){
            return MD5Util.md5(req.getBrowserInfo());
        }
        // 以客户端Ui组件id做识别标志
        if(StringUtils.isNotEmpty(req.getClientUid())){
            return req.getClientUid();
        }
        return null;
    }

    protected void afterValidateFail(final CaptchaVO data) {
        if (limitHandler != null) {
            // 验证失败 分钟内计数
            final String fails = String.format(FrequencyLimitHandler.LIMIT_KEY, "FAIL", data.getClientUid());
            final CaptchaCacheService cs = getCacheService(cacheType);
            if (!cs.exists(fails)) {
                cs.set(fails, "1", 60);
            }
            cs.increment(fails, 1);
        }
    }

    /**
     * 加载resources下的font字体，add by lide1202@hotmail.com
     * 部署在linux中，如果没有安装中文字段，水印和点选文字，中文无法显示，
     * 通过加载resources下的font字体解决，无需在linux中安装字体
     */
    private void loadWaterMarkFont() {
        try {
            if (waterMarkFontStr.toLowerCase().endsWith(".ttf") || waterMarkFontStr.toLowerCase().endsWith(".ttc")
                    || waterMarkFontStr.toLowerCase().endsWith(".otf")) {
                waterMarkFont = Font.createFont(Font.TRUETYPE_FONT,
                        getClass().getResourceAsStream("/fonts/" + waterMarkFontStr))
                        .deriveFont(Font.BOLD, HAN_ZI_SIZE / 2);
            } else {
                waterMarkFont = new Font(waterMarkFontStr, Font.BOLD, HAN_ZI_SIZE / 2);
            }

        } catch (final Exception e) {
            logger.error("load font error:{}", e);
        }
    }

    public static boolean base64StrToImage(final String imgStr, final String path) {
        if (imgStr == null) {
            return false;
        }

        final Base64.Decoder decoder = Base64.getDecoder();
        try {
            // 解密
            final byte[] b = decoder.decode(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            final File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            final OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * 解密前端坐标aes加密
     *
     * @param point
     * @return
     * @throws Exception
     */
    public static String decrypt(final String point, final String key) throws Exception {
        return AESUtil.aesDecrypt(point, key);
    }

    protected static int getEnOrChLength(final String s) {
        int enCount = 0;
        int chCount = 0;
        for (int i = 0; i < s.length(); i++) {
            final int length = String.valueOf(s.charAt(i)).getBytes(StandardCharsets.UTF_8).length;
            if (length > 1) {
                chCount++;
            } else {
                enCount++;
            }
        }
        final int chOffset = (HAN_ZI_SIZE / 2) * chCount + 5;
        final int enOffset = enCount * 8;
        return chOffset + enOffset;
    }


}
