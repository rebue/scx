package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class RacOpLogModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Long id;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Long userId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String sysId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String opTitle;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 300, message = "的长度不能大于300")
    private String opDetail;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private LocalDateTime opDatetime;
}
