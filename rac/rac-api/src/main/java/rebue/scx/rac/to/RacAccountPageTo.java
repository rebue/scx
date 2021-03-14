/**
 * @mbg.dontOverWrite
 */
package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import rebue.robotech.to.PageTo;

import java.io.Serializable;

/**
 * 账户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacAccountPageTo extends PageTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 领域ID
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 关键字
     */
    @Length(max = 256, message = "QQ的OpenId的长度不能大于256")
    private String            keywords;

}
