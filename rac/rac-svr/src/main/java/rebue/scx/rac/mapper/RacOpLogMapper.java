package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacOpLogMo;

@Mapper
public interface RacOpLogMapper extends MapperRootInterface<RacOpLogMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, sysId, opTitle, opDetail, opDatetime);

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
    int insert(InsertStatementProvider<RacOpLogMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacOpLogMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacOpLogMoResult")
    Optional<RacOpLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacOpLogMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="SYS_ID", property="sysId", jdbcType=JdbcType.VARCHAR),
        @Result(column="OP_TITLE", property="opTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="OP_DETAIL", property="opDetail", jdbcType=JdbcType.VARCHAR),
        @Result(column="OP_DATETIME", property="opDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RacOpLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racOpLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racOpLog, completer);
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
    default int insert(RacOpLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOpLog, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(sysId).toProperty("sysId")
            .map(opTitle).toProperty("opTitle")
            .map(opDetail).toProperty("opDetail")
            .map(opDatetime).toProperty("opDatetime")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacOpLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racOpLog, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(sysId).toProperty("sysId")
            .map(opTitle).toProperty("opTitle")
            .map(opDetail).toProperty("opDetail")
            .map(opDatetime).toProperty("opDatetime")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacOpLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOpLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(sysId).toPropertyWhenPresent("sysId", record::getSysId)
            .map(opTitle).toPropertyWhenPresent("opTitle", record::getOpTitle)
            .map(opDetail).toPropertyWhenPresent("opDetail", record::getOpDetail)
            .map(opDatetime).toPropertyWhenPresent("opDatetime", record::getOpDatetime)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOpLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racOpLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racOpLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racOpLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOpLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racOpLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacOpLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(sysId).equalTo(record::getSysId)
                .set(opTitle).equalTo(record::getOpTitle)
                .set(opDetail).equalTo(record::getOpDetail)
                .set(opDatetime).equalTo(record::getOpDatetime);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacOpLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(sysId).equalToWhenPresent(record::getSysId)
                .set(opTitle).equalToWhenPresent(record::getOpTitle)
                .set(opDetail).equalToWhenPresent(record::getOpDetail)
                .set(opDatetime).equalToWhenPresent(record::getOpDatetime);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacOpLogMo record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(sysId).equalTo(record::getSysId)
            .set(opTitle).equalTo(record::getOpTitle)
            .set(opDetail).equalTo(record::getOpDetail)
            .set(opDatetime).equalTo(record::getOpDatetime)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacOpLogMo record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(sysId).equalToWhenPresent(record::getSysId)
            .set(opTitle).equalToWhenPresent(record::getOpTitle)
            .set(opDetail).equalToWhenPresent(record::getOpDetail)
            .set(opDatetime).equalToWhenPresent(record::getOpDatetime)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacOpLogMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
            .and(opDetail, isEqualToWhenPresent(record::getOpDetail))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOpLogMo> selectOne(RacOpLogMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
            .and(opDetail, isEqualToWhenPresent(record::getOpDetail))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacOpLogMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
            .and(opDetail, isEqualToWhenPresent(record::getOpDetail))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime))
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
    default boolean existSelective(RacOpLogMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpLogMo> selectSelective(RacOpLogMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
            .and(opDetail, isEqualToWhenPresent(record::getOpDetail))
            .and(opDatetime, isEqualToWhenPresent(record::getOpDatetime))
        );
    }
}