/**
 * @mbg.dontOverWriteFile
 */
package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rebue.robotech.to.PageTo;

/**
 * 账户unionId映射
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacAccountUnionIdTo extends PageTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 映射来源账户ID
     *
     */
    @NotNull(message = "映射来源账户ID不能为空")
    @PositiveOrZero(message = "映射来源账户ID不能为负数")
    private Long              srcId;
    /**
     * 映射目的账户ID
     *
     */
    @NotNull(message = "映射目的账户ID不能为空")
    @PositiveOrZero(message = "映射目的账户ID不能为负数")
    private Long              dstId;

}
