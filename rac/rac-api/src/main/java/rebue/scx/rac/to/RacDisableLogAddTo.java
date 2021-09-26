package rebue.scx.rac.to;

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
 * 账户启/禁用日志
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacDisableLogAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            realmId;

    /**
     * 代理禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "代理禁用操作员ID不能为负数")
    private Long              disableOpAgentId;

    /**
     * 代理启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "代理启用操作员ID不能为负数")
    private Long              enableOpAgentId;

    /**
     * 启用操作员ID
     */
    @PositiveOrZero(message = "启用操作员ID不能为负数")
    private Long              enableOpId;

    /**
     * 禁用账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "禁用账户ID不能为空")
    @PositiveOrZero(message = "禁用账户ID不能为负数")
    private Long              accountId;

    /**
     * 禁用操作员ID
     */
    @PositiveOrZero(message = "禁用操作员ID不能为负数")
    private Long              disableOpId;

    /**
     * 禁用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "禁用原因不能为空")
    @Length(max = 100, message = "禁用原因的长度不能大于100")
    private String            disableReason;

    /**
     * 禁用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "禁用时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     disableDatetime;

    /**
     * 启用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "启用原因的长度不能大于100")
    private String            enableReason;

    /**
     * 启用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     enableDatetime;
}
