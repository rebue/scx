package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacAppTagDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacAppTag racAppTag = new RacAppTag();

    /**
    * 应用标签ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racAppTag.id;

    /**
    * 应用ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> appId = racAppTag.appId;

    /**
    * 字典项ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> dicItemId = racAppTag.dicItemId;

    public static final class RacAppTag extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> appId = column("APP_ID", JDBCType.VARCHAR);

        public final SqlColumn<Long> dicItemId = column("DIC_ITEM_ID", JDBCType.BIGINT);

        public RacAppTag() {
            super("RAC_APP_TAG");
        }
    }
}