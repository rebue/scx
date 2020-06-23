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
    * 用户操作日志ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racOpLog.id;

    /**
    * 用户ID(如为1则是散客)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> userId = racOpLog.userId;

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> sysId = racOpLog.sysId;

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
    public static final SqlColumn<LocalDateTime> opTime = racOpLog.opTime;

    public static final class RacOpLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("USER_ID", JDBCType.BIGINT);

        public final SqlColumn<String> sysId = column("SYS_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> opTitle = column("OP_TITLE", JDBCType.VARCHAR);

        public final SqlColumn<String> opDetail = column("OP_DETAIL", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> opTime = column("OP_TIME", JDBCType.TIMESTAMP);

        public RacOpLog() {
            super("RAC_OP_LOG");
        }
    }
}