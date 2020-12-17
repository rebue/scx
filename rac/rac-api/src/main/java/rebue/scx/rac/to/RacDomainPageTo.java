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
import lombok.EqualsAndHashCode;
import rebue.robotech.to.PageTo;

/**
 * 领域
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacDomainPageTo extends PageTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    * 领域名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 20, message = "领域名称的长度不能大于20")
    private String            name;
    /**
    * 领域备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @Length(max = 50, message = "领域备注的长度不能大于50")
    private String            remark;

}