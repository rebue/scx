package rebue.scx.rrl.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.body;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.contentType;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.headers;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.host;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.id;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.method;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.path;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.port;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.queryParams;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.rrlReqLog;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.scheme;
import static rebue.scx.rrl.mapper.RrlReqLogDynamicSqlSupport.uri;

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
import rebue.scx.rrl.mo.RrlReqLogMo;

@Mapper
public interface RrlReqLogMapper extends MapperRootInterface<RrlReqLogMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, method, scheme, host, port, path, uri, headers, contentType, queryParams, body);

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
    int insert(InsertStatementProvider<RrlReqLogMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RrlReqLogMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RrlReqLogMoResult")
    Optional<RrlReqLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RrlReqLogMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="METHOD", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="SCHEME", property="scheme", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOST", property="host", jdbcType=JdbcType.VARCHAR),
        @Result(column="PORT", property="port", jdbcType=JdbcType.INTEGER),
        @Result(column="PATH", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="URI", property="uri", jdbcType=JdbcType.VARCHAR),
        @Result(column="HEADERS", property="headers", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTENT_TYPE", property="contentType", jdbcType=JdbcType.VARCHAR),
        @Result(column="QUERY_PARAMS", property="queryParams", jdbcType=JdbcType.VARCHAR),
        @Result(column="BODY", property="body", jdbcType=JdbcType.VARCHAR)
    })
    List<RrlReqLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rrlReqLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rrlReqLog, completer);
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
    default int insert(RrlReqLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, rrlReqLog, c ->
            c.map(id).toProperty("id")
            .map(method).toProperty("method")
            .map(scheme).toProperty("scheme")
            .map(host).toProperty("host")
            .map(port).toProperty("port")
            .map(path).toProperty("path")
            .map(uri).toProperty("uri")
            .map(headers).toProperty("headers")
            .map(contentType).toProperty("contentType")
            .map(queryParams).toProperty("queryParams")
            .map(body).toProperty("body")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RrlReqLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rrlReqLog, c ->
            c.map(id).toProperty("id")
            .map(method).toProperty("method")
            .map(scheme).toProperty("scheme")
            .map(host).toProperty("host")
            .map(port).toProperty("port")
            .map(path).toProperty("path")
            .map(uri).toProperty("uri")
            .map(headers).toProperty("headers")
            .map(contentType).toProperty("contentType")
            .map(queryParams).toProperty("queryParams")
            .map(body).toProperty("body")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RrlReqLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, rrlReqLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(method).toPropertyWhenPresent("method", record::getMethod)
            .map(scheme).toPropertyWhenPresent("scheme", record::getScheme)
            .map(host).toPropertyWhenPresent("host", record::getHost)
            .map(port).toPropertyWhenPresent("port", record::getPort)
            .map(path).toPropertyWhenPresent("path", record::getPath)
            .map(uri).toPropertyWhenPresent("uri", record::getUri)
            .map(headers).toPropertyWhenPresent("headers", record::getHeaders)
            .map(contentType).toPropertyWhenPresent("contentType", record::getContentType)
            .map(queryParams).toPropertyWhenPresent("queryParams", record::getQueryParams)
            .map(body).toPropertyWhenPresent("body", record::getBody)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlReqLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rrlReqLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlReqLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rrlReqLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlReqLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rrlReqLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlReqLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rrlReqLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RrlReqLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(method).equalTo(record::getMethod)
                .set(scheme).equalTo(record::getScheme)
                .set(host).equalTo(record::getHost)
                .set(port).equalTo(record::getPort)
                .set(path).equalTo(record::getPath)
                .set(uri).equalTo(record::getUri)
                .set(headers).equalTo(record::getHeaders)
                .set(contentType).equalTo(record::getContentType)
                .set(queryParams).equalTo(record::getQueryParams)
                .set(body).equalTo(record::getBody);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RrlReqLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(method).equalToWhenPresent(record::getMethod)
                .set(scheme).equalToWhenPresent(record::getScheme)
                .set(host).equalToWhenPresent(record::getHost)
                .set(port).equalToWhenPresent(record::getPort)
                .set(path).equalToWhenPresent(record::getPath)
                .set(uri).equalToWhenPresent(record::getUri)
                .set(headers).equalToWhenPresent(record::getHeaders)
                .set(contentType).equalToWhenPresent(record::getContentType)
                .set(queryParams).equalToWhenPresent(record::getQueryParams)
                .set(body).equalToWhenPresent(record::getBody);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RrlReqLogMo record) {
        return update(c ->
            c.set(method).equalTo(record::getMethod)
            .set(scheme).equalTo(record::getScheme)
            .set(host).equalTo(record::getHost)
            .set(port).equalTo(record::getPort)
            .set(path).equalTo(record::getPath)
            .set(uri).equalTo(record::getUri)
            .set(headers).equalTo(record::getHeaders)
            .set(contentType).equalTo(record::getContentType)
            .set(queryParams).equalTo(record::getQueryParams)
            .set(body).equalTo(record::getBody)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RrlReqLogMo record) {
        return update(c ->
            c.set(method).equalToWhenPresent(record::getMethod)
            .set(scheme).equalToWhenPresent(record::getScheme)
            .set(host).equalToWhenPresent(record::getHost)
            .set(port).equalToWhenPresent(record::getPort)
            .set(path).equalToWhenPresent(record::getPath)
            .set(uri).equalToWhenPresent(record::getUri)
            .set(headers).equalToWhenPresent(record::getHeaders)
            .set(contentType).equalToWhenPresent(record::getContentType)
            .set(queryParams).equalToWhenPresent(record::getQueryParams)
            .set(body).equalToWhenPresent(record::getBody)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RrlReqLogMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(scheme, isEqualToWhenPresent(record::getScheme))
            .and(host, isEqualToWhenPresent(record::getHost))
            .and(port, isEqualToWhenPresent(record::getPort))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(uri, isEqualToWhenPresent(record::getUri))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(contentType, isEqualToWhenPresent(record::getContentType))
            .and(queryParams, isEqualToWhenPresent(record::getQueryParams))
            .and(body, isEqualToWhenPresent(record::getBody))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlReqLogMo> selectOne(RrlReqLogMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(scheme, isEqualToWhenPresent(record::getScheme))
            .and(host, isEqualToWhenPresent(record::getHost))
            .and(port, isEqualToWhenPresent(record::getPort))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(uri, isEqualToWhenPresent(record::getUri))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(contentType, isEqualToWhenPresent(record::getContentType))
            .and(queryParams, isEqualToWhenPresent(record::getQueryParams))
            .and(body, isEqualToWhenPresent(record::getBody))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RrlReqLogMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(scheme, isEqualToWhenPresent(record::getScheme))
            .and(host, isEqualToWhenPresent(record::getHost))
            .and(port, isEqualToWhenPresent(record::getPort))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(uri, isEqualToWhenPresent(record::getUri))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(contentType, isEqualToWhenPresent(record::getContentType))
            .and(queryParams, isEqualToWhenPresent(record::getQueryParams))
            .and(body, isEqualToWhenPresent(record::getBody))
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
    default boolean existSelective(RrlReqLogMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlReqLogMo> selectSelective(RrlReqLogMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(scheme, isEqualToWhenPresent(record::getScheme))
            .and(host, isEqualToWhenPresent(record::getHost))
            .and(port, isEqualToWhenPresent(record::getPort))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(uri, isEqualToWhenPresent(record::getUri))
            .and(headers, isEqualToWhenPresent(record::getHeaders))
            .and(contentType, isEqualToWhenPresent(record::getContentType))
            .and(queryParams, isEqualToWhenPresent(record::getQueryParams))
            .and(body, isEqualToWhenPresent(record::getBody))
        );
    }
}