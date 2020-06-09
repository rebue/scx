package rebue.scx.rac.svc.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.svc.JwtSvc;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.ro.SignUpRo;
import rebue.scx.rac.svc.RacApiSignUpSvc;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.to.SignUpByUserNameTo;

/**
 * API用户注册服务的实现类
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
@Slf4j
@Service
public class RacApiSignUpSvcImpl implements RacApiSignUpSvc {

    @Resource
    private JwtSvc jwtSvc;

    @Lazy
    @Resource
    private RacUserSvc racUserSvc;

    /**
     * 通过用户名称注册
     */
    @Override
    public SignUpRo signUpByUserName(final SignUpByUserNameTo to) {
        log.info("通过用户名称注册: to-{}", to);

        // To转Mo
        final RacUserMo mo = new RacUserMo();
        BeanUtils.copyProperties(to, mo);

        // 添加用户
        mo.setModifiedTimestamp(System.currentTimeMillis());
        final Ro addRo = racUserSvc.add(mo);

        // Ro转SignUpRo
        final SignUpRo ro = new SignUpRo();
        BeanUtils.copyProperties(addRo, ro);

        // 如果添加成功，JWT签名
        if (ResultDic.SUCCESS.equals(ro.getResult())) {
            final Map<String, Object> addtions = new LinkedHashMap<>();
            addtions.put("sysId", to.getSysId());
            final JwtSignTo signTo = new JwtSignTo(ro.getId().toString(), addtions);
            jwtSvc.sign(signTo);

        }

        return ro;
    }

}
