package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacUserRoleDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacUserRole racUserRole = new RacUserRole();

    /**
    * 用户角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racUserRole.id;

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> sysId = racUserRole.sysId;

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> roleId = racUserRole.roleId;

    /**
    * 用户ID(如为1则是散客)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> userId = racUserRole.userId;

    public static final class RacUserRole extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> sysId = column("SYS_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> roleId = column("ROLE_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("USER_ID", JDBCType.BIGINT);

        public RacUserRole() {
            super("RAC_USER_ROLE");
        }
    }
}