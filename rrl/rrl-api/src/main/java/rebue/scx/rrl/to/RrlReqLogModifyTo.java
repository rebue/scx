package rebue.scx.rrl.to;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 请求日志
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RrlReqLogModifyTo implements Serializable {

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
     * 请求方法
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 10, message = "请求方法的长度不能大于10")
    private String            method;

    /**
     * 请求地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 512, message = "请求地址的长度不能大于512")
    private String            uri;

    /**
     * 请求头
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 3076, message = "请求头的长度不能大于3076")
    private String            headers;

    /**
     * 内容类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 30, message = "内容类型的长度不能大于30")
    private String            contentType;

    /**
     * 请求查询参数
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "请求查询参数的长度不能大于2048")
    private String            queryParams;

    /**
     * 请求主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "请求主体的长度不能大于2048")
    private String            body;
}
