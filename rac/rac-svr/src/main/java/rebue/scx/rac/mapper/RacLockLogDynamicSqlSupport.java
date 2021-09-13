package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacLockLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacLockLog racLockLog = new RacLockLog();

    /**
    * 锁定日志ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racLockLog.id;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> realmId = racLockLog.realmId;

    /**
    * 锁定账户的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> lockAccountId = racLockLog.lockAccountId;

    /**
    * 锁定原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> lockReason = racLockLog.lockReason;

    /**
    * 锁定时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> lockDatetime = racLockLog.lockDatetime;

    /**
    * 解锁原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> unlockReason = racLockLog.unlockReason;

    /**
    * 解锁时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> unlockDatetime = racLockLog.unlockDatetime;

    /**
    * 解锁操作员的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> unlockOpId = racLockLog.unlockOpId;

    /**
    * 解锁操作的代理人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> unlockOpAgentId = racLockLog.unlockOpAgentId;

    /**
    * 自动解锁时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> autoUnlockDatetime = racLockLog.autoUnlockDatetime;

    public static final class RacLockLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> realmId = column("REALM_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> lockAccountId = column("LOCK_ACCOUNT_ID", JDBCType.BIGINT);

        public final SqlColumn<String> lockReason = column("LOCK_REASON", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> lockDatetime = column("LOCK_DATETIME", JDBCType.TIMESTAMP);

        public final SqlColumn<String> unlockReason = column("UNLOCK_REASON", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> unlockDatetime = column("UNLOCK_DATETIME", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> unlockOpId = column("UNLOCK_OP_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> unlockOpAgentId = column("UNLOCK_OP_AGENT_ID", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> autoUnlockDatetime = column("AUTO_UNLOCK_DATETIME", JDBCType.TIMESTAMP);

        public RacLockLog() {
            super("RAC_LOCK_LOG");
        }
    }
}