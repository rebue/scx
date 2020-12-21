package rebue.scx.sgn.to;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import rebue.robotech.valid.ModifyGroup;

/**
 * 签名密钥
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SgnSecretAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID 一般会设置为OrgID
     *
     * 这里的ID须手动传过来
     */
    @NotBlank(groups = ModifyGroup.class, message = "ID不能为空")
    @Length(max = 128, message = "ID的长度不能大于128")
    private String            id;

    /**
     * 密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "密钥不能为空")
    @Length(max = 128, message = "密钥的长度不能大于128")
    private String            secret;
}
