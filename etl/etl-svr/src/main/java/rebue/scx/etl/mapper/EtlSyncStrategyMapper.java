package rebue.scx.etl.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.dstConnId;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.dstName;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.etlSyncStrategy;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.id;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.isEnabled;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.name;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.remark;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.srcConnId;
import static rebue.scx.etl.mapper.EtlSyncStrategyDynamicSqlSupport.srcName;

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
import rebue.scx.etl.mo.EtlSyncStrategyMo;

@Mapper
public interface EtlSyncStrategyMapper extends MapperRootInterface<EtlSyncStrategyMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, isEnabled, srcConnId, srcName, dstConnId, dstName, remark);

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
    int insert(InsertStatementProvider<EtlSyncStrategyMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EtlSyncStrategyMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("EtlSyncStrategyMoResult")
    Optional<EtlSyncStrategyMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "EtlSyncStrategyMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT),
        @Result(column = "SRC_CONN_ID", property = "srcConnId", jdbcType = JdbcType.BIGINT), @Result(column = "SRC_NAME", property = "srcName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "DST_CONN_ID", property = "dstConnId", jdbcType = JdbcType.BIGINT), @Result(column = "DST_NAME", property = "dstName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<EtlSyncStrategyMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, etlSyncStrategy, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, etlSyncStrategy, completer);
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
    default int insert(EtlSyncStrategyMo record) {
        return MyBatis3Utils.insert(this::insert, record, etlSyncStrategy,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(isEnabled).toProperty("isEnabled").map(srcConnId).toProperty("srcConnId").map(srcName)
                .toProperty("srcName").map(dstConnId).toProperty("dstConnId").map(dstName).toProperty("dstName").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<EtlSyncStrategyMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, etlSyncStrategy,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(isEnabled).toProperty("isEnabled").map(srcConnId).toProperty("srcConnId").map(srcName)
                .toProperty("srcName").map(dstConnId).toProperty("dstConnId").map(dstName).toProperty("dstName").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(EtlSyncStrategyMo record) {
        return MyBatis3Utils.insert(this::insert, record, etlSyncStrategy,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName).map(isEnabled)
                .toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(srcConnId).toPropertyWhenPresent("srcConnId", record::getSrcConnId).map(srcName)
                .toPropertyWhenPresent("srcName", record::getSrcName).map(dstConnId).toPropertyWhenPresent("dstConnId", record::getDstConnId).map(dstName)
                .toPropertyWhenPresent("dstName", record::getDstName).map(remark).toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlSyncStrategyMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, etlSyncStrategy, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, etlSyncStrategy, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, etlSyncStrategy, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlSyncStrategyMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, etlSyncStrategy, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(EtlSyncStrategyMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(isEnabled).equalTo(record::getIsEnabled).set(srcConnId).equalTo(record::getSrcConnId)
            .set(srcName).equalTo(record::getSrcName).set(dstConnId).equalTo(record::getDstConnId).set(dstName).equalTo(record::getDstName).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EtlSyncStrategyMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(isEnabled).equalToWhenPresent(record::getIsEnabled).set(srcConnId)
            .equalToWhenPresent(record::getSrcConnId).set(srcName).equalToWhenPresent(record::getSrcName).set(dstConnId).equalToWhenPresent(record::getDstConnId).set(dstName)
            .equalToWhenPresent(record::getDstName).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(EtlSyncStrategyMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(isEnabled).equalTo(record::getIsEnabled).set(srcConnId).equalTo(record::getSrcConnId).set(srcName)
            .equalTo(record::getSrcName).set(dstConnId).equalTo(record::getDstConnId).set(dstName).equalTo(record::getDstName).set(remark).equalTo(record::getRemark)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(EtlSyncStrategyMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(isEnabled).equalToWhenPresent(record::getIsEnabled).set(srcConnId)
            .equalToWhenPresent(record::getSrcConnId).set(srcName).equalToWhenPresent(record::getSrcName).set(dstConnId).equalToWhenPresent(record::getDstConnId).set(dstName)
            .equalToWhenPresent(record::getDstName).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
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
    default int deleteSelective(EtlSyncStrategyMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(srcConnId, isEqualToWhenPresent(record::getSrcConnId))
            .and(srcName, isEqualToWhenPresent(record::getSrcName)).and(dstConnId, isEqualToWhenPresent(record::getDstConnId))
            .and(dstName, isEqualToWhenPresent(record::getDstName)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlSyncStrategyMo> selectOne(EtlSyncStrategyMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(srcConnId, isEqualToWhenPresent(record::getSrcConnId))
            .and(srcName, isEqualToWhenPresent(record::getSrcName)).and(dstConnId, isEqualToWhenPresent(record::getDstConnId))
            .and(dstName, isEqualToWhenPresent(record::getDstName)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(EtlSyncStrategyMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(srcConnId, isEqualToWhenPresent(record::getSrcConnId))
            .and(srcName, isEqualToWhenPresent(record::getSrcName)).and(dstConnId, isEqualToWhenPresent(record::getDstConnId))
            .and(dstName, isEqualToWhenPresent(record::getDstName)).and(remark, isEqualToWhenPresent(record::getRemark)));
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
    default boolean existSelective(EtlSyncStrategyMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyMo> selectSelective(EtlSyncStrategyMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(srcConnId, isEqualToWhenPresent(record::getSrcConnId))
            .and(srcName, isEqualToWhenPresent(record::getSrcName)).and(dstConnId, isEqualToWhenPresent(record::getDstConnId))
            .and(dstName, isEqualToWhenPresent(record::getDstName)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlSyncStrategyMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
