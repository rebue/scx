package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacDelegationDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacDelegation racDelegation = new RacDelegation();

    /**
    * 账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racDelegation.id;

    /**
    * 委托人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> principalId = racDelegation.principalId;

    /**
    * 代理人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> agentId = racDelegation.agentId;

    public static final class RacDelegation extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> principalId = column("PRINCIPAL_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> agentId = column("AGENT_ID", JDBCType.BIGINT);

        public RacDelegation() {
            super("RAC_DELEGATION");
        }
    }
}