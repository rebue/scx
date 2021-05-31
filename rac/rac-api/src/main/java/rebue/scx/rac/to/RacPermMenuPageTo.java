package rebue.scx.rac.to;

import java.io.Serializable;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rebue.robotech.to.PageTo;

/**
 * 权限菜单
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacPermMenuPageTo extends PageTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String            sysId;

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "权限ID不能为负数")
    private Long              permId;

    /**
     * 菜单URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "菜单URN的长度不能大于100")
    private String            menuUrn;
}
