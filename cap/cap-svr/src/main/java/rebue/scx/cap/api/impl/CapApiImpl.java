package rebue.scx.cap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.commom.ResponseModel;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.ra.CaptchaVORa;
import rebue.scx.cap.svc.CaptchaService;
import rebue.wheel.core.util.OrikaUtils;

@DubboService
public class CapApiImpl implements CapApi {
    @Autowired
    private CaptchaService captchaService;

    @Override
    public Ro<CaptchaVORa> getVo(final CaptchaVO to) {
        System.out.println("11111111111111111111111111111111");
        final CaptchaVO     mo      = OrikaUtils.map(to, CaptchaVO.class);
        final ResponseModel model   = captchaService.get(mo);
        final CaptchaVO     repData = (CaptchaVO) model.getRepData();
        final CaptchaVORa   ra      = new CaptchaVORa();
        final rebue.scx.cap.mo.CaptchaVO  cmVo    = OrikaUtils.map(repData, rebue.scx.cap.mo.CaptchaVO.class);
        ra.setDataVo(repData);
        return new Ro<>(ResultDic.SUCCESS, "获取验证码成功", ra);

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
        final ResponseModel model = captchaService.verification(captchaVO);
        if (model.getRepCode().equals("0000")) {
            return new Ro<>(ResultDic.SUCCESS, "校验成功");
        }
        else {
            return new Ro<>(ResultDic.FAIL, "校验失败");

        }
    }

}
