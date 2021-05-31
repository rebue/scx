package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacOpsOrgDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacOpsOrg racOpsOrg = new RacOpsOrg();

    /**
    * ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racOpsOrg.id;

    /**
    * 主组织ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> masterOrgId = racOpsOrg.masterOrgId;

    /**
    * 从组织ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> slaveOrgId = racOpsOrg.slaveOrgId;

    public static final class RacOpsOrg extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> masterOrgId = column("MASTER_ORG_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> slaveOrgId = column("SLAVE_ORG_ID", JDBCType.BIGINT);

        public RacOpsOrg() {
            super("RAC_OPS_ORG");
        }
    }
}