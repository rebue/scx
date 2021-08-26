package com.github.rebue.scx.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OapAppDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final OapApp oapApp = new OapApp();

    /**
    * 主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = oapApp.id;

    /**
    * rac_app主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> appId = oapApp.appId;

    /**
    * oidc client id
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> clientId = oapApp.clientId;

    /**
    * oidc secret
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> secret = oapApp.secret;

    /**
    * 建立时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = oapApp.createTimestamp;

    /**
    * 修改时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> updateTimestamp = oapApp.updateTimestamp;

    public static final class OapApp extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> appId = column("APP_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> clientId = column("CLIENT_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> secret = column("SECRET", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTimestamp = column("UPDATE_TIMESTAMP", JDBCType.BIGINT);

        public OapApp() {
            super("OAP_APP");
        }
    }
}