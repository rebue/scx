package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacSysUserDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacSysUser racSysUser = new RacSysUser();

    /**
    * 系统用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racSysUser.id;

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> sysId = racSysUser.sysId;

    /**
    * 用户ID(如为1则是散客)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> userId = racSysUser.userId;

    public static final class RacSysUser extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> sysId = column("SYS_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("USER_ID", JDBCType.BIGINT);

        public RacSysUser() {
            super("RAC_SYS_USER");
        }
    }
}