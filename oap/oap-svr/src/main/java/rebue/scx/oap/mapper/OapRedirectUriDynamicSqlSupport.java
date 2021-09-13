package rebue.scx.oap.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OapRedirectUriDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final OapRedirectUri oapRedirectUri = new OapRedirectUri();

    /**
    * 主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = oapRedirectUri.id;

    /**
    * OAP_APP主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> appId = oapRedirectUri.appId;

    /**
    * 允许的重定向URI, 最后一个字符可以是通配符*
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> redirectUri = oapRedirectUri.redirectUri;

    /**
    * 建立时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = oapRedirectUri.createTimestamp;

    /**
    * 修改时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> updateTimestamp = oapRedirectUri.updateTimestamp;

    public static final class OapRedirectUri extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> appId = column("APP_ID", JDBCType.BIGINT);

        public final SqlColumn<String> redirectUri = column("REDIRECT_URI", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTimestamp = column("UPDATE_TIMESTAMP", JDBCType.BIGINT);

        public OapRedirectUri() {
            super("OAP_REDIRECT_URI");
        }
    }
}