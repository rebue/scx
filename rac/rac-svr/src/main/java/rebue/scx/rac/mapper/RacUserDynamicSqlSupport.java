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
    * 用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racUser.id;

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
    * 建立时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = racUser.createTimestamp;

    /**
    * 修改时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> updateTimestamp = racUser.updateTimestamp;

    public static final class RacUser extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> mobile = column("MOBILE", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedMobile = column("IS_VERIFIED_MOBILE", JDBCType.BIT);

        public final SqlColumn<String> email = column("EMAIL", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedEmail = column("IS_VERIFIED_EMAIL", JDBCType.BIT);

        public final SqlColumn<String> realName = column("REAL_NAME", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isVerifiedRealname = column("IS_VERIFIED_REALNAME", JDBCType.BIT);

        public final SqlColumn<String> idCard = column("ID_CARD", JDBCType.CHAR);

        public final SqlColumn<Boolean> isVerifiedIdcard = column("IS_VERIFIED_IDCARD", JDBCType.BIT);

        public final SqlColumn<Byte> sex = column("SEX", JDBCType.TINYINT);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTimestamp = column("UPDATE_TIMESTAMP", JDBCType.BIGINT);

        public RacUser() {
            super("RAC_USER");
        }
    }
}