package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 组织信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacOrgModifyTo implements Serializable {

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
    @Length(max = 30, message = "的长度不能大于30")
    private String name;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Long parentId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Byte orgType;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Integer leftValue;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "不能为负数")
    private Integer rightValue;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 80, message = "的长度不能大于80")
    private String fullName;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 200, message = "的长度不能大于200")
    private String introduction;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "的长度不能大于100")
    private String remark;
}
