package rebue.scx.rac.to;

import java.io.Serializable;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * 权限
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermDelTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 权限分组ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "权限分组ID不能为负数")
    private Long              groupId;

    /**
     * 权限名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "权限名称的长度不能大于20")
    private String            name;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean           isEnabled;

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "顺序号不能为负数")
    private Byte              seqNo;

    /**
     * 权限备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "权限备注的长度不能大于50")
    private String            remark;
}
