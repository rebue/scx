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
 * 应用
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class RacAppMo implements Serializable, Mo<String> {

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = ModifyGroup.class, message = "应用ID不能为空")
    @Length(max = 32, message = "应用ID的长度不能大于32")
    private String            id;

    /**
     * 应用名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(groups = AddGroup.class, message = "应用名称不能为空")
    @Length(max = 20, message = "应用名称的长度不能大于20")
    private String            name;

    /**
     * 应用备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "应用备注的长度不能大于50")
    private String            remark;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 应用名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     * 应用名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 应用备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 应用备注
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
        sb.append(", realmId=").append(realmId);
        sb.append(", url=").append(url);
        sb.append(", menu=").append(menu);
        sb.append(", remark=").append(remark);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", seqNo=").append(seqNo);
        sb.append(", isCertified=").append(isCertified);
        sb.append(", authnType=").append(authnType);
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
        RacAppMo other = (RacAppMo) that;
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
        return "String";
    }

    /**
     * 应用URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "应用URL的长度不能大于100")
    private String url;

    /**
     * 应用URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUrl() {
        return url;
    }

    /**
     * 应用URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 3000, message = "菜单的长度不能大于3000")
    private String menu;

    /**
     * 菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMenu() {
        return menu;
    }

    /**
     * 菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMenu(String menu) {
        this.menu = menu;
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
     * 是否启用(如果应用没有启用，则不显示在第三方认证页面）
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否启用不能为空")
    private Boolean isEnabled;

    /**
     * 是否启用(如果应用没有启用，则不显示在第三方认证页面）
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 是否启用(如果应用没有启用，则不显示在第三方认证页面）
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 应用图片地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 512, message = "应用图片地址的长度不能大于512")
    private String imgUrl;

    /**
     * 应用图片地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 应用图片地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "顺序号不能为空")
    private Byte    seqNo;

    /**
     * 是否认证(@deprecated 请使用认证方式判断)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotNull(groups = AddGroup.class, message = "是否认证不能为空")
    private Boolean isCertified;

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getSeqNo() {
        return seqNo;
    }

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSeqNo(Byte seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 是否认证(@deprecated 请使用认证方式判断)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsCertified() {
        return isCertified;
    }

    /**
     * 是否认证(@deprecated 请使用认证方式判断)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    /**
     * 认证方式(0:未认证;1:共用Cookie;2:OIDC/OAuth2;3:CAS)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "认证方式不能为负数")
    private Byte authnType;

    /**
     * 认证方式(0:未认证;1:共用Cookie;2:OIDC/OAuth2;3:CAS)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getAuthnType() {
        return authnType;
    }

    /**
     * 认证方式(0:未认证;1:共用Cookie;2:OIDC/OAuth2;3:CAS)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAuthnType(Byte authnType) {
        this.authnType = authnType;
    }
}
