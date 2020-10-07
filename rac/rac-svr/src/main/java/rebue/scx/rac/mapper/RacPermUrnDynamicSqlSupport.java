package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacPermUrnDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacPermUrn racPermUrn = new RacPermUrn();

    /**
    * 权限URN的ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racPermUrn.id;

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> permId = racPermUrn.permId;

    /**
    * URN
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> urn = racPermUrn.urn;

    public static final class RacPermUrn extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> permId = column("PERM_ID", JDBCType.BIGINT);

        public final SqlColumn<String> urn = column("URN", JDBCType.VARCHAR);

        public RacPermUrn() {
            super("RAC_PERM_URN");
        }
    }
}