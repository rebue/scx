/*
 * Copyright © 2018 anji-plus
 * 
 * http://www.anji-plus.com
 * All rights reserved.
 */
package rebue.scx.cap.svc.impl;

import java.util.Properties;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaService;
import rebue.scx.cap.util.StringUtils;
import rebue.wheel.api.exception.RuntimeExceptionX;

public class DefaultCaptchaServiceImpl extends AbstractCaptchaService {

    @Override
    public String captchaType() {
        return "default";
    }

    @Override
    public void init(final Properties config) {
        for (final String s : CaptchaServiceFactory.instances.keySet()) {
            if (captchaType().equals(s)) {
                continue;
            }
            getService(s).init(config);
        }
    }

    @Override
    public void destroy(final Properties config) {
        for (final String s : CaptchaServiceFactory.instances.keySet()) {
            if (captchaType().equals(s)) {
                continue;
            }
            getService(s).destroy(config);
        }
    }

    private CaptchaService getService(final String captchaType) {
        CaptchaService captchaService = CaptchaServiceFactory.instances.get(captchaType);
        if (captchaService == null) {
            throw new RuntimeExceptionX("请求验证类型有错，请联系管理员");
        }
        else {
            return captchaService;
        }
    }

    @Override
    public Ro<?> get(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return new Ro<>(ResultDic.FAIL, "参数对象不能为空");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaType())) {
            return new Ro<>(ResultDic.FAIL, "验证码类型不能为空");
        }
        return getService(captchaVO.getCaptchaType()).get(captchaVO);
    }

    @Override
    public Ro<?> check(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return new Ro<>(ResultDic.FAIL, "参数对象不能为空");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaType())) {
            return new Ro<>(ResultDic.FAIL, "验证码类型不能为空");
        }
        if (StringUtils.isEmpty(captchaVO.getToken())) {
            return new Ro<>(ResultDic.FAIL, "验证码token不能为空");
        }
        return getService(captchaVO.getCaptchaType()).check(captchaVO);
    }

    @Override
    public Ro<?> verification(final CaptchaVO captchaVO) {
        if (captchaVO == null) {
            return new Ro<>(ResultDic.FAIL, "参数对象不能为空");
        }
        if (StringUtils.isEmpty(captchaVO.getCaptchaVerification())) {
            return new Ro<>(ResultDic.FAIL, "二次校验参数不能为空");
        }
        try {
            final String codeKey = String.format(REDIS_SECOND_CAPTCHA_KEY, captchaVO.getCaptchaVerification());
            if (!CaptchaServiceFactory.getCache(cacheType).exists(codeKey)) {
                return new Ro<>(ResultDic.FAIL, "图形验证码已失效，请重新获取");
            }
            // 二次校验取值后，即刻失效
            // CaptchaServiceFactory.getCache(cacheType).delete(codeKey);
        } catch (final Exception e) {
            logger.error("图形验证码坐标解析失败", e);
            return new Ro<>(ResultDic.FAIL, "图形验证码解析失败，请联系管理员");
        }
        return new Ro<>(ResultDic.SUCCESS, "图形验证码二次校验成功");
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

}
