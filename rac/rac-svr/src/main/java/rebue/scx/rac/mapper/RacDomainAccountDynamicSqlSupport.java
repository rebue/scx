package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacDomainAccountDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacDomainAccount racDomainAccount = new RacDomainAccount();

    /**
    * 领域账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racDomainAccount.id;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> domainId = racDomainAccount.domainId;

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accountId = racDomainAccount.accountId;

    public static final class RacDomainAccount extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> domainId = column("DOMAIN_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> accountId = column("ACCOUNT_ID", JDBCType.BIGINT);

        public RacDomainAccount() {
            super("RAC_DOMAIN_ACCOUNT");
        }
    }
}