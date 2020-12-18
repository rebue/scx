package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacPermGroupApi;
import rebue.scx.rac.jo.RacPermGroupJo;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.svc.RacPermGroupSvc;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupDelTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermGroupOneTo;
import rebue.scx.rac.to.RacPermGroupPageTo;

/**
 * 权限分组API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermGroupApiImpl extends
    BaseApiImpl<java.lang.Long, RacPermGroupAddTo, RacPermGroupModifyTo, RacPermGroupDelTo, RacPermGroupOneTo, RacPermGroupPageTo, RacPermGroupMo, RacPermGroupJo, RacPermGroupSvc>
    implements RacPermGroupApi {
}
