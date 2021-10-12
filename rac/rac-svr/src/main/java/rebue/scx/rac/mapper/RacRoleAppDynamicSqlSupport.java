package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacRoleAppDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacRoleApp racRoleApp = new RacRoleApp();

    /**
    * 角色应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racRoleApp.id;

    /**
    * 应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> appId = racRoleApp.appId;

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> roleId = racRoleApp.roleId;

    public static final class RacRoleApp extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> appId = column("APP_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> roleId = column("ROLE_ID", JDBCType.BIGINT);

        public RacRoleApp() {
            super("RAC_ROLE_APP");
        }
    }
}