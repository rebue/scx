package rebue.scx.rac.mo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 账户启/禁用日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacDisableLogMo implements Serializable, Mo<Long> {

    /**
     * 日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "日志ID不能为空")
    @PositiveOrZero(message = "日志ID不能为负数")
    private Long              id;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            realmId;

    /**
     * 代理禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "代理禁用操作员ID不能为负数")
    private Long              disableOpAgentId;

    /**
     * 代理启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "代理启用操作员ID不能为负数")
    private Long              enableOpAgentId;

    /**
     * 启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "启用操作员ID不能为负数")
    private Long              enableOpId;

    /**
     * 禁用账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "禁用账户ID不能为空")
    @PositiveOrZero(message = "禁用账户ID不能为负数")
    private Long              accountId;

    /**
     * 禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "禁用操作员ID不能为空")
    @PositiveOrZero(message = "禁用操作员ID不能为负数")
    private Long              disableOpId;

    /**
     * 禁用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "禁用原因不能为空")
    @Length(max = 100, message = "禁用原因的长度不能大于100")
    private String            disableReason;

    /**
     * 禁用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(groups = AddGroup.class, message = "禁用时间不能为空")
    private LocalDateTime     disableDatetime;

    /**
     * 启用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "启用原因的长度不能大于100")
    private String            enableReason;

    /**
     * 启用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     enableDatetime;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 禁用账户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      account;

    /**
     * 代理禁用操作员
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      disableOpAgent;

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacRealmMo        realm;

    /**
     * 禁用操作员
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      disableOp;

    /**
     * 代理启用操作员
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      enableOpAgent;

    /**
     * 启用操作员
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      enableOp;

    /**
     * 日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 日志ID
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
    public String getRealmId() {
        return realmId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    /**
     * 代理禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getDisableOpAgentId() {
        return disableOpAgentId;
    }

    /**
     * 代理禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDisableOpAgentId(Long disableOpAgentId) {
        this.disableOpAgentId = disableOpAgentId;
    }

    /**
     * 代理启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getEnableOpAgentId() {
        return enableOpAgentId;
    }

    /**
     * 代理启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEnableOpAgentId(Long enableOpAgentId) {
        this.enableOpAgentId = enableOpAgentId;
    }

    /**
     * 启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getEnableOpId() {
        return enableOpId;
    }

    /**
     * 启用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEnableOpId(Long enableOpId) {
        this.enableOpId = enableOpId;
    }

    /**
     * 禁用账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 禁用账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getDisableOpId() {
        return disableOpId;
    }

    /**
     * 禁用操作员ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDisableOpId(Long disableOpId) {
        this.disableOpId = disableOpId;
    }

    /**
     * 禁用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDisableReason() {
        return disableReason;
    }

    /**
     * 禁用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    /**
     * 禁用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getDisableDatetime() {
        return disableDatetime;
    }

    /**
     * 禁用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDisableDatetime(LocalDateTime disableDatetime) {
        this.disableDatetime = disableDatetime;
    }

    /**
     * 启用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEnableReason() {
        return enableReason;
    }

    /**
     * 启用原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEnableReason(String enableReason) {
        this.enableReason = enableReason;
    }

    /**
     * 启用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getEnableDatetime() {
        return enableDatetime;
    }

    /**
     * 启用时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEnableDatetime(LocalDateTime enableDatetime) {
        this.enableDatetime = enableDatetime;
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
        sb.append(", realmId=").append(realmId);
        sb.append(", disableOpAgentId=").append(disableOpAgentId);
        sb.append(", enableOpAgentId=").append(enableOpAgentId);
        sb.append(", enableOpId=").append(enableOpId);
        sb.append(", accountId=").append(accountId);
        sb.append(", disableOpId=").append(disableOpId);
        sb.append(", disableReason=").append(disableReason);
        sb.append(", disableDatetime=").append(disableDatetime);
        sb.append(", enableReason=").append(enableReason);
        sb.append(", enableDatetime=").append(enableDatetime);
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
        RacDisableLogMo other = (RacDisableLogMo) that;
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
}
