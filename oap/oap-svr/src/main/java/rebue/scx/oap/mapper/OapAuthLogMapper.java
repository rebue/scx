package rebue.scx.oap.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThanWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThanWhenPresent;
import static rebue.scx.oap.mapper.OapAuthLogDynamicSqlSupport.id;
import static rebue.scx.oap.mapper.OapAuthLogDynamicSqlSupport.isSuccess;
import static rebue.scx.oap.mapper.OapAuthLogDynamicSqlSupport.oapAuthLog;
import static rebue.scx.oap.mapper.OapAuthLogDynamicSqlSupport.opDatetime;
import static rebue.scx.oap.mapper.OapAuthLogDynamicSqlSupport.reason;

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
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
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
import rebue.scx.oap.mo.OapAuthLogMo;
import rebue.scx.oap.to.OapAuthLogPageTo;

@Mapper
public interface OapAuthLogMapper extends MapperRootInterface<OapAuthLogMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, isSuccess, opDatetime, reason);

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
    int insert(InsertStatementProvider<OapAuthLogMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OapAuthLogMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("OapAuthLogMoResult")
    Optional<OapAuthLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "OapAuthLogMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "IS_SUCCESS", property = "isSuccess", jdbcType = JdbcType.BIT), @Result(column = "OP_DATETIME", property = "opDatetime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "REASON", property = "reason", jdbcType = JdbcType.VARCHAR)
    })
    List<OapAuthLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, oapAuthLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, oapAuthLog, completer);
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
    default int insert(OapAuthLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapAuthLog,
            c -> c.map(id).toProperty("id").map(isSuccess).toProperty("isSuccess").map(opDatetime).toProperty("opDatetime").map(reason).toProperty("reason"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<OapAuthLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, oapAuthLog,
            c -> c.map(id).toProperty("id").map(isSuccess).toProperty("isSuccess").map(opDatetime).toProperty("opDatetime").map(reason).toProperty("reason"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(OapAuthLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapAuthLog,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(isSuccess).toPropertyWhenPresent("isSuccess", record::getIsSuccess).map(opDatetime)
                .toPropertyWhenPresent("opDatetime", record::getOpDatetime).map(reason).toPropertyWhenPresent("reason", record::getReason));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapAuthLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, oapAuthLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAuthLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, oapAuthLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAuthLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, oapAuthLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapAuthLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, oapAuthLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(OapAuthLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(isSuccess).equalTo(record::getIsSuccess).set(opDatetime).equalTo(record::getOpDatetime).set(reason)
            .equalTo(record::getReason);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OapAuthLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(isSuccess).equalToWhenPresent(record::getIsSuccess).set(opDatetime).equalToWhenPresent(record::getOpDatetime)
            .set(reason).equalToWhenPresent(record::getReason);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(OapAuthLogMo record) {
        return update(c -> c.set(isSuccess).equalTo(record::getIsSuccess).set(opDatetime).equalTo(record::getOpDatetime).set(reason).equalTo(record::getReason).where(id,
            isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(OapAuthLogMo record) {
        return update(c -> c.set(isSuccess).equalToWhenPresent(record::getIsSuccess).set(opDatetime).equalToWhenPresent(record::getOpDatetime).set(reason)
            .equalToWhenPresent(record::getReason).where(id, isEqualTo(record::getId)));
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
    default int deleteSelective(OapAuthLogMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(isSuccess, isEqualToWhenPresent(record::getIsSuccess))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)).and(reason, isEqualToWhenPresent(record::getReason)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapAuthLogMo> selectOne(OapAuthLogMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(isSuccess, isEqualToWhenPresent(record::getIsSuccess))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)).and(reason, isEqualToWhenPresent(record::getReason)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(OapAuthLogMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(isSuccess, isEqualToWhenPresent(record::getIsSuccess))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)).and(reason, isEqualToWhenPresent(record::getReason)));
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
    default boolean existSelective(OapAuthLogMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAuthLogMo> selectSelective(OapAuthLogMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(isSuccess, isEqualToWhenPresent(record::getIsSuccess))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)).and(reason, isEqualToWhenPresent(record::getReason)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAuthLogMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * 今日认证概况
     *
     * @param record
     */
    default Long countSurvey(OapAuthLogPageTo record) {
        SelectStatementProvider countSurvey = SqlBuilder.countFrom(oapAuthLog).where(oapAuthLog.opDatetime, isGreaterThanWhenPresent(record.getStartDate()))
            .and(oapAuthLog.opDatetime, isLessThanWhenPresent(record.getEndDate())).and(oapAuthLog.isSuccess, isEqualToWhenPresent(record.getIsSuccess())).build()
            .render(RenderingStrategies.MYBATIS3);
        long count = count(countSurvey);
        return count;
    }
}
