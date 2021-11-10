package rebue.scx.rac.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.svc.RacDicSvc;
import rebue.scx.rac.to.ex.RacDicMoEx;

/**
 * 登堡配置
 * 
 * @author yuanman
 *
 */
@Component
public class LevelProtectUtils {
    @Resource
    private RacDicSvc racDicSvc;
    /**
     * 等保字典的key
     */
    private String    LEVEL_PROTECT = "levelProtect";

    public Map<String, String> getConfigMap() {
        RacDicMoEx          dicMo = (RacDicMoEx) racDicSvc.getByDicKey(LEVEL_PROTECT);
        Map<String, String> map   = new HashMap<String, String>();
        if (dicMo != null && dicMo.getDicItems() != null) {
            List<RacDicItemMo> dicItems = dicMo.getDicItems();
            dicItems.stream().map(item -> {
                map.put(item.getDicItemKey(), item.getDicItemValue());
                return item;
            }).distinct()
                    .collect(Collectors.toList());
            return map;
        }
        return map;
    }
}
