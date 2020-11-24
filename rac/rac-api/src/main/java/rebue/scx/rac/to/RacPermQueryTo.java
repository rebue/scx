package rebue.scx.rac.to;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 权限信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermQueryTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String groupId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String sysId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String name;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isAuthorize;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte orderNo;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "的长度不能大于50")
    private String remark;
}
