package rebue.scx.rrl.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 响应日志
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RrlRespLogModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "ID不能为空")
    @PositiveOrZero(message = "ID不能为负数")
    private Long              id;

    /**
     * 响应状态码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 3, message = "响应状态码的长度不能大于3")
    private String            statusCode;

    /**
     * 响应头部
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 3076, message = "响应头部的长度不能大于3076")
    private String            headers;
}
