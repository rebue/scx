package rebue.scx.rac.to.ex;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.mo.RacDicMo;

/**
 * 单一字典且带字典项
 *
 */
@ToString
@JsonInclude(Include.NON_NULL)
public class RacDicMoEx extends RacDicMo {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;

    /**
     * 字典项
     */
    @Setter
    @Getter
    private List<RacDicItemMo> dicItems;

}
