package rebue.scx.oap.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OapGrantDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final OapGrant oapGrant = new OapGrant();

    /**
    * 主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = oapGrant.id;

    /**
    * rac_account主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accountId = oapGrant.accountId;

    /**
    * oidc access token
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> accessToken = oapGrant.accessToken;

    /**
    * oidc refresh token
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> refreshToken = oapGrant.refreshToken;

    /**
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> accessTokenJson = oapGrant.accessTokenJson;

    /**
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> accessTokenExpireTimestamp = oapGrant.accessTokenExpireTimestamp;

    /**
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> refreshTokenExpiresTimestamp = oapGrant.refreshTokenExpiresTimestamp;

    /**
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = oapGrant.createTimestamp;

    public static final class OapGrant extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> accountId = column("ACCOUNT_ID", JDBCType.BIGINT);

        public final SqlColumn<String> accessToken = column("ACCESS_TOKEN", JDBCType.VARCHAR);

        public final SqlColumn<String> refreshToken = column("REFRESH_TOKEN", JDBCType.VARCHAR);

        public final SqlColumn<String> accessTokenJson = column("ACCESS_TOKEN_JSON", JDBCType.VARCHAR);

        public final SqlColumn<Long> accessTokenExpireTimestamp = column("ACCESS_TOKEN_EXPIRE_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> refreshTokenExpiresTimestamp = column("REFRESH_TOKEN_EXPIRES_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public OapGrant() {
            super("OAP_GRANT");
        }
    }
}