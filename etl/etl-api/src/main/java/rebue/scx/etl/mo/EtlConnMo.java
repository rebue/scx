package rebue.scx.etl.mo;

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
 * 数据库连接器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class EtlConnMo implements Serializable, Mo<Long> {

    /**
     * 连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "连接器ID不能为空")
    @PositiveOrZero(message = "连接器ID不能为负数")
    private Long              id;

    /**
     * 数据库连接器名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "数据库连接器名称不能为空")
    @Length(max = 32, message = "数据库连接器名称的长度不能大于32")
    private String            name;

    /**
     * 数据库类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "数据库类型不能为空")
    @PositiveOrZero(message = "数据库类型不能为负数")
    private Byte              dbType;

    /**
     * 数据库名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "数据库名称不能为空")
    @Length(max = 32, message = "数据库名称的长度不能大于32")
    private String            dbName;

    /**
     * 主机名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "主机名称不能为空")
    @Length(max = 32, message = "主机名称的长度不能大于32")
    private String            host;

    /**
     * 端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "端口号不能为空")
    @PositiveOrZero(message = "端口号不能为负数")
    private Short             port;

    /**
     * 用户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "用户名称不能为空")
    @Length(max = 32, message = "用户名称的长度不能大于32")
    private String            userName;

    /**
     * 用户密码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "用户密码不能为空")
    @Length(max = 32, message = "用户密码的长度不能大于32")
    private String            userPswd;

    /**
     * 源备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "源备注的长度不能大于32")
    private String            remark;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 数据库连接器名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 数据库连接器名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 数据库类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getDbType() {
        return dbType;
    }

    /**
     * 数据库类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDbType(Byte dbType) {
        this.dbType = dbType;
    }

    /**
     * 数据库名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * 数据库名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * 主机名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getHost() {
        return host;
    }

    /**
     * 主机名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Short getPort() {
        return port;
    }

    /**
     * 端口号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPort(Short port) {
        this.port = port;
    }

    /**
     * 用户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户密码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUserPswd() {
        return userPswd;
    }

    /**
     * 用户密码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    /**
     * 源备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 源备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", name=").append(name);
        sb.append(", dbType=").append(dbType);
        sb.append(", dbName=").append(dbName);
        sb.append(", host=").append(host);
        sb.append(", port=").append(port);
        sb.append(", userName=").append(userName);
        sb.append(", userPswd=").append(userPswd);
        sb.append(", remark=").append(remark);
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
        EtlConnMo other = (EtlConnMo) that;
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
}
