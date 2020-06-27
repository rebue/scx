package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacRolePermDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacRolePerm racRolePerm = new RacRolePerm();

    /**
    * 角色权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racRolePerm.id;

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> roleId = racRolePerm.roleId;

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> permId = racRolePerm.permId;

    public static final class RacRolePerm extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> roleId = column("ROLE_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> permId = column("PERM_ID", JDBCType.VARCHAR);

        public RacRolePerm() {
            super("RAC_ROLE_PERM");
        }
    }
}