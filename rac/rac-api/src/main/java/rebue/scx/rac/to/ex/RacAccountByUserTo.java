/**
 * @mbg.dontOverWriteFile
 */
package rebue.scx.rac.to.ex;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rebue.robotech.to.PageTo;

/**
 * 账户
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacAccountByUserTo extends PageTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     *
     */
    @NotNull(message = "账户ID不能为空")
    @PositiveOrZero(message = "账户ID不能为负数")
    private Long              accountId;
    /**
     * 领域ID
     */
    @NotNull(message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            realmId;

    /**
     * 查询账户的关键字
     */
    @Length(max = 256, message = "搜索关键字不能超过20位数")
    private String            keywords;

}
