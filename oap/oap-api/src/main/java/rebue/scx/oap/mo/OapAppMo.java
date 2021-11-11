package rebue.scx.oap.mo;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;
import rebue.scx.rac.mo.RacAppMo;

/**
 * 第三方应用
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class OapAppMo implements Serializable, Mo<Long> {

    /**
     * 主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "主键不能为空")
    @PositiveOrZero(message = "主键不能为负数")
    private Long              id;

    /**
     * 相关连的rac应用信息
     */
    @Setter
    @Getter
    private RacAppMo          racAppMo;

    /**
     * rac_app主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "rac_app主键不能为空")
    @Length(max = 32, message = "rac_app主键的长度不能大于32")
    private String            appId;

    /**
     * oidc client id
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "oidc不能为空")
    @Length(max = 255, message = "oidc的长度不能大于255")
    private String            clientId;

    /**
     * oidc secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "oidc不能为空")
    @Length(max = 255, message = "oidc的长度不能大于255")
    private String            secret;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "建立时间戳不能为空")
    @PositiveOrZero(message = "建立时间戳不能为负数")
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "修改时间戳不能为空")
    @PositiveOrZero(message = "修改时间戳不能为负数")
    private Long              updateTimestamp;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * rac_app主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAppId() {
        return appId;
    }

    /**
     * rac_app主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * oidc client id
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * oidc client id
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * oidc secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSecret() {
        return secret;
    }

    /**
     * oidc secret
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUpdateTimestamp(Long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
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
        sb.append(", appId=").append(appId);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", clientId=").append(clientId);
        sb.append(", secret=").append(secret);
        sb.append(", objId=").append(objId);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", updateTimestamp=").append(updateTimestamp);
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
        OapAppMo other = (OapAppMo) that;
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
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     * 对象ID(文件ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long    objId;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 对象ID(文件ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getObjId() {
        return objId;
    }

    /**
     * 对象ID(文件ID)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setObjId(Long objId) {
        this.objId = objId;
    }
}
