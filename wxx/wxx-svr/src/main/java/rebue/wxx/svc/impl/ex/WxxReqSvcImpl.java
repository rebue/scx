package rebue.wxx.svc.impl.ex;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ro.Ro;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.fapi.WxxFapi;
import rebue.wxx.fro.WxxGetAccessTokenFro;
import rebue.wxx.fro.WxxMessageTemplateSendFro;
import rebue.wxx.fto.WxxMessageTemplateSendFto;
import rebue.wxx.svc.ex.WxxAppCacheSvc;
import rebue.wxx.svc.ex.WxxReqSvc;

/**
 * 向微信发送请求的服务的实现类
 *
 * <pre>
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
@Slf4j
public class WxxReqSvcImpl implements WxxReqSvc {
    @Resource
    private WxxAppCacheSvc appCacheSvc;

    @Resource
    private WxxFapi        wxxFapi;

    /**
     * 刷新Access token
     *
     * @param appId   微信的AppId
     * @param cco     App的缓存对象，可从缓存中获取传进来；也可以为空，会自动获取
     * @param isForce 是否强制刷新(缓存未到期时也刷新，默认为否，缓存到期了才刷新)
     */
    @Override
    public void refreshAccessToken(final String appId, WxxAppCco cco, final boolean isForce) {
        final LocalDateTime now = LocalDateTime.now();

        if (cco == null) {
            cco = appCacheSvc.getById(appId);
        }

        // 如果强制刷新、下次请求时间为空或小于当前时间，发出请求
        if (isForce || cco.getNextRequestTime() == null || cco.getNextRequestTime().compareTo(now) < 0) {
            final WxxGetAccessTokenFro accessTokenFro = wxxFapi.getAccessToken(cco.getAppId(), cco.getAppSecret());
            log.info("accessTokenFro: {}", accessTokenFro);
            if (accessTokenFro.getErrcode() == null || accessTokenFro.getErrcode() == 0) {
                cco.setAccessToken(accessTokenFro.getAccess_token());
                cco.setNextRequestTime(now.plusSeconds(accessTokenFro.getExpires_in()).minusMinutes(15));    // 在返回的超时时间前15分钟再次刷新
                appCacheSvc.putToCache(cco);
            }
        }
        else {
            log.trace("还未到计划下次发出请求的时间: appCco-{}", cco);
        }
    }

    /**
     * 发送模板类的消息
     */
    @Override
    public Ro<?> sendTemplateMessage(final String appId, final WxxMessageTemplateSendFto to) {
        final WxxAppCco cco = appCacheSvc.getById(appId);
        if (StringUtils.isBlank(cco.getAccessToken())) {
            return new Ro<>(ResultDic.FAIL, "尚未获取到Access token", appId);
        }
        final WxxMessageTemplateSendFro fro = wxxFapi.sendTemplateMessage(cco.getAccessToken(), to);
        if (fro.getErrcode().equals(0)) {
            return new Ro<>(ResultDic.SUCCESS, "发送消息成功", new IdRa<>(fro.getMsgid()));
        }
        return new Ro<>(ResultDic.FAIL, "发送消息失败", fro.getErrmsg());
    }

}
