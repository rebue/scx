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
    * 请求方法
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> method = rrlReqLog.method;

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

    public static final class RrlReqLog extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> method = column("METHOD", JDBCType.VARCHAR);

        public final SqlColumn<String> uri = column("URI", JDBCType.VARCHAR);

        public final SqlColumn<String> headers = column("HEADERS", JDBCType.VARCHAR);

        public final SqlColumn<String> contentType = column("CONTENT_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> queryParams = column("QUERY_PARAMS", JDBCType.VARCHAR);

        public final SqlColumn<String> body = column("BODY", JDBCType.VARCHAR);

        public RrlReqLog() {
            super("RRL_REQ_LOG");
        }
    }
}