package rebue.scx.oap.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OapAuthLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final OapAuthLog oapAuthLog = new OapAuthLog();

    /**
    * 记录ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = oapAuthLog.id;

    /**
    * 是否成功认证：0失败，1成功
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isSuccess = oapAuthLog.isSuccess;

    /**
    * 操作时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> opDatetime = oapAuthLog.opDatetime;

    /**
    * 记录原因
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> reason = oapAuthLog.reason;

    public static final class OapAuthLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Boolean> isSuccess = column("IS_SUCCESS", JDBCType.BIT);

        public final SqlColumn<LocalDateTime> opDatetime = column("OP_DATETIME", JDBCType.TIMESTAMP);

        public final SqlColumn<String> reason = column("REASON", JDBCType.VARCHAR);

        public OapAuthLog() {
            super("OAP_AUTH_LOG");
        }
    }
}