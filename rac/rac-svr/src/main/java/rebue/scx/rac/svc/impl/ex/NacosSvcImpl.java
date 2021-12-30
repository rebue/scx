package rebue.scx.rac.svc.impl.ex;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.config.NacosStrategies;
import rebue.scx.rac.svc.ex.NacosSvc;

/**
 * 账户登录服务的实现类
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
public class NacosSvcImpl<ADD_TO, MODIFY_TO, DEL_TO> implements NacosSvc<ADD_TO, MODIFY_TO, DEL_TO> {

    @Resource
    private NacosStrategies nacosStrategies;

    @Override
    public Ro<?> getNacosConfig(String tpye) {
        Object nacosConfig = nacosStrategies.getItems().get(tpye).getNacosConfig();
        return new Ro<>(ResultDic.SUCCESS, "查询成功", nacosConfig);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Ro<?> addPublishConfig(String tpye, ADD_TO to) {
        boolean boo = nacosStrategies.getItems().get(tpye).addPublishConfig(to);
        return new Ro<>(ResultDic.SUCCESS, "添加成功", boo);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Ro<?> updatePublishConfig(String tpye, MODIFY_TO to) {
        boolean boo = nacosStrategies.getItems().get(tpye).updatePublishConfig(to);
        return new Ro<>(ResultDic.SUCCESS, "修改成功", boo);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Ro<?> delPublishConfig(String tpye, DEL_TO to) {
        boolean boo = nacosStrategies.getItems().get(tpye).delPublishConfig(to);
        return new Ro<>(ResultDic.SUCCESS, "删除成功", boo);
    }

}
