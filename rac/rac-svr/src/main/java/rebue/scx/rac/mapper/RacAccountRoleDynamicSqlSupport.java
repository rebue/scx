package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacAccountRoleDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacAccountRole racAccountRole = new RacAccountRole();

    /**
    * 账户角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racAccountRole.id;

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> roleId = racAccountRole.roleId;

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accountId = racAccountRole.accountId;

    public static final class RacAccountRole extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("ROLE_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> accountId = column("ACCOUNT_ID", JDBCType.BIGINT);

        public RacAccountRole() {
            super("RAC_ACCOUNT_ROLE");
        }
    }
}