package rebue.scx.cap.svc.impl;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CapSMSSendingSvc;
import rebue.scx.cap.svc.CaptchaService;
import rebue.scx.cap.to.CapEmailTo;
import rebue.scx.cap.to.CapEmailVerificationTo;
import rebue.scx.cap.to.CapSMSTo;
import rebue.scx.cap.to.CapSMSVerificationTo;
import rebue.scx.msg.api.EmailMessageSendingApi;
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
    private static String             cacheType        = "redis";
    private static String             SMS_KEY_PREFIX   = "CapSMSSendingSvcImpl+sms:";
    private static String             EMAIL_KEY_PREFIX = "CapSMSSendingSvcImpl+email:";
    @DubboReference
    private TemplateMessageSendingApi templateMessageSendingApi;
    @DubboReference
    private EmailMessageSendingApi    emailMessageSendingApi;

    @Autowired
    private CaptchaService            captchaService;
    /**
     * 验证码自然过期时间
     */
    private Long                      EXPIRE_TIME      = 5 * 60L;

    /**
     * 模板短信
     * 
     * @param phoneNumber 11位数手机号
     */
    @Override
    public Ro<?> sendTemplateSMS(CapSMSTo to) {
        final CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(to.getCaptchaVerification());
        final Ro<?> model = captchaService.verification(captchaVO);
        // 图形校验通过才发送短信
        if (model.getResult().getCode() == 1) {
            final String phoneNumber = to.getPhoneNumber();
            final String code        = getSixRandom();
            final String redisKey    = SMS_KEY_PREFIX + phoneNumber + code;
            // 距离上次获取验证码超过60秒后才可以重新获取验证码
            Set<String>  keys        = CaptchaServiceFactory.getCache(cacheType)
                    .keys(StringUtils.rightPad(SMS_KEY_PREFIX + phoneNumber, SMS_KEY_PREFIX.length() + 17, "?"));
            Ro<?>        waitTime    = isWaitTime(keys);
            if (waitTime != null) {
                return waitTime;
            }
            // 删除旧的手机验证码缓存
            CaptchaServiceFactory.getCache(cacheType).delete(keys);
            CaptchaServiceFactory.getCache(cacheType).set(redisKey, code, EXPIRE_TIME);
            Ro<?> sendTemplateSMS = templateMessageSendingApi.sendTemplateSMS(phoneNumber, code);
            return sendTemplateSMS;
        }
        else {
            return new Ro<>(ResultDic.FAIL, "图形验证码校验失败");
        }
    }

    /**
     * 短信验证码校验
     * 
     */
    @Override
    public Ro<?> msgSMSVerification(CapSMSVerificationTo to) {

        final String phoneNumber = to.getPhoneNumber();
        final String code        = to.getCode();
        final String redisKey    = SMS_KEY_PREFIX + phoneNumber + code;
        final String verifiy     = CaptchaServiceFactory.getCache(cacheType).get(redisKey);
        if (verifiy == null) {
            return new Ro<>(ResultDic.FAIL, "手机验证码已失效过期或验证码错误");
        }
        boolean bool = code.equals(verifiy);
        if (bool) {
            // CaptchaServiceFactory.getCache(cacheType).delete(redisKey);
            return new Ro<>(ResultDic.SUCCESS, "短信校验成功", bool);
        }
        return new Ro<>(ResultDic.FAIL, "短信校验失败", bool);
    }

    /**
     * 模板邮箱
     */
    @Override
    public Ro<?> sendTemplateEmail(CapEmailTo to) {
        final CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(to.getCaptchaVerification());
        final Ro<?> model = captchaService.verification(captchaVO);
        // 图形校验通过才发送短信
        if (model.getResult().getCode() == 1) {
            final String email    = to.getEmail();
            final String code     = getSixRandom();
            final String redisKey = EMAIL_KEY_PREFIX + email + code;
            // 距离上次获取验证码超过60秒后才可以重新获取验证码
            Set<String>  keys     = CaptchaServiceFactory.getCache(cacheType)
                    .keys(StringUtils.rightPad(EMAIL_KEY_PREFIX + email, EMAIL_KEY_PREFIX.length() + email.length() + 6, "?"));
            Ro<?>        waitTime = isWaitTime(keys);
            if (waitTime != null) {
                return waitTime;
            }
            // 删除旧的邮箱验证码缓存
            CaptchaServiceFactory.getCache(cacheType).delete(keys);
            CaptchaServiceFactory.getCache(cacheType).set(redisKey, code, EXPIRE_TIME);
            Ro<?> sendTemplateSMS = emailMessageSendingApi.sendEmailTemple(new String[] { email
            }, code);
            return sendTemplateSMS;
        }
        else {
            return new Ro<>(ResultDic.FAIL, "图形验证码校验失败");
        }
    }

    @Override
    public Ro<?> msgEmailVerification(CapEmailVerificationTo to) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 校验成功后删除手机验证码缓存
     * 
     * @param phoneNumber
     * @param code
     */
    @Override
    public void deleteVerifiyMobileCode(final CapSMSVerificationTo to) {
        final String phoneNumber = to.getPhoneNumber();
        final String code        = to.getCode();
        final String redisKey    = SMS_KEY_PREFIX + phoneNumber + code;
        CaptchaServiceFactory.getCache(cacheType).delete(redisKey);
    }

    /**
     * 产生6位随机数(000000-999999)校验码
     *
     * @return 6位随机数
     */
    private static String getSixRandom() {
        return StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0");
    }

    /**
     * 判断是否等待60秒
     */
    private Ro<?> isWaitTime(Set<String> keys) {
        boolean isWaitTime = false;
        Long    expire     = 5 * 60L;
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            expire = CaptchaServiceFactory.getCache(cacheType).getExpire(key);
            if (EXPIRE_TIME - expire < 60) {
                isWaitTime = true;
            }
        }
        if (isWaitTime) {
            isWaitTime = false;
            return new Ro<>(ResultDic.FAIL, "请等待" + (60 + expire - EXPIRE_TIME) + "秒后，重新获取获取验证码");
        }
        return null;
    }

}
