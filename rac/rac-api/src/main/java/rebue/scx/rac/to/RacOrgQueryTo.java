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
public class RacOrgQueryTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组织名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "组织名称的长度不能大于30")
    private String name;

    /**
     * 组织类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "组织类型不能为负数")
    private Byte orgType;

    /**
     * 左值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "左值不能为负数")
    private Integer leftValue;

    /**
     * 右值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "右值不能为负数")
    private Integer rightValue;

    /**
     * 组织全名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 80, message = "组织全名的长度不能大于80")
    private String fullName;

    /**
     * 组织简介
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 200, message = "组织简介的长度不能大于200")
    private String introduction;

    /**
     * 组织备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "组织备注的长度不能大于100")
    private String remark;
}
