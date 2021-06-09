package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import lombok.NoArgsConstructor;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.mo.RacDicMo;

/**
 * 当前字典信息
 *
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DicListWithItemRa implements Serializable {

    private static final long  serialVersionUID = 1L;

    /**
     * 分页参数
     */
    private PageInfo<RacDicMo> page;

    /**
     * 字典列表
     */
    private List<RacDicMo>     dicList;

    /**
     * 字典项列表
     */
    private List<RacDicItemMo> itemList;

}
