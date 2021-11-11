package rebue.scx.oap.to;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * 认证记录
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class OapAuthLogModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "记录ID不能为空")
    private Long              id;

    /**
     * 是否成功认证：0失败，1成功
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean           isSuccess;

    /**
     * 操作时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     opDatetime;

    /**
     * 记录原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "记录原因的长度不能大于100")
    private String            reason;
}
