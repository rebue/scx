/*
 * Copyright © 2018 anji-plus
 * 安吉加加信息技术有限公司
 * http://www.anji-plus.com
 * All rights reserved.
 */
package rebue.scx.cap.mo;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;


public class CaptchaVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 验证码id(后台申请)
     */
    private String            captchaId;

    private String            projectCode;

    /**
     * 验证码类型:(clickWord,blockPuzzle)
     */
    private String            captchaType;

    private String            captchaOriginalPath;

    private String            captchaFontType;

    private Integer           captchaFontSize;

    private String            secretKey;

    /**
     * 原生图片base64
     */
    private String            originalImageBase64;

    /**
     * 滑块点选坐标
     */
    private PointVO           point;

    /**
     * 滑块图片base64
     */
    private String            jigsawImageBase64;

    /**
     * 点选文字
     */
    private List<String>      wordList;

    /**
     * 点选坐标
     */
    private List<Point>       pointList;

    /**
     * 点坐标(base64加密传输)
     */
    private String            pointJson;

    /**
     * UUID(每次请求的验证码唯一标识)
     */
    private String            token;

    /**
     * 校验结果
     */
    private Boolean           result           = false;

    /**
     * 后台二次校验参数
     */
    private String            captchaVerification;

    /***
     * 客户端UI组件id,组件初始化时设置一次，UUID
     */
    private String            clientUid;
    /***
     * 客户端的请求时间，预留字段
     */
    private Long              ts;

    /***
     * 客户端ip+userAgent
     */
    private String            browserInfo;

    public void resetClientFlag() {
        browserInfo = null;
        clientUid   = null;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(final String captchaId) {
        this.captchaId = captchaId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(final String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(final String captchaType) {
        this.captchaType = captchaType;
    }

    public String getCaptchaOriginalPath() {
        return captchaOriginalPath;
    }

    public void setCaptchaOriginalPath(final String captchaOriginalPath) {
        this.captchaOriginalPath = captchaOriginalPath;
    }

    public String getCaptchaFontType() {
        return captchaFontType;
    }

    public void setCaptchaFontType(final String captchaFontType) {
        this.captchaFontType = captchaFontType;
    }

    public Integer getCaptchaFontSize() {
        return captchaFontSize;
    }

    public void setCaptchaFontSize(final Integer captchaFontSize) {
        this.captchaFontSize = captchaFontSize;
    }

    public String getOriginalImageBase64() {
        return originalImageBase64;
    }

    public void setOriginalImageBase64(final String originalImageBase64) {
        this.originalImageBase64 = originalImageBase64;
    }

    public PointVO getPoint() {
        return point;
    }

    public void setPoint(final PointVO point) {
        this.point = point;
    }

    public String getJigsawImageBase64() {
        return jigsawImageBase64;
    }

    public void setJigsawImageBase64(final String jigsawImageBase64) {
        this.jigsawImageBase64 = jigsawImageBase64;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(final List<String> wordList) {
        this.wordList = wordList;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(final List<Point> pointList) {
        this.pointList = pointList;
    }

    public String getPointJson() {
        return pointJson;
    }

    public void setPointJson(final String pointJson) {
        this.pointJson = pointJson;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(final Boolean result) {
        this.result = result;
    }

    public String getCaptchaVerification() {
        return captchaVerification;
    }

    public void setCaptchaVerification(final String captchaVerification) {
        this.captchaVerification = captchaVerification;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClientUid() {
        return clientUid;
    }

    public void setClientUid(final String clientUid) {
        this.clientUid = clientUid;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(final Long ts) {
        this.ts = ts;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(final String browserInfo) {
        this.browserInfo = browserInfo;
    }
}
