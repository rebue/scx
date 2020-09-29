package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import rebue.robotech.to.ListTo;

/**
 * 权限URN
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacPermUrnListTo extends ListTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "权限ID的长度不能大于32")
    private String permId;

    /**
     * URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "URN的长度不能大于100")
    private String urn;
}