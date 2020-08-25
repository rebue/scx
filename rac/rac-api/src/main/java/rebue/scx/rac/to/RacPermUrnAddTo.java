package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 权限URN
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermUrnAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "不能为空")
    @Length(max = 32, message = "的长度不能大于32")
    private String permId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "不能为空")
    @Length(max = 100, message = "的长度不能大于100")
    private String urn;
}
