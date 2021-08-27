package rebue.scx.cap.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapApi;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaService;

@RestController
@CrossOrigin
// @RequestMapping("/cap-svr")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;
    @Reference
    private CapApi         api;

    @PostMapping("/cap/captcha/get")
    public Mono<Ro<?>> get(@RequestBody final CaptchaVO to) {
        return Mono.create(callback -> callback.success(captchaService.get(to)));
    }

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