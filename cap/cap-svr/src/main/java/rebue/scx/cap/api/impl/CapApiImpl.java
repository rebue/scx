package rebue.scx.cap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaService;

@DubboService
public class CapApiImpl implements CapApi {
    @Autowired
    private CaptchaService captchaService;

    @Override
    public Ro<?> getVo(final CaptchaVO mo) {
        return captchaService.get(mo);
    }

    @Override
    public Ro<?> checkVo(final CaptchaVO to) {
        return captchaService.check(to);
    }

    @Override
    public Ro<?> verification(final CaptchaVO captchaVO) {
        final Ro<?> model = captchaService.verification(captchaVO);
        // if (model.getResult().getCode() == 1) {
        // return new Ro<>(ResultDic.SUCCESS, model.getMsg());
        // }
        // else {
        // return new Ro<>(ResultDic.FAIL, model.getMsg());
        // }
        return model;
    }

    /**
     * 校验成功后删除验证码缓存
     * 
     * @param captchaVO
     */
    @Override
    public void deleteVerifiyCode(CaptchaVO captchaVO) {
        captchaService.deleteVerifiyCode(captchaVO);
    }

}
