package rebue.scx.etl.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.to.EtlConnAddTo;
import rebue.scx.etl.to.EtlConnListTo;
import rebue.scx.etl.to.EtlConnModifyTo;
import rebue.scx.etl.to.EtlConnPageTo;

/**
 * 数据库连接器的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface EtlConnApi extends BaseApi<java.lang.Long, EtlConnAddTo, EtlConnModifyTo, EtlConnPageTo, EtlConnMo> {
    /**
     * 根据连接器ID查询表名
     *
     * @param id 数据库连接器ID
     */
    Ro<?> getTablesName(Long id);

    /**
     * 根据连接器ID和表名查询列名
     * 
     * @param id        数据库连接器ID
     * @param tableName 表名
     */
    Ro<?> getColumnsByTableName(Long id, final String tableName);

    /**
     * 测试连接
     * 
     * @param id 数据库连接器ID
     * 
     * @return
     */
    Ro<BooleanRa> testConnectionById(Long id);

    /**
     * 查询数据库连接器的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    Ro<ListRa<EtlConnMo>> list(EtlConnListTo qo);
}
