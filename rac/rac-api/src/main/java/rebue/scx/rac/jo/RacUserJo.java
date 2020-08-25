package rebue.scx.rac.jo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息
 *
 * The persistent class for the RAC_USER database table.
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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long id;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "NICKNAME", nullable = true, length = 20)
    private String nickname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "AVATAR", nullable = true, length = 300)
    private String avatar;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_NAME", nullable = true, length = 20)
    private String signInName;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_PSWD", nullable = true, length = 32)
    private String signInPswd;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "PAY_PSWD", nullable = true, length = 32)
    private String payPswd;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SALT", nullable = true, length = 6)
    private String salt;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "MOBILE", nullable = true, length = 11)
    private String mobile;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_MOBILE", nullable = true, length = 1)
    private Boolean isVerifiedMobile;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "EMAIL", nullable = true, length = 50)
    private String email;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_EMAIL", nullable = true, length = 1)
    private Boolean isVerifiedEmail;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_OPEN_ID", nullable = true, length = 64)
    private String wxOpenId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_UNION_ID", nullable = true, length = 64)
    private String wxUnionId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_NICKNAME", nullable = true, length = 100)
    private String wxNickname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_AVATAR", nullable = true, length = 300)
    private String wxAvatar;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_OPEN_ID", nullable = true, length = 64)
    private String qqOpenId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_UNION_ID", nullable = true, length = 64)
    private String qqUnionId;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_NICKNAME", nullable = true, length = 100)
    private String qqNickname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_AVATAR", nullable = true, length = 300)
    private String qqAvatar;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "REAL_NAME", nullable = true, length = 100)
    private String realName;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_REALNAME", nullable = true, length = 1)
    private Boolean isVerifiedRealname;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "ID_CARD", nullable = true, length = 18)
    private String idCard;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "IS_VERIFIED_IDCARD", nullable = true, length = 1)
    private Boolean isVerifiedIdcard;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SEX", nullable = true, length = 3)
    private Byte sex;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "AGE", nullable = true, length = 3)
    private Byte age;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_TESTER", nullable = false, length = 1)
    private Boolean isTester;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_ENABLED", nullable = false, length = 1)
    private Boolean isEnabled;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "UPDATE_TIMESTAMP", nullable = false, length = 20)
    private Long updateTimestamp;

    /**
     * 用户列表
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<RacOpLogJo> racOpLogList;

    /**
     * 用户列表
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<RacSignInLogJo> racSignInLogList;

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
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
