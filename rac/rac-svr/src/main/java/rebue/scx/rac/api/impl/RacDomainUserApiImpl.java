package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacDomainUserApi;
import rebue.scx.rac.jo.RacDomainUserJo;
import rebue.scx.rac.mo.RacDomainUserMo;
import rebue.scx.rac.svc.RacDomainUserSvc;
import rebue.scx.rac.to.RacDomainUserAddTo;
import rebue.scx.rac.to.RacDomainUserDelTo;
import rebue.scx.rac.to.RacDomainUserModifyTo;
import rebue.scx.rac.to.RacDomainUserOneTo;
import rebue.scx.rac.to.RacDomainUserPageTo;

/**
 * 领域用户API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacDomainUserApiImpl extends
    BaseApiImpl<java.lang.Long, RacDomainUserAddTo, RacDomainUserModifyTo, RacDomainUserDelTo, RacDomainUserOneTo, RacDomainUserPageTo, RacDomainUserMo, RacDomainUserJo, RacDomainUserSvc>
    implements RacDomainUserApi {
}
