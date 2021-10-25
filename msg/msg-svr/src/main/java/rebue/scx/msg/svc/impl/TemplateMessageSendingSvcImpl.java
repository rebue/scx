package rebue.scx.msg.svc.impl;

import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.Setter;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
import rebue.scx.msg.to.MsgSMSTo;
import rebue.scx.msg.util.SmsUtil;

@Service
public class TemplateMessageSendingSvcImpl implements TemplateMessageSendingSvc {

    @Autowired
    @Setter
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SmsUtil             sms;

    /**
     * 模板短信
     * 
     * @param phoneNumber
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber) {
        final String code     = getFourRandom();
        final String redisKey = phoneNumber + code;
        stringRedisTemplate.opsForValue().set(redisKey, code, 5 * 60L);
        // sms.sendSMSCode(phoneNumber, code);
        return new Ro<>(ResultDic.SUCCESS, "发送成功", code);
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(MsgSMSTo to) {
        final String phoneNumber = to.getPhoneNumber();
        final String code        = to.getCode();
        final String redisKey    = phoneNumber + code;
        final String Verifiy     = stringRedisTemplate.opsForValue().get(redisKey);
        stringRedisTemplate.delete(redisKey);
        boolean bool = code.equals(Verifiy);
        return new Ro<>(ResultDic.SUCCESS, "短信校验结果", bool);
    }

    /**
     * 产生6位随机数(000000-999999)校验码
     *
     * @return 6位随机数
     */
    public static String getFourRandom() {
        return StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0");
    }

}
