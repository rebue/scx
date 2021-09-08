package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacAccountLockDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacAccountLock racAccountLock = new RacAccountLock();

    /**
    * ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racAccountLock.id;

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accountId = racAccountLock.accountId;

    /**
    * 锁定时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> lockDatetime = racAccountLock.lockDatetime;

    public static final class RacAccountLock extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> accountId = column("ACCOUNT_ID", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> lockDatetime = column("LOCK_DATETIME", JDBCType.TIMESTAMP);

        public RacAccountLock() {
            super("RAC_ACCOUNT_LOCK");
        }
    }
}