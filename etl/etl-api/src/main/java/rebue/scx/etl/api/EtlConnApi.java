package rebue.scx.etl.api;

import rebue.robotech.api.BaseApi;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.to.EtlConnAddTo;
import rebue.scx.etl.to.EtlConnModifyTo;
import rebue.scx.etl.to.EtlConnPageTo;

/**
 * 数据库连接器的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface EtlConnApi extends BaseApi<java.lang.Long, EtlConnAddTo, EtlConnModifyTo, EtlConnPageTo, EtlConnMo> {
}
