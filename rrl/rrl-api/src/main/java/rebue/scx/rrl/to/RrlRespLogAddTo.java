package rebue.scx.rrl.to;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
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
public class RrlRespLogAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "响应状态码不能为空")
    @Length(max = 3, message = "响应状态码的长度不能大于3")
    private String            statusCode;

    /**
     * 响应头部
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "响应头部不能为空")
    @Length(max = 3076, message = "响应头部的长度不能大于3076")
    private String            headers;

    /**
     * ID(传入响应ID，不要自动生成，因为要设置响应ID等于请求ID)
     */
    @NotNull(message = "ID不能为空")
    @PositiveOrZero(message = "ID不能为负数")
    private Long              id;

    /**
     * 响应主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "响应主体的长度不能大于2048")
    private String            body;

    /**
     * 响应时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "响应时间戳不能为空")
    @PositiveOrZero(message = "响应时间戳不能为负数")
    private Long              createTimestamp;

    /**
     * COOKIES
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "COOKIES的长度不能大于2048")
    private String            cookies;
}
