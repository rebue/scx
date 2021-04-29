package rebue.scx.rrl.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 响应日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RrlRespLogMo implements Serializable, Mo<Long> {

    /**
     * ID 等于请求ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "ID不能为空")
    @PositiveOrZero(message = "ID不能为负数")
    private Long              id;

    /**
     * 响应状态码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "响应状态码不能为空")
    @Length(max = 3, message = "响应状态码的长度不能大于3")
    private String            statusCode;

    /**
     * 响应头部
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "响应头部不能为空")
    @Length(max = 3076, message = "响应头部的长度不能大于3076")
    private String            headers;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID 等于请求ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * ID 等于请求ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 响应状态码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 响应头部
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getHeaders() {
        return headers;
    }

    /**
     * 响应头部
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setHeaders(String headers) {
        this.headers = headers;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eventId=").append(eventId);
        sb.append(", statusCode=").append(statusCode);
        sb.append(", headers=").append(headers);
        sb.append(", cookies=").append(cookies);
        sb.append(", body=").append(body);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RrlRespLogMo other = (RrlRespLogMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * 获取ID的类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String getIdType() {
        return "Long";
    }

    /**
     * 响应状态码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 响应主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "响应主体的长度不能大于2048")
    private String body;

    /**
     * 响应时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "响应时间戳不能为空")
    @PositiveOrZero(message = "响应时间戳不能为负数")
    private Long   createTimestamp;

    /**
     * 响应主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getBody() {
        return body;
    }

    /**
     * 响应主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 响应时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * 响应时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * COOKIES
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 2048, message = "COOKIES的长度不能大于2048")
    private String cookies;

    /**
     * COOKIES
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getCookies() {
        return cookies;
    }

    /**
     * COOKIES
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    /**
     * 事件ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "事件ID不能为空")
    @Length(max = 30, message = "事件ID的长度不能大于30")
    private String eventId;

    /**
     * 事件ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * 事件ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
