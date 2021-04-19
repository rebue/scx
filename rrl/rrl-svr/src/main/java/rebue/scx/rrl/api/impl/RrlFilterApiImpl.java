package rebue.scx.rrl.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rrl.api.RrlFilterApi;
import rebue.scx.rrl.jo.RrlFilterJo;
import rebue.scx.rrl.mo.RrlFilterMo;
import rebue.scx.rrl.svc.RrlFilterSvc;
import rebue.scx.rrl.to.RrlFilterAddTo;
import rebue.scx.rrl.to.RrlFilterDelTo;
import rebue.scx.rrl.to.RrlFilterListTo;
import rebue.scx.rrl.to.RrlFilterModifyTo;
import rebue.scx.rrl.to.RrlFilterOneTo;
import rebue.scx.rrl.to.RrlFilterPageTo;

/**
 * 过滤器API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RrlFilterApiImpl
    extends BaseApiImpl<java.lang.Long, RrlFilterAddTo, RrlFilterModifyTo, RrlFilterDelTo, RrlFilterOneTo, RrlFilterListTo, RrlFilterPageTo, RrlFilterMo, RrlFilterJo, RrlFilterSvc>
    implements RrlFilterApi {
}
