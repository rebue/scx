package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacPermGroupDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacPermGroup racPermGroup = new RacPermGroup();

    /**
    * 权限分组ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racPermGroup.id;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> domainId = racPermGroup.domainId;

    /**
    * 权限分组名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = racPermGroup.name;

    /**
    * 是否启用(如果分组没有启用，其下所有权限都要被设置为不启用；只要有一个权限启用，其分组就必须启用)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isEnabled = racPermGroup.isEnabled;

    /**
    * 顺序号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> orderNo = racPermGroup.orderNo;

    /**
    * 权限分组备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racPermGroup.remark;

    public static final class RacPermGroup extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> domainId = column("DOMAIN_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isEnabled = column("IS_ENABLED", JDBCType.BIT);

        public final SqlColumn<Byte> orderNo = column("ORDER_NO", JDBCType.TINYINT);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacPermGroup() {
            super("RAC_PERM_GROUP");
        }
    }
}