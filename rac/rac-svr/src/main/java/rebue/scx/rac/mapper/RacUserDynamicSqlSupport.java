package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacUserDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacUser racUser = new RacUser();

    /**
    * 用户ID(如为1则是散客)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racUser.id;

    /**
    * 用户昵称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> nickname = racUser.nickname;

    /**
    * 用户头像
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> avatar = racUser.avatar;

    /**
    * 登录名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> signInName = racUser.signInName;

    /**
    * 登录密码
    *              计算方法：密码+密码组合码 --》 小写 -》 md5 -》 Hex
    *              注意：
    *              1. 计算方法中的密码在前端传过来时推荐先进行md5序列化，以避免在密码传递过程中使用明码被截获
    *              2. 密码组合码在生成密码时随机生成并保存下来，和密码组合起来使用，增加破解的难度
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> signInPswd = racUser.signInPswd;

    /**
    * 支付密码
    *              用户的支付密码默认和登录密码一致
    *              计算方法与登录密码一致
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> payPswd = racUser.payPswd;

    /**
    * 密码组合码
    *              与密码组合加密用
    *              登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> salt = racUser.salt;

    /**
    * 手机
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> mobile = racUser.mobile;

    /**
    * 是否已验证手机号码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedMobile = racUser.isVerifiedMobile;

    /**
    * 电子邮箱
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> email = racUser.email;

    /**
    * 是否已验证电子邮箱
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedEmail = racUser.isVerifiedEmail;

    /**
    * 微信的OpenId
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> wxOpenId = racUser.wxOpenId;

    /**
    * 微信的UnionId
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> wxUnionId = racUser.wxUnionId;

    /**
    * 微信昵称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> wxNickname = racUser.wxNickname;

    /**
    * 微信头像
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> wxAvatar = racUser.wxAvatar;

    /**
    * QQ的OpenId
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> qqOpenId = racUser.qqOpenId;

    /**
    * QQ的UnionId
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> qqUnionId = racUser.qqUnionId;

    /**
    * QQ昵称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> qqNickname = racUser.qqNickname;

    /**
    * QQ头像
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> qqAvatar = racUser.qqAvatar;

    /**
    * 用户实名
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> realName = racUser.realName;

    /**
    * 是否已验证实名
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedRealname = racUser.isVerifiedRealname;

    /**
    * 身份证号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> idCard = racUser.idCard;

    /**
    * 是否已验证身份证号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedIdcard = racUser.isVerifiedIdcard;

    /**
    * 性别
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> sex = racUser.sex;

    /**
    * 年龄
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> age = racUser.age;

    /**
    * 是否测试者
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isTester = racUser.isTester;

    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isEnabled = racUser.isEnabled;

    /**
    * 修改时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> updateTimestamp = racUser.updateTimestamp;

    public static final class RacUser extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("NICKNAME", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("AVATAR", JDBCType.VARCHAR);

        public final SqlColumn<String> signInName = column("SIGN_IN_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> signInPswd = column("SIGN_IN_PSWD", JDBCType.VARCHAR);

        public final SqlColumn<String> payPswd = column("PAY_PSWD", JDBCType.VARCHAR);

        public final SqlColumn<String> salt = column("SALT", JDBCType.CHAR);

        public final SqlColumn<String> mobile = column("MOBILE", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedMobile = column("IS_VERIFIED_MOBILE", JDBCType.BIT);

        public final SqlColumn<String> email = column("EMAIL", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedEmail = column("IS_VERIFIED_EMAIL", JDBCType.BIT);

        public final SqlColumn<String> wxOpenId = column("WX_OPEN_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> wxUnionId = column("WX_UNION_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> wxNickname = column("WX_NICKNAME", JDBCType.VARCHAR);

        public final SqlColumn<String> wxAvatar = column("WX_AVATAR", JDBCType.VARCHAR);

        public final SqlColumn<String> qqOpenId = column("QQ_OPEN_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> qqUnionId = column("QQ_UNION_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> qqNickname = column("QQ_NICKNAME", JDBCType.VARCHAR);

        public final SqlColumn<String> qqAvatar = column("QQ_AVATAR", JDBCType.VARCHAR);

        public final SqlColumn<String> realName = column("REAL_NAME", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedRealname = column("IS_VERIFIED_REALNAME", JDBCType.BIT);

        public final SqlColumn<String> idCard = column("ID_CARD", JDBCType.CHAR);

        public final SqlColumn<Boolean> isVerifiedIdcard = column("IS_VERIFIED_IDCARD", JDBCType.BIT);

        public final SqlColumn<Byte> sex = column("SEX", JDBCType.TINYINT);

        public final SqlColumn<Byte> age = column("AGE", JDBCType.TINYINT);

        public final SqlColumn<Boolean> isTester = column("IS_TESTER", JDBCType.BIT);

        public final SqlColumn<Boolean> isEnabled = column("IS_ENABLED", JDBCType.BIT);

        public final SqlColumn<Long> updateTimestamp = column("UPDATE_TIMESTAMP", JDBCType.BIGINT);

        public RacUser() {
            super("RAC_USER");
        }
    }
}