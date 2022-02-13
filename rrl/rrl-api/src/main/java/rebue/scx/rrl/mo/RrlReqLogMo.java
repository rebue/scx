package rebue.scx.rrl.mo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 请求日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RrlReqLogMo implements Serializable, Mo<Long> {

    /**
     * ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "ID不能为空")
    @PositiveOrZero(message = "ID不能为负数")
    private Long              id;

    /**
     * 请求方法
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "请求方法不能为空")
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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 请求方法
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMethod() {
        return method;
    }

    /**
     * 请求方法
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 请求头
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getHeaders() {
        return headers;
    }

    /**
     * 请求头
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setHeaders(String headers) {
        this.headers = headers;
    }

    /**
     * 内容类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 内容类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 请求查询参数
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQueryParams() {
        return queryParams;
    }

    /**
     * 请求查询参数
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    /**
     * 请求主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getBody() {
        return body;
    }

    /**
     * 请求主体
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setBody(String body) {
        this.body = body;
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
        sb.append(", sessionId=").append(sessionId);
        sb.append(", method=").append(method);
        sb.append(", scheme=").append(scheme);
        sb.append(", host=").append(host);
        sb.append(", port=").append(port);
        sb.append(", path=").append(path);
        sb.append(", uri=").append(uri);
        sb.append(", headers=").append(headers);
        sb.append(", contentType=").append(contentType);
        sb.append(", cookies=").append(cookies);
        sb.append(", queryParams=").append(queryParams);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", body=").append(body);
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
        RrlReqLogMo other = (RrlReqLogMo) that;
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
    @JsonIgnore
    public String getIdType() {
        return "Long";
    }

    /**
     * 请求协议
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "请求协议不能为空")
    @Length(max = 10, message = "请求协议的长度不能大于10")
    private String  scheme;

    /**
     * 请求主机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "请求主机不能为空")
    @Length(max = 30, message = "请求主机的长度不能大于30")
    private String  host;

    /**
     * 请求端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "请求端口号不能为空")
    private Integer port;

    /**
     * 请求协议
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * 请求协议
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * 请求主机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getHost() {
        return host;
    }

    /**
     * 请求主机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 请求端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 请求端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 请求路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "请求路径不能为空")
    @Length(max = 512, message = "请求路径的长度不能大于512")
    private String path;

    /**
     * 请求路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPath() {
        return path;
    }

    /**
     * 请求路径
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 请求地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "请求地址不能为空")
    @Length(max = 512, message = "请求地址的长度不能大于512")
    private String uri;

    /**
     * 请求地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUri() {
        return uri;
    }

    /**
     * 请求地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 请求时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "请求时间戳不能为空")
    @PositiveOrZero(message = "请求时间戳不能为负数")
    private Long createTimestamp;

    /**
     * 请求时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * 请求时间戳
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

    /**
     * 会话ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "会话ID不能为空")
    @PositiveOrZero(message = "会话ID不能为负数")
    private Long sessionId;

    /**
     * 会话ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 会话ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
