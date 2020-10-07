package rebue.scx.rac.svc.ex.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.jwt.ra.JwtSignRa;
import rebue.scx.jwt.to.JwtSignTo;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.svc.ex.SignUpSvc;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.ex.SignUpByUserNameTo;

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
public class SignUpSvcImpl implements SignUpSvc {

    @DubboReference(application = "jwt-svr")
    private JwtApi     jwtApi;

    @Autowired
    private RacUserSvc racUserSvc;

    @Autowired
    private Mapper     dozerMapper;

    /**
     * 通过用户名称注册
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signUpByUserName(final SignUpByUserNameTo to) {
        // 添加用户
        final RacUserAddTo addTo = dozerMapper.map(to, RacUserAddTo.class);
        addTo.setUpdateTimestamp(System.currentTimeMillis());
        final Long userId = racUserSvc.add(addTo);

        // 如果添加成功，JWT签名
        if (userId != null) {
            final Map<String, Object> addtions = new LinkedHashMap<>();
            addtions.put("sysId", to.getSysId());
            final JwtSignTo signTo = new JwtSignTo(userId.toString(), addtions);
            final Ro<JwtSignRa> signRo = jwtApi.sign(signTo);
            if (ResultDic.SUCCESS.equals(signRo.getResult())) {
                return new Ro<>(ResultDic.FAIL, "注册用户成功", null, new SignUpOrInRa(userId, signRo.getExtra().getSign(), signRo.getExtra().getExpirationTime()));
            } else {
                return new Ro<>(ResultDic.FAIL, "JWT签名失败");
            }
        } else {
            return new Ro<>(ResultDic.FAIL, "添加用户失败");
        }

    }

}
