package rebue.scx.rac.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rebue.robotech.to.PageTo;

/**
 * 字典项
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacDicItemPageTo extends PageTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "字典ID不能为负数")
    private Long              dicId;

    /**
     * 字典项名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 200, message = "字典项名称的长度不能大于200")
    private String            name;

    /**
     * 字典备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "字典备注的长度不能大于50")
    private String            remark;

    /**
     * 树编码(每三位为一级)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "树编码的长度不能大于50")
    private String            treeCode;

    /**
     * 字典项Key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "字典项Key的长度不能大于32")
    private String            dicItemKey;

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "组织ID不能为负数")
    private Long              orgId;

    /**
     * 修改时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     updateDatetime;

    /**
     * 字典项值
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 200, message = "字典项值的长度不能大于200")
    private String            dicItemValue;
}
