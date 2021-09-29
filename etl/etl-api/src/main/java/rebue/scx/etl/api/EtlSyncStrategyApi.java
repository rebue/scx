package rebue.scx.etl.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.etl.mo.EtlSyncStrategyMo;
import rebue.scx.etl.to.EtlSyncStrategyAddTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyEnableTo;
import rebue.scx.etl.to.EtlSyncStrategyModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyPageTo;

/**
 * 同步策略的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface EtlSyncStrategyApi extends BaseApi<java.lang.Long, EtlSyncStrategyAddTo, EtlSyncStrategyModifyTo, EtlSyncStrategyPageTo, EtlSyncStrategyMo> {
    /**
     * 启用/禁用策略
     *
     */
    Ro<?> enable(EtlSyncStrategyModifyEnableTo to);
}
