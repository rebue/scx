package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacPersonApi;
import rebue.scx.rac.jo.RacPersonJo;
import rebue.scx.rac.mo.RacPersonMo;
import rebue.scx.rac.svc.RacPersonSvc;
import rebue.scx.rac.to.RacPersonAddTo;
import rebue.scx.rac.to.RacPersonDelTo;
import rebue.scx.rac.to.RacPersonListTo;
import rebue.scx.rac.to.RacPersonModifyTo;
import rebue.scx.rac.to.RacPersonOneTo;
import rebue.scx.rac.to.RacPersonPageTo;

/**
 * 个人API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacPersonApiImpl
    extends BaseApiImpl<java.lang.Long, RacPersonAddTo, RacPersonModifyTo, RacPersonDelTo, RacPersonOneTo, RacPersonListTo, RacPersonPageTo, RacPersonMo, RacPersonJo, RacPersonSvc>
    implements RacPersonApi {
}
