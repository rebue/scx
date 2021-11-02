package rebue.scx.cap.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.api.CapSMSSendingApi;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.cap.to.CapSMSVerificationTo;

/**
 * 短信发送
 * 
 * @author yuanman
 *
 */
@RestController
public class CapSMSSendingCtrl {

    @Resource
    private CapSMSSendingApi api;

    /**
     * 发送短信
     */
    @PostMapping("/cap/sms/sending")
    public Mono<Ro<?>> msgSMSSending(@RequestBody CapSMSTo to) {
        return Mono.create(callback -> callback.success(api.sendTemplateSMS(to)));
    }

    /**
     * 短信验证码校验
     *
     */
    @PostMapping("/cap/sms/verification")
    public Mono<Ro<?>> msgSMSVerification(@RequestBody CapSMSVerificationTo to) {
        return Mono.create(callback -> callback.success(api.msgSMSVerification(to)));
    }
}
