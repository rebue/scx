package rebue.scx.orp.mo;

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
 * 三方应用账户信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class OapGrantMo implements Serializable, Mo<Long> {

    /**
     * 主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "主键不能为空")
    @PositiveOrZero(message = "主键不能为负数")
    private Long              id;

    /**
     * rac_account主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "rac_account主键不能为空")
    @PositiveOrZero(message = "rac_account主键不能为负数")
    private Long              accountId;

    /**
     * oidc access token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "oidc不能为空")
    @Length(max = 50, message = "oidc的长度不能大于50")
    private String            accessToken;

    /**
     * oidc refresh token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "oidc不能为空")
    @Length(max = 50, message = "oidc的长度不能大于50")
    private String            refreshToken;

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "创建时间不能为空")
    @PositiveOrZero(message = "创建时间不能为负数")
    private Long              createTimestamp;

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
     * rac_account主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * rac_account主键
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * oidc access token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * oidc access token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * oidc refresh token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * oidc refresh token
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
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
        sb.append(", accountId=").append(accountId);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", accessTokenJson=").append(accessTokenJson);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", accessTokenExpireTimestamp=").append(accessTokenExpireTimestamp);
        sb.append(", refreshTokenExpiresTimestamp=").append(refreshTokenExpiresTimestamp);
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
        OapGrantMo other = (OapGrantMo) that;
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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "不能为空")
    @Length(max = 255, message = "的长度不能大于255")
    private String accessTokenJson;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAccessTokenJson() {
        return accessTokenJson;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccessTokenJson(String accessTokenJson) {
        this.accessTokenJson = accessTokenJson;
    }

    /**
     * access token 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "access不能为空")
    @PositiveOrZero(message = "access不能为负数")
    private Long accessTokenExpireTimestamp;

    /**
     * refresh token 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "refresh不能为空")
    @PositiveOrZero(message = "refresh不能为负数")
    private Long refreshTokenExpiresTimestamp;

    /**
     * access token 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getAccessTokenExpireTimestamp() {
        return accessTokenExpireTimestamp;
    }

    /**
     * access token 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccessTokenExpireTimestamp(Long accessTokenExpireTimestamp) {
        this.accessTokenExpireTimestamp = accessTokenExpireTimestamp;
    }

    /**
     * refresh token 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getRefreshTokenExpiresTimestamp() {
        return refreshTokenExpiresTimestamp;
    }

    /**
     * refresh token 过期时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRefreshTokenExpiresTimestamp(Long refreshTokenExpiresTimestamp) {
        this.refreshTokenExpiresTimestamp = refreshTokenExpiresTimestamp;
    }
}
