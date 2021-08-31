package rebue.scx.cap.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaService;

/**
 * 验证码控制器
 */
@RestController
@CrossOrigin
// @RequestMapping("/cap-svr")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取验证码
     * 
     * @param to
     * 
     * @return
     */
    @PostMapping("/cap/captcha/get")
    public Mono<Ro<?>> get(@RequestBody final CaptchaVO to) {
        return Mono.create(callback -> callback.success(captchaService.get(to)));
    }

    /**
     * 校验验证码
     * 
     * @param to
     * 
     * @return
     */
    @PostMapping("/cap/captcha/check")
    public Mono<Ro<?>> check(@RequestBody final CaptchaVO to) {
        return Mono.create(callback -> callback.success(captchaService.check(to)));
    }

    // 测试使用
    // @PostMapping("/cap/captcha/verify")
    public Mono<Ro<?>> verify(@RequestBody final CaptchaVO data) {
        return Mono.create(callback -> callback.success(captchaService.verification(data)));
    }

}