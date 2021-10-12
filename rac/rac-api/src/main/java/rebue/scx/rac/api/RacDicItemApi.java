package rebue.scx.rac.api;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.to.RacDicItemAddTo;
import rebue.scx.rac.to.RacDicItemModifyTo;
import rebue.scx.rac.to.RacDicItemPageTo;

/**
 * 字典项的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacDicItemApi extends BaseApi<java.lang.Long, RacDicItemAddTo, RacDicItemModifyTo, RacDicItemPageTo, RacDicItemMo> {
    /**
     * 上移动字典项的信息，传入ID
     *
     * @param qo
     */
    Ro<?> moveUp(RacDicItemModifyTo qo);

    /**
     * 下移动字典项的信息，传入ID
     *
     * @param qo
     */
    Ro<?> moveDown(RacDicItemModifyTo qo);
}
