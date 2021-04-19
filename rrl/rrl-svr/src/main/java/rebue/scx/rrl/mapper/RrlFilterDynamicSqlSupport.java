package rebue.scx.rrl.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RrlFilterDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RrlFilter rrlFilter = new RrlFilter();

    /**
    * ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = rrlFilter.id;

    /**
    * 请求方法
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> method = rrlFilter.method;

    /**
    * 请求地址
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> uri = rrlFilter.uri;

    public static final class RrlFilter extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> method = column("METHOD", JDBCType.VARCHAR);

        public final SqlColumn<String> uri = column("URI", JDBCType.VARCHAR);

        public RrlFilter() {
            super("RRL_FILTER");
        }
    }
}