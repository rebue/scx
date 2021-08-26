/*
 *Copyright © 2018 anji-plus

 *http://www.anji-plus.com
 *All rights reserved.
 */
package rebue.scx.cap.svc.impl;

import java.util.Properties;

import rebue.scx.cap.commom.RepCodeEnum;
import rebue.scx.cap.commom.ResponseModel;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaService;
import rebue.scx.cap.util.StringUtils;

public class DefaultCaptchaServiceImpl extends AbstractCaptchaService{

    @Override
    public String captchaType() {
        return "default";
    }

    @Override
    public void init(final Properties config) {
        for (final String s : CaptchaServiceFactory.instances.keySet()) {
            if(captchaType().equals(s)){
                continue;
            }
            getService(s).init(config);
        }
    }

    @Override
    public void destroy(final Properties config) {
        for (final String s : CaptchaServiceFactory.instances.keySet()) {
            if(captchaType().equals(s)){
                continue;
            }
            getService(s).destroy(config);
        }
    }

    private CaptchaService getService(final String captchaType){
        return CaptchaServiceFactory.instances.get(captchaType);
    }

    @Override
    public ResponseModel get(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return RepCodeEnum.NULL_ERROR.parseError("captchaVO");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaType())) {
            return RepCodeEnum.NULL_ERROR.parseError("类型");
        }
        return getService(captchaVO.getCaptchaType()).get(captchaVO);
    }

    @Override
    public ResponseModel check(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return RepCodeEnum.NULL_ERROR.parseError("captchaVO");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaType())) {
            return RepCodeEnum.NULL_ERROR.parseError("类型");
        }
        if (StringUtils.isEmpty(captchaVO.getToken())) {
            return RepCodeEnum.NULL_ERROR.parseError("token");
        }
        return getService(captchaVO.getCaptchaType()).check(captchaVO);
    }

    @Override
    public ResponseModel verification(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return RepCodeEnum.NULL_ERROR.parseError("captchaVO");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaVerification())) {
            return RepCodeEnum.NULL_ERROR.parseError("二次校验参数");
        }
        try {
            final String codeKey = String.format(REDIS_SECOND_CAPTCHA_KEY, captchaVO.getCaptchaVerification());
            if (!CaptchaServiceFactory.getCache(cacheType).exists(codeKey)) {
                return ResponseModel.errorMsg(RepCodeEnum.API_CAPTCHA_INVALID);
            }
            //二次校验取值后，即刻失效
            CaptchaServiceFactory.getCache(cacheType).delete(codeKey);
        } catch (final Exception e) {
            logger.error("验证码坐标解析失败", e);
            return ResponseModel.errorMsg(e.getMessage());
        }
        return ResponseModel.success();
    }

}
