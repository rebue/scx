package rebue.scx.etl.mo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * 同步策略
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class EtlSyncStrategyMo implements Serializable, Mo<Long> {

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "策略ID不能为空")
    @PositiveOrZero(message = "策略ID不能为负数")
    private Long                  id;
    /**
     * 策略详情
     */
    @Setter
    @Getter
    List<EtlSyncStrategyDetailMo> strategyDetailList;
    /**
     * 策略名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "策略名称不能为空")
    @Length(max = 32, message = "策略名称的长度不能大于32")
    private String                name;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean               isEnabled;

    /**
     * 来源的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "来源的连接器ID不能为空")
    @PositiveOrZero(message = "来源的连接器ID不能为负数")
    private Long                  srcConnId;

    /**
     * 来源名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "来源名称不能为空")
    @Length(max = 32, message = "来源名称的长度不能大于32")
    private String                srcName;

    /**
     * 目的的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "目的的连接器ID不能为空")
    @PositiveOrZero(message = "目的的连接器ID不能为负数")
    private Long                  dstConnId;

    /**
     * 目的名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "目的名称不能为空")
    @Length(max = 32, message = "目的名称的长度不能大于32")
    private String                dstName;

    /**
     * 策略备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "策略备注的长度不能大于32")
    private String                remark;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long     serialVersionUID = 1L;

    /**
     * 目的的连接器
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private EtlConnMo             dstConn;

    /**
     * 来源的连接器
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private EtlConnMo             srcConn;
    /**
     * 回显的来源所有表下拉框数据
     */
    @Getter
    @Setter
    private List<String>          srcTableName;
    /**
     * 回显的目的所有表下拉框数据
     */
    @Getter
    @Setter
    private List<String>          dstTableName;
    /**
     * 回显示的来源表字段名，key为表名
     */
    @Getter
    @Setter
    Map<String, Set<String>>      srcFieldsMap;
    /**
     * 回显示的目的表字段名，key为表名
     */
    @Getter
    @Setter
    Map<String, Set<String>>      dstFieldsMap;

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 策略名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 策略名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
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
     * 来源的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getSrcConnId() {
        return srcConnId;
    }

    /**
     * 来源的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcConnId(Long srcConnId) {
        this.srcConnId = srcConnId;
    }

    /**
     * 来源名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSrcName() {
        return srcName;
    }

    /**
     * 来源名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    /**
     * 目的的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getDstConnId() {
        return dstConnId;
    }

    /**
     * 目的的连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstConnId(Long dstConnId) {
        this.dstConnId = dstConnId;
    }

    /**
     * 目的名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDstName() {
        return dstName;
    }

    /**
     * 目的名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstName(String dstName) {
        this.dstName = dstName;
    }

    /**
     * 策略备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 策略备注
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
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", srcConnId=").append(srcConnId);
        sb.append(", srcName=").append(srcName);
        sb.append(", dstConnId=").append(dstConnId);
        sb.append(", dstName=").append(dstName);
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
        EtlSyncStrategyMo other = (EtlSyncStrategyMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
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
