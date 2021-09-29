package rebue.scx.etl.svc.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import rebue.robotech.dic.SqlDic;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.etl.dao.EtlSyncStrategyDao;
import rebue.scx.etl.jo.EtlSyncStrategyJo;
import rebue.scx.etl.mapper.EtlSyncStrategyMapper;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.mo.EtlSyncStrategyDetailMo;
import rebue.scx.etl.mo.EtlSyncStrategyMo;
import rebue.scx.etl.svc.EtlConnSvc;
import rebue.scx.etl.svc.EtlSyncStrategyDetailSvc;
import rebue.scx.etl.svc.EtlSyncStrategySvc;
import rebue.scx.etl.to.EtlSyncStrategyAddTo;
import rebue.scx.etl.to.EtlSyncStrategyDelTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailAddTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailDelTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailListTo;
import rebue.scx.etl.to.EtlSyncStrategyListTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyEnableTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyOneTo;
import rebue.scx.etl.to.EtlSyncStrategyPageTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.JdbcUtils;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 同步策略服务实现
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
public class EtlSyncStrategySvcImpl extends
        BaseSvcImpl<java.lang.Long, EtlSyncStrategyAddTo, EtlSyncStrategyModifyTo, EtlSyncStrategyDelTo, EtlSyncStrategyOneTo, EtlSyncStrategyListTo, EtlSyncStrategyPageTo, EtlSyncStrategyMo, EtlSyncStrategyJo, EtlSyncStrategyMapper, EtlSyncStrategyDao>
        implements EtlSyncStrategySvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private EtlSyncStrategySvc       thisSvc;
    @Resource
    private EtlSyncStrategyDetailSvc syncStrategyDetailSvc;
    @Resource
    private EtlConnSvc               connSvc;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, EtlSyncStrategyAddTo, EtlSyncStrategyModifyTo, EtlSyncStrategyDelTo, EtlSyncStrategyOneTo, EtlSyncStrategyListTo, EtlSyncStrategyPageTo, EtlSyncStrategyMo, EtlSyncStrategyJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<EtlSyncStrategyMo> getMoClass() {
        return EtlSyncStrategyMo.class;
    }

    /**
     * 启用/禁用策略
     *
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public EtlSyncStrategyMo enable(EtlSyncStrategyModifyEnableTo to) {
        final EtlSyncStrategyMo mo = OrikaUtils.map(to, EtlSyncStrategyMo.class);
        return super.modifyMoById(mo);
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
    public EtlSyncStrategyMo add(EtlSyncStrategyAddTo to) {
        final EtlSyncStrategyMo         mo              = OrikaUtils.map(to, EtlSyncStrategyMo.class);
        // 添加策略
        EtlSyncStrategyMo               strategyMo      = thisSvc.addMo(mo);
        // 添加策略详情
        String                          srcTableNames   = to.getSrcTableNames();
        Map<String, Object>             srcMap          = JSON.parseObject(srcTableNames);
        String                          dstTableNames   = to.getDstTableNames();
        Map<String, Object>             dstMap          = JSON.parseObject(dstTableNames);
        String                          srcDstMapString = to.getSrcDstMap();
        JSONObject                      srcDstMap       = JSON.parseObject(srcDstMapString);

        Iterator<Entry<String, Object>> srcDstIt        = srcDstMap.entrySet().iterator();
        while (srcDstIt.hasNext()) {
            Map.Entry<String, Object> srcDst        = srcDstIt.next();
            List<String>              srcList       = JSON.parseArray(srcMap.get(srcDst.getKey()).toString(), String.class);
            List<String>              dstList       = JSON.parseArray(dstMap.get(srcDst.getValue()).toString().toString(), String.class);
            Map<String, String>       srcColumnsMap = this.getColumnsMapByTableName(strategyMo.getSrcConnId(), srcDst.getKey());
            Map<String, String>       dstColumnsMap = this.getColumnsMapByTableName(strategyMo.getDstConnId(), (String) srcDst.getValue());

            if (srcList.size() == dstList.size()) {
                for (int i = 0; i < srcList.size(); i++) {
                    EtlSyncStrategyDetailAddTo detaillAddTo = new EtlSyncStrategyDetailAddTo();
                    detaillAddTo.setStrategyId(strategyMo.getId());
                    // 设置来源
                    detaillAddTo.setSrcTableName(srcDst.getKey());
                    detaillAddTo.setSrcFieldName(srcList.get(i));
                    detaillAddTo.setSrcFieldType(srcColumnsMap.get(srcList.get(i)));
                    // 设置目的
                    detaillAddTo.setDstTableName((String) srcDst.getValue());
                    detaillAddTo.setDstFieldName(dstList.get(i));
                    detaillAddTo.setDstFieldType(dstColumnsMap.get(dstList.get(i)));
                    syncStrategyDetailSvc.add(detaillAddTo);
                }
            }
            else {
                throw new RuntimeExceptionX("数据源不对应，请先确认");
            }
        }
        return strategyMo;
    }

    /**
     * 通过ID修改记录
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public EtlSyncStrategyMo modifyById(EtlSyncStrategyModifyTo to) {
        EtlSyncStrategyMo          strategyMo = thisSvc.getById(to.getId());
        // 先删除策略详情
        EtlSyncStrategyDetailDelTo delTo      = new EtlSyncStrategyDetailDelTo();
        delTo.setStrategyId(strategyMo.getId());
        syncStrategyDetailSvc.delSelective(delTo);

        // 后添加策略详情
        String                          srcTableNames   = to.getSrcTableNames();
        JSONObject                      srcMap          = JSON.parseObject(srcTableNames);
        String                          dstTableNames   = to.getDstTableNames();
        JSONObject                      dstMap          = JSON.parseObject(dstTableNames);
        String                          srcDstMapString = to.getSrcDstMap();
        JSONObject                      srcDstMap       = JSON.parseObject(srcDstMapString);
        Iterator<Entry<String, Object>> srcDstIt        = srcDstMap.entrySet().iterator();
        while (srcDstIt.hasNext()) {
            Map.Entry<String, Object> srcDst        = srcDstIt.next();
            List<String>              srcList       = JSON.parseArray(srcMap.get(srcDst.getKey()).toString(), String.class);
            List<String>              dstList       = JSON.parseArray(dstMap.get(srcDst.getValue()).toString().toString(), String.class);
            Map<String, String>       srcColumnsMap = this.getColumnsMapByTableName(strategyMo.getSrcConnId(), srcDst.getKey());
            Map<String, String>       dstColumnsMap = this.getColumnsMapByTableName(strategyMo.getDstConnId(), (String) srcDst.getValue());

            if (srcList.size() == dstList.size()) {
                for (int i = 0; i < srcList.size(); i++) {
                    EtlSyncStrategyDetailAddTo detaillAddTo = new EtlSyncStrategyDetailAddTo();
                    detaillAddTo.setStrategyId(strategyMo.getId());
                    // 设置来源
                    detaillAddTo.setSrcTableName(srcDst.getKey());
                    detaillAddTo.setSrcFieldName(srcList.get(i));
                    detaillAddTo.setSrcFieldType(srcColumnsMap.get(srcList.get(i)));
                    // 设置目的
                    detaillAddTo.setDstTableName((String) srcDst.getValue());
                    detaillAddTo.setDstFieldName(dstList.get(i));
                    detaillAddTo.setDstFieldType(dstColumnsMap.get(dstList.get(i)));
                    syncStrategyDetailSvc.add(detaillAddTo);
                }
            }
            else {
                throw new RuntimeExceptionX("数据源不对应，请先确认");
            }
        }
        final EtlSyncStrategyMo mo = OrikaUtils.map(to, getMoClass());
        return this.modifyMoById(mo);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public EtlSyncStrategyMo modifyMoById(EtlSyncStrategyMo mo) {
        final int rowCount = _mapper.updateByPrimaryKeySelective(mo);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("修改记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("修改记录异常，影响行数为" + rowCount);
        }
        // XXX 注意这里是this，而不是getThisSvc()，这是避免使用到了缓存
        return thisSvc.getById(mo.getId());
    }

    /**
     * 删除
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(Long id) {
        EtlSyncStrategyMo          strategyMo = thisSvc.getById(id);
        // 先删除策略详情
        EtlSyncStrategyDetailDelTo delTo      = new EtlSyncStrategyDetailDelTo();
        delTo.setStrategyId(strategyMo.getId());
        syncStrategyDetailSvc.delSelective(delTo);
        // 后删除策略
        super.delById(id);
    }

    /**
     * 查询带有策略详情
     */
    @Override
    public EtlSyncStrategyMo getById(Long id) {
        // TODO Auto-generated method stub
        EtlSyncStrategyMo           strategyMo = super.getById(id);
        EtlSyncStrategyDetailListTo listTo     = new EtlSyncStrategyDetailListTo();
        listTo.setStrategyId(strategyMo.getId());
        List<EtlSyncStrategyDetailMo> list         = syncStrategyDetailSvc.list(listTo);
        Map<String, Set<String>>      srcFieldsMap = new HashMap<String, Set<String>>();
        Map<String, Set<String>>      dstFieldsMap = new HashMap<String, Set<String>>();
        list.stream().map(item -> {
            final Set<String>               srcFieldNames = new HashSet<>();
            final Set<String>               dstFieldNames = new HashSet<>();
            Map<String, String>             srcColumnsMap = this.getColumnsMapByTableName(strategyMo.getSrcConnId(), item.getSrcTableName());
            Map<String, String>             dstColumnsMap = this.getColumnsMapByTableName(strategyMo.getDstConnId(), item.getDstTableName());
            Iterator<Entry<String, String>> srcIterator   = srcColumnsMap.entrySet().iterator();
            while (srcIterator.hasNext()) {
                srcFieldNames.add(srcIterator.next().getKey());
            }
            srcFieldsMap.put(item.getSrcTableName(), srcFieldNames);
            Iterator<Entry<String, String>> dstIterator = dstColumnsMap.entrySet().iterator();
            while (dstIterator.hasNext()) {
                dstFieldNames.add(dstIterator.next().getKey());
            }
            dstFieldsMap.put(item.getDstTableName(), dstFieldNames);
            return item;
        }).collect(Collectors.toList());

        strategyMo.setStrategyDetailList(list);
        // 获取数据库下的所有表名
        List<String> srcTableNames = this.getTableNames(strategyMo.getSrcConnId());
        List<String> dstTableNames = this.getTableNames(strategyMo.getDstConnId());
        strategyMo.setSrcTableName(srcTableNames);
        strategyMo.setSrcFieldsMap(srcFieldsMap);
        strategyMo.setDstTableName(dstTableNames);
        strategyMo.setDstFieldsMap(dstFieldsMap);
        return strategyMo;
    }

    /**
     * 获取数据库的所有表名
     * 
     * @param connId 连接器ID
     * 
     */
    private List<String> getTableNames(Long connId) {
        EtlConnMo    ConnMo = connSvc.getById(connId);
        String       url    = JdbcUtils.getUrl(ConnMo.getHost(), ConnMo.getPort(), ConnMo.getDbName(),
                SqlDic.getItem(ConnMo.getDbType()).getDesc());
        List<String> list   = JdbcUtils.getTables(url, ConnMo.getUserName(), ConnMo.getUserPswd());
        return list;
    }

    /**
     * 获取表字段名以及对应字段类型
     * 
     * @param connId    连接器ID
     * @param tableName 查询的表名
     * 
     * @return 返回Map<字段名, 字段类型>
     */
    private Map<String, String> getColumnsMapByTableName(Long connId, String tableName) {
        EtlConnMo           ConnMo     = connSvc.getById(connId);
        String              url        = JdbcUtils.getUrl(ConnMo.getHost(), ConnMo.getPort(), ConnMo.getDbName(),
                SqlDic.getItem(ConnMo.getDbType()).getDesc());
        Map<String, String> columnsMap = JdbcUtils.getColumnsByTableName(url, ConnMo.getUserName(), ConnMo.getUserPswd(), tableName);
        return columnsMap;
    }

}
