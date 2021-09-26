package rebue.scx.etl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.etl.api.EtlSyncStrategyDetailApi;
import rebue.scx.etl.jo.EtlSyncStrategyDetailJo;
import rebue.scx.etl.mo.EtlSyncStrategyDetailMo;
import rebue.scx.etl.svc.EtlSyncStrategyDetailSvc;
import rebue.scx.etl.to.EtlSyncStrategyDetailAddTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailDelTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailListTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailOneTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailPageTo;

/**
 * 同步策略详情API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class EtlSyncStrategyDetailApiImpl extends
    BaseApiImpl<java.lang.Long, EtlSyncStrategyDetailAddTo, EtlSyncStrategyDetailModifyTo, EtlSyncStrategyDetailDelTo, EtlSyncStrategyDetailOneTo, EtlSyncStrategyDetailListTo, EtlSyncStrategyDetailPageTo, EtlSyncStrategyDetailMo, EtlSyncStrategyDetailJo, EtlSyncStrategyDetailSvc>
    implements EtlSyncStrategyDetailApi {
}
