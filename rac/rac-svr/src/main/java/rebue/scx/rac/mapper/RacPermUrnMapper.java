package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacPermUrnDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacPermUrnDynamicSqlSupport.permId;
import static rebue.scx.rac.mapper.RacPermUrnDynamicSqlSupport.racPermUrn;
import static rebue.scx.rac.mapper.RacPermUrnDynamicSqlSupport.urn;

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
import rebue.scx.rac.mo.RacPermUrnMo;

@Mapper
public interface RacPermUrnMapper extends MapperRootInterface<RacPermUrnMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, permId, urn);

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
    int insert(InsertStatementProvider<RacPermUrnMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacPermUrnMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacPermUrnMoResult")
    Optional<RacPermUrnMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacPermUrnMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="PERM_ID", property="permId", jdbcType=JdbcType.BIGINT),
        @Result(column="URN", property="urn", jdbcType=JdbcType.VARCHAR)
    })
    List<RacPermUrnMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racPermUrn, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racPermUrn, completer);
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
    default int insert(RacPermUrnMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPermUrn, c ->
            c.map(id).toProperty("id")
            .map(permId).toProperty("permId")
            .map(urn).toProperty("urn")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacPermUrnMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racPermUrn, c ->
            c.map(id).toProperty("id")
            .map(permId).toProperty("permId")
            .map(urn).toProperty("urn")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacPermUrnMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPermUrn, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(permId).toPropertyWhenPresent("permId", record::getPermId)
            .map(urn).toPropertyWhenPresent("urn", record::getUrn)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermUrnMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racPermUrn, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermUrnMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racPermUrn, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermUrnMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racPermUrn, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermUrnMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racPermUrn, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacPermUrnMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(permId).equalTo(record::getPermId)
                .set(urn).equalTo(record::getUrn);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacPermUrnMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(permId).equalToWhenPresent(record::getPermId)
                .set(urn).equalToWhenPresent(record::getUrn);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacPermUrnMo record) {
        return update(c ->
            c.set(permId).equalTo(record::getPermId)
            .set(urn).equalTo(record::getUrn)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacPermUrnMo record) {
        return update(c ->
            c.set(permId).equalToWhenPresent(record::getPermId)
            .set(urn).equalToWhenPresent(record::getUrn)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacPermUrnMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(permId, isEqualToWhenPresent(record::getPermId))
            .and(urn, isEqualToWhenPresent(record::getUrn))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermUrnMo> selectOne(RacPermUrnMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(permId, isEqualToWhenPresent(record::getPermId))
            .and(urn, isEqualToWhenPresent(record::getUrn))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacPermUrnMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(permId, isEqualToWhenPresent(record::getPermId))
            .and(urn, isEqualToWhenPresent(record::getUrn))
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
    default boolean existSelective(RacPermUrnMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermUrnMo> selectSelective(RacPermUrnMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(permId, isEqualToWhenPresent(record::getPermId))
            .and(urn, isEqualToWhenPresent(record::getUrn))
        );
    }
}