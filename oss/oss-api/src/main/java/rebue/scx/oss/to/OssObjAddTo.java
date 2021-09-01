package rebue.scx.oss.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 对象
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class OssObjAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 对象类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "对象类型不能为空")
    @Length(max = 30, message = "对象类型的长度不能大于30")
    private String            objType;

    /**
     * 对象大小
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "对象大小不能为空")
    @PositiveOrZero(message = "对象大小不能为负数")
    private Long              objSize;

    /**
     * 对象URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "对象URL不能为空")
    @Length(max = 512, message = "对象URL的长度不能大于512")
    private String            url;

    /**
     * 创建人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "创建人的账户ID不能为空")
    @PositiveOrZero(message = "创建人的账户ID不能为负数")
    private Long              creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "创建时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     createDatetime;

    /**
     * 对象名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "对象名称不能为空")
    @Length(max = 512, message = "对象名称的长度不能大于512")
    private String            objName;

    /**
     * 对象分组
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "对象分组不能为空")
    @Length(max = 30, message = "对象分组的长度不能大于30")
    private String            objGroup;

    /**
     * 创建人的组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "创建人的组织ID不能为负数")
    private Long              creatorOrgId;
}
