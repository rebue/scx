package rebue.scx.rac.to;import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;



/**
 * 领域
 * 
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacDomainOneTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 领域名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "领域名称的长度不能大于20")
    private String name;

    /**
     * 领域备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "领域备注的长度不能大于50")
    private String remark;
}
