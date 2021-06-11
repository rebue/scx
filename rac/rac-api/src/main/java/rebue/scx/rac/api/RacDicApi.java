package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacDicMo;
import rebue.scx.rac.ra.DicListWithItemRa;
import rebue.scx.rac.to.RacDicAddTo;
import rebue.scx.rac.to.RacDicModifyTo;
import rebue.scx.rac.to.RacDicPageTo;
import rebue.scx.rac.to.ex.DicListWithItemTo;

/**
 * 字典API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacDicApi extends BaseApi<java.lang.Long, RacDicAddTo, RacDicModifyTo, RacDicPageTo, RacDicMo> {

    /**
     * 查询字典的信息
     *
     * @param qo 查询的具体条件
     */
    Ro<DicListWithItemRa> listWithDic(DicListWithItemTo to);
}
