package rebue.scx.rac.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 禁用账户并记录日志
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAccountDisableTo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 启用或禁用
     */
    @NotNull(message = "是否启用不能为空")
    private Boolean           isEnabled;

    /**
     * 领域ID
     *
     */
    private String            realmId;

    /**
     * 被禁用账户的账户ID
     *
     */
    @PositiveOrZero(message = "禁用账户的账户ID不能为负数")
    private Long              disableAccountId;

    /**
     * 禁用操作员的账户ID
     *
     */
    @PositiveOrZero(message = "禁用操作员的账户ID不能为负数")
    private Long              lockOpId;

    /**
     * 禁用原因
     *
     */
    @Length(max = 100, message = "禁用原因的长度不能大于100")
    private String            disableReason;

    /**
     * 禁用时间
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     disableDatetime;

    /**
     * 启用原因
     */
    @Length(max = 100, message = "启用原因的长度不能大于100")
    private String            eableReason;

    /**
     * 启用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     eableDatetime;

    /**
     * 启用操作员的账户ID
     */
    @PositiveOrZero(message = "启用操作员的账户ID不能为负数")
    private Long              eableOpId;

}
