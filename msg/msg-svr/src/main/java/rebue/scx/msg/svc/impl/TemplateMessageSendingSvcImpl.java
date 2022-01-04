package rebue.scx.msg.svc.impl;

import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.config.SmsConfig;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
import rebue.scx.msg.util.SmsUtil;
import rebue.wheel.core.util.RegexUtils;

@Service
public class TemplateMessageSendingSvcImpl implements TemplateMessageSendingSvc {
    @Resource
    private SmsConfig smsConfig;

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber, String code) {
        boolean matchMobile = RegexUtils.matchMobile(phoneNumber);
        if (matchMobile) {
            return SmsUtil.sendSMSCode(phoneNumber, code, smsConfig);
        }
        else {
            return new Ro<>(ResultDic.FAIL, "发送失败", "请输入正确的手机号");
        }
    }

    /**
     * 产生6位随机数(000000-999999)校验码
     *
     * @return 6位随机数
     */
    public static String getSixRandom() {
        return StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0");
    }

}
