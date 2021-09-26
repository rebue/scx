package rebue.scx.etl.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EtlSyncStrategyDetailDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final EtlSyncStrategyDetail etlSyncStrategyDetail = new EtlSyncStrategyDetail();

    /**
    * 策略详情ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = etlSyncStrategyDetail.id;

    /**
    * 策略ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> strategyId = etlSyncStrategyDetail.strategyId;

    /**
    * 来源表名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> srcTableName = etlSyncStrategyDetail.srcTableName;

    /**
    * 来源字段名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> srcFieldName = etlSyncStrategyDetail.srcFieldName;

    /**
    * 来源字段类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> srcFieldType = etlSyncStrategyDetail.srcFieldType;

    /**
    * 来源字段长度
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> srcFieldLength = etlSyncStrategyDetail.srcFieldLength;

    /**
    * 来源字段精度
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> srcFieldPrecision = etlSyncStrategyDetail.srcFieldPrecision;

    /**
    * 目的表名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dstTableName = etlSyncStrategyDetail.dstTableName;

    /**
    * 目的字段名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dstFieldName = etlSyncStrategyDetail.dstFieldName;

    /**
    * 目的字段类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dstFieldType = etlSyncStrategyDetail.dstFieldType;

    /**
    * 目的字段长度
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> dstFieldLength = etlSyncStrategyDetail.dstFieldLength;

    /**
    * 目的字段精度
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> dstFieldPrecision = etlSyncStrategyDetail.dstFieldPrecision;

    public static final class EtlSyncStrategyDetail extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> strategyId = column("STRATEGY_ID", JDBCType.BIGINT);

        public final SqlColumn<String> srcTableName = column("SRC_TABLE_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> srcFieldName = column("SRC_FIELD_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> srcFieldType = column("SRC_FIELD_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<Byte> srcFieldLength = column("SRC_FIELD_LENGTH", JDBCType.TINYINT);

        public final SqlColumn<Byte> srcFieldPrecision = column("SRC_FIELD_PRECISION", JDBCType.TINYINT);

        public final SqlColumn<String> dstTableName = column("DST_TABLE_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> dstFieldName = column("DST_FIELD_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> dstFieldType = column("DST_FIELD_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<Byte> dstFieldLength = column("DST_FIELD_LENGTH", JDBCType.TINYINT);

        public final SqlColumn<Byte> dstFieldPrecision = column("DST_FIELD_PRECISION", JDBCType.TINYINT);

        public EtlSyncStrategyDetail() {
            super("ETL_SYNC_STRATEGY_DETAIL");
        }
    }
}