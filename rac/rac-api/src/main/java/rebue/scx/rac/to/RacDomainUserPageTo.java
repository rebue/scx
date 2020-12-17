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
 * 领域用户
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RacDomainUserPageTo extends PageTo implements Serializable {

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
    * 用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long              userId;
    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Boolean           isEnabled;

}