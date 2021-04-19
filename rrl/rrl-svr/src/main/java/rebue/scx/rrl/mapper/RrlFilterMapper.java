package rebue.scx.rrl.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rrl.mapper.RrlFilterDynamicSqlSupport.id;
import static rebue.scx.rrl.mapper.RrlFilterDynamicSqlSupport.method;
import static rebue.scx.rrl.mapper.RrlFilterDynamicSqlSupport.rrlFilter;
import static rebue.scx.rrl.mapper.RrlFilterDynamicSqlSupport.uri;

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
import rebue.scx.rrl.mo.RrlFilterMo;

@Mapper
public interface RrlFilterMapper extends MapperRootInterface<RrlFilterMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, method, uri);

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
    int insert(InsertStatementProvider<RrlFilterMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RrlFilterMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RrlFilterMoResult")
    Optional<RrlFilterMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RrlFilterMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="METHOD", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="URI", property="uri", jdbcType=JdbcType.VARCHAR)
    })
    List<RrlFilterMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rrlFilter, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rrlFilter, completer);
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
    default int insert(RrlFilterMo record) {
        return MyBatis3Utils.insert(this::insert, record, rrlFilter, c ->
            c.map(id).toProperty("id")
            .map(method).toProperty("method")
            .map(uri).toProperty("uri")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RrlFilterMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rrlFilter, c ->
            c.map(id).toProperty("id")
            .map(method).toProperty("method")
            .map(uri).toProperty("uri")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RrlFilterMo record) {
        return MyBatis3Utils.insert(this::insert, record, rrlFilter, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(method).toPropertyWhenPresent("method", record::getMethod)
            .map(uri).toPropertyWhenPresent("uri", record::getUri)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlFilterMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rrlFilter, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlFilterMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rrlFilter, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlFilterMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rrlFilter, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlFilterMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rrlFilter, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RrlFilterMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(method).equalTo(record::getMethod)
                .set(uri).equalTo(record::getUri);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RrlFilterMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(method).equalToWhenPresent(record::getMethod)
                .set(uri).equalToWhenPresent(record::getUri);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RrlFilterMo record) {
        return update(c ->
            c.set(method).equalTo(record::getMethod)
            .set(uri).equalTo(record::getUri)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RrlFilterMo record) {
        return update(c ->
            c.set(method).equalToWhenPresent(record::getMethod)
            .set(uri).equalToWhenPresent(record::getUri)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RrlFilterMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(uri, isEqualToWhenPresent(record::getUri))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RrlFilterMo> selectOne(RrlFilterMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(uri, isEqualToWhenPresent(record::getUri))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RrlFilterMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(uri, isEqualToWhenPresent(record::getUri))
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
    default boolean existSelective(RrlFilterMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RrlFilterMo> selectSelective(RrlFilterMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(method, isEqualToWhenPresent(record::getMethod))
            .and(uri, isEqualToWhenPresent(record::getUri))
        );
    }
}