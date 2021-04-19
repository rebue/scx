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
    * 响应状态码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> statusCode = rrlRespLog.statusCode;

    /**
    * 响应头部
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> headers = rrlRespLog.headers;

    public static final class RrlRespLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Byte> statusCode = column("STATUS_CODE", JDBCType.TINYINT);

        public final SqlColumn<String> headers = column("HEADERS", JDBCType.VARCHAR);

        public RrlRespLog() {
            super("RRL_RESP_LOG");
        }
    }
}