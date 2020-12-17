package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacMenuApi;
import rebue.scx.rac.jo.RacMenuJo;
import rebue.scx.rac.mo.RacMenuMo;
import rebue.scx.rac.svc.RacMenuSvc;
import rebue.scx.rac.to.RacMenuAddTo;
import rebue.scx.rac.to.RacMenuDelTo;
import rebue.scx.rac.to.RacMenuModifyTo;
import rebue.scx.rac.to.RacMenuOneTo;
import rebue.scx.rac.to.RacMenuPageTo;

/**
 * 菜单API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacMenuApiImpl extends BaseApiImpl<java.lang.Long, RacMenuAddTo, RacMenuModifyTo, RacMenuDelTo, RacMenuOneTo, RacMenuPageTo, RacMenuMo, RacMenuJo, RacMenuSvc>
    implements RacMenuApi {
}
