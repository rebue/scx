package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacDisableLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacDisableLog racDisableLog = new RacDisableLog();

    /**
    * 启用操作员ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> enableOpId = racDisableLog.enableOpId;

    /**
    * 禁用账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accountId = racDisableLog.accountId;

    /**
    * 禁用操作员ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> disableOpId = racDisableLog.disableOpId;

    /**
    * 日志ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racDisableLog.id;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> realmId = racDisableLog.realmId;

    /**
    * 代理禁用操作员ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> disableOpAgentId = racDisableLog.disableOpAgentId;

    /**
    * 代理启用操作员ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> enableOpAgentId = racDisableLog.enableOpAgentId;

    /**
    * 禁用原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> disableReason = racDisableLog.disableReason;

    /**
    * 禁用时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> disableDatetime = racDisableLog.disableDatetime;

    /**
    * 启用原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> enableReason = racDisableLog.enableReason;

    /**
    * 启用时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> enableDatetime = racDisableLog.enableDatetime;

    public static final class RacDisableLog extends SqlTable {
        public final SqlColumn<Long> enableOpId = column("ENABLE_OP_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> accountId = column("ACCOUNT_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> disableOpId = column("DISABLE_OP_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> realmId = column("REALM_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> disableOpAgentId = column("DISABLE_OP_AGENT_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> enableOpAgentId = column("ENABLE_OP_AGENT_ID", JDBCType.BIGINT);

        public final SqlColumn<String> disableReason = column("DISABLE_REASON", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> disableDatetime = column("DISABLE_DATETIME", JDBCType.TIMESTAMP);

        public final SqlColumn<String> enableReason = column("ENABLE_REASON", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> enableDatetime = column("ENABLE_DATETIME", JDBCType.TIMESTAMP);

        public RacDisableLog() {
            super("RAC_DISABLE_LOG");
        }
    }
}