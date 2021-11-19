package rebue.scx.msg.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import rebue.scx.msg.api.TemplateMessageSendingApi;

/**
 * 模板消息发送
 * 
 * @author yuanman
 *
 */
@RestController
public class TemplateMessageSendingCtrl {
    @Resource
    private TemplateMessageSendingApi api;

    // /**
    // * 发送短信
    // */
    // @PostMapping("/msg/sms/sending")
    // public Mono<Ro<?>> msgSMSSending() {
    // return Mono.create(callback -> callback.success(api.sendTemplateSMS("18775885903")));
    // }

    // /**
    // * 短信验证码校验
    // *
    // */
    // @PostMapping("/msg/sms/verification")
    // public Mono<Ro<?>> msgSMSVerification(MsgSMSVerificationTo to) {
    // return Mono.create(callback -> callback.success(api.msgSMSVerification(to)));
    // }
}
