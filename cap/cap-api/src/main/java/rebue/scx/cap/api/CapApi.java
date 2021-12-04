package rebue.scx.cap.api;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.scx.cap.mo.CaptchaVO;

@Validated
public interface CapApi {
    /**
     * 获取图片信息
     *
     */
    Ro<?> getVo(CaptchaVO to);

    /**
     * 一次验证图片信息
     *
     */
    Ro<?> checkVo(CaptchaVO to);

    /**
     * 二次验证登录信息
     * 
     */
    Ro<?> verification(CaptchaVO captchaVO);

    /**
     * 校验成功后删除验证码缓存
     * 
     */
    void deleteVerifiyCode(CaptchaVO captchaVO);

}
