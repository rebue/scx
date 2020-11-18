package rebue.scx.rac.to;import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * 操作日志
 * 
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacOpLogModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "操作日志ID不能为空") @PositiveOrZero(message = "操作日志ID不能为负数")
    private Long id;

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long userId;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String sysId;

    /**
     * 操作标题
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "操作标题的长度不能大于32")
    private String opTitle;

    /**
     * 操作详情
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "操作详情的长度不能大于300")
    private String opDetail;

    /**
     * 操作时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime opDatetime;

    /**
     * 操作类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "操作类型的长度不能大于20")
    private String opType;
}
