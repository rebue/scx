package rebue.scx.rac.svc.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.rac.dao.RacAppDao;
import rebue.scx.rac.jo.RacAppJo;
import rebue.scx.rac.mapper.RacAppMapper;
import rebue.scx.rac.mapper.RacAppTagMapper;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.mo.RacAppTagMo;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.svc.RacDicItemSvc;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppDelTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppOneTo;
import rebue.scx.rac.to.RacAppPageTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 应用服务实现
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
public class RacAppSvcImpl
        extends BaseSvcImpl<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppDelTo, RacAppOneTo, RacAppListTo, RacAppPageTo, RacAppMo, RacAppJo, RacAppMapper, RacAppDao>
        implements RacAppSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacAppSvc       thisSvc;
    @Resource
    private RacAppTagMapper appTagMapper;
    @Resource
    private RacDicItemSvc   racDicItemSvc;

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacAppMo> getMoClass() {
        return RacAppMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppDelTo, RacAppOneTo, RacAppListTo, RacAppPageTo, RacAppMo, RacAppJo> getThisSvc() {
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
    public RacAppMo add(RacAppAddTo to) {
        RacAppOneTo oneTo = new RacAppOneTo();
        oneTo.setRealmId(to.getRealmId());
        // 排序顺序号
        Long count = thisSvc.countSelective(oneTo);
        to.setSeqNo((byte) (count + 0));
        final RacAppMo mo    = OrikaUtils.map(to, RacAppMo.class);
        RacAppMo       addMo = thisSvc.addMo(mo);
        if (to.getDicItemIds() != null) {
            Iterator<Long> iterator = to.getDicItemIds().iterator();
            while (iterator.hasNext()) {
                final RacAppTagMo appTagMo = new RacAppTagMo();
                // 如果id为空那么自动生成分布式id
                appTagMo.setId(_idWorker.getId());
                appTagMo.setAppId(addMo.getId());
                appTagMo.setDicItemId(iterator.next());
                appTagMapper.insertSelective(appTagMo);
            }
        }
        return addMo;
    }

    /**
     * 是否启用应用
     *
     * @param to 修改的具体数据
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void enable(RacAppEnabledTo to) {
        final RacAppMo mo = OrikaUtils.map(to, RacAppMo.class);
        thisSvc.modifyMoById(mo);
    }

    /**
     * 通过ID修改记录内容
     *
     * @param to 修改的参数，必须包含ID
     *
     * @return 如果成功，且仅修改一条记录，正常返回，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacAppMo modifyById(final RacAppModifyTo to) {
        final RacAppMo    mo       = OrikaUtils.map(to, getMoClass());
        RacAppMo          appMo    = this.modifyMoById(mo);
        // 先删除应用标签
        final RacAppTagMo appTagMo = new RacAppTagMo();
        appTagMo.setAppId(appMo.getId());
        appTagMapper.deleteSelective(appTagMo);
        // 再添加，已达到修改效果
        if (to.getDicItemIds() != null) {
            Iterator<Long> iterator = to.getDicItemIds().iterator();
            while (iterator.hasNext()) {
                final RacAppTagMo appTag = new RacAppTagMo();
                // 如果id为空那么自动生成分布式id
                appTag.setId(_idWorker.getId());
                appTag.setAppId(appMo.getId());
                appTag.setDicItemId(iterator.next());
                appTagMapper.insertSelective(appTag);
            }
        }
        return appMo;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacAppMo modifyMoById(RacAppMo mo) {
        final int rowCount = _mapper.updateByPrimaryKeySelective(mo);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("修改记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("修改记录异常，影响行数为" + rowCount);
        }
        // XXX 注意这里是this，而不是getThisSvc()，这是避免使用到了缓存
        return this.getById(mo.getId());
    }

    /**
     * 通过ID删除记录
     *
     * @param id 要删除记录的ID
     *
     * @return 如果成功，且删除一条记录，正常返回，否则会抛出运行时异常
     */
    @Override
    public void delById(String id) {
        RacAppMo          byId     = getById(id);
        // 先删除关联应用标签
        final RacAppTagMo appTagMo = new RacAppTagMo();
        appTagMo.setAppId(id);
        appTagMapper.deleteSelective(appTagMo);
        // 再删除应用
        final int rowCount = _mapper.deleteByPrimaryKey(id);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
        }
        // 删除后对其余应用进行顺序号更新
        _mapper.updateSeqNoByDeleteAfter(byId);
    }

    /**
     * 上移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveUp(final RacAppModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacAppMo qo    = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        final RacAppMo appQo = new RacAppMo();
        appQo.setSeqNo((byte) (qo.getSeqNo() - 1));
        appQo.setRealmId(qo.getRealmId());
        final RacAppMo appUp = _mapper.selectOne(appQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据上面一条的数据的顺序号
        appUp.setSeqNo((byte) (appUp.getSeqNo() + 1));
        final RacAppMo mo = OrikaUtils.map(appUp, getMoClass());
        thisSvc.modifyMoById(mo);
        // 修改当前这条数据的顺序号
        final RacAppMo qoMo = OrikaUtils.map(qo, getMoClass());
        qoMo.setSeqNo((byte) (qo.getSeqNo() - 1));
        thisSvc.modifyMoById(qoMo);
    }

    /**
     * 下移动
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moveDown(final RacAppModifyTo to) {
        // 获取当前这条数据的具体数据
        final RacAppMo qo    = _mapper.selectByPrimaryKey(to.getId()).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 获取当前这条数据下面一条的具体数据
        final RacAppMo appQo = new RacAppMo();
        appQo.setSeqNo((byte) (qo.getSeqNo() + 1));
        appQo.setRealmId(qo.getRealmId());
        final RacAppMo appDown = _mapper.selectOne(appQo).orElseThrow(() -> new RuntimeExceptionX("该记录查找不到，或已经发生变动！"));
        // 修改当前这条数据下面一条的数据的顺序号
        appDown.setSeqNo((byte) (appDown.getSeqNo() - 1));
        final RacAppMo mo = OrikaUtils.map(appDown, getMoClass());
        thisSvc.modifyMoById(mo);
        // 修改当前这条数据的顺序号
        final RacAppMo qoMo = OrikaUtils.map(qo, getMoClass());
        qoMo.setSeqNo((byte) (qo.getSeqNo() + 1));
        thisSvc.modifyMoById(qoMo);
    }

    /**
     * 根据Id查询应用信息
     */
    @Override
    public RacAppMo getById(final String id) {
        RacAppMo          appMo    = _mapper.selectByPrimaryKey(id).orElse(null);
        final RacAppTagMo appTagMo = new RacAppTagMo();
        appTagMo.setAppId(id);
        List<RacAppTagMo> list = appTagMapper.selectSelective(appTagMo);
        if (list.size() > 0) {
            List<Long>         collect = list.stream().map(item -> {
                                           return item.getDicItemId();
                                       }).collect(Collectors.toList());
            List<RacDicItemMo> listIn  = racDicItemSvc.listIn(collect);
            // 回显示标签
            appMo.setAppLabelList(listIn);
        }
        return appMo;
    }

    /**
     * 根据应用ID查询对应的认证信息
     */
    @Override
    public List<RacAppTagMo> listInAppIdList(List<String> appIds) {
        return appTagMapper.listInAppIdList(appIds);

    }

    /**
     * 根据顺序号查询App
     */
    @Override
    public List<RacAppMo> list(RacAppListTo qo) {
        final RacAppMo mo = OrikaUtils.map(qo, getMoClass());
        return _mapper.selectListOrderBySeqNo(mo);
    }

    /**
     * 根据帐号ID查询他可以看到的应用
     *
     * @param accountIds 账户ID集合
     *
     * @return
     */
    @Override
    public List<RacAppMo> selectAppByAccountIds(List<Long> accountIds) {
        return _mapper.selectAppByAccountIds(accountIds);
    }

    /**
     * 查询应用
     */
    @Override
    public List<RacAppMo> listOrderBySeqNo(RacAppListTo qo) {
        final RacAppMo mo = OrikaUtils.map(qo, RacAppMo.class);
        return _mapper.selectSelectiveOrderBySeqNo(mo);
    }
}
