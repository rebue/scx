package rebue.scx.msg.svc.impl;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.msg.svc.TemplateMessageSendingSvc;
import rebue.scx.msg.to.MsgSMSVerificationTo;
import rebue.scx.msg.util.SmsUtil;

@Service
public class TemplateMessageSendingSvcImpl implements TemplateMessageSendingSvc {
    @Resource
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
        final String code     = getSixRandom();
        final String redisKey = phoneNumber + code;
        // 重新获取验证码
        // 删除旧的验证码缓存
        Set<String>  keys     = stringRedisTemplate.keys(StringUtils.rightPad(phoneNumber, 17, "?"));
        stringRedisTemplate.delete(keys);
        stringRedisTemplate.opsForValue().set(redisKey, code, 5 * 60L, TimeUnit.SECONDS);
        final String Verifiy = stringRedisTemplate.opsForValue().get(redisKey);
        return sms.sendSMSCode(phoneNumber, code);
    }

    /**
     * 模板短信
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber, String code) {
        return sms.sendSMSCode(phoneNumber, code);
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(MsgSMSVerificationTo to) {
        // final String phoneNumber = to.getPhoneNumber();
        // final String code = to.getCode();
        // final String redisKey = phoneNumber + code;
        // final String Verifiy = stringRedisTemplate.opsForValue().get(redisKey);
        // stringRedisTemplate.delete(redisKey);
        // boolean bool = code.equals(Verifiy);
        // return new Ro<>(ResultDic.SUCCESS, "短信校验结果", bool);
        return null;
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
