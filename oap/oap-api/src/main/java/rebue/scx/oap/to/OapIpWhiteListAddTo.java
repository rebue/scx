package rebue.scx.oap.to;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * 第三方应用IP白名单
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class OapIpWhiteListAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * OAP_APP主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "OAP_APP主键不能为空")
    @PositiveOrZero(message = "OAP_APP主键不能为负数")
    private Long              appId;

    /**
     * 白名单IP
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "白名单IP不能为空")
    @Length(max = 255, message = "白名单IP的长度不能大于255")
    private String            ipAddr;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "建立时间戳不能为空")
    @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "修改时间戳不能为空")
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long              updateTimestamp;
}
