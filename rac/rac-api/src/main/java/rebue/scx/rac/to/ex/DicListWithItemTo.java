package rebue.scx.rac.to.ex;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rebue.robotech.to.PageTo;

/**
 * 查询字典信息
 *
 */

@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DicListWithItemTo extends PageTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    @Getter
    @Setter
    private String            keywords;
    /**
     * 领域ID
     */
    @Getter
    @Setter
    private List<String>      domainIds;
    /**
     * 系统ID
     */
    @Getter
    @Setter
    private List<String>      sysIds;

}
