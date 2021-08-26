/*
 *Copyright © 2018 anji-plus
 *http://www.anji-plus.com
 *All rights reserved.
 */
package rebue.scx.cap.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rebue.scx.cap.commom.CaptchaBaseMapEnum;


public class ImageUtils {
    private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    private static Map<String, String> originalCacheMap = new ConcurrentHashMap();  //滑块底图
    private static Map<String, String> slidingBlockCacheMap = new ConcurrentHashMap(); //滑块
    private static Map<String, String> picClickCacheMap = new ConcurrentHashMap(); //点选文字
    private static Map<String, String[]> fileNameMap = new ConcurrentHashMap<>();

    public static void cacheImage(final String captchaOriginalPathJigsaw, final String captchaOriginalPathClick) {
        //滑动拼图
        if (StringUtils.isBlank(captchaOriginalPathJigsaw)) {
            originalCacheMap.putAll(getResourcesImagesFile("defaultImages/jigsaw/original"));
            slidingBlockCacheMap.putAll(getResourcesImagesFile("defaultImages/jigsaw/slidingBlock"));
        } else {
            originalCacheMap.putAll(getImagesFile(captchaOriginalPathJigsaw + File.separator + "original"));
            slidingBlockCacheMap.putAll(getImagesFile(captchaOriginalPathJigsaw + File.separator + "slidingBlock"));
        }
        //点选文字
        if (StringUtils.isBlank(captchaOriginalPathClick)) {
            picClickCacheMap.putAll(getResourcesImagesFile("defaultImages/pic-click"));
        } else {
            picClickCacheMap.putAll(getImagesFile(captchaOriginalPathClick));
        }
        fileNameMap.put(CaptchaBaseMapEnum.ORIGINAL.getCodeValue(), originalCacheMap.keySet().toArray(new String[0]));
        fileNameMap.put(CaptchaBaseMapEnum.SLIDING_BLOCK.getCodeValue(), slidingBlockCacheMap.keySet().toArray(new String[0]));
        fileNameMap.put(CaptchaBaseMapEnum.PIC_CLICK.getCodeValue(), picClickCacheMap.keySet().toArray(new String[0]));
        logger.info("初始化底图:{}", JsonUtil.toJSONString(fileNameMap));
    }

    public static void cacheBootImage(final Map<String, String> originalMap, final Map<String, String> slidingBlockMap, final Map<String, String> picClickMap) {
        originalCacheMap.putAll(originalMap);
        slidingBlockCacheMap.putAll(slidingBlockMap);
        picClickCacheMap.putAll(picClickMap);
        fileNameMap.put(CaptchaBaseMapEnum.ORIGINAL.getCodeValue(), originalCacheMap.keySet().toArray(new String[0]));
        fileNameMap.put(CaptchaBaseMapEnum.SLIDING_BLOCK.getCodeValue(), slidingBlockCacheMap.keySet().toArray(new String[0]));
        fileNameMap.put(CaptchaBaseMapEnum.PIC_CLICK.getCodeValue(), picClickCacheMap.keySet().toArray(new String[0]));
        logger.info("自定义resource底图:{}", JsonUtil.toJSONString(fileNameMap));
    }


    public static BufferedImage getOriginal() {
        final String[] strings = fileNameMap.get(CaptchaBaseMapEnum.ORIGINAL.getCodeValue());
        if (null == strings || strings.length == 0) {
            return null;
        }
        final Integer randomInt = RandomUtils.getRandomInt(0, strings.length);
        final String s = originalCacheMap.get(strings[randomInt]);
        return getBase64StrToImage(s);
    }

    public static String getslidingBlock() {
        final String[] strings = fileNameMap.get(CaptchaBaseMapEnum.SLIDING_BLOCK.getCodeValue());
        if (null == strings || strings.length == 0) {
            return null;
        }
        final Integer randomInt = RandomUtils.getRandomInt(0, strings.length);
        final String s = slidingBlockCacheMap.get(strings[randomInt]);
        return s;
    }

    public static BufferedImage getPicClick() {
        final String[] strings = fileNameMap.get(CaptchaBaseMapEnum.PIC_CLICK.getCodeValue());
        if (null == strings || strings.length == 0) {
            return null;
        }
        final Integer randomInt = RandomUtils.getRandomInt(0, strings.length);
        final String s = picClickCacheMap.get(strings[randomInt]);
        return getBase64StrToImage(s);
    }

    /**
     * 图片转base64 字符串
     *
     * @param templateImage
     * @return
     */
    public static String getImageToBase64Str(final BufferedImage templateImage) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(templateImage, "png", baos);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final byte[] bytes = baos.toByteArray();

        final Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(bytes).trim();
    }

    /**
     * base64 字符串转图片
     *
     * @param base64String
     * @return
     */
    public static BufferedImage getBase64StrToImage(final String base64String) {
        try {
            final Base64.Decoder decoder = Base64.getDecoder();
            final byte[] bytes = decoder.decode(base64String);
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            return ImageIO.read(inputStream);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Map<String, String> getResourcesImagesFile(final String path) {
        //默认提供六张底图
        final Map<String, String> imgMap = new HashMap<>();
        final ClassLoader classLoader = ImageUtils.class.getClassLoader();
        for (int i = 1; i <= 6; i++) {
            final InputStream resourceAsStream = classLoader.getResourceAsStream(path.concat("/").concat(String.valueOf(i).concat(".png")));
            byte[] bytes = new byte[0];
            try {
                bytes = FileCopyUtils.copyToByteArray(resourceAsStream);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            final String string = Base64Utils.encodeToString(bytes);
            final String filename = String.valueOf(i).concat(".png");
            imgMap.put(filename, string);
        }
        return imgMap;
    }

    private static Map<String, String> getImagesFile(final String path) {
        final Map<String, String> imgMap = new HashMap<>();
        final File file = new File(path);
        if (!file.exists()) {
            return new HashMap<>();
        }
        final File[] files = file.listFiles();
        Arrays.stream(files).forEach(item -> {
            try {
                final FileInputStream fileInputStream = new FileInputStream(item);
                final byte[] bytes = FileCopyUtils.copyToByteArray(fileInputStream);
                final String string = Base64Utils.encodeToString(bytes);
                imgMap.put(item.getName(), string);
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });
        return imgMap;
    }

}
