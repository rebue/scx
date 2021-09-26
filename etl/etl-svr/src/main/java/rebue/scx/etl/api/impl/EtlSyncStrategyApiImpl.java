package rebue.scx.etl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.etl.api.EtlSyncStrategyApi;
import rebue.scx.etl.jo.EtlSyncStrategyJo;
import rebue.scx.etl.mo.EtlSyncStrategyMo;
import rebue.scx.etl.svc.EtlSyncStrategySvc;
import rebue.scx.etl.to.EtlSyncStrategyAddTo;
import rebue.scx.etl.to.EtlSyncStrategyDelTo;
import rebue.scx.etl.to.EtlSyncStrategyListTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyOneTo;
import rebue.scx.etl.to.EtlSyncStrategyPageTo;

/**
 * 同步策略API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class EtlSyncStrategyApiImpl extends
    BaseApiImpl<java.lang.Long, EtlSyncStrategyAddTo, EtlSyncStrategyModifyTo, EtlSyncStrategyDelTo, EtlSyncStrategyOneTo, EtlSyncStrategyListTo, EtlSyncStrategyPageTo, EtlSyncStrategyMo, EtlSyncStrategyJo, EtlSyncStrategySvc>
    implements EtlSyncStrategyApi {
}
