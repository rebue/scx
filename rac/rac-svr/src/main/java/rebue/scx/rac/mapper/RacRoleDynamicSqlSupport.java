package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacRoleDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacRole racRole = new RacRole();

    /**
    * 角色ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racRole.id;

    /**
    * 角色名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = racRole.name;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> realmId = racRole.realmId;

    /**
    * 角色身份(字典项KEY)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> status = racRole.status;

    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isEnabled = racRole.isEnabled;

    /**
    * 顺序号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> seqNo = racRole.seqNo;

    /**
    * 角色备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racRole.remark;

    public static final class RacRole extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> realmId = column("REALM_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("STATUS", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isEnabled = column("IS_ENABLED", JDBCType.BIT);

        public final SqlColumn<Byte> seqNo = column("SEQ_NO", JDBCType.TINYINT);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacRole() {
            super("RAC_ROLE");
        }
    }
}