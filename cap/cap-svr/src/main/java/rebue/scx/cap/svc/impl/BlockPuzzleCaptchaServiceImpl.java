/*
 * Copyright © 2018 anji-plus
 * http://www.anji-plus.com
 * All rights reserved.
 */
package rebue.scx.cap.svc.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.commom.CaptchaTypeEnum;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.mo.PointVO;
import rebue.scx.cap.ra.CaptchaVORa;
import rebue.scx.cap.util.AESUtil;
import rebue.scx.cap.util.ImageUtils;
import rebue.scx.cap.util.JsonUtil;
import rebue.scx.cap.util.RandomUtils;
import rebue.scx.cap.util.StringUtils;

/**
 * 滑动验证码
 */
public class BlockPuzzleCaptchaServiceImpl extends AbstractCaptchaService {

    @Override
    public void init(final Properties config) {
        super.init(config);
    }

    @Override
    public void destroy(final Properties config) {
        logger.info("start-clear-history-data-", captchaType());
    }

    @Override
    public String captchaType() {
        return CaptchaTypeEnum.BLOCKPUZZLE.getCodeValue();
    }

    @Override
    public Ro<?> get(final CaptchaVO captchaVO) {
        final Ro<?> r = super.get(captchaVO);
        if (!validatedReq(r)) {
            return r;
        }
        // 原生图片
        final BufferedImage originalImage = ImageUtils.getOriginal();
        if (null == originalImage) {
            logger.error("滑动底图未初始化成功，请检查路径");
            return new Ro<>(ResultDic.FAIL, "滑动底图未初始化成功，请检查路径");
        }
        // 设置水印
        final Graphics backgroundGraphics = originalImage.getGraphics();
        final int      width              = originalImage.getWidth();
        final int      height             = originalImage.getHeight();
        backgroundGraphics.setFont(waterMarkFont);
        backgroundGraphics.setColor(Color.white);
        backgroundGraphics.drawString(waterMark, width - getEnOrChLength(waterMark), height - (HAN_ZI_SIZE / 2) + 7);

        // 抠图图片
        final String        jigsawImageBase64 = ImageUtils.getslidingBlock();
        final BufferedImage jigsawImage       = ImageUtils.getBase64StrToImage(jigsawImageBase64);
        if (null == jigsawImage) {
            logger.error("滑动底图未初始化成功，请检查路径");
            return new Ro<>(ResultDic.FAIL, "滑动底图未初始化成功，请检查路径");
        }
        final CaptchaVO captcha = pictureTemplatesCut(originalImage, jigsawImage, jigsawImageBase64);
        if (captcha == null
                || StringUtils.isBlank(captcha.getJigsawImageBase64())
                || StringUtils.isBlank(captcha.getOriginalImageBase64())) {
            return new Ro<>(ResultDic.FAIL, "获取验证码失败,请联系管理员");
        }
        final CaptchaVORa ra = new CaptchaVORa();
        ra.setDataVo(captcha);
        return new Ro<>(ResultDic.SUCCESS, "获取验证码成功", ra);
    }

    @Override
    public Ro<?> check(final CaptchaVO captchaVO) {
        final Ro<?> r = super.check(captchaVO);
        if (!validatedReq(r)) {
            return r;
        }
        // 取坐标信息
        final String codeKey = String.format(REDIS_CAPTCHA_KEY, captchaVO.getToken());
        if (!CaptchaServiceFactory.getCache(cacheType).exists(codeKey)) {
            return new Ro<>(ResultDic.FAIL, "验证码已失效，请重新获取");
        }
        final String s = CaptchaServiceFactory.getCache(cacheType).get(codeKey);
        // 验证码只用一次，即刻失效
        CaptchaServiceFactory.getCache(cacheType).delete(codeKey);
        PointVO point     = null;
        PointVO point1    = null;
        String  pointJson = null;
        try {
            point     = JsonUtil.parseObject(s, PointVO.class);
            // aes解密
            pointJson = decrypt(captchaVO.getPointJson(), point.getSecretKey());
            point1    = JsonUtil.parseObject(pointJson, PointVO.class);
        } catch (final Exception e) {
            logger.error("验证码坐标解析失败", e);
            afterValidateFail(captchaVO);
            return new Ro<>(ResultDic.FAIL, "验证码坐标解析失败，请联系管理员");
        }
        if (point.x - Integer.parseInt(slipOffset) > point1.x
                || point1.x > point.x + Integer.parseInt(slipOffset)
                || point.y != point1.y) {
            afterValidateFail(captchaVO);
            return new Ro<>(ResultDic.FAIL, "验证码验证失败");
        }
        // 校验成功，将信息存入缓存
        final String secretKey = point.getSecretKey();
        String       value     = null;
        try {
            value = AESUtil.aesEncrypt(captchaVO.getToken().concat("---").concat(pointJson), secretKey);
        } catch (final Exception e) {
            logger.error("AES加密失败", e);
            afterValidateFail(captchaVO);
            return new Ro<>(ResultDic.FAIL, "加密失败,请联系管理员");
        }
        final String secondKey = String.format(REDIS_SECOND_CAPTCHA_KEY, value);
        CaptchaServiceFactory.getCache(cacheType).set(secondKey, captchaVO.getToken(), EXPIRESIN_THREE);
        captchaVO.setResult(true);
        captchaVO.resetClientFlag();
        final CaptchaVORa ra = new CaptchaVORa();
        ra.setDataVo(captchaVO);
        return new Ro<>(ResultDic.SUCCESS, "验证码校验成功", ra);
    }

    @Override
    public Ro<?> verification(final CaptchaVO captchaVO) {
        final Ro<?> r = super.verification(captchaVO);
        if (!validatedReq(r)) {
            return r;
        }
        try {
            final String codeKey = String.format(REDIS_SECOND_CAPTCHA_KEY, captchaVO.getCaptchaVerification());
            if (!CaptchaServiceFactory.getCache(cacheType).exists(codeKey)) {
                return new Ro<>(ResultDic.FAIL, "验证码已失效，请重新获取");
            }
            // 二次校验取值后，即刻失效
            // CaptchaServiceFactory.getCache(cacheType).delete(codeKey);
        } catch (final Exception e) {
            logger.error("验证码坐标解析失败", e);
            return new Ro<>(ResultDic.FAIL, "验证码坐标解析失败，请联系管理员");
        }
        return new Ro<>(ResultDic.SUCCESS, "验证码校验成功");
    }

    /**
     * 校验成功后删除验证码缓存
     * 
     * @param captchaVO
     * 
     * @return
     */
    @Override
    public Ro<?> deleteVerifiyCode(CaptchaVO captchaVO) {
        final Ro<?> r = super.verification(captchaVO);
        if (!validatedReq(r)) {
            return r;
        }
        try {
            final String codeKey = String.format(REDIS_SECOND_CAPTCHA_KEY, captchaVO.getCaptchaVerification());
            if (!CaptchaServiceFactory.getCache(cacheType).exists(codeKey)) {
                return new Ro<>(ResultDic.FAIL, "验证码已失效，请重新获取");
            }
            // 二次校验取值后，删除验证码缓存
            CaptchaServiceFactory.getCache(cacheType).delete(codeKey);
        } catch (final Exception e) {
            logger.error("验证码坐标解析失败", e);
            return new Ro<>(ResultDic.FAIL, "验证码坐标解析失败，请联系管理员");
        }
        return new Ro<>(ResultDic.SUCCESS, "验证码校验成功");
    }

    /**
     * 根据模板切图
     *
     * @throws Exception
     */
    public CaptchaVO pictureTemplatesCut(final BufferedImage originalImage, final BufferedImage jigsawImage, final String jigsawImageBase64) {
        try {
            final CaptchaVO dataVO         = new CaptchaVO();

            final int       originalWidth  = originalImage.getWidth();
            final int       originalHeight = originalImage.getHeight();
            final int       jigsawWidth    = jigsawImage.getWidth();
            final int       jigsawHeight   = jigsawImage.getHeight();

            // 随机生成拼图坐标
            final PointVO point = generateJigsawPoint(originalWidth, originalHeight, jigsawWidth, jigsawHeight);
            final int     x     = point.getX();
            // final int y = point.getY();

            // 生成新的拼图图像
            BufferedImage    newJigsawImage = new BufferedImage(jigsawWidth, jigsawHeight, jigsawImage.getType());
            final Graphics2D graphics       = newJigsawImage.createGraphics();

            final int        bold           = 5;
            // 如果需要生成RGB格式，需要做如下配置,Transparency 设置透明
            newJigsawImage = graphics.getDeviceConfiguration().createCompatibleImage(jigsawWidth, jigsawHeight, Transparency.TRANSLUCENT);
            // 新建的图像根据模板颜色赋值,源图生成遮罩
            cutByTemplate(originalImage, jigsawImage, newJigsawImage, x, 0);
            if (captchaInterferenceOptions > 0) {
                int position = 0;
                if (originalWidth - x - 5 > jigsawWidth * 2) {
                    // 在原扣图右边插入干扰图
                    position = RandomUtils.getRandomInt(x + jigsawWidth + 5, originalWidth - jigsawWidth);
                }
                else {
                    // 在原扣图左边插入干扰图
                    position = RandomUtils.getRandomInt(100, x - jigsawWidth - 5);
                }
                while (true) {
                    final String s = ImageUtils.getslidingBlock();
                    if (!jigsawImageBase64.equals(s)) {
                        interferenceByTemplate(originalImage, Objects.requireNonNull(ImageUtils.getBase64StrToImage(s)), position, 0);
                        break;
                    }
                }
            }
            if (captchaInterferenceOptions > 1) {
                while (true) {
                    final String s = ImageUtils.getslidingBlock();
                    if (!jigsawImageBase64.equals(s)) {
                        final Integer randomInt = RandomUtils.getRandomInt(jigsawWidth, 100 - jigsawWidth);
                        interferenceByTemplate(originalImage, Objects.requireNonNull(ImageUtils.getBase64StrToImage(s)),
                                randomInt, 0);
                        break;
                    }
                }
            }

            // 设置“抗锯齿”的属性
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            graphics.drawImage(newJigsawImage, 0, 0, null);
            graphics.dispose();

            final ByteArrayOutputStream os = new ByteArrayOutputStream();// 新建流。
            ImageIO.write(newJigsawImage, IMAGE_TYPE_PNG, os);// 利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
            final byte[]                jigsawImages = os.toByteArray();

            final ByteArrayOutputStream oriImagesOs  = new ByteArrayOutputStream();// 新建流。
            ImageIO.write(originalImage, IMAGE_TYPE_PNG, oriImagesOs);// 利用ImageIO类提供的write方法，将bi以jpg图片的数据模式写入流。
            final byte[]         oriCopyImages = oriImagesOs.toByteArray();
            final Base64.Encoder encoder       = Base64.getEncoder();
            dataVO.setOriginalImageBase64(encoder.encodeToString(oriCopyImages).replaceAll("\r|\n", ""));
            // point信息不传到前端，只做后端check校验
            // dataVO.setPoint(point);
            dataVO.setJigsawImageBase64(encoder.encodeToString(jigsawImages).replaceAll("\r|\n", ""));
            dataVO.setToken(RandomUtils.getUUID());
            dataVO.setSecretKey(point.getSecretKey());
            // base64StrToImage(encoder.encodeToString(oriCopyImages), "D:\\原图.png");
            // base64StrToImage(encoder.encodeToString(jigsawImages), "D:\\滑动.png");

            // 将坐标信息存入redis中
            final String codeKey = String.format(REDIS_CAPTCHA_KEY, dataVO.getToken());
            CaptchaServiceFactory.getCache(cacheType).set(codeKey, JsonUtil.toJSONString(point), EXPIRESIN_SECONDS);
            logger.debug("token：{},point:{}", dataVO.getToken(), JsonUtil.toJSONString(point));
            return dataVO;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 随机生成拼图坐标
     *
     * @param originalWidth
     * @param originalHeight
     * @param jigsawWidth
     * @param jigsawHeight
     * 
     * @return
     */
    private static PointVO generateJigsawPoint(final int originalWidth, final int originalHeight, final int jigsawWidth, final int jigsawHeight) {
        final Random random           = new Random();
        final int    widthDifference  = originalWidth - jigsawWidth;
        final int    heightDifference = originalHeight - jigsawHeight;
        int          x, y = 0;
        if (widthDifference <= 0) {
            x = 5;
        }
        else {
            x = random.nextInt(originalWidth - jigsawWidth - 100) + 100;
        }
        if (heightDifference <= 0) {
            y = 5;
        }
        else {
            y = random.nextInt(originalHeight - jigsawHeight) + 5;
        }
        String key = null;
        if (captchaAesStatus) {
            key = AESUtil.getKey();
        }
        return new PointVO(x, y, key);
    }

    /**
     * @param oriImage      原图
     * @param templateImage 模板图
     * @param newImage      新抠出的小图
     * @param x             随机扣取坐标X
     * @param y             随机扣取坐标y
     * 
     * @throws Exception
     */
    private static void cutByTemplate(final BufferedImage oriImage, final BufferedImage templateImage, final BufferedImage newImage, final int x, final int y) {
        // 临时数组遍历用于高斯模糊存周边像素值
        final int[][] martrix = new int[3][3];
        final int[]   values  = new int[9];

        final int     xLength = templateImage.getWidth();
        final int     yLength = templateImage.getHeight();
        // 模板图像宽度
        for (int i = 0; i < xLength; i++) {
            // 模板图片高度
            for (int j = 0; j < yLength; j++) {
                // 如果模板图像当前像素点不是透明色 copy源文件信息到目标图片中
                final int rgb = templateImage.getRGB(i, j);
                if (rgb < 0) {
                    newImage.setRGB(i, j, oriImage.getRGB(x + i, y + j));

                    // 抠图区域高斯模糊
                    readPixel(oriImage, x + i, y + j, values);
                    fillMatrix(martrix, values);
                    oriImage.setRGB(x + i, y + j, avgMatrix(martrix));
                }

                // 防止数组越界判断
                if (i == (xLength - 1) || j == (yLength - 1)) {
                    continue;
                }
                final int rightRgb = templateImage.getRGB(i + 1, j);
                final int downRgb  = templateImage.getRGB(i, j + 1);
                // 描边处理，,取带像素和无像素的界点，判断该点是不是临界轮廓点,如果是设置该坐标像素是白色
                if ((rgb >= 0 && rightRgb < 0) || (rgb < 0 && rightRgb >= 0) || (rgb >= 0 && downRgb < 0) || (rgb < 0 && downRgb >= 0)) {
                    newImage.setRGB(i, j, Color.white.getRGB());
                    oriImage.setRGB(x + i, y + j, Color.white.getRGB());
                }
            }
        }

    }

    /**
     * 干扰抠图处理
     *
     * @param oriImage      原图
     * @param templateImage 模板图
     * @param x             随机扣取坐标X
     * @param y             随机扣取坐标y
     * 
     * @throws Exception
     */
    private static void interferenceByTemplate(final BufferedImage oriImage, final BufferedImage templateImage, final int x, final int y) {
        // 临时数组遍历用于高斯模糊存周边像素值
        final int[][] martrix = new int[3][3];
        final int[]   values  = new int[9];

        final int     xLength = templateImage.getWidth();
        final int     yLength = templateImage.getHeight();
        // 模板图像宽度
        for (int i = 0; i < xLength; i++) {
            // 模板图片高度
            for (int j = 0; j < yLength; j++) {
                // 如果模板图像当前像素点不是透明色 copy源文件信息到目标图片中
                final int rgb = templateImage.getRGB(i, j);
                if (rgb < 0) {
                    // 抠图区域高斯模糊
                    readPixel(oriImage, x + i, y + j, values);
                    fillMatrix(martrix, values);
                    oriImage.setRGB(x + i, y + j, avgMatrix(martrix));
                }
                // 防止数组越界判断
                if (i == (xLength - 1) || j == (yLength - 1)) {
                    continue;
                }
                final int rightRgb = templateImage.getRGB(i + 1, j);
                final int downRgb  = templateImage.getRGB(i, j + 1);
                // 描边处理，,取带像素和无像素的界点，判断该点是不是临界轮廓点,如果是设置该坐标像素是白色
                if ((rgb >= 0 && rightRgb < 0) || (rgb < 0 && rightRgb >= 0) || (rgb >= 0 && downRgb < 0) || (rgb < 0 && downRgb >= 0)) {
                    oriImage.setRGB(x + i, y + j, Color.white.getRGB());
                }
            }
        }

    }

    private static void readPixel(final BufferedImage img, final int x, final int y, final int[] pixels) {
        final int xStart  = x - 1;
        final int yStart  = y - 1;
        int       current = 0;
        for (int i = xStart; i < 3 + xStart; i++) {
            for (int j = yStart; j < 3 + yStart; j++) {
                int tx = i;
                if (tx < 0) {
                    tx = -tx;

                }
                else if (tx >= img.getWidth()) {
                    tx = x;
                }
                int ty = j;
                if (ty < 0) {
                    ty = -ty;
                }
                else if (ty >= img.getHeight()) {
                    ty = y;
                }
                pixels[current++] = img.getRGB(tx, ty);

            }
        }
    }

    private static void fillMatrix(final int[][] matrix, final int[] values) {
        int filled = 0;
        for (int i = 0; i < matrix.length; i++) {
            final int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                x[j] = values[filled++];
            }
        }
    }

    private static int avgMatrix(final int[][] matrix) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < matrix.length; i++) {
            final int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                if (j == 1) {
                    continue;
                }
                final Color c = new Color(x[j]);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r / 8, g / 8, b / 8).getRGB();
    }

}
