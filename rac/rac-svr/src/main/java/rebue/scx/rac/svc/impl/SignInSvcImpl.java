package rebue.scx.rac.svc.impl;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.ro.Ro;
import rebue.scx.jwt.api.JwtApi;
import rebue.scx.rac.ra.SignUpOrInRa;
import rebue.scx.rac.svc.SignInSvc;
import rebue.scx.rac.to.SignInByUserNameTo;

/**
 * 用户注册服务的实现类
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
public class SignInSvcImpl implements SignInSvc {

    @DubboReference(application = "jwt-svr")
    private JwtApi jwtApi;

//    @Autowired
//    private RacUserSvc       racUserSvc;
//
//    @Autowired
//    private RacUserMapper    racUserMapper;
//    @Autowired
//    private RacSysUserMapper racSysUserMapper;
//
//    @Autowired
//    private Mapper           dozerMapper;

    /**
     * 通过用户名称登录(按照 邮箱->手机->登录名 的顺序查找用户)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<SignUpOrInRa> signInByUserName(final SignInByUserNameTo to) {
        return null;
    }

}
