package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacDomainUserDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacDomainUser racDomainUser = new RacDomainUser();

    /**
    * 领域用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racDomainUser.id;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> domainId = racDomainUser.domainId;

    /**
    * 用户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> userId = racDomainUser.userId;

    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isEnabled = racDomainUser.isEnabled;

    public static final class RacDomainUser extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> domainId = column("DOMAIN_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("USER_ID", JDBCType.BIGINT);

        public final SqlColumn<Boolean> isEnabled = column("IS_ENABLED", JDBCType.BIT);

        public RacDomainUser() {
            super("RAC_DOMAIN_USER");
        }
    }
}