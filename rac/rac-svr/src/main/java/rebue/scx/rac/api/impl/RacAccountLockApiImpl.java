package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacAccountLockApi;
import rebue.scx.rac.jo.RacAccountLockJo;
import rebue.scx.rac.mo.RacAccountLockMo;
import rebue.scx.rac.svc.RacAccountLockSvc;
import rebue.scx.rac.to.RacAccountLockAddTo;
import rebue.scx.rac.to.RacAccountLockDelTo;
import rebue.scx.rac.to.RacAccountLockListTo;
import rebue.scx.rac.to.RacAccountLockModifyTo;
import rebue.scx.rac.to.RacAccountLockOneTo;
import rebue.scx.rac.to.RacAccountLockPageTo;

/**
 * 账户锁定API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacAccountLockApiImpl extends
    BaseApiImpl<java.lang.Long, RacAccountLockAddTo, RacAccountLockModifyTo, RacAccountLockDelTo, RacAccountLockOneTo, RacAccountLockListTo, RacAccountLockPageTo, RacAccountLockMo, RacAccountLockJo, RacAccountLockSvc>
    implements RacAccountLockApi {
}
