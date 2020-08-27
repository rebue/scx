package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户操作日志
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacOpLogAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID(如为1则是散客)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "用户ID不能为空")
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long userId;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "系统ID不能为空")
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String sysId;

    /**
     * 操作标题
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "操作标题不能为空")
    @Length(max = 32, message = "操作标题的长度不能大于32")
    private String opTitle;

    /**
     * 操作详情
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "操作详情不能为空")
    @Length(max = 300, message = "操作详情的长度不能大于300")
    private String opDetail;

    /**
     * 操作时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "操作时间不能为空")
    private LocalDateTime opDatetime;
}
