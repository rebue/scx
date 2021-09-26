package rebue.scx.etl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
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
    BaseApiImpl<java.lang.Long, EtlConnAddTo, EtlConnModifyTo, EtlConnDelTo, EtlConnOneTo, EtlConnListTo, EtlConnPageTo, EtlConnMo, EtlConnJo, EtlConnSvc> implements EtlConnApi {
}
