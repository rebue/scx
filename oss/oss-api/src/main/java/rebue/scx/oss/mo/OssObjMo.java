package rebue.scx.oss.mo;

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

import rebue.robotech.mo.Mo;
import rebue.robotech.valid.AddGroup;
import rebue.robotech.valid.ModifyGroup;

/**
 * 对象
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class OssObjMo implements Serializable, Mo<Long> {

    /**
     * 对象ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = ModifyGroup.class, message = "对象ID不能为空")
    @PositiveOrZero(message = "对象ID不能为负数")
    private Long              id;

    /**
     * 对象类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "对象类型不能为空")
    @Length(max = 30, message = "对象类型的长度不能大于30")
    private String            objType;

    /**
     * 对象大小
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "对象大小不能为空")
    @PositiveOrZero(message = "对象大小不能为负数")
    private Long              objSize;

    /**
     * 对象URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "对象URL不能为空")
    @Length(max = 512, message = "对象URL的长度不能大于512")
    private String            url;

    /**
     * 创建人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "创建人的账户ID不能为空")
    @PositiveOrZero(message = "创建人的账户ID不能为负数")
    private Long              creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(groups = AddGroup.class, message = "创建时间不能为空")
    private LocalDateTime     createDatetime;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 对象ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 对象ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 对象类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getObjType() {
        return objType;
    }

    /**
     * 对象类型
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setObjType(String objType) {
        this.objType = objType;
    }

    /**
     * 对象大小
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getObjSize() {
        return objSize;
    }

    /**
     * 对象大小
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setObjSize(Long objSize) {
        this.objSize = objSize;
    }

    /**
     * 对象URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUrl() {
        return url;
    }

    /**
     * 对象URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 创建人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 创建人的账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 创建时间
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
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
        sb.append(", objName=").append(objName);
        sb.append(", objGroup=").append(objGroup);
        sb.append(", objType=").append(objType);
        sb.append(", objSize=").append(objSize);
        sb.append(", url=").append(url);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", creatorOrgId=").append(creatorOrgId);
        sb.append(", createDatetime=").append(createDatetime);
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
        OssObjMo other = (OssObjMo) that;
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

    /**
     * 对象名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "对象名称不能为空")
    @Length(max = 512, message = "对象名称的长度不能大于512")
    private String objName;

    /**
     * 对象分组
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "对象分组不能为空")
    @Length(max = 30, message = "对象分组的长度不能大于30")
    private String objGroup;

    /**
     * 创建人的组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "创建人的组织ID不能为负数")
    private Long   creatorOrgId;

    /**
     * 对象名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getObjName() {
        return objName;
    }

    /**
     * 对象名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setObjName(String objName) {
        this.objName = objName;
    }

    /**
     * 对象分组
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getObjGroup() {
        return objGroup;
    }

    /**
     * 对象分组
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setObjGroup(String objGroup) {
        this.objGroup = objGroup;
    }

    /**
     * 创建人的组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreatorOrgId() {
        return creatorOrgId;
    }

    /**
     * 创建人的组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreatorOrgId(Long creatorOrgId) {
        this.creatorOrgId = creatorOrgId;
    }
}
