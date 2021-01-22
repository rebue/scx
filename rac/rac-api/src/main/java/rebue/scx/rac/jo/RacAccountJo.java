package rebue.scx.rac.jo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 账户
 *
 * The persistent class for the RAC_ACCOUNT database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_ACCOUNT")
@Getter
@Setter
@ToString
public class RacAccountJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 是否启用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_ENABLED", nullable = false, length = 1)
    private Boolean           isEnabled;

    /**
     * 登录名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_NAME", nullable = true, length = 20)
    private String            signInName;

    /**
     * 登录手机
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_MOBILE", nullable = true, length = 11)
    private String            signInMobile;

    /**
     * 登录邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_EMAIL", nullable = true, length = 50)
    private String            signInEmail;

    /**
     * 登录密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
     *             注意：
     *             1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     *             2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_PSWD", nullable = true, length = 32)
    private String            signInPswd;

    /**
     * 登录密码组合码(与密码组合加密用，详见登录密码备注)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_PSWD_SALT", nullable = true, length = 6)
    private String            signInPswdSalt;

    /**
     * 登录账户昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_NICKNAME", nullable = true, length = 20)
    private String            signInNickname;

    /**
     * 登录账户头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "SIGN_IN_AVATAR", nullable = true, length = 300)
    private String            signInAvatar;

    /**
     * 微信的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_OPEN_ID", nullable = true, length = 64)
    private String            wxOpenId;

    /**
     * 微信的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_UNION_ID", nullable = true, length = 64)
    private String            wxUnionId;

    /**
     * 微信昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_NICKNAME", nullable = true, length = 100)
    private String            wxNickname;

    /**
     * 微信头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "WX_AVATAR", nullable = true, length = 300)
    private String            wxAvatar;

    /**
     * QQ的OpenId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_OPEN_ID", nullable = true, length = 64)
    private String            qqOpenId;

    /**
     * QQ的UnionId
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_UNION_ID", nullable = true, length = 64)
    private String            qqUnionId;

    /**
     * QQ昵称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_NICKNAME", nullable = true, length = 100)
    private String            qqNickname;

    /**
     * QQ头像
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "QQ_AVATAR", nullable = true, length = 300)
    private String            qqAvatar;

    /**
     * 是否测试者
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "IS_TESTER", nullable = false, length = 1)
    private Boolean           isTester;

    /**
     * 建立时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "CREATE_TIMESTAMP", nullable = false, length = 20)
    private Long              createTimestamp;

    /**
     * 修改时间戳
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "UPDATE_TIMESTAMP", nullable = false, length = 20)
    private Long              updateTimestamp;

    /**
     * 个人
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacPersonJo       person;

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
        RacAccountJo other = (RacAccountJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * 组织
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacOrgJo org;
}
