package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacSysDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacSys racSys = new RacSys();

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> id = racSys.id;

    /**
    * 系统名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = racSys.name;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> domainId = racSys.domainId;

    /**
    * 索引URN
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> indexUrn = racSys.indexUrn;

    /**
    * 菜单URN
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> menuUrn = racSys.menuUrn;

    /**
    * 系统备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racSys.remark;

    public static final class RacSys extends SqlTable {
        public final SqlColumn<String> id = column("ID", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> domainId = column("DOMAIN_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> indexUrn = column("INDEX_URN", JDBCType.VARCHAR);

        public final SqlColumn<String> menuUrn = column("MENU_URN", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacSys() {
            super("RAC_SYS");
        }
    }
}