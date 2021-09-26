package rebue.scx.etl.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EtlSyncStrategyDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final EtlSyncStrategy etlSyncStrategy = new EtlSyncStrategy();

    /**
    * 策略ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = etlSyncStrategy.id;

    /**
    * 策略名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = etlSyncStrategy.name;

    /**
    * 是否启用
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Boolean> isEnabled = etlSyncStrategy.isEnabled;

    /**
    * 来源的连接器ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> srcConnId = etlSyncStrategy.srcConnId;

    /**
    * 来源名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> srcName = etlSyncStrategy.srcName;

    /**
    * 目的的连接器ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> dstConnId = etlSyncStrategy.dstConnId;

    /**
    * 目的名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dstName = etlSyncStrategy.dstName;

    /**
    * 策略备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = etlSyncStrategy.remark;

    public static final class EtlSyncStrategy extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isEnabled = column("IS_ENABLED", JDBCType.BIT);

        public final SqlColumn<Long> srcConnId = column("SRC_CONN_ID", JDBCType.BIGINT);

        public final SqlColumn<String> srcName = column("SRC_NAME", JDBCType.VARCHAR);

        public final SqlColumn<Long> dstConnId = column("DST_CONN_ID", JDBCType.BIGINT);

        public final SqlColumn<String> dstName = column("DST_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public EtlSyncStrategy() {
            super("ETL_SYNC_STRATEGY");
        }
    }
}