package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacPermUrnApi;
import rebue.scx.rac.jo.RacPermUrnJo;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.svc.RacPermUrnSvc;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnDelTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnOneTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URNAPI实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermUrnApiImpl
    extends BaseApiImpl<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnDelTo, RacPermUrnOneTo, RacPermUrnPageTo, RacPermUrnMo, RacPermUrnJo, RacPermUrnSvc>
    implements RacPermUrnApi {
}
