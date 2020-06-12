package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户操作日志
 *
 * 数据库表: RAC_OP_LOG
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "用户操作日志")
@JsonInclude(Include.NON_NULL)
public class RacOpLogMo implements Serializable {

    /**
     *    用户操作日志ID
     *
     *    数据库字段: RAC_OP_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "用户操作日志ID")
    private Long id;

    /**
     *    用户ID(如为1则是散客)
     *
     *    数据库字段: RAC_OP_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "用户ID(如为1则是散客)")
    private Long userId;

    /**
     *    系统ID
     *
     *    数据库字段: RAC_OP_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "系统ID")
    private String sysId;

    /**
     *    操作标题
     *
     *    数据库字段: RAC_OP_LOG.OP_TITLE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "操作标题")
    private String opTitle;

    /**
     *    操作详情
     *
     *    数据库字段: RAC_OP_LOG.OP_DETAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "操作详情")
    private String opDetail;

    /**
     *    操作时间
     *
     *    数据库字段: RAC_OP_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date opTime;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户操作日志ID
     *
     *    数据库字段: RAC_OP_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户操作日志ID
     *
     *    数据库字段: RAC_OP_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户ID(如为1则是散客)
     *
     *    数据库字段: RAC_OP_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *    用户ID(如为1则是散客)
     *
     *    数据库字段: RAC_OP_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *    系统ID
     *
     *    数据库字段: RAC_OP_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    系统ID
     *
     *    数据库字段: RAC_OP_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    操作标题
     *
     *    数据库字段: RAC_OP_LOG.OP_TITLE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getOpTitle() {
        return opTitle;
    }

    /**
     *    操作标题
     *
     *    数据库字段: RAC_OP_LOG.OP_TITLE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpTitle(String opTitle) {
        this.opTitle = opTitle;
    }

    /**
     *    操作详情
     *
     *    数据库字段: RAC_OP_LOG.OP_DETAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getOpDetail() {
        return opDetail;
    }

    /**
     *    操作详情
     *
     *    数据库字段: RAC_OP_LOG.OP_DETAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }

    /**
     *    操作时间
     *
     *    数据库字段: RAC_OP_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     *    操作时间
     *
     *    数据库字段: RAC_OP_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", sysId=").append(sysId);
        sb.append(", opTitle=").append(opTitle);
        sb.append(", opDetail=").append(opDetail);
        sb.append(", opTime=").append(opTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
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
        RacOpLogMo other = (RacOpLogMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
