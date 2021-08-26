package rebue.scx.cap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.ra.CaptchaVORa;
import rebue.scx.cap.svc.CaptchaService;

@DubboService
public class CapApiImpl implements CapApi {
    @Autowired
    private CaptchaService captchaService;

    @Override
    public Ro<?> getVo(final CaptchaVO mo) {
        // TODO Auto-generated method stub
        return null;

    }

    @Override
    public Ro<CaptchaVORa> checkVo(final CaptchaVO to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ro<?> verifyVo(final String verification) {
        final CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(verification);
        final Ro<?> model = captchaService.verification(captchaVO);
        if (model.getResult().getCode()==1) {
            return new Ro<>(ResultDic.SUCCESS, "校验成功");
        }
        else {
            return new Ro<>(ResultDic.FAIL, "校验失败");

        }
    }

}
