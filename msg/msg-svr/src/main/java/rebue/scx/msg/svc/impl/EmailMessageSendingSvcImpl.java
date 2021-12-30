
package rebue.scx.msg.svc.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import rebue.msg.fapi.msgFapi;
import rebue.scx.msg.config.EmailConfig;
import rebue.scx.msg.svc.EmailMessageSendingSvc;
import rebue.scx.msg.util.EmailUtil;

@Service
public class EmailMessageSendingSvcImpl implements EmailMessageSendingSvc {

    @Resource
    private msgFapi     msgFapi;

    @Resource
    private EmailConfig emailConfig;

    /**
     * 普通短信处理
     * 
     * @param title 标题
     * @param text  内容
     * @param datas 接收人
     * 
     * @return
     */
    @Override
    public String sendEmailOrdinary(String title, String text, String[] datas) {
        final String json       = EmailUtil.getOrdinaryJson(title, text, datas);
        // auth_string
        String       authString = emailConfig.getAppKey() + ":" + emailConfig.getAppSecret();
        authString = EmailUtil.getEncoderToString(authString);
        String       appKey = "Basic " + authString;
        final String result = msgFapi.SendEmailOrdinary(json, emailConfig.getEmailOrdinaryEndpoint(), appKey);

        return result;
    }

    /**
     * 模板邮箱信息处理
     * 
     * @param datas 接受人
     * @param var   验证码
     * 
     * @return
     */
    @Override
    public String sendEmailTemple(String[] datas, String var) {
        String jsonString = EmailUtil.getTempleJson(datas, var, emailConfig.getTempId());
        // auth_string
        String authString = emailConfig.getAppKey() + ":" + emailConfig.getAppSecret();
        authString = EmailUtil.getEncoderToString(authString);
        String       appKey = "Basic " + authString;
        final String result = msgFapi.sendEmailTemplet(jsonString, emailConfig.getEmailTempletEndpoint(), appKey);
        return result;
    }

}
