package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacLockLogMo;

@Mapper
public interface RacLockLogMapper extends MapperRootInterface<RacLockLogMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, sysId, lockUserId, lockOpId, lockReason, lockDatetime, unlockReason, unlockDatetime, unlockOpId);

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
    int insert(InsertStatementProvider<RacLockLogMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacLockLogMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacLockLogMoResult")
    Optional<RacLockLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacLockLogMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SYS_ID", property="sysId", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_USER_ID", property="lockUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="LOCK_OP_ID", property="lockOpId", jdbcType=JdbcType.BIGINT),
        @Result(column="LOCK_REASON", property="lockReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_DATETIME", property="lockDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UNLOCK_REASON", property="unlockReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_DATETIME", property="unlockDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UNLOCK_OP_ID", property="unlockOpId", jdbcType=JdbcType.BIGINT)
    })
    List<RacLockLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racLockLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racLockLog, completer);
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
    default int insert(RacLockLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racLockLog, c ->
            c.map(id).toProperty("id")
            .map(sysId).toProperty("sysId")
            .map(lockUserId).toProperty("lockUserId")
            .map(lockOpId).toProperty("lockOpId")
            .map(lockReason).toProperty("lockReason")
            .map(lockDatetime).toProperty("lockDatetime")
            .map(unlockReason).toProperty("unlockReason")
            .map(unlockDatetime).toProperty("unlockDatetime")
            .map(unlockOpId).toProperty("unlockOpId")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacLockLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racLockLog, c ->
            c.map(id).toProperty("id")
            .map(sysId).toProperty("sysId")
            .map(lockUserId).toProperty("lockUserId")
            .map(lockOpId).toProperty("lockOpId")
            .map(lockReason).toProperty("lockReason")
            .map(lockDatetime).toProperty("lockDatetime")
            .map(unlockReason).toProperty("unlockReason")
            .map(unlockDatetime).toProperty("unlockDatetime")
            .map(unlockOpId).toProperty("unlockOpId")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacLockLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racLockLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(sysId).toPropertyWhenPresent("sysId", record::getSysId)
            .map(lockUserId).toPropertyWhenPresent("lockUserId", record::getLockUserId)
            .map(lockOpId).toPropertyWhenPresent("lockOpId", record::getLockOpId)
            .map(lockReason).toPropertyWhenPresent("lockReason", record::getLockReason)
            .map(lockDatetime).toPropertyWhenPresent("lockDatetime", record::getLockDatetime)
            .map(unlockReason).toPropertyWhenPresent("unlockReason", record::getUnlockReason)
            .map(unlockDatetime).toPropertyWhenPresent("unlockDatetime", record::getUnlockDatetime)
            .map(unlockOpId).toPropertyWhenPresent("unlockOpId", record::getUnlockOpId)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacLockLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racLockLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacLockLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racLockLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacLockLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racLockLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacLockLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racLockLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacLockLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(sysId).equalTo(record::getSysId)
                .set(lockUserId).equalTo(record::getLockUserId)
                .set(lockOpId).equalTo(record::getLockOpId)
                .set(lockReason).equalTo(record::getLockReason)
                .set(lockDatetime).equalTo(record::getLockDatetime)
                .set(unlockReason).equalTo(record::getUnlockReason)
                .set(unlockDatetime).equalTo(record::getUnlockDatetime)
                .set(unlockOpId).equalTo(record::getUnlockOpId);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacLockLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(sysId).equalToWhenPresent(record::getSysId)
                .set(lockUserId).equalToWhenPresent(record::getLockUserId)
                .set(lockOpId).equalToWhenPresent(record::getLockOpId)
                .set(lockReason).equalToWhenPresent(record::getLockReason)
                .set(lockDatetime).equalToWhenPresent(record::getLockDatetime)
                .set(unlockReason).equalToWhenPresent(record::getUnlockReason)
                .set(unlockDatetime).equalToWhenPresent(record::getUnlockDatetime)
                .set(unlockOpId).equalToWhenPresent(record::getUnlockOpId);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacLockLogMo record) {
        return update(c ->
            c.set(sysId).equalTo(record::getSysId)
            .set(lockUserId).equalTo(record::getLockUserId)
            .set(lockOpId).equalTo(record::getLockOpId)
            .set(lockReason).equalTo(record::getLockReason)
            .set(lockDatetime).equalTo(record::getLockDatetime)
            .set(unlockReason).equalTo(record::getUnlockReason)
            .set(unlockDatetime).equalTo(record::getUnlockDatetime)
            .set(unlockOpId).equalTo(record::getUnlockOpId)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacLockLogMo record) {
        return update(c ->
            c.set(sysId).equalToWhenPresent(record::getSysId)
            .set(lockUserId).equalToWhenPresent(record::getLockUserId)
            .set(lockOpId).equalToWhenPresent(record::getLockOpId)
            .set(lockReason).equalToWhenPresent(record::getLockReason)
            .set(lockDatetime).equalToWhenPresent(record::getLockDatetime)
            .set(unlockReason).equalToWhenPresent(record::getUnlockReason)
            .set(unlockDatetime).equalToWhenPresent(record::getUnlockDatetime)
            .set(unlockOpId).equalToWhenPresent(record::getUnlockOpId)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacLockLogMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(lockUserId, isEqualToWhenPresent(record::getLockUserId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacLockLogMo> selectOne(RacLockLogMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(lockUserId, isEqualToWhenPresent(record::getLockUserId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacLockLogMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(lockUserId, isEqualToWhenPresent(record::getLockUserId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
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
    default boolean existSelective(RacLockLogMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacLockLogMo> selectSelective(RacLockLogMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(lockUserId, isEqualToWhenPresent(record::getLockUserId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
        );
    }
}