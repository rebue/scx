package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacPermMenuApi;
import rebue.scx.rac.jo.RacPermMenuJo;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuDelTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuOneTo;
import rebue.scx.rac.to.RacPermMenuPageTo;

/**
 * 权限菜单API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPermMenuApiImpl extends
    BaseApiImpl<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuDelTo, RacPermMenuOneTo, RacPermMenuListTo, RacPermMenuPageTo, RacPermMenuMo, RacPermMenuJo, RacPermMenuSvc>
    implements RacPermMenuApi {
}
