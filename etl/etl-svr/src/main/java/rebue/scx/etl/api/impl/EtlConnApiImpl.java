package rebue.scx.etl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.etl.api.EtlConnApi;
import rebue.scx.etl.jo.EtlConnJo;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.svc.EtlConnSvc;
import rebue.scx.etl.to.EtlConnAddTo;
import rebue.scx.etl.to.EtlConnDelTo;
import rebue.scx.etl.to.EtlConnListTo;
import rebue.scx.etl.to.EtlConnModifyTo;
import rebue.scx.etl.to.EtlConnOneTo;
import rebue.scx.etl.to.EtlConnPageTo;

/**
 * 数据库连接器API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class EtlConnApiImpl extends
        BaseApiImpl<java.lang.Long, EtlConnAddTo, EtlConnModifyTo, EtlConnDelTo, EtlConnOneTo, EtlConnListTo, EtlConnPageTo, EtlConnMo, EtlConnJo, EtlConnSvc>
        implements EtlConnApi {

    /**
     * 查询数据库连接器的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public Ro<ListRa<EtlConnMo>> list(EtlConnListTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(_svc.list(qo)));
    }

    /**
     * 根据连接器ID查询表名
     *
     * @param id 数据库连接器ID
     */
    @Override
    public Ro<?> getTablesName(Long id) {
        return _svc.getTablesName(id);
    }

    /**
     * 根据连接器ID和表名查询列名
     * 
     * @param id        数据库连接器ID
     * @param tableName 表名
     */
    @Override
    public Ro<?> getColumnsByTableName(Long id, String tableName) {
        return _svc.getColumnsByTableName(id, tableName);
    }

    /**
     * 测试连接
     * 
     * @param id 数据库连接器ID
     * 
     * @return
     */
    @Override
    public Ro<BooleanRa> testConnectionById(Long id) {
        return _svc.testConnectionById(id);
    }

}
