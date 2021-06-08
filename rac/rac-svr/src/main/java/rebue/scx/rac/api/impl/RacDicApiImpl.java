package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.rac.api.RacDicApi;
import rebue.scx.rac.jo.RacDicJo;
import rebue.scx.rac.mo.RacDicMo;
import rebue.scx.rac.svc.RacDicSvc;
import rebue.scx.rac.to.RacDicAddTo;
import rebue.scx.rac.to.RacDicDelTo;
import rebue.scx.rac.to.RacDicListTo;
import rebue.scx.rac.to.RacDicModifyTo;
import rebue.scx.rac.to.RacDicOneTo;
import rebue.scx.rac.to.RacDicPageTo;

/**
 * 字典API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacDicApiImpl extends BaseApiImpl<java.lang.String, RacDicAddTo, RacDicModifyTo, RacDicDelTo, RacDicOneTo, RacDicListTo, RacDicPageTo, RacDicMo, RacDicJo, RacDicSvc>
    implements RacDicApi {

}
