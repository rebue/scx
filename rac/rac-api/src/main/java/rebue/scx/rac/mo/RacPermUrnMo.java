package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 权限URN
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacPermUrnMo implements Serializable {

    /**
     * 权限URN的ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String permId;

    /**
     * URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String urn;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 权限URN的ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     * 权限URN的ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPermId() {
        return permId;
    }

    /**
     * 权限ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPermId(String permId) {
        this.permId = permId;
    }

    /**
     * URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUrn() {
        return urn;
    }

    /**
     * URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUrn(String urn) {
        this.urn = urn;
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
        sb.append(", permId=").append(permId);
        sb.append(", urn=").append(urn);
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
        RacPermUrnMo other = (RacPermUrnMo) that;
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
}