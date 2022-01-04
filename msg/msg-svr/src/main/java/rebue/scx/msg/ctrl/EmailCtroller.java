package rebue.scx.msg.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
import rebue.scx.msg.api.EmailMessageSendingApi;
import rebue.scx.msg.to.EmailOrdinary;
import rebue.scx.msg.to.EmailTemplate;

@RestController
@Log4j
public class EmailCtroller {

    // @Resource
    // private EmailUtil emailUtil;
    @Resource
    private EmailMessageSendingApi api;

    final String[]                 data = { "2530364891@qq.com"
    };

    /**
     * @title 邮箱标题
     * 
     * @text 邮箱内容
     *       普通邮箱发送
     * 
     * @return
     */
    @PostMapping("/email/ordinary")
    public String sendEmailOrdinary(EmailOrdinary emailOrdinary) {
        return null;
        // return impl.sendEmailOrdinary(emailOrdinary);
    }

    /**
     * 邮箱的模板发送
     * 
     * @return
     */
    @PostMapping("/email/template")
    public String sendEmailTempale(EmailTemplate emailTemplate) {
        return null;
        // String[] datas, String code
        // return api.SendEmailTemple(emailTemplate);
    }
}
