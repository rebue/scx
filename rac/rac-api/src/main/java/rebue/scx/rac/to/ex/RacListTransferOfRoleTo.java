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

/**
 * 角色以及角色账户关系
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacListTransferOfRoleTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 领域ID
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    @NotNull(message = "账户ID不能为空")
    private String            domainId;

    /**
     * 查询角色下可添加账户的关键字
     */
    @Length(max = 256, message = "搜索关键字不能超过20位数")
    private String            addableKeywords;

    /**
     * 查询角色下存在账户的关键字
     */
    @Length(max = 256, message = "搜索关键字不能超过20位数")
    private String            existKeywords;

    /**
     * 账户ID
     *
     */
    @PositiveOrZero(message = "账户ID不能为负数")
    @NotNull(message = "账户ID不能为空")
    private Long              accountId;

}
