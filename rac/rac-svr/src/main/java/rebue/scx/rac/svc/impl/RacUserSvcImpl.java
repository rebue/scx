package rebue.scx.rac.svc.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacUserDao;
import rebue.scx.rac.jo.RacUserJo;
import rebue.scx.rac.mapper.RacUserMapper;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserDelTo;
import rebue.scx.rac.to.RacUserListTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserOneTo;
import rebue.scx.rac.to.RacUserPageTo;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 用户服务实现
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
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class RacUserSvcImpl
        extends
        BaseSvcImpl<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserDelTo, RacUserOneTo, RacUserListTo, RacUserPageTo, RacUserMo, RacUserJo, RacUserMapper, RacUserDao>
        implements RacUserSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacUserSvc thisSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacUserMo> getMoClass() {
        return RacUserMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserDelTo, RacUserOneTo, RacUserListTo, RacUserPageTo, RacUserMo, RacUserJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 添加记录
     *
     * @param to 添加的参数
     *
     * @return 如果成功，且仅添加一条记录，返回添加时自动生成的ID，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacUserMo add(RacUserAddTo to) {
        to.setCreateTimestamp(System.currentTimeMillis());
        to.setUpdateTimestamp(System.currentTimeMillis());
        final RacUserMo mo     = OrikaUtils.map(to, getMoClass());
        String          idCard = mo.getIdCard();
        if (idCard != null) {
            // 取身份证第17位数自动判断性别，0为女，1为男
            int parseInt = Integer.parseInt(idCard.substring(16, 17));
            mo.setSex((byte) (parseInt % 2));
        }
        if (to.getMobile() != null && to.getMobile().equals("")) {
            mo.setMobile(null);
        }
        if (to.getEmail() != null) {
            if (to.getEmail().equals("")) {
                mo.setEmail(null);
            }
        }
        return thisSvc.addMo(mo);
    }

    /**
     * 根据姓名和身份张号查询用户信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public RacUserMo getOneByRealNameIdCard(RacUserOneTo one) {
        return thisSvc.getOne(one);
    }

    @Override
    public PageInfo<RacUserMo> page(RacUserPageTo qo) {
        final ISelect select = () -> _mapper.listQo(qo);
        return super.page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
    }

}
