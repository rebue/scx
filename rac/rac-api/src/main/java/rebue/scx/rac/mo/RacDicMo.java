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
 * 字典
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacDicMo implements Serializable, Mo<Long> {

    /**
     * 字典ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "字典ID不能为空")
    @PositiveOrZero(message = "字典ID不能为负数")
    private Long              id;

    /**
     * 字典名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "字典名称不能为空")
    @Length(max = 200, message = "字典名称的长度不能大于200")
    private String            name;

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "应用ID的长度不能大于32")
    private String            appId;

    /**
     * 字典备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "字典备注的长度不能大于50")
    private String            remark;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private RacAppMo          app;

    /**
     * 字典ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 字典名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 字典名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAppId(final String appId) {
        this.appId = appId;
    }

    /**
     * 字典备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 字典备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(final String remark) {
        this.remark = remark;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dicKey=").append(dicKey);
        sb.append(", name=").append(name);
        sb.append(", realmId=").append(realmId);
        sb.append(", appId=").append(appId);
        sb.append(", remark=").append(remark);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        final RacDicMo other = (RacDicMo) that;
        return (getId() == null ? other.getId() == null : getId().equals(other.getId()));
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
     * 字典Key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "字典Key不能为空")
    @Length(max = 32, message = "字典Key的长度不能大于32")
    private String dicKey;

    /**
     * 字典ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 字典Key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDicKey() {
        return dicKey;
    }

    /**
     * 字典Key
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDicKey(final String dicKey) {
        this.dicKey = dicKey;
    }

    /**
     * 修改时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(groups = AddGroup.class, message = "修改时间不能为空")
    private LocalDateTime updateDatetime;

    /**
     * 修改时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * 修改时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUpdateDatetime(final LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
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
    public void setRealmId(final String realmId) {
        this.realmId = realmId;
    }
}
