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
 * 请求日志
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RrlReqLogAddTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 请求方法
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "请求方法不能为空")
    @Length(max = 10, message = "请求方法的长度不能大于10")
    private String            method;

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
    @Length(max = 65535, message = "请求主体的长度不能大于65535")
    private String            body;

    /**
     * 请求协议
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "请求协议不能为空")
    @Length(max = 10, message = "请求协议的长度不能大于10")
    private String            scheme;

    /**
     * 请求主机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "请求主机不能为空")
    @Length(max = 30, message = "请求主机的长度不能大于30")
    private String            host;

    /**
     * 请求端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "请求端口号不能为空")
    private Integer           port;

    /**
     * 请求路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "请求路径不能为空")
    @Length(max = 512, message = "请求路径的长度不能大于512")
    private String            path;

    /**
     * 请求地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "请求地址不能为空")
    @Length(max = 512, message = "请求地址的长度不能大于512")
    private String            uri;

    /**
     * COOKIES
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "COOKIES的长度不能大于2048")
    private String            cookies;

    /**
     * 事件ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "事件ID不能为空")
    @Length(max = 30, message = "事件ID的长度不能大于30")
    private String            eventId;

    /**
     * 会话ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(message = "会话ID不能为空")
    @PositiveOrZero(message = "会话ID不能为负数")
    private Long              sessionId;
}
