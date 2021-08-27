package rebue.scx.cap.svc.impl;

import java.util.Objects;
import java.util.Properties;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.cap.commom.Const;
import rebue.scx.cap.mo.CaptchaVO;
import rebue.scx.cap.svc.CaptchaCacheService;
import rebue.scx.cap.util.StringUtils;


public interface FrequencyLimitHandler {

    String LIMIT_KEY = "AJ.CAPTCHA.REQ.LIMIT-%s-%s";

    /**
     * get 接口限流
     *
     * @param captchaVO
     * @return
     */
    Ro<?> validateGet(CaptchaVO captchaVO);

    /**
     * check接口限流
     *
     * @param captchaVO
     * @return
     */
    Ro<?> validateCheck(CaptchaVO captchaVO);

    /**
     * verify接口限流
     *
     * @param captchaVO
     * @return
     */
    Ro<?> validateVerify(CaptchaVO captchaVO);


    /***
     * 验证码接口限流:
     *      客户端ClientUid 组件实例化时设置一次，如：场景码+UUID，客户端可以本地缓存,保证一个组件只有一个值
     *
     * 针对同一个客户端的请求，做如下限制:
     * get
     *   1分钟内check失败5次，锁定5分钟
     *   1分钟内不能超过120次。
     * check:
     *   1分钟内不超过600次
     * verify:
     *   1分钟内不超过600次
     */
    class DefaultLimitHandler implements FrequencyLimitHandler {
        private final Properties config;
        private final CaptchaCacheService cacheService;

        public DefaultLimitHandler(final Properties config, final CaptchaCacheService cacheService) {
            this.config = config;
            this.cacheService = cacheService;
        }

        private String getClientCId(final CaptchaVO input, final String type) {
            return String.format(LIMIT_KEY ,type,input.getClientUid());
        }

        @Override
        public Ro<?> validateGet(final CaptchaVO d) {
            // 无客户端身份标识，不限制
            if(StringUtils.isEmpty(d.getClientUid())){
                return null;
            }
            final String getKey = getClientCId(d, "GET");
            final String lockKey = getClientCId(d, "LOCK");
            // 失败次数过多，锁定
            if (Objects.nonNull(cacheService.get(lockKey))) {
                return new Ro<>(ResultDic.FAIL, "接口验证失败数过多，请稍后再试");
            }
            String getCnts = cacheService.get(getKey);
            if (Objects.isNull(getCnts)) {
                cacheService.set(getKey, "1", 60);
                getCnts = "1";
            }
            cacheService.increment(getKey, 1);
            // 1分钟内请求次数过多
            if (Long.valueOf(getCnts) > Long.parseLong(config.getProperty(Const.REQ_GET_MINUTE_LIMIT, "120"))) {                
                return new Ro<>(ResultDic.FAIL, "get接口请求次数超限，请稍后再试!");
            }

            // 失败次数验证
            final String failKey = getClientCId(d, "FAIL");
            final String failCnts = cacheService.get(failKey);
            // 没有验证失败，通过校验
            if (Objects.isNull(failCnts)) {
                return null;
            }
            // 1分钟内失败5次
            if (Long.valueOf(failCnts) > Long.parseLong(config.getProperty(Const.REQ_GET_LOCK_LIMIT, "5"))) {
                // get接口锁定5分钟
                cacheService.set(lockKey, "1", Long.valueOf(config.getProperty(Const.REQ_GET_LOCK_SECONDS, "300")));
                return new Ro<>(ResultDic.FAIL, "接口验证失败数过多，请稍后再试");
            }
            return null;
        }

        @Override
        public Ro<?> validateCheck(final CaptchaVO d) {
            // 无客户端身份标识，不限制
            if(StringUtils.isEmpty(d.getClientUid())){
                return null;
            }
            final String key = getClientCId(d, "CHECK");
            String v = cacheService.get(key);
            if (Objects.isNull(v)) {
                cacheService.set(key, "1", 60);
                v = "1";
            }
            cacheService.increment(key, 1);
            if (Long.valueOf(v) > Long.valueOf(config.getProperty(Const.REQ_CHECK_MINUTE_LIMIT, "600"))) {
                return new Ro<>(ResultDic.FAIL, "check接口请求次数超限，请稍后再试!");
            }
            return null;
        }

        @Override
        public Ro<?> validateVerify(final CaptchaVO d) {
            final String key = getClientCId(d, "VERIFY");
            String v = cacheService.get(key);
            if (Objects.isNull(v)) {
                cacheService.set(key, "1", 60);
                v = "1";
            }
            cacheService.increment(key, 1);
            if (Long.valueOf(v) > Long.valueOf(config.getProperty(Const.REQ_VALIDATE_MINUTE_LIMIT, "600"))) {
                return new Ro<>(ResultDic.FAIL, "verify请求次数超限");
            }
            return null;
        }
    }

}