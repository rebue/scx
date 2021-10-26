package rebue.scx.cap.svc.impl;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.svc.CapSMSSendingSvc;
import rebue.scx.cap.to.CapSMSVerificationTo;
import rebue.scx.msg.api.TemplateMessageSendingApi;

/**
 * 短信实现
 * 请使用redis缓存，本地缓存无keys方法
 * 
 * @author yuanman
 *
 */
@Service
public class CapSMSSendingSvcImpl implements CapSMSSendingSvc {
    private static String             cacheType   = "redis";
    @DubboReference
    private TemplateMessageSendingApi templateMessageSendingApi;
    /**
     * 验证码自然过期时间
     */
    private Long                      EXPIRE_TIME = 5 * 60L;

    /**
     * 模板短信
     * 
     * @param phoneNumber
     */
    @Override
    public Ro<?> sendTemplateSMS(String phoneNumber) {
        final String code     = getSixRandom();
        final String redisKey = phoneNumber + code;
        // 距离上次获取验证码超过60秒后才可以重新获取验证码
        Set<String>  keys     = CaptchaServiceFactory.getCache(cacheType).keys(StringUtils.rightPad(phoneNumber, 17, "?"));
        // Ro<?> waitTime = isWaitTime(keys);
        // if (waitTime != null) {
        // return waitTime;
        // }
        // 删除旧的验证码缓存
        CaptchaServiceFactory.getCache(cacheType).delete(keys);
        CaptchaServiceFactory.getCache(cacheType).set(redisKey, code, EXPIRE_TIME);
        Ro<?> sendTemplateSMS = templateMessageSendingApi.sendTemplateSMS(phoneNumber, code);
        return sendTemplateSMS;
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(CapSMSVerificationTo to) {
        final String phoneNumber = to.getPhoneNumber();
        final String code        = to.getCode();
        final String redisKey    = phoneNumber + code;
        final String verifiy     = CaptchaServiceFactory.getCache(cacheType).get(redisKey);
        if (verifiy == null) {
            return new Ro<>(ResultDic.FAIL, "验证码已过期或验证码错误");
        }
        boolean bool = code.equals(verifiy);
        if (bool) {
            CaptchaServiceFactory.getCache(cacheType).delete(redisKey);
            return new Ro<>(ResultDic.SUCCESS, "短信校验成功", bool);
        }
        return new Ro<>(ResultDic.FAIL, "短信校验失败", bool);
    }

    /**
     * 产生6位随机数(000000-999999)校验码
     *
     * @return 6位随机数
     */
    public static String getSixRandom() {
        return StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0");
    }

    /**
     * 判断是否等待60秒
     */
    private Ro<?> isWaitTime(Set<String> keys) {
        boolean isWaitTime = false;
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key    = iterator.next();
            Long   expire = CaptchaServiceFactory.getCache(cacheType).getExpire(key);
            if (EXPIRE_TIME - expire < 60) {
                isWaitTime = true;
            }
        }
        if (isWaitTime) {
            isWaitTime = false;
            return new Ro<>(ResultDic.FAIL, "请等待60秒后，重新获取获取验证码");
        }
        return null;
    }

}
