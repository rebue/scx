package rebue.scx.rac.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 菜单信息
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacMenuModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String id;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "的长度不能大于32")
    private String sysId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String code;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String name;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "的长度不能大于30")
    private String title;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String path;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "的长度不能大于20")
    private String icon;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "的长度不能大于50")
    private String remark;
}
