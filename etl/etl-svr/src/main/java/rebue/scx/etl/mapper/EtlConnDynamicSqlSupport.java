package rebue.scx.etl.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EtlConnDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final EtlConn etlConn = new EtlConn();

    /**
    * 连接器ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = etlConn.id;

    /**
    * 数据库连接器名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = etlConn.name;

    /**
    * 数据库类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> dbType = etlConn.dbType;

    /**
    * 数据库名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dbName = etlConn.dbName;

    /**
    * 主机名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> host = etlConn.host;

    /**
    * 端口号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Short> port = etlConn.port;

    /**
    * 用户名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> userName = etlConn.userName;

    /**
    * 用户密码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> userPswd = etlConn.userPswd;

    /**
    * 源备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = etlConn.remark;

    public static final class EtlConn extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<Byte> dbType = column("DB_TYPE", JDBCType.TINYINT);

        public final SqlColumn<String> dbName = column("DB_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> host = column("HOST", JDBCType.VARCHAR);

        public final SqlColumn<Short> port = column("PORT", JDBCType.SMALLINT);

        public final SqlColumn<String> userName = column("USER_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> userPswd = column("USER_PSWD", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public EtlConn() {
            super("ETL_CONN");
        }
    }
}