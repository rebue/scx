package rebue.scx.rac.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
 * 菜单
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacMenuAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(message = "系统ID不能为空")
    @Length(max = 32, message = "系统ID的长度不能大于32")
    private String            sysId;
    /**
    * 菜单编码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(message = "菜单编码不能为空")
    @Length(max = 20, message = "菜单编码的长度不能大于20")
    private String            code;
    /**
    * 菜单名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称的长度不能大于20")
    private String            name;
    /**
    * 标题(点击菜单后显示在内容页面的标题)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 30, message = "标题的长度不能大于30")
    private String            title;
    /**
    * 路径
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotBlank(message = "路径不能为空")
    @Length(max = 20, message = "路径的长度不能大于20")
    private String            path;
    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @NotNull(message = "是否启用不能为空")
    private Boolean           isEnabled;
    /**
    * 菜单图标
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 20, message = "菜单图标的长度不能大于20")
    private String            icon;
    /**
    * 菜单备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 50, message = "菜单备注的长度不能大于50")
    private String            remark;

}