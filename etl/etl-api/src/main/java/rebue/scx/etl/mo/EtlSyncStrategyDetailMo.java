package rebue.scx.etl.mo;

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
 * 同步策略详情
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class EtlSyncStrategyDetailMo implements Serializable, Mo<Long> {

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "策略ID不能为负数")
    private Long              strategyId;

    /**
     * 来源表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "来源表名称的长度不能大于32")
    private String            srcTableName;

    /**
     * 来源字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "来源字段名称不能为空")
    @Length(max = 32, message = "来源字段名称的长度不能大于32")
    private String            srcFieldName;

    /**
     * 来源字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "来源字段类型不能为空")
    @Length(max = 32, message = "来源字段类型的长度不能大于32")
    private String            srcFieldType;

    /**
     * 来源字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "来源字段长度不能为负数")
    private Byte              srcFieldLength;

    /**
     * 来源字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "来源字段精度不能为负数")
    private Byte              srcFieldPrecision;

    /**
     * 目的表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "目的表名称不能为空")
    @Length(max = 32, message = "目的表名称的长度不能大于32")
    private String            dstTableName;

    /**
     * 目的字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "目的字段名称不能为空")
    @Length(max = 32, message = "目的字段名称的长度不能大于32")
    private String            dstFieldName;

    /**
     * 目的字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "目的字段类型不能为空")
    @Length(max = 32, message = "目的字段类型的长度不能大于32")
    private String            dstFieldType;

    /**
     * 目的字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "目的字段长度不能为负数")
    private Byte              dstFieldLength;

    /**
     * 目的字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "目的字段精度不能为负数")
    private Byte              dstFieldPrecision;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 策略
     *
     * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
     */
    @Getter
    @Setter
    private EtlSyncStrategyMo strategy;

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getStrategyId() {
        return strategyId;
    }

    /**
     * 策略ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    /**
     * 来源表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSrcTableName() {
        return srcTableName;
    }

    /**
     * 来源表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcTableName(String srcTableName) {
        this.srcTableName = srcTableName;
    }

    /**
     * 来源字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSrcFieldName() {
        return srcFieldName;
    }

    /**
     * 来源字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcFieldName(String srcFieldName) {
        this.srcFieldName = srcFieldName;
    }

    /**
     * 来源字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSrcFieldType() {
        return srcFieldType;
    }

    /**
     * 来源字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcFieldType(String srcFieldType) {
        this.srcFieldType = srcFieldType;
    }

    /**
     * 来源字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getSrcFieldLength() {
        return srcFieldLength;
    }

    /**
     * 来源字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcFieldLength(Byte srcFieldLength) {
        this.srcFieldLength = srcFieldLength;
    }

    /**
     * 来源字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getSrcFieldPrecision() {
        return srcFieldPrecision;
    }

    /**
     * 来源字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSrcFieldPrecision(Byte srcFieldPrecision) {
        this.srcFieldPrecision = srcFieldPrecision;
    }

    /**
     * 目的表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDstTableName() {
        return dstTableName;
    }

    /**
     * 目的表名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstTableName(String dstTableName) {
        this.dstTableName = dstTableName;
    }

    /**
     * 目的字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDstFieldName() {
        return dstFieldName;
    }

    /**
     * 目的字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstFieldName(String dstFieldName) {
        this.dstFieldName = dstFieldName;
    }

    /**
     * 目的字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getDstFieldType() {
        return dstFieldType;
    }

    /**
     * 目的字段类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstFieldType(String dstFieldType) {
        this.dstFieldType = dstFieldType;
    }

    /**
     * 目的字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getDstFieldLength() {
        return dstFieldLength;
    }

    /**
     * 目的字段长度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstFieldLength(Byte dstFieldLength) {
        this.dstFieldLength = dstFieldLength;
    }

    /**
     * 目的字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getDstFieldPrecision() {
        return dstFieldPrecision;
    }

    /**
     * 目的字段精度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setDstFieldPrecision(Byte dstFieldPrecision) {
        this.dstFieldPrecision = dstFieldPrecision;
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
        sb.append(", strategyId=").append(strategyId);
        sb.append(", srcTableName=").append(srcTableName);
        sb.append(", srcFieldName=").append(srcFieldName);
        sb.append(", srcFieldType=").append(srcFieldType);
        sb.append(", srcFieldLength=").append(srcFieldLength);
        sb.append(", srcFieldPrecision=").append(srcFieldPrecision);
        sb.append(", dstTableName=").append(dstTableName);
        sb.append(", dstFieldName=").append(dstFieldName);
        sb.append(", dstFieldType=").append(dstFieldType);
        sb.append(", dstFieldLength=").append(dstFieldLength);
        sb.append(", dstFieldPrecision=").append(dstFieldPrecision);
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
        EtlSyncStrategyDetailMo other = (EtlSyncStrategyDetailMo) that;
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
     * 策略详情ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "策略详情ID不能为空")
    @PositiveOrZero(message = "策略详情ID不能为负数")
    private Long id;

    /**
     * 策略详情ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 策略详情ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }
}
