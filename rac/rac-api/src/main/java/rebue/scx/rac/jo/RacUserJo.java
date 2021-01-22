package rebue.scx.rac.jo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户
 *
 * The persistent class for the RAC_USER database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_USER")
@Getter
@Setter
@ToString
public class RacUserJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "MOBILE", nullable = true, length = 11)
    private String            mobile;

    /**
     * 是否已验证手机号码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_MOBILE", nullable = true, length = 1)
    private Boolean           isVerifiedMobile;

    /**
     * 电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "EMAIL", nullable = true, length = 50)
    private String            email;

    /**
     * 是否已验证电子邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_EMAIL", nullable = true, length = 1)
    private Boolean           isVerifiedEmail;

    /**
     * 用户实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "REAL_NAME", nullable = true, length = 100)
    private String            realName;

    /**
     * 是否已验证实名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_REALNAME", nullable = true, length = 1)
    private Boolean           isVerifiedRealname;

    /**
     * 身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "ID_CARD", nullable = true, length = 18)
    private String            idCard;

    /**
     * 是否已验证身份证号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_IDCARD", nullable = true, length = 1)
    private Boolean           isVerifiedIdcard;

    /**
     * 性别
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SEX", nullable = true, length = 3)
    private Byte              sex;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "CREATER_TIMESTAMP", nullable = false, length = 20)
    private Long              createrTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "UPDATE_TIMESTAMP", nullable = false, length = 20)
    private Long              updateTimestamp;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RacUserJo other = (RacUserJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
