package rebue.scx.cap.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rebue.scx.cap.commom.ResponseModel;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaService;



/**
 * 后端二次校验接口示例
 */
@RestController
//@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/auth/login")
    public ResponseModel get(@RequestParam("captchaVerification") final String captchaVerification) {
        final CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(captchaVerification);
        final ResponseModel response = captchaService.verification(captchaVO);
        if(response.isSuccess() == false){
            //验证码校验失败，返回信息告诉前端
            //repCode  0000  无异常，代表成功
            //repCode  9999  服务器内部异常
            //repCode  0011  参数不能为空
            //repCode  6110  验证码已失效，请重新获取
            //repCode  6111  验证失败
            //repCode  6112  获取验证码失败,请联系管理员
        }
        return response;
    }

}
