package rebue.scx.rac.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 用户信息
 *
 * 数据库表: RAC_USER
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Schema(description = "用户信息")
@JsonInclude(Include.NON_NULL)
public class RacUserMo implements Serializable {

    /**
     *    用户ID(如为1则是散客)
     *
     *    数据库字段: RAC_USER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "用户ID(如为1则是散客)")
    private Long id;

    /**
     *    用户昵称
     *
     *    数据库字段: RAC_USER.NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "用户昵称")
    private String nickname;

    /**
     *    用户头像
     *
     *    数据库字段: RAC_USER.AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     *    登录名称
     *
     *    数据库字段: RAC_USER.SIGN_IN_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "登录名称")
    private String signInName;

    /**
     *    登录密码
     *                 计算方法：密码+密码组合码 -> 小写 -> md5 -> Hex
     *                 注意：
     *                 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     *                 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     *    数据库字段: RAC_USER.SIGN_IN_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "登录密码\n" + "             计算方法：密码+密码组合码 -> 小写 -> md5 -> Hex\n" + "             注意：\n" + "             1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获\n" + "             2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度")
    private String signInPswd;

    /**
     *    支付密码
     *                 用户的支付密码默认和登录密码一致
     *                 计算方法与登录密码一致
     *
     *    数据库字段: RAC_USER.PAY_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "支付密码\n" + "             用户的支付密码默认和登录密码一致\n" + "             计算方法与登录密码一致")
    private String payPswd;

    /**
     *    密码组合码
     *                 与密码组合加密用
     *                 登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: RAC_USER.SALT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "密码组合码\n" + "             与密码组合加密用\n" + "             登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))")
    private String salt;

    /**
     *    手机
     *
     *    数据库字段: RAC_USER.MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "手机")
    private String mobile;

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否已验证手机号码")
    private Boolean isVerifiedMobile;

    /**
     *    电子邮箱
     *
     *    数据库字段: RAC_USER.EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "电子邮箱")
    private String email;

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否已验证电子邮箱")
    private Boolean isVerifiedEmail;

    /**
     *    微信的OpenId
     *
     *    数据库字段: RAC_USER.WX_OPEN_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "微信的OpenId")
    private String wxOpenId;

    /**
     *    微信的UnionId
     *
     *    数据库字段: RAC_USER.WX_UNION_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "微信的UnionId")
    private String wxUnionId;

    /**
     *    微信昵称
     *
     *    数据库字段: RAC_USER.WX_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "微信昵称")
    private String wxNickname;

    /**
     *    微信头像
     *
     *    数据库字段: RAC_USER.WX_AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "微信头像")
    private String wxAvatar;

    /**
     *    QQ的OpenId
     *
     *    数据库字段: RAC_USER.QQ_OPEN_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "QQ的OpenId")
    private String qqOpenId;

    /**
     *    QQ的UnionId
     *
     *    数据库字段: RAC_USER.QQ_UNION_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "QQ的UnionId")
    private String qqUnionId;

    /**
     *    QQ昵称
     *
     *    数据库字段: RAC_USER.QQ_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "QQ昵称")
    private String qqNickname;

    /**
     *    QQ头像
     *
     *    数据库字段: RAC_USER.QQ_AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "QQ头像")
    private String qqAvatar;

    /**
     *    用户实名
     *
     *    数据库字段: RAC_USER.REAL_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "用户实名")
    private String realName;

    /**
     *    是否已验证实名
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否已验证实名")
    private Boolean isVerifiedRealname;

    /**
     *    身份证号
     *
     *    数据库字段: RAC_USER.ID_CARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "身份证号")
    private String idCard;

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否已验证身份证号")
    private Boolean isVerifiedIdcard;

    /**
     *    性别
     *
     *    数据库字段: RAC_USER.SEX
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "性别")
    private Byte sex;

    /**
     *    年龄
     *
     *    数据库字段: RAC_USER.AGE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "年龄")
    private Byte age;

    /**
     *    是否测试者
     *
     *    数据库字段: RAC_USER.IS_TESTER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否测试者")
    private Boolean isTester;

    /**
     *    是否启用
     *
     *    数据库字段: RAC_USER.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "是否启用")
    private Boolean isEnabled;

    /**
     *    修改时间戳
     *
     *    数据库字段: RAC_USER.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Schema(description = "修改时间戳")
    private Long modifiedTimestamp;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户ID(如为1则是散客)
     *
     *    数据库字段: RAC_USER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户ID(如为1则是散客)
     *
     *    数据库字段: RAC_USER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户昵称
     *
     *    数据库字段: RAC_USER.NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *    用户昵称
     *
     *    数据库字段: RAC_USER.NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *    用户头像
     *
     *    数据库字段: RAC_USER.AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *    用户头像
     *
     *    数据库字段: RAC_USER.AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     *    登录名称
     *
     *    数据库字段: RAC_USER.SIGN_IN_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInName() {
        return signInName;
    }

    /**
     *    登录名称
     *
     *    数据库字段: RAC_USER.SIGN_IN_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    /**
     *    登录密码
     *                 计算方法：密码+密码组合码 -> 小写 -> md5 -> Hex
     *                 注意：
     *                 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     *                 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     *    数据库字段: RAC_USER.SIGN_IN_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSignInPswd() {
        return signInPswd;
    }

    /**
     *    登录密码
     *                 计算方法：密码+密码组合码 -> 小写 -> md5 -> Hex
     *                 注意：
     *                 1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
     *                 2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
     *
     *    数据库字段: RAC_USER.SIGN_IN_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSignInPswd(String signInPswd) {
        this.signInPswd = signInPswd;
    }

    /**
     *    支付密码
     *                 用户的支付密码默认和登录密码一致
     *                 计算方法与登录密码一致
     *
     *    数据库字段: RAC_USER.PAY_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPayPswd() {
        return payPswd;
    }

    /**
     *    支付密码
     *                 用户的支付密码默认和登录密码一致
     *                 计算方法与登录密码一致
     *
     *    数据库字段: RAC_USER.PAY_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPayPswd(String payPswd) {
        this.payPswd = payPswd;
    }

    /**
     *    密码组合码
     *                 与密码组合加密用
     *                 登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: RAC_USER.SALT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSalt() {
        return salt;
    }

    /**
     *    密码组合码
     *                 与密码组合加密用
     *                 登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: RAC_USER.SALT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *    手机
     *
     *    数据库字段: RAC_USER.MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *    手机
     *
     *    数据库字段: RAC_USER.MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedMobile() {
        return isVerifiedMobile;
    }

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedMobile(Boolean isVerifiedMobile) {
        this.isVerifiedMobile = isVerifiedMobile;
    }

    /**
     *    电子邮箱
     *
     *    数据库字段: RAC_USER.EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEmail() {
        return email;
    }

    /**
     *    电子邮箱
     *
     *    数据库字段: RAC_USER.EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    /**
     *    微信的OpenId
     *
     *    数据库字段: RAC_USER.WX_OPEN_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     *    微信的OpenId
     *
     *    数据库字段: RAC_USER.WX_OPEN_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     *    微信的UnionId
     *
     *    数据库字段: RAC_USER.WX_UNION_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxUnionId() {
        return wxUnionId;
    }

    /**
     *    微信的UnionId
     *
     *    数据库字段: RAC_USER.WX_UNION_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    /**
     *    微信昵称
     *
     *    数据库字段: RAC_USER.WX_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     *    微信昵称
     *
     *    数据库字段: RAC_USER.WX_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    /**
     *    微信头像
     *
     *    数据库字段: RAC_USER.WX_AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxAvatar() {
        return wxAvatar;
    }

    /**
     *    微信头像
     *
     *    数据库字段: RAC_USER.WX_AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxAvatar(String wxAvatar) {
        this.wxAvatar = wxAvatar;
    }

    /**
     *    QQ的OpenId
     *
     *    数据库字段: RAC_USER.QQ_OPEN_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqOpenId() {
        return qqOpenId;
    }

    /**
     *    QQ的OpenId
     *
     *    数据库字段: RAC_USER.QQ_OPEN_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    /**
     *    QQ的UnionId
     *
     *    数据库字段: RAC_USER.QQ_UNION_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqUnionId() {
        return qqUnionId;
    }

    /**
     *    QQ的UnionId
     *
     *    数据库字段: RAC_USER.QQ_UNION_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqUnionId(String qqUnionId) {
        this.qqUnionId = qqUnionId;
    }

    /**
     *    QQ昵称
     *
     *    数据库字段: RAC_USER.QQ_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqNickname() {
        return qqNickname;
    }

    /**
     *    QQ昵称
     *
     *    数据库字段: RAC_USER.QQ_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqNickname(String qqNickname) {
        this.qqNickname = qqNickname;
    }

    /**
     *    QQ头像
     *
     *    数据库字段: RAC_USER.QQ_AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqAvatar() {
        return qqAvatar;
    }

    /**
     *    QQ头像
     *
     *    数据库字段: RAC_USER.QQ_AVATAR
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqAvatar(String qqAvatar) {
        this.qqAvatar = qqAvatar;
    }

    /**
     *    用户实名
     *
     *    数据库字段: RAC_USER.REAL_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRealName() {
        return realName;
    }

    /**
     *    用户实名
     *
     *    数据库字段: RAC_USER.REAL_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     *    是否已验证实名
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedRealname() {
        return isVerifiedRealname;
    }

    /**
     *    是否已验证实名
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedRealname(Boolean isVerifiedRealname) {
        this.isVerifiedRealname = isVerifiedRealname;
    }

    /**
     *    身份证号
     *
     *    数据库字段: RAC_USER.ID_CARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     *    身份证号
     *
     *    数据库字段: RAC_USER.ID_CARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedIdcard() {
        return isVerifiedIdcard;
    }

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: RAC_USER.IS_VERIFIED_IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedIdcard(Boolean isVerifiedIdcard) {
        this.isVerifiedIdcard = isVerifiedIdcard;
    }

    /**
     *    性别
     *
     *    数据库字段: RAC_USER.SEX
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getSex() {
        return sex;
    }

    /**
     *    性别
     *
     *    数据库字段: RAC_USER.SEX
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     *    年龄
     *
     *    数据库字段: RAC_USER.AGE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getAge() {
        return age;
    }

    /**
     *    年龄
     *
     *    数据库字段: RAC_USER.AGE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     *    是否测试者
     *
     *    数据库字段: RAC_USER.IS_TESTER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsTester() {
        return isTester;
    }

    /**
     *    是否测试者
     *
     *    数据库字段: RAC_USER.IS_TESTER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsTester(Boolean isTester) {
        this.isTester = isTester;
    }

    /**
     *    是否启用
     *
     *    数据库字段: RAC_USER.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: RAC_USER.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: RAC_USER.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: RAC_USER.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setModifiedTimestamp(Long modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
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
        sb.append(", nickname=").append(nickname);
        sb.append(", avatar=").append(avatar);
        sb.append(", signInName=").append(signInName);
        sb.append(", signInPswd=").append(signInPswd);
        sb.append(", payPswd=").append(payPswd);
        sb.append(", salt=").append(salt);
        sb.append(", mobile=").append(mobile);
        sb.append(", isVerifiedMobile=").append(isVerifiedMobile);
        sb.append(", email=").append(email);
        sb.append(", isVerifiedEmail=").append(isVerifiedEmail);
        sb.append(", wxOpenId=").append(wxOpenId);
        sb.append(", wxUnionId=").append(wxUnionId);
        sb.append(", wxNickname=").append(wxNickname);
        sb.append(", wxAvatar=").append(wxAvatar);
        sb.append(", qqOpenId=").append(qqOpenId);
        sb.append(", qqUnionId=").append(qqUnionId);
        sb.append(", qqNickname=").append(qqNickname);
        sb.append(", qqAvatar=").append(qqAvatar);
        sb.append(", realName=").append(realName);
        sb.append(", isVerifiedRealname=").append(isVerifiedRealname);
        sb.append(", idCard=").append(idCard);
        sb.append(", isVerifiedIdcard=").append(isVerifiedIdcard);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", isTester=").append(isTester);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", modifiedTimestamp=").append(modifiedTimestamp);
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
        RacUserMo other = (RacUserMo) that;
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
