package com.github.rebue.scx.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OapIpWhiteListDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final OapIpWhiteList oapIpWhiteList = new OapIpWhiteList();

    /**
    * 主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = oapIpWhiteList.id;

    /**
    * OAP_APP主键
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> appId = oapIpWhiteList.appId;

    /**
    * 白名单IP
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> ipAddr = oapIpWhiteList.ipAddr;

    /**
    * 建立时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> createTimestamp = oapIpWhiteList.createTimestamp;

    /**
    * 修改时间戳
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> updateTimestamp = oapIpWhiteList.updateTimestamp;

    public static final class OapIpWhiteList extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> appId = column("APP_ID", JDBCType.BIGINT);

        public final SqlColumn<String> ipAddr = column("IP_ADDR", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTimestamp = column("CREATE_TIMESTAMP", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTimestamp = column("UPDATE_TIMESTAMP", JDBCType.BIGINT);

        public OapIpWhiteList() {
            super("OAP_IP_WHITE_LIST");
        }
    }
}