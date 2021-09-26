package rebue.scx.etl.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.dstFieldLength;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.dstFieldName;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.dstFieldPrecision;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.dstFieldType;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.dstTableName;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.etlSyncStrategyDetail;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.id;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.srcFieldLength;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.srcFieldName;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.srcFieldPrecision;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.srcFieldType;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.srcTableName;
import static rebue.scx.etl.mapper.EtlSyncStrategyDetailDynamicSqlSupport.strategyId;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import rebue.robotech.mybatis.MapperRootInterface;
import rebue.scx.etl.mo.EtlSyncStrategyDetailMo;

@Mapper
public interface EtlSyncStrategyDetailMapper extends MapperRootInterface<EtlSyncStrategyDetailMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, strategyId, srcTableName, srcFieldName, srcFieldType, srcFieldLength, srcFieldPrecision, dstTableName, dstFieldName,
        dstFieldType, dstFieldLength, dstFieldPrecision);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<EtlSyncStrategyDetailMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EtlSyncStrategyDetailMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("EtlSyncStrategyDetailMoResult")
    Optional<EtlSyncStrategyDetailMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "EtlSyncStrategyDetailMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "STRATEGY_ID", property = "strategyId", jdbcType = JdbcType.BIGINT),
        @Result(column = "SRC_TABLE_NAME", property = "srcTableName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SRC_FIELD_NAME", property = "srcFieldName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SRC_FIELD_TYPE", property = "srcFieldType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SRC_FIELD_LENGTH", property = "srcFieldLength", jdbcType = JdbcType.TINYINT),
        @Result(column = "SRC_FIELD_PRECISION", property = "srcFieldPrecision", jdbcType = JdbcType.TINYINT),
        @Result(column = "DST_TABLE_NAME", property = "dstTableName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "DST_FIELD_NAME", property = "dstFieldName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "DST_FIELD_TYPE", property = "dstFieldType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "DST_FIELD_LENGTH", property = "dstFieldLength", jdbcType = JdbcType.TINYINT),
        @Result(column = "DST_FIELD_PRECISION", property = "dstFieldPrecision", jdbcType = JdbcType.TINYINT)
    })
    List<EtlSyncStrategyDetailMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, etlSyncStrategyDetail, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, etlSyncStrategyDetail, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(EtlSyncStrategyDetailMo record) {
        return MyBatis3Utils.insert(this::insert, record, etlSyncStrategyDetail,
            c -> c.map(id).toProperty("id").map(strategyId).toProperty("strategyId").map(srcTableName).toProperty("srcTableName").map(srcFieldName).toProperty("srcFieldName")
                .map(srcFieldType).toProperty("srcFieldType").map(srcFieldLength).toProperty("srcFieldLength").map(srcFieldPrecision).toProperty("srcFieldPrecision")
                .map(dstTableName).toProperty("dstTableName").map(dstFieldName).toProperty("dstFieldName").map(dstFieldType).toProperty("dstFieldType").map(dstFieldLength)
                .toProperty("dstFieldLength").map(dstFieldPrecision).toProperty("dstFieldPrecision"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<EtlSyncStrategyDetailMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, etlSyncStrategyDetail,
            c -> c.map(id).toProperty("id").map(strategyId).toProperty("strategyId").map(srcTableName).toProperty("srcTableName").map(srcFieldName).toProperty("srcFieldName")
                .map(srcFieldType).toProperty("srcFieldType").map(srcFieldLength).toProperty("srcFieldLength").map(srcFieldPrecision).toProperty("srcFieldPrecision")
                .map(dstTableName).toProperty("dstTableName").map(dstFieldName).toProperty("dstFieldName").map(dstFieldType).toProperty("dstFieldType").map(dstFieldLength)
                .toProperty("dstFieldLength").map(dstFieldPrecision).toProperty("dstFieldPrecision"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(EtlSyncStrategyDetailMo record) {
        return MyBatis3Utils.insert(this::insert, record, etlSyncStrategyDetail,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(strategyId).toPropertyWhenPresent("strategyId", record::getStrategyId).map(srcTableName)
                .toPropertyWhenPresent("srcTableName", record::getSrcTableName).map(srcFieldName).toPropertyWhenPresent("srcFieldName", record::getSrcFieldName).map(srcFieldType)
                .toPropertyWhenPresent("srcFieldType", record::getSrcFieldType).map(srcFieldLength).toPropertyWhenPresent("srcFieldLength", record::getSrcFieldLength)
                .map(srcFieldPrecision).toPropertyWhenPresent("srcFieldPrecision", record::getSrcFieldPrecision).map(dstTableName)
                .toPropertyWhenPresent("dstTableName", record::getDstTableName).map(dstFieldName).toPropertyWhenPresent("dstFieldName", record::getDstFieldName).map(dstFieldType)
                .toPropertyWhenPresent("dstFieldType", record::getDstFieldType).map(dstFieldLength).toPropertyWhenPresent("dstFieldLength", record::getDstFieldLength)
                .map(dstFieldPrecision).toPropertyWhenPresent("dstFieldPrecision", record::getDstFieldPrecision));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlSyncStrategyDetailMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, etlSyncStrategyDetail, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyDetailMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, etlSyncStrategyDetail, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyDetailMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, etlSyncStrategyDetail, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlSyncStrategyDetailMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, etlSyncStrategyDetail, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(EtlSyncStrategyDetailMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(strategyId).equalTo(record::getStrategyId).set(srcTableName).equalTo(record::getSrcTableName).set(srcFieldName)
            .equalTo(record::getSrcFieldName).set(srcFieldType).equalTo(record::getSrcFieldType).set(srcFieldLength).equalTo(record::getSrcFieldLength).set(srcFieldPrecision)
            .equalTo(record::getSrcFieldPrecision).set(dstTableName).equalTo(record::getDstTableName).set(dstFieldName).equalTo(record::getDstFieldName).set(dstFieldType)
            .equalTo(record::getDstFieldType).set(dstFieldLength).equalTo(record::getDstFieldLength).set(dstFieldPrecision).equalTo(record::getDstFieldPrecision);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EtlSyncStrategyDetailMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(strategyId).equalToWhenPresent(record::getStrategyId).set(srcTableName).equalToWhenPresent(record::getSrcTableName)
            .set(srcFieldName).equalToWhenPresent(record::getSrcFieldName).set(srcFieldType).equalToWhenPresent(record::getSrcFieldType).set(srcFieldLength)
            .equalToWhenPresent(record::getSrcFieldLength).set(srcFieldPrecision).equalToWhenPresent(record::getSrcFieldPrecision).set(dstTableName)
            .equalToWhenPresent(record::getDstTableName).set(dstFieldName).equalToWhenPresent(record::getDstFieldName).set(dstFieldType).equalToWhenPresent(record::getDstFieldType)
            .set(dstFieldLength).equalToWhenPresent(record::getDstFieldLength).set(dstFieldPrecision).equalToWhenPresent(record::getDstFieldPrecision);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(EtlSyncStrategyDetailMo record) {
        return update(c -> c.set(strategyId).equalTo(record::getStrategyId).set(srcTableName).equalTo(record::getSrcTableName).set(srcFieldName).equalTo(record::getSrcFieldName)
            .set(srcFieldType).equalTo(record::getSrcFieldType).set(srcFieldLength).equalTo(record::getSrcFieldLength).set(srcFieldPrecision).equalTo(record::getSrcFieldPrecision)
            .set(dstTableName).equalTo(record::getDstTableName).set(dstFieldName).equalTo(record::getDstFieldName).set(dstFieldType).equalTo(record::getDstFieldType)
            .set(dstFieldLength).equalTo(record::getDstFieldLength).set(dstFieldPrecision).equalTo(record::getDstFieldPrecision).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(EtlSyncStrategyDetailMo record) {
        return update(c -> c.set(strategyId).equalToWhenPresent(record::getStrategyId).set(srcTableName).equalToWhenPresent(record::getSrcTableName).set(srcFieldName)
            .equalToWhenPresent(record::getSrcFieldName).set(srcFieldType).equalToWhenPresent(record::getSrcFieldType).set(srcFieldLength)
            .equalToWhenPresent(record::getSrcFieldLength).set(srcFieldPrecision).equalToWhenPresent(record::getSrcFieldPrecision).set(dstTableName)
            .equalToWhenPresent(record::getDstTableName).set(dstFieldName).equalToWhenPresent(record::getDstFieldName).set(dstFieldType).equalToWhenPresent(record::getDstFieldType)
            .set(dstFieldLength).equalToWhenPresent(record::getDstFieldLength).set(dstFieldPrecision).equalToWhenPresent(record::getDstFieldPrecision)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(EtlSyncStrategyDetailMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(strategyId, isEqualToWhenPresent(record::getStrategyId))
            .and(srcTableName, isEqualToWhenPresent(record::getSrcTableName)).and(srcFieldName, isEqualToWhenPresent(record::getSrcFieldName))
            .and(srcFieldType, isEqualToWhenPresent(record::getSrcFieldType)).and(srcFieldLength, isEqualToWhenPresent(record::getSrcFieldLength))
            .and(srcFieldPrecision, isEqualToWhenPresent(record::getSrcFieldPrecision)).and(dstTableName, isEqualToWhenPresent(record::getDstTableName))
            .and(dstFieldName, isEqualToWhenPresent(record::getDstFieldName)).and(dstFieldType, isEqualToWhenPresent(record::getDstFieldType))
            .and(dstFieldLength, isEqualToWhenPresent(record::getDstFieldLength)).and(dstFieldPrecision, isEqualToWhenPresent(record::getDstFieldPrecision)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlSyncStrategyDetailMo> selectOne(EtlSyncStrategyDetailMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(strategyId, isEqualToWhenPresent(record::getStrategyId))
            .and(srcTableName, isEqualToWhenPresent(record::getSrcTableName)).and(srcFieldName, isEqualToWhenPresent(record::getSrcFieldName))
            .and(srcFieldType, isEqualToWhenPresent(record::getSrcFieldType)).and(srcFieldLength, isEqualToWhenPresent(record::getSrcFieldLength))
            .and(srcFieldPrecision, isEqualToWhenPresent(record::getSrcFieldPrecision)).and(dstTableName, isEqualToWhenPresent(record::getDstTableName))
            .and(dstFieldName, isEqualToWhenPresent(record::getDstFieldName)).and(dstFieldType, isEqualToWhenPresent(record::getDstFieldType))
            .and(dstFieldLength, isEqualToWhenPresent(record::getDstFieldLength)).and(dstFieldPrecision, isEqualToWhenPresent(record::getDstFieldPrecision)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(EtlSyncStrategyDetailMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(strategyId, isEqualToWhenPresent(record::getStrategyId))
            .and(srcTableName, isEqualToWhenPresent(record::getSrcTableName)).and(srcFieldName, isEqualToWhenPresent(record::getSrcFieldName))
            .and(srcFieldType, isEqualToWhenPresent(record::getSrcFieldType)).and(srcFieldLength, isEqualToWhenPresent(record::getSrcFieldLength))
            .and(srcFieldPrecision, isEqualToWhenPresent(record::getSrcFieldPrecision)).and(dstTableName, isEqualToWhenPresent(record::getDstTableName))
            .and(dstFieldName, isEqualToWhenPresent(record::getDstFieldName)).and(dstFieldType, isEqualToWhenPresent(record::getDstFieldType))
            .and(dstFieldLength, isEqualToWhenPresent(record::getDstFieldLength)).and(dstFieldPrecision, isEqualToWhenPresent(record::getDstFieldPrecision)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existByPrimaryKey(Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existSelective(EtlSyncStrategyDetailMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyDetailMo> selectSelective(EtlSyncStrategyDetailMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(strategyId, isEqualToWhenPresent(record::getStrategyId))
            .and(srcTableName, isEqualToWhenPresent(record::getSrcTableName)).and(srcFieldName, isEqualToWhenPresent(record::getSrcFieldName))
            .and(srcFieldType, isEqualToWhenPresent(record::getSrcFieldType)).and(srcFieldLength, isEqualToWhenPresent(record::getSrcFieldLength))
            .and(srcFieldPrecision, isEqualToWhenPresent(record::getSrcFieldPrecision)).and(dstTableName, isEqualToWhenPresent(record::getDstTableName))
            .and(dstFieldName, isEqualToWhenPresent(record::getDstFieldName)).and(dstFieldType, isEqualToWhenPresent(record::getDstFieldType))
            .and(dstFieldLength, isEqualToWhenPresent(record::getDstFieldLength)).and(dstFieldPrecision, isEqualToWhenPresent(record::getDstFieldPrecision)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyDetailMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
