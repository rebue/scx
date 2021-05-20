package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.domainId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockAccountId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockDatetime;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockOpId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockReason;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.racLockLog;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.unlockDatetime;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.unlockOpId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.unlockReason;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
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
import rebue.scx.rac.mo.Ex.RacLockLogExMo;
import rebue.scx.rac.to.RacLockLogPageTo;

@Mapper
public interface RacLockLogMapper extends MapperRootInterface<RacLockLogMo, Long> {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, domainId, lockAccountId, lockOpId, lockReason, lockDatetime, unlockReason, unlockDatetime, unlockOpId);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RacLockLogMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacLockLogMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacLockLogMoResult")
    Optional<RacLockLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacLockLogMoResult", value = {
        @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "DOMAIN_ID", property = "domainId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOCK_ACCOUNT_ID", property = "lockAccountId", jdbcType = JdbcType.BIGINT),
        @Result(column = "LOCK_OP_ID", property = "lockOpId", jdbcType = JdbcType.BIGINT),
        @Result(column = "LOCK_REASON", property = "lockReason", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOCK_DATETIME", property = "lockDatetime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "UNLOCK_REASON", property = "unlockReason", jdbcType = JdbcType.VARCHAR),
        @Result(column = "UNLOCK_DATETIME", property = "unlockDatetime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "UNLOCK_OP_ID", property = "unlockOpId", jdbcType = JdbcType.BIGINT)
    })
    List<RacLockLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long count(final CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racLockLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(final DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racLockLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteByPrimaryKey(final Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insert(final RacLockLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racLockLog, c -> c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(lockAccountId).toProperty("lockAccountId")
            .map(lockOpId).toProperty("lockOpId")
            .map(lockReason).toProperty("lockReason")
            .map(lockDatetime).toProperty("lockDatetime")
            .map(unlockReason).toProperty("unlockReason")
            .map(unlockDatetime).toProperty("unlockDatetime")
            .map(unlockOpId).toProperty("unlockOpId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(final Collection<RacLockLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racLockLog, c -> c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(lockAccountId).toProperty("lockAccountId")
            .map(lockOpId).toProperty("lockOpId")
            .map(lockReason).toProperty("lockReason")
            .map(lockDatetime).toProperty("lockDatetime")
            .map(unlockReason).toProperty("unlockReason")
            .map(unlockDatetime).toProperty("unlockDatetime")
            .map(unlockOpId).toProperty("unlockOpId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(final RacLockLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racLockLog, c -> c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(domainId).toPropertyWhenPresent("domainId", record::getDomainId)
            .map(lockAccountId).toPropertyWhenPresent("lockAccountId", record::getLockAccountId)
            .map(lockOpId).toPropertyWhenPresent("lockOpId", record::getLockOpId)
            .map(lockReason).toPropertyWhenPresent("lockReason", record::getLockReason)
            .map(lockDatetime).toPropertyWhenPresent("lockDatetime", record::getLockDatetime)
            .map(unlockReason).toPropertyWhenPresent("unlockReason", record::getUnlockReason)
            .map(unlockDatetime).toPropertyWhenPresent("unlockDatetime", record::getUnlockDatetime)
            .map(unlockOpId).toPropertyWhenPresent("unlockOpId", record::getUnlockOpId));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacLockLogMo> selectOne(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racLockLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacLockLogMo> select(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racLockLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacLockLogMo> selectDistinct(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racLockLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacLockLogMo> selectByPrimaryKey(final Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(final UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racLockLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(final RacLockLogMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
            .set(domainId).equalTo(record::getDomainId)
            .set(lockAccountId).equalTo(record::getLockAccountId)
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
    static UpdateDSL<UpdateModel> updateSelectiveColumns(final RacLockLogMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
            .set(domainId).equalToWhenPresent(record::getDomainId)
            .set(lockAccountId).equalToWhenPresent(record::getLockAccountId)
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
    @Override
    default int updateByPrimaryKey(final RacLockLogMo record) {
        return update(c -> c.set(domainId).equalTo(record::getDomainId)
            .set(lockAccountId).equalTo(record::getLockAccountId)
            .set(lockOpId).equalTo(record::getLockOpId)
            .set(lockReason).equalTo(record::getLockReason)
            .set(lockDatetime).equalTo(record::getLockDatetime)
            .set(unlockReason).equalTo(record::getUnlockReason)
            .set(unlockDatetime).equalTo(record::getUnlockDatetime)
            .set(unlockOpId).equalTo(record::getUnlockOpId)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(final RacLockLogMo record) {
        return update(c -> c.set(domainId).equalToWhenPresent(record::getDomainId)
            .set(lockAccountId).equalToWhenPresent(record::getLockAccountId)
            .set(lockOpId).equalToWhenPresent(record::getLockOpId)
            .set(lockReason).equalToWhenPresent(record::getLockReason)
            .set(lockDatetime).equalToWhenPresent(record::getLockDatetime)
            .set(unlockReason).equalToWhenPresent(record::getUnlockReason)
            .set(unlockDatetime).equalToWhenPresent(record::getUnlockDatetime)
            .set(unlockOpId).equalToWhenPresent(record::getUnlockOpId)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(final RacLockLogMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacLockLogMo> selectOne(final RacLockLogMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(final RacLockLogMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existByPrimaryKey(final Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existSelective(final RacLockLogMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacLockLogMo> selectSelective(final RacLockLogMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId))
            .and(lockOpId, isEqualToWhenPresent(record::getLockOpId))
            .and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime))
            .and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime))
            .and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId)));
    }

    /**
     * 解锁日志
     *
     * @param record
     *
     * @return
     */
    @Update("update RAC_LOCK_LOG lo set lo.unlock_reason=#{record.unlockReason}, lo.unlock_datetime=#{record.unlockDatetime}, lo.unlock_op_id=#{record.unlockOpId}  "
        + " where lo.lock_account_id=#{record.lockAccountId} and lo.unlock_op_id is null  "
        + " order by  lo.lock_datetime desc limit 1")
    int updateByPrimaryKeySelectEx(@Param(value = "record") RacLockLogMo record);

    /**
     * 查询日志/条件/分页
     *
     * @param record
     *
     * @return
     */
    @Select({ "<script>"
        + "SELECT lo.*, a.SIGN_IN_NAME signInName,a.SIGN_IN_MOBILE signInMobile, a.SIGN_IN_EMAIL signInEmail, a.WX_NICKNAME wxNickname, a.QQ_NICKNAME qqNickname, a.SIGN_IN_NICKNAME signInNickname,"
        + "  b.SIGN_IN_NAME locksignInName,b.SIGN_IN_MOBILE locksignInMobile, b.SIGN_IN_EMAIL locksignInEmail, b.WX_NICKNAME lockwxNickname, b.QQ_NICKNAME lockqqNickname, b.SIGN_IN_NICKNAME locksignInNickname,"
        + "  c.SIGN_IN_NAME unlocksignInName,c.SIGN_IN_MOBILE unlocksignInMobile, c.SIGN_IN_EMAIL unlocksignInEmail, c.WX_NICKNAME unlockwxNickname, c.QQ_NICKNAME unlockqqNickname, c.SIGN_IN_NICKNAME unlocksignInNickname "
        + "  FROM RAC_LOCK_LOG lo " + "  left join RAC_ACCOUNT a ON lo.LOCK_ACCOUNT_ID=a.ID "
        + "  left join RAC_ACCOUNT b ON lo.LOCK_OP_ID=b.ID " 
        + "  left join RAC_ACCOUNT c ON lo.UNLOCK_OP_ID=c.ID " + "  where 1=1 and a.domain_Id=#{record.domainId} "
        + "<if test='record.keywords!=null'> "
        + "  and (a.SIGN_IN_NAME like '%${record.keywords}%' or b.SIGN_IN_NAME like '%${record.keywords}%' or c.SIGN_IN_NAME like '%${record.keywords}%') </if>"
        + "<if test='record.startDate!=null and record.endDate!=null'>"
        + "  and (lo.LOCK_DATETIME between  '${record.startDate}' and  '${record.endDate}'  or lo.UNLOCK_DATETIME between '${record.startDate}' and  '${record.endDate}') </if>"
        + "</script>"
    })
    List<RacLockLogExMo> selectEx(@Param(value = "record") RacLockLogPageTo record);
}