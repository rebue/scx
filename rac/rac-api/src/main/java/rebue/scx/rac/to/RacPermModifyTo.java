package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 权限信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "权限ID不能为空")
    @Length(max = 32, message = "权限ID的长度不能大于32")
    private String id;

    /**
     * 权限分组ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "权限分组ID的长度不能大于32")
    private String groupId;

    /**
     * 系统ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String sysId;

    /**
     * 权限名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "权限名称的长度不能大于20")
    private String name;

    /**
     * 是否鉴权(不鉴权意味着放开访问权限)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isAuthorize;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte orderNo;

    /**
     * 权限备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "权限备注的长度不能大于50")
    private String remark;
}
