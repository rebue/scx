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
     * 菜单ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "菜单ID的长度不能大于32")
    private String id;

    /**
     * 菜单编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "菜单编码的长度不能大于20")
    private String code;

    /**
     * 菜单名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "菜单名称的长度不能大于20")
    private String name;

    /**
     * 标题
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "标题的长度不能大于30")
    private String title;

    /**
     * 路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "路径的长度不能大于20")
    private String path;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * 菜单图标
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "菜单图标的长度不能大于20")
    private String icon;

    /**
     * 菜单备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "菜单备注的长度不能大于50")
    private String remark;
}
