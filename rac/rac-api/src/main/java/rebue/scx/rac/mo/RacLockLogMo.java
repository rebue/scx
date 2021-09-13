package rebue.scx.rac.mo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 锁定日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacLockLogMo implements Serializable, Mo<Long> {

    /**
     * 锁定日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "锁定日志ID不能为空")
    @PositiveOrZero(message = "锁定日志ID不能为负数")
    private Long              id;

    /**
     * 锁定账户的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "锁定账户的账户ID不能为空")
    @PositiveOrZero(message = "锁定账户的账户ID不能为负数")
    private Long              lockAccountId;

    /**
     * 锁定原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "锁定原因不能为空")
    @Length(max = 100, message = "锁定原因的长度不能大于100")
    private String            lockReason;

    /**
     * 锁定时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(groups = AddGroup.class, message = "锁定时间不能为空")
    private LocalDateTime     lockDatetime;

    /**
     * 解锁原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "解锁原因的长度不能大于100")
    private String            unlockReason;

    /**
     * 解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime     unlockDatetime;

    /**
     * 解锁操作员的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "解锁操作员的账户ID不能为负数")
    private Long              unlockOpId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 锁定账户的账户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      lockAccount;

    /**
     * 解锁操作员的账户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo      unlockOp;

    /**
     * 锁定日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 锁定日志ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 锁定账户的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getLockAccountId() {
        return lockAccountId;
    }

    /**
     * 锁定账户的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLockAccountId(Long lockAccountId) {
        this.lockAccountId = lockAccountId;
    }

    /**
     * 锁定原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLockReason() {
        return lockReason;
    }

    /**
     * 锁定原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    /**
     * 锁定时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getLockDatetime() {
        return lockDatetime;
    }

    /**
     * 锁定时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLockDatetime(LocalDateTime lockDatetime) {
        this.lockDatetime = lockDatetime;
    }

    /**
     * 解锁原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUnlockReason() {
        return unlockReason;
    }

    /**
     * 解锁原因
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockReason(String unlockReason) {
        this.unlockReason = unlockReason;
    }

    /**
     * 解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getUnlockDatetime() {
        return unlockDatetime;
    }

    /**
     * 解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockDatetime(LocalDateTime unlockDatetime) {
        this.unlockDatetime = unlockDatetime;
    }

    /**
     * 解锁操作员的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUnlockOpId() {
        return unlockOpId;
    }

    /**
     * 解锁操作员的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockOpId(Long unlockOpId) {
        this.unlockOpId = unlockOpId;
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
        sb.append(", lockAccountId=").append(lockAccountId);
        sb.append(", lockReason=").append(lockReason);
        sb.append(", lockDatetime=").append(lockDatetime);
        sb.append(", unlockReason=").append(unlockReason);
        sb.append(", unlockDatetime=").append(unlockDatetime);
        sb.append(", unlockOpId=").append(unlockOpId);
        sb.append(", unlockOpAgentId=").append(unlockOpAgentId);
        sb.append(", autoUnlockDatetime=").append(autoUnlockDatetime);
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
        RacLockLogMo other = (RacLockLogMo) that;
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
     * 解锁操作的代理人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "解锁操作的代理人的账户ID不能为负数")
    private Long         unlockOpAgentId;

    /**
     * 解锁操作的代理人的账户
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAccountMo unlockOpAgent;

    /**
     * 解锁操作的代理人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUnlockOpAgentId() {
        return unlockOpAgentId;
    }

    /**
     * 解锁操作的代理人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockOpAgentId(Long unlockOpAgentId) {
        this.unlockOpAgentId = unlockOpAgentId;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "领域ID不能为空")
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String     realmId;

    /**
     * 领域
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacRealmMo realm;

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
     * 自动解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime autoUnlockDatetime;

    /**
     * 自动解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getAutoUnlockDatetime() {
        return autoUnlockDatetime;
    }

    /**
     * 自动解锁时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAutoUnlockDatetime(LocalDateTime autoUnlockDatetime) {
        this.autoUnlockDatetime = autoUnlockDatetime;
    }
}
