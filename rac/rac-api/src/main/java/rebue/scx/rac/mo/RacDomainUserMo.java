package rebue.scx.rac.mo;

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

/**
 * 领域用户
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacDomainUserMo implements Serializable, Mo<Long> {

    /**
     * 领域用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "领域用户ID不能为空")
    @PositiveOrZero(message = "领域用户ID不能为负数")
    private Long              id;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            domainId;

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "用户ID不能为空")
    @PositiveOrZero(message = "用户ID不能为负数")
    private Long              userId;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean           isEnabled;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacDomainMo       domain;

    /**
     * 用户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacUserMo         user;

    /**
     * 领域用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 领域用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", domainId=").append(domainId);
        sb.append(", userId=").append(userId);
        sb.append(", isEnabled=").append(isEnabled);
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
        RacDomainUserMo other = (RacDomainUserMo) that;
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
