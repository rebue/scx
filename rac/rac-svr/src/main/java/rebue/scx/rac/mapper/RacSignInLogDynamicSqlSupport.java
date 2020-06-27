package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacSignInLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacSignInLog racSignInLog = new RacSignInLog();

    /**
    * 用户登录日志ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racSignInLog.id;

    /**
    * 用户ID(如为1则是散客)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> userId = racSignInLog.userId;

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> sysId = racSignInLog.sysId;

    /**
    * 登录类型(与注册类型一致)
    *              LOGIN_NAME: 登录名与密码
    *              EMAIL_PASSWORD: 电子邮箱与密码
    *              MOBILE_PASSWORD: 手机号与密码
    *              MOBILE_SMS. 手机短信验证
    *              WECHAT_OFFICIAL_ACCOUNTS: 微信公众号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> loginWay = racSignInLog.loginWay;

    /**
    * 登录时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> loginDatetime = racSignInLog.loginDatetime;

    public static final class RacSignInLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("USER_ID", JDBCType.BIGINT);

        public final SqlColumn<String> sysId = column("SYS_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> loginWay = column("LOGIN_WAY", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> loginDatetime = column("LOGIN_DATETIME", JDBCType.TIMESTAMP);

        public RacSignInLog() {
            super("RAC_SIGN_IN_LOG");
        }
    }
}