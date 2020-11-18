package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacOrgUserDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacOrgUser racOrgUser = new RacOrgUser();

    /**
    * 组织用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racOrgUser.id;

    /**
    * 组织ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> orgId = racOrgUser.orgId;

    /**
    * 用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> userId = racOrgUser.userId;

    public static final class RacOrgUser extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> orgId = column("ORG_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("USER_ID", JDBCType.BIGINT);

        public RacOrgUser() {
            super("RAC_ORG_USER");
        }
    }
}