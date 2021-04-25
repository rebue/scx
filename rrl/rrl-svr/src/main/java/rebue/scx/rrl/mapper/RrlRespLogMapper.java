package rebue.scx.rrl.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.body;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.cookies;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.createTimestamp;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.headers;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.id;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.rrlRespLog;
import static rebue.scx.rrl.mapper.RrlRespLogDynamicSqlSupport.statusCode;

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
import rebue.scx.rrl.mo.RrlRespLogMo;

@Mapper
public interface RrlRespLogMapper extends MapperRootInterface<RrlRespLogMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, statusCode, headers, body, createTimestamp, cookies);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<RrlRespLogMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RrlRespLogMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RrlRespLogMoResult")
    Optional<RrlRespLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RrlRespLogMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="STATUS_CODE", property="statusCode", jdbcType=JdbcType.CHAR),
        @Result(column="HEADERS", property="headers", jdbcType=JdbcType.VARCHAR),
        @Result(column="BODY", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIMESTAMP", property="createTimestamp", jdbcType=JdbcType.BIGINT),
        @Result(column="COOKIES", property="cookies", jdbcType=JdbcType.VARCHAR)
    })
    List<RrlRespLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rrlRespLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rrlRespLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(RrlRespLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, rrlRespLog, c ->
            c.map(id).toProperty("id")
            .map(statusCode).toProperty("statusCode")
            .map(headers).toProperty("headers")
            .map(body).toProperty("body")
            .map(createTimestamp).toProperty("createTimestamp")
            .map(cookies).toProperty("cookies")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RrlRespLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rrlRespLog, c ->
            c.map(id).toProperty("id")
            .map(statusCode).toProperty("statusCode")
            .map(headers).toProperty("headers")
            .map(body).toProperty("body")
            .map(createTimestamp).toProperty("createTimestamp")
            .map(cookies).toProperty("cookies")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RrlRespLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, rrlRespLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(statusCode).toPropertyWhenPresent("statusCode", record::getStatusCode)
            .map(headers).toPropertyWhenPresent("headers", record::getHeaders)
            .map(body).toPropertyWhenPresent("body", record::getBody)
            .map(createTimestamp).toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp)
            .map(cookies).toPropertyWhenPresent("cookies", record::getCookies)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlRespLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rrlRespLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlRespLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rrlRespLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlRespLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rrlRespLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlRespLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rrlRespLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RrlRespLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(statusCode).equalTo(record::getStatusCode)
                .set(headers).equalTo(record::getHeaders)
                .set(body).equalTo(record::getBody)
                .set(createTimestamp).equalTo(record::getCreateTimestamp)
                .set(cookies).equalTo(record::getCookies);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RrlRespLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(statusCode).equalToWhenPresent(record::getStatusCode)
                .set(headers).equalToWhenPresent(record::getHeaders)
                .set(body).equalToWhenPresent(record::getBody)
                .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
                .set(cookies).equalToWhenPresent(record::getCookies);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RrlRespLogMo record) {
        return update(c ->
            c.set(statusCode).equalTo(record::getStatusCode)
            .set(headers).equalTo(record::getHeaders)
            .set(body).equalTo(record::getBody)
            .set(createTimestamp).equalTo(record::getCreateTimestamp)
            .set(cookies).equalTo(record::getCookies)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RrlRespLogMo record) {
        return update(c ->
            c.set(statusCode).equalToWhenPresent(record::getStatusCode)
            .set(headers).equalToWhenPresent(record::getHeaders)
            .set(body).equalToWhenPresent(record::getBody)
            .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
            .set(cookies).equalToWhenPresent(record::getCookies)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RrlRespLogMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(statusCode, isEqualToWhenPresent(record::getStatusCode))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(body, isEqualToWhenPresent(record::getBody))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(cookies, isEqualToWhenPresent(record::getCookies))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlRespLogMo> selectOne(RrlRespLogMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(statusCode, isEqualToWhenPresent(record::getStatusCode))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(body, isEqualToWhenPresent(record::getBody))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(cookies, isEqualToWhenPresent(record::getCookies))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RrlRespLogMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(statusCode, isEqualToWhenPresent(record::getStatusCode))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(body, isEqualToWhenPresent(record::getBody))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(cookies, isEqualToWhenPresent(record::getCookies))
        );
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
    default boolean existSelective(RrlRespLogMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlRespLogMo> selectSelective(RrlRespLogMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(statusCode, isEqualToWhenPresent(record::getStatusCode))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(body, isEqualToWhenPresent(record::getBody))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(cookies, isEqualToWhenPresent(record::getCookies))
        );
    }
}