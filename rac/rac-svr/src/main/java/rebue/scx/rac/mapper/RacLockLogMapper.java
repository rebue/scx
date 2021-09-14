package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.autoUnlockDatetime;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockAccountId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockDatetime;
// import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockOpAgentId;
// import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockOpId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.lockReason;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.racLockLog;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.unlockDatetime;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.unlockOpAgentId;
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
import rebue.scx.rac.mo.ex.RacLockLogExMo;
import rebue.scx.rac.to.RacLockLogPageTo;

@Mapper
public interface RacLockLogMapper extends MapperRootInterface<RacLockLogMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, realmId, lockAccountId, lockReason, lockDatetime, unlockReason, unlockDatetime, unlockOpId, unlockOpAgentId,
        autoUnlockDatetime);

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
    int insert(InsertStatementProvider<RacLockLogMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacLockLogMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacLockLogMoResult")
    Optional<RacLockLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacLockLogMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOCK_ACCOUNT_ID", property = "lockAccountId", jdbcType = JdbcType.BIGINT),
        @Result(column = "LOCK_REASON", property = "lockReason", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOCK_DATETIME", property = "lockDatetime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "UNLOCK_REASON", property = "unlockReason", jdbcType = JdbcType.VARCHAR),
        @Result(column = "UNLOCK_DATETIME", property = "unlockDatetime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "UNLOCK_OP_ID", property = "unlockOpId", jdbcType = JdbcType.BIGINT),
        @Result(column = "UNLOCK_OP_AGENT_ID", property = "unlockOpAgentId", jdbcType = JdbcType.BIGINT),
        @Result(column = "AUTO_UNLOCK_DATETIME", property = "autoUnlockDatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RacLockLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
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
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(RacLockLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racLockLog,
            c -> c.map(id).toProperty("id").map(realmId).toProperty("realmId").map(lockAccountId).toProperty("lockAccountId").map(lockReason).toProperty("lockReason")
                .map(lockDatetime).toProperty("lockDatetime").map(unlockReason).toProperty("unlockReason").map(unlockDatetime).toProperty("unlockDatetime").map(unlockOpId)
                .toProperty("unlockOpId").map(unlockOpAgentId).toProperty("unlockOpAgentId").map(autoUnlockDatetime).toProperty("autoUnlockDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacLockLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racLockLog,
            c -> c.map(id).toProperty("id").map(realmId).toProperty("realmId").map(lockAccountId).toProperty("lockAccountId").map(lockReason).toProperty("lockReason")
                .map(lockDatetime).toProperty("lockDatetime").map(unlockReason).toProperty("unlockReason").map(unlockDatetime).toProperty("unlockDatetime").map(unlockOpId)
                .toProperty("unlockOpId").map(unlockOpAgentId).toProperty("unlockOpAgentId").map(autoUnlockDatetime).toProperty("autoUnlockDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacLockLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racLockLog,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(realmId).toPropertyWhenPresent("realmId", record::getRealmId).map(lockAccountId)
                .toPropertyWhenPresent("lockAccountId", record::getLockAccountId).map(lockReason).toPropertyWhenPresent("lockReason", record::getLockReason).map(lockDatetime)
                .toPropertyWhenPresent("lockDatetime", record::getLockDatetime).map(unlockReason).toPropertyWhenPresent("unlockReason", record::getUnlockReason).map(unlockDatetime)
                .toPropertyWhenPresent("unlockDatetime", record::getUnlockDatetime).map(unlockOpId).toPropertyWhenPresent("unlockOpId", record::getUnlockOpId).map(unlockOpAgentId)
                .toPropertyWhenPresent("unlockOpAgentId", record::getUnlockOpAgentId).map(autoUnlockDatetime)
                .toPropertyWhenPresent("autoUnlockDatetime", record::getAutoUnlockDatetime));
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
        return selectOne(c -> c.where(id, isEqualTo(id_)));
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
        return dsl.set(id).equalTo(record::getId).set(realmId).equalTo(record::getRealmId).set(lockAccountId).equalTo(record::getLockAccountId).set(lockReason)
            .equalTo(record::getLockReason).set(lockDatetime).equalTo(record::getLockDatetime).set(unlockReason).equalTo(record::getUnlockReason).set(unlockDatetime)
            .equalTo(record::getUnlockDatetime).set(unlockOpId).equalTo(record::getUnlockOpId).set(unlockOpAgentId).equalTo(record::getUnlockOpAgentId).set(autoUnlockDatetime)
            .equalTo(record::getAutoUnlockDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacLockLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(realmId).equalToWhenPresent(record::getRealmId).set(lockAccountId).equalToWhenPresent(record::getLockAccountId)
            .set(lockReason).equalToWhenPresent(record::getLockReason).set(lockDatetime).equalToWhenPresent(record::getLockDatetime).set(unlockReason)
            .equalToWhenPresent(record::getUnlockReason).set(unlockDatetime).equalToWhenPresent(record::getUnlockDatetime).set(unlockOpId).equalToWhenPresent(record::getUnlockOpId)
            .set(unlockOpAgentId).equalToWhenPresent(record::getUnlockOpAgentId).set(autoUnlockDatetime).equalToWhenPresent(record::getAutoUnlockDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacLockLogMo record) {
        return update(c -> c.set(realmId).equalTo(record::getRealmId).set(lockAccountId).equalTo(record::getLockAccountId).set(lockReason).equalTo(record::getLockReason)
            .set(lockDatetime).equalTo(record::getLockDatetime).set(unlockReason).equalTo(record::getUnlockReason).set(unlockDatetime).equalTo(record::getUnlockDatetime)
            .set(unlockOpId).equalTo(record::getUnlockOpId).set(unlockOpAgentId).equalTo(record::getUnlockOpAgentId).set(autoUnlockDatetime).equalTo(record::getAutoUnlockDatetime)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacLockLogMo record) {
        return update(c -> c.set(realmId).equalToWhenPresent(record::getRealmId).set(lockAccountId).equalToWhenPresent(record::getLockAccountId).set(lockReason)
            .equalToWhenPresent(record::getLockReason).set(lockDatetime).equalToWhenPresent(record::getLockDatetime).set(unlockReason).equalToWhenPresent(record::getUnlockReason)
            .set(unlockDatetime).equalToWhenPresent(record::getUnlockDatetime).set(unlockOpId).equalToWhenPresent(record::getUnlockOpId).set(unlockOpAgentId)
            .equalToWhenPresent(record::getUnlockOpAgentId).set(autoUnlockDatetime).equalToWhenPresent(record::getAutoUnlockDatetime).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacLockLogMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId)).and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime)).and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime)).and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
            .and(unlockOpAgentId, isEqualToWhenPresent(record::getUnlockOpAgentId)).and(autoUnlockDatetime, isEqualToWhenPresent(record::getAutoUnlockDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacLockLogMo> selectOne(RacLockLogMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId)).and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime)).and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime)).and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
            .and(unlockOpAgentId, isEqualToWhenPresent(record::getUnlockOpAgentId)).and(autoUnlockDatetime, isEqualToWhenPresent(record::getAutoUnlockDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacLockLogMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId)).and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime)).and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime)).and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
            .and(unlockOpAgentId, isEqualToWhenPresent(record::getUnlockOpAgentId)).and(autoUnlockDatetime, isEqualToWhenPresent(record::getAutoUnlockDatetime)));
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
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(lockAccountId, isEqualToWhenPresent(record::getLockAccountId)).and(lockReason, isEqualToWhenPresent(record::getLockReason))
            .and(lockDatetime, isEqualToWhenPresent(record::getLockDatetime)).and(unlockReason, isEqualToWhenPresent(record::getUnlockReason))
            .and(unlockDatetime, isEqualToWhenPresent(record::getUnlockDatetime)).and(unlockOpId, isEqualToWhenPresent(record::getUnlockOpId))
            .and(unlockOpAgentId, isEqualToWhenPresent(record::getUnlockOpAgentId)).and(autoUnlockDatetime, isEqualToWhenPresent(record::getAutoUnlockDatetime)));
    }

    /**
     * 解锁日志
     *
     * @param record
     *
     * @return
     */
    @Update("update RAC_LOCK_LOG lo set lo.unlock_reason=#{record.unlockReason}, lo.unlock_datetime=#{record.unlockDatetime}, lo.unlock_op_id=#{record.unlockOpId}  "
        + " where lo.lock_account_id=#{record.lockAccountId} and lo.unlock_op_id is null  " + " order by  lo.lock_datetime desc limit 1")
    int updateUnLockOpLogEx(@Param(value = "record") RacLockLogMo record);

    /**
     * 查询日志/条件/分页
     *
     * @param record
     *
     * @return
     */
    @Select({ "<script>"
        + "SELECT lo.*, a.SIGN_IN_NAME signInName, a.SIGN_IN_MOBILE signInMobile, a.SIGN_IN_EMAIL signInEmail, a.WX_NICKNAME wxNickname, a.QQ_NICKNAME qqNickname, a.SIGN_IN_NICKNAME signInNickname,"
        + "  c.SIGN_IN_NAME unlockSignInName, c.SIGN_IN_MOBILE unlockSignInMobile, c.SIGN_IN_EMAIL unlockSignInEmail, c.WX_NICKNAME unlockwxNickname, c.QQ_NICKNAME unlockqqNickname, c.SIGN_IN_NICKNAME unlockSignInNickname, "
        + "  e.SIGN_IN_NAME unlockAgentSignInName, e.SIGN_IN_MOBILE unlockAgentSignInMobile, e.SIGN_IN_EMAIL unlockAgentSignInEmail, e.WX_NICKNAME unlockAgentwxNickname, e.QQ_NICKNAME unlockAgentqqNickname, e.SIGN_IN_NICKNAME unlockAgentSignInNickname "
        + "  FROM RAC_LOCK_LOG lo " + "  left join RAC_ACCOUNT a ON lo.LOCK_ACCOUNT_ID=a.ID " + "  left join RAC_ACCOUNT c ON lo.UNLOCK_OP_ID=c.ID "
        + "  left join RAC_ACCOUNT e ON lo.UNLOCK_OP_AGENT_ID=e.ID " + "  where 1=1 and a.realm_Id=#{record.realmId} " + "<if test='record.keywords!=null'> " + "  and ("
        + "    a.ID like '%${record.keywords}%' or a.SIGN_IN_NAME like '%${record.keywords}%' or a.SIGN_IN_MOBILE like '%${record.keywords}%' or a.SIGN_IN_EMAIL like '%${record.keywords}%' "
        + " or a.WX_NICKNAME like '%${record.keywords}%' or a.QQ_NICKNAME like '%${record.keywords}%' or a.SIGN_IN_NICKNAME like '%${record.keywords}%' "
        + " or c.ID like '%${record.keywords}%' or c.SIGN_IN_NAME like '%${record.keywords}%' or c.SIGN_IN_MOBILE like '%${record.keywords}%' or c.SIGN_IN_EMAIL like '%${record.keywords}%' "
        + " or c.WX_NICKNAME like '%${record.keywords}%' or c.QQ_NICKNAME like '%${record.keywords}%' or c.SIGN_IN_NICKNAME like '%${record.keywords}%' "
        + " or e.ID like '%${record.keywords}%' or e.SIGN_IN_NAME like '%${record.keywords}%' or e.SIGN_IN_MOBILE like '%${record.keywords}%' or e.SIGN_IN_EMAIL like '%${record.keywords}%' "
        + " or e.WX_NICKNAME like '%${record.keywords}%' or e.QQ_NICKNAME like '%${record.keywords}%' or e.SIGN_IN_NICKNAME like '%${record.keywords}%' " + "   ) </if>"
        + "<if test='record.startDate!=null and record.endDate!=null'>"
        + "  and (lo.LOCK_DATETIME between  '${record.startDate}' and  '${record.endDate}'  or lo.UNLOCK_DATETIME between '${record.startDate}' and  '${record.endDate}') </if>"
        + "</script>"
    })
    List<RacLockLogExMo> selectEx(@Param(value = "record") RacLockLogPageTo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacLockLogMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }
}
