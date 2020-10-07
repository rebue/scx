package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacPersonDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacPerson racPerson = new RacPerson();

    /**
    * 个人ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racPerson.id;

    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isEnabled = racPerson.isEnabled;

    /**
    * 支付密码(小写(MD5(小写(MD5(密码明文))+小写(密码组合码))))
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> payPswd = racPerson.payPswd;

    /**
    * 支付密码组合码(与支付密码组合加密用，详见支付密码备注)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> payPswdSalt = racPerson.payPswdSalt;

    /**
    * 手机
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> mobile = racPerson.mobile;

    /**
    * 是否已验证手机号码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedMobile = racPerson.isVerifiedMobile;

    /**
    * 电子邮箱
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> email = racPerson.email;

    /**
    * 是否已验证电子邮箱
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedEmail = racPerson.isVerifiedEmail;

    /**
    * 用户实名
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> realName = racPerson.realName;

    /**
    * 是否已验证实名
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedRealname = racPerson.isVerifiedRealname;

    /**
    * 身份证号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> idCard = racPerson.idCard;

    /**
    * 是否已验证身份证号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isVerifiedIdcard = racPerson.isVerifiedIdcard;

    /**
    * 性别
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> sex = racPerson.sex;

    /**
    * 建立时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createrTimestamp = racPerson.createrTimestamp;

    /**
    * 修改时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> updateTimestamp = racPerson.updateTimestamp;

    public static final class RacPerson extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Boolean> isEnabled = column("IS_ENABLED", JDBCType.BIT);

        public final SqlColumn<String> payPswd = column("PAY_PSWD", JDBCType.VARCHAR);

        public final SqlColumn<String> payPswdSalt = column("PAY_PSWD_SALT", JDBCType.CHAR);

        public final SqlColumn<String> mobile = column("MOBILE", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedMobile = column("IS_VERIFIED_MOBILE", JDBCType.BIT);

        public final SqlColumn<String> email = column("EMAIL", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedEmail = column("IS_VERIFIED_EMAIL", JDBCType.BIT);

        public final SqlColumn<String> realName = column("REAL_NAME", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedRealname = column("IS_VERIFIED_REALNAME", JDBCType.BIT);

        public final SqlColumn<String> idCard = column("ID_CARD", JDBCType.CHAR);

        public final SqlColumn<Boolean> isVerifiedIdcard = column("IS_VERIFIED_IDCARD", JDBCType.BIT);

        public final SqlColumn<Byte> sex = column("SEX", JDBCType.TINYINT);

        public final SqlColumn<Long> createrTimestamp = column("CREATER_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTimestamp = column("UPDATE_TIMESTAMP", JDBCType.BIGINT);

        public RacPerson() {
            super("RAC_PERSON");
        }
    }
}