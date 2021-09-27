package rebue.scx.etl.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.etl.jo.EtlConnJo;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.to.EtlConnAddTo;
import rebue.scx.etl.to.EtlConnDelTo;
import rebue.scx.etl.to.EtlConnListTo;
import rebue.scx.etl.to.EtlConnModifyTo;
import rebue.scx.etl.to.EtlConnOneTo;
import rebue.scx.etl.to.EtlConnPageTo;

/**
 * 数据库连接器服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface EtlConnSvc extends BaseSvc<java.lang.Long, EtlConnAddTo, EtlConnModifyTo, EtlConnDelTo, EtlConnOneTo, EtlConnListTo, EtlConnPageTo, EtlConnMo, EtlConnJo> {
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
    Ro<?> getColumnsByTableName(Long id, String tableName);

    /**
     * 测试连接
     * 
     * @param id 数据库连接器ID
     * 
     * @return
     */
    Ro<BooleanRa> testConnectionById(Long id);
}
