package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacDicItemApi;
import rebue.scx.rac.jo.RacDicItemJo;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.svc.RacDicItemSvc;
import rebue.scx.rac.to.RacDicItemAddTo;
import rebue.scx.rac.to.RacDicItemDelTo;
import rebue.scx.rac.to.RacDicItemListTo;
import rebue.scx.rac.to.RacDicItemModifyTo;
import rebue.scx.rac.to.RacDicItemOneTo;
import rebue.scx.rac.to.RacDicItemPageTo;

/**
 * 字典项API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacDicItemApiImpl extends
    BaseApiImpl<java.lang.Long, RacDicItemAddTo, RacDicItemModifyTo, RacDicItemDelTo, RacDicItemOneTo, RacDicItemListTo, RacDicItemPageTo, RacDicItemMo, RacDicItemJo, RacDicItemSvc>
    implements RacDicItemApi {

    /**
     * 上移动字典项的信息，传入ID
     *
     * @param qo
     */
    @Override
    public Ro<?> moveUp(RacDicItemModifyTo qo) {
        _svc.moveUp(qo);
        return new Ro<>(ResultDic.SUCCESS, "上移成功");
    }

    /**
     * 下移动字典项的信息，传入ID
     *
     * @param qo
     */
    @Override
    public Ro<?> moveDown(RacDicItemModifyTo qo) {
        _svc.moveDown(qo);
        return new Ro<>(ResultDic.SUCCESS, "下移成功");
    }
}
