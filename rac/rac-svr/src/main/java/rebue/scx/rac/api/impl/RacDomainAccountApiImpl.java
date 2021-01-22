package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacDomainAccountApi;
import rebue.scx.rac.jo.RacDomainAccountJo;
import rebue.scx.rac.mo.RacDomainAccountMo;
import rebue.scx.rac.svc.RacDomainAccountSvc;
import rebue.scx.rac.to.RacDomainAccountAddTo;
import rebue.scx.rac.to.RacDomainAccountDelTo;
import rebue.scx.rac.to.RacDomainAccountListTo;
import rebue.scx.rac.to.RacDomainAccountModifyTo;
import rebue.scx.rac.to.RacDomainAccountOneTo;
import rebue.scx.rac.to.RacDomainAccountPageTo;

/**
 * 领域账户API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacDomainAccountApiImpl extends
    BaseApiImpl<java.lang.Long, RacDomainAccountAddTo, RacDomainAccountModifyTo, RacDomainAccountDelTo, RacDomainAccountOneTo, RacDomainAccountListTo, RacDomainAccountPageTo, RacDomainAccountMo, RacDomainAccountJo, RacDomainAccountSvc>
    implements RacDomainAccountApi {
}
