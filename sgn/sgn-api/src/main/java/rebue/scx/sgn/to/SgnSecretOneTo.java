package rebue.scx.sgn.to;

import java.io.Serializable;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * 签名密钥
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SgnSecretOneTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 128, message = "密钥的长度不能大于128")
    private String            secret;
}