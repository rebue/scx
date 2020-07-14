package rebue.scx.rac.svc.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.ra.SignUpRa;
import rebue.scx.rac.svc.RacSignUpSvc;
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
@Service
public class RacSignUpSvcImpl implements RacSignUpSvc {

    @DubboReference(application = "jwt-svr")
    private JwtApi jwtApi;

    @Autowired
    private RacUserSvc racUserSvc;

    /**
     * 通过用户名称注册
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpRa> signUpByUserName(final SignUpByUserNameTo to) {
        // To转Mo
        final RacUserMo mo = new RacUserMo();
        BeanUtils.copyProperties(to, mo);

        // 添加用户
        mo.setUpdateTimestamp(System.currentTimeMillis());
        final Ro<IdRa<Long>> addRo = racUserSvc.add(mo);

        final Ro<SignUpRa> ro = new Ro<>();
        // 复制addRo除addition外其它的属性到返回值
        BeanUtils.copyProperties(addRo, ro, "addition");
        // 复制addRo的属性到返回值的addition
        ro.setExtra(new SignUpRa());
        BeanUtils.copyProperties(addRo.getExtra(), ro.getExtra());

        // 如果添加成功，JWT签名
        if (ResultDic.SUCCESS.equals(ro.getResult())) {
            final Map<String, Object> addtions = new LinkedHashMap<>();
            addtions.put("sysId", to.getSysId());
            final JwtSignTo signTo = new JwtSignTo(ro.getExtra().getId().toString(), addtions);
            jwtApi.sign(signTo);
        }

        return ro;
    }

}
