package rebue.scx.rrl.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RrlReqLogDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RrlReqLog rrlReqLog = new RrlReqLog();

    /**
    * ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = rrlReqLog.id;

    /**
    * 事件ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> eventId = rrlReqLog.eventId;

    /**
    * 请求方法
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> method = rrlReqLog.method;

    /**
    * 请求协议
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> scheme = rrlReqLog.scheme;

    /**
    * 请求主机
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> host = rrlReqLog.host;

    /**
    * 请求端口号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Integer> port = rrlReqLog.port;

    /**
    * 请求路径
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> path = rrlReqLog.path;

    /**
    * 请求地址
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> uri = rrlReqLog.uri;

    /**
    * 请求头
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> headers = rrlReqLog.headers;

    /**
    * 内容类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> contentType = rrlReqLog.contentType;

    /**
    * COOKIES
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> cookies = rrlReqLog.cookies;

    /**
    * 请求查询参数
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> queryParams = rrlReqLog.queryParams;

    /**
    * 请求主体
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> body = rrlReqLog.body;

    /**
    * 请求时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = rrlReqLog.createTimestamp;

    public static final class RrlReqLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> eventId = column("EVENT_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> method = column("METHOD", JDBCType.VARCHAR);

        public final SqlColumn<String> scheme = column("SCHEME", JDBCType.VARCHAR);

        public final SqlColumn<String> host = column("HOST", JDBCType.VARCHAR);

        public final SqlColumn<Integer> port = column("PORT", JDBCType.INTEGER);

        public final SqlColumn<String> path = column("PATH", JDBCType.VARCHAR);

        public final SqlColumn<String> uri = column("URI", JDBCType.VARCHAR);

        public final SqlColumn<String> headers = column("HEADERS", JDBCType.VARCHAR);

        public final SqlColumn<String> contentType = column("CONTENT_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> cookies = column("COOKIES", JDBCType.VARCHAR);

        public final SqlColumn<String> queryParams = column("QUERY_PARAMS", JDBCType.VARCHAR);

        public final SqlColumn<String> body = column("BODY", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public RrlReqLog() {
            super("RRL_REQ_LOG");
        }
    }
}