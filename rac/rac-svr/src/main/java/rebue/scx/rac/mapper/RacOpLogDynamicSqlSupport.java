package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacOpLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacOpLog racOpLog = new RacOpLog();

    /**
    * 操作日志ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racOpLog.id;

    /**
    * 应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> appId = racOpLog.appId;

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accountId = racOpLog.accountId;

    /**
    * 代理人ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> agentId = racOpLog.agentId;

    /**
    * 操作类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> opType = racOpLog.opType;

    /**
    * 操作标题
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> opTitle = racOpLog.opTitle;

    /**
    * 操作详情
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> opDetail = racOpLog.opDetail;

    /**
    * 操作时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> opDatetime = racOpLog.opDatetime;

    public static final class RacOpLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> appId = column("APP_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> accountId = column("ACCOUNT_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> agentId = column("AGENT_ID", JDBCType.BIGINT);

        public final SqlColumn<String> opType = column("OP_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> opTitle = column("OP_TITLE", JDBCType.VARCHAR);

        public final SqlColumn<String> opDetail = column("OP_DETAIL", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> opDatetime = column("OP_DATETIME", JDBCType.TIMESTAMP);

        public RacOpLog() {
            super("RAC_OP_LOG");
        }
    }
}