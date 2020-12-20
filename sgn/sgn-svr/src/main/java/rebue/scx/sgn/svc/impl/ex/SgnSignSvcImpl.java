package rebue.scx.sgn.svc.impl.ex;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.svc.SgnSignSvc;

/**
 * 签名与校验服务的实现类
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
public class SgnSignSvcImpl implements SgnSignSvc {
    private static final String REDIS_KEY_SIGN_ID_PREFIX = "rebue.scx.sgn.svc.sign-id.";

    @Override
    public Ro<?> verify(final Map<String, ?> paramMap) {
        // // 获取签名ID
        // final String signId = (String) paramMap.get("signId");
        // if (StringUtils.isBlank(signId)) {
        // return new Ro<>(ResultDic.PARAM_ERROR, "验证签名错误: 请求参数中没有signId");
        // }
        //
        // // 通过签名ID获取签名key
        // final String signKey = get(signId);
        // if (StringUtils.isBlank(signKey)) {
        // return new Ro<>(ResultDic.WARN, "验证签名错误: signId错误");
        // }
        //
        // if (SignUtils.verify1(paramMap, signKey)) {
        // return new Ro<>(ResultDic.SUCCESS, "验证签名正确");
        // }
        // else {
        // return new Ro<>(ResultDic.WARN, "验证签名错误: 签名不正确");
        // }
        return null;
    }
}
