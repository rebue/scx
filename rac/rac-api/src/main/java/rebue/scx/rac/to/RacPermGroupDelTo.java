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
 * 权限分组
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacPermGroupDelTo implements Serializable {

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
    * 权限分组名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 20, message = "权限分组名称的长度不能大于20")
    private String            name;
    /**
    * 是否启用(如果分组没有启用，其下所有权限都要被设置为不启用；只要有一个权限启用，其分组就必须启用)
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
    private Byte              orderNo;
    /**
    * 权限分组备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 50, message = "权限分组备注的长度不能大于50")
    private String            remark;

}