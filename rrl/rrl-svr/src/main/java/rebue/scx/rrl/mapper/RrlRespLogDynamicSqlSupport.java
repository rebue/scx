package rebue.scx.rrl.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RrlRespLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RrlRespLog rrlRespLog = new RrlRespLog();

    /**
    * ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = rrlRespLog.id;

    /**
    * 事件ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> eventId = rrlRespLog.eventId;

    /**
    * 会话ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> sessionId = rrlRespLog.sessionId;

    /**
    * 响应状态码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> statusCode = rrlRespLog.statusCode;

    /**
    * 响应头部
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> headers = rrlRespLog.headers;

    /**
    * COOKIES
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> cookies = rrlRespLog.cookies;

    /**
    * 响应主体
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> body = rrlRespLog.body;

    /**
    * 响应时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = rrlRespLog.createTimestamp;

    public static final class RrlRespLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> eventId = column("EVENT_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> sessionId = column("SESSION_ID", JDBCType.BIGINT);

        public final SqlColumn<String> statusCode = column("STATUS_CODE", JDBCType.CHAR);

        public final SqlColumn<String> headers = column("HEADERS", JDBCType.VARCHAR);

        public final SqlColumn<String> cookies = column("COOKIES", JDBCType.VARCHAR);

        public final SqlColumn<String> body = column("BODY", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public RrlRespLog() {
            super("RRL_RESP_LOG");
        }
    }
}