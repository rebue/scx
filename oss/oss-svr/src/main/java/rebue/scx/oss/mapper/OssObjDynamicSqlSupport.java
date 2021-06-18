package rebue.scx.oss.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OssObjDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final OssObj ossObj = new OssObj();

    /**
    * 对象ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = ossObj.id;

    /**
    * 对象名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = ossObj.name;

    /**
    * 对象类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> objType = ossObj.objType;

    /**
    * 对象大小
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> objSize = ossObj.objSize;

    /**
    * 对象URL
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> url = ossObj.url;

    /**
    * 创建人的账户ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> creatorId = ossObj.creatorId;

    /**
    * 创建时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> createDatetime = ossObj.createDatetime;

    public static final class OssObj extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> objType = column("OBJ_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<Long> objSize = column("OBJ_SIZE", JDBCType.BIGINT);

        public final SqlColumn<String> url = column("URL", JDBCType.VARCHAR);

        public final SqlColumn<Long> creatorId = column("CREATOR_ID", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createDatetime = column("CREATE_DATETIME", JDBCType.TIMESTAMP);

        public OssObj() {
            super("OSS_OBJ");
        }
    }
}