package rebue.wxx.svc.impl.ex;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.wheel.api.OrikaUtils;
import rebue.wheel.turing.SignUtils;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.svc.ex.WxxAppCacheSvc;
import rebue.wxx.svc.ex.WxxRespSvc;
import rebue.wxx.to.ex.WxxRespAuthorizeTo;

/**
 * 响应微信发过来请求的服务的实现类
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
public class WxxRespSvcImpl implements WxxRespSvc {
    @Resource
    private WxxAppCacheSvc appCacheSvc;

    /**
     * 提供给微信验证本服务器身份的接口
     * 微信公众号绑定网站的域名时，会向此url发送GET请求进行校验，
     * 而要注意，微信通过同样的url，发出POST请求时却是推送消息过来，
     * 所以GET和POST要区分对待，本方法是处理GET的，下一个方法是处理POST的
     */
    @Override
    public String authorize(final String appId, final WxxRespAuthorizeTo to) {
        final WxxAppCco cco = appCacheSvc.getById(appId);
        if (SignUtils.verify2(OrikaUtils.mapAsMap(cco), cco.getAppToken())) {
            log.info("微信初始化验证成功：appId-{} to-{}", appId, to);
            return to.getEchostr();
        }
        else {
            final String msg = "微信初始化验证传入参数验证没有通过，不排除有人在试图模仿微信官方服务器发来信息";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }

}
