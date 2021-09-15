package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.accountId;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.disableDatetime;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.disableOpAgentId;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.disableOpId;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.disableReason;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.enableDatetime;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.enableOpAgentId;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.enableOpId;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.enableReason;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.racDisableLog;
import static rebue.scx.rac.mapper.RacDisableLogDynamicSqlSupport.realmId;

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
import rebue.scx.rac.mo.RacDisableLogMo;
import rebue.scx.rac.mo.ex.RacDisableLogExMo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.RacDisableLogPageTo;

@Mapper
public interface RacDisableLogMapper extends MapperRootInterface<RacDisableLogMo, Long> {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, realmId, disableOpAgentId, enableOpAgentId, enableOpId, accountId, disableOpId, disableReason, disableDatetime,
            enableReason, enableDatetime);

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
    int insert(InsertStatementProvider<RacDisableLogMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDisableLogMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacDisableLogMoResult")
    Optional<RacDisableLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacDisableLogMoResult", value = {
            @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DISABLE_OP_AGENT_ID", property = "disableOpAgentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "ENABLE_OP_AGENT_ID", property = "enableOpAgentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "ENABLE_OP_ID", property = "enableOpId", jdbcType = JdbcType.BIGINT),
            @Result(column = "ACCOUNT_ID", property = "accountId", jdbcType = JdbcType.BIGINT),
            @Result(column = "DISABLE_OP_ID", property = "disableOpId", jdbcType = JdbcType.BIGINT),
            @Result(column = "DISABLE_REASON", property = "disableReason", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DISABLE_DATETIME", property = "disableDatetime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "ENABLE_REASON", property = "enableReason", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ENABLE_DATETIME", property = "enableDatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RacDisableLogMo> selectMany(SelectStatementProvider selectStatement);

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
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDisableLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDisableLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insert(RacDisableLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDisableLog, c -> c.map(id).toProperty("id")
                .map(realmId).toProperty("realmId")
                .map(disableOpAgentId).toProperty("disableOpAgentId")
                .map(enableOpAgentId).toProperty("enableOpAgentId")
                .map(enableOpId).toProperty("enableOpId")
                .map(accountId).toProperty("accountId")
                .map(disableOpId).toProperty("disableOpId")
                .map(disableReason).toProperty("disableReason")
                .map(disableDatetime).toProperty("disableDatetime")
                .map(enableReason).toProperty("enableReason")
                .map(enableDatetime).toProperty("enableDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(Collection<RacDisableLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDisableLog, c -> c.map(id).toProperty("id")
                .map(realmId).toProperty("realmId")
                .map(disableOpAgentId).toProperty("disableOpAgentId")
                .map(enableOpAgentId).toProperty("enableOpAgentId")
                .map(enableOpId).toProperty("enableOpId")
                .map(accountId).toProperty("accountId")
                .map(disableOpId).toProperty("disableOpId")
                .map(disableReason).toProperty("disableReason")
                .map(disableDatetime).toProperty("disableDatetime")
                .map(enableReason).toProperty("enableReason")
                .map(enableDatetime).toProperty("enableDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(RacDisableLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDisableLog, c -> c.map(id).toPropertyWhenPresent("id", record::getId)
                .map(realmId).toPropertyWhenPresent("realmId", record::getRealmId)
                .map(disableOpAgentId).toPropertyWhenPresent("disableOpAgentId", record::getDisableOpAgentId)
                .map(enableOpAgentId).toPropertyWhenPresent("enableOpAgentId", record::getEnableOpAgentId)
                .map(enableOpId).toPropertyWhenPresent("enableOpId", record::getEnableOpId)
                .map(accountId).toPropertyWhenPresent("accountId", record::getAccountId)
                .map(disableOpId).toPropertyWhenPresent("disableOpId", record::getDisableOpId)
                .map(disableReason).toPropertyWhenPresent("disableReason", record::getDisableReason)
                .map(disableDatetime).toPropertyWhenPresent("disableDatetime", record::getDisableDatetime)
                .map(enableReason).toPropertyWhenPresent("enableReason", record::getEnableReason)
                .map(enableDatetime).toPropertyWhenPresent("enableDatetime", record::getEnableDatetime));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDisableLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDisableLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDisableLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDisableLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDisableLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDisableLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDisableLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDisableLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacDisableLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(realmId).equalTo(record::getRealmId)
                .set(disableOpAgentId).equalTo(record::getDisableOpAgentId)
                .set(enableOpAgentId).equalTo(record::getEnableOpAgentId)
                .set(enableOpId).equalTo(record::getEnableOpId)
                .set(accountId).equalTo(record::getAccountId)
                .set(disableOpId).equalTo(record::getDisableOpId)
                .set(disableReason).equalTo(record::getDisableReason)
                .set(disableDatetime).equalTo(record::getDisableDatetime)
                .set(enableReason).equalTo(record::getEnableReason)
                .set(enableDatetime).equalTo(record::getEnableDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacDisableLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(realmId).equalToWhenPresent(record::getRealmId)
                .set(disableOpAgentId).equalToWhenPresent(record::getDisableOpAgentId)
                .set(enableOpAgentId).equalToWhenPresent(record::getEnableOpAgentId)
                .set(enableOpId).equalToWhenPresent(record::getEnableOpId)
                .set(accountId).equalToWhenPresent(record::getAccountId)
                .set(disableOpId).equalToWhenPresent(record::getDisableOpId)
                .set(disableReason).equalToWhenPresent(record::getDisableReason)
                .set(disableDatetime).equalToWhenPresent(record::getDisableDatetime)
                .set(enableReason).equalToWhenPresent(record::getEnableReason)
                .set(enableDatetime).equalToWhenPresent(record::getEnableDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(RacDisableLogMo record) {
        return update(c -> c.set(realmId).equalTo(record::getRealmId)
                .set(disableOpAgentId).equalTo(record::getDisableOpAgentId)
                .set(enableOpAgentId).equalTo(record::getEnableOpAgentId)
                .set(enableOpId).equalTo(record::getEnableOpId)
                .set(accountId).equalTo(record::getAccountId)
                .set(disableOpId).equalTo(record::getDisableOpId)
                .set(disableReason).equalTo(record::getDisableReason)
                .set(disableDatetime).equalTo(record::getDisableDatetime)
                .set(enableReason).equalTo(record::getEnableReason)
                .set(enableDatetime).equalTo(record::getEnableDatetime)
                .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(RacDisableLogMo record) {
        return update(c -> c.set(realmId).equalToWhenPresent(record::getRealmId)
                .set(disableOpAgentId).equalToWhenPresent(record::getDisableOpAgentId)
                .set(enableOpAgentId).equalToWhenPresent(record::getEnableOpAgentId)
                .set(enableOpId).equalToWhenPresent(record::getEnableOpId)
                .set(accountId).equalToWhenPresent(record::getAccountId)
                .set(disableOpId).equalToWhenPresent(record::getDisableOpId)
                .set(disableReason).equalToWhenPresent(record::getDisableReason)
                .set(disableDatetime).equalToWhenPresent(record::getDisableDatetime)
                .set(enableReason).equalToWhenPresent(record::getEnableReason)
                .set(enableDatetime).equalToWhenPresent(record::getEnableDatetime)
                .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default BasicColumn[] getColumns() {
        return selectList;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(RacDisableLogMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId))
                .and(realmId, isEqualToWhenPresent(record::getRealmId))
                .and(disableOpAgentId, isEqualToWhenPresent(record::getDisableOpAgentId))
                .and(enableOpAgentId, isEqualToWhenPresent(record::getEnableOpAgentId))
                .and(enableOpId, isEqualToWhenPresent(record::getEnableOpId))
                .and(accountId, isEqualToWhenPresent(record::getAccountId))
                .and(disableOpId, isEqualToWhenPresent(record::getDisableOpId))
                .and(disableReason, isEqualToWhenPresent(record::getDisableReason))
                .and(disableDatetime, isEqualToWhenPresent(record::getDisableDatetime))
                .and(enableReason, isEqualToWhenPresent(record::getEnableReason))
                .and(enableDatetime, isEqualToWhenPresent(record::getEnableDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDisableLogMo> selectOne(RacDisableLogMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId))
                .and(realmId, isEqualToWhenPresent(record::getRealmId))
                .and(disableOpAgentId, isEqualToWhenPresent(record::getDisableOpAgentId))
                .and(enableOpAgentId, isEqualToWhenPresent(record::getEnableOpAgentId))
                .and(enableOpId, isEqualToWhenPresent(record::getEnableOpId))
                .and(accountId, isEqualToWhenPresent(record::getAccountId))
                .and(disableOpId, isEqualToWhenPresent(record::getDisableOpId))
                .and(disableReason, isEqualToWhenPresent(record::getDisableReason))
                .and(disableDatetime, isEqualToWhenPresent(record::getDisableDatetime))
                .and(enableReason, isEqualToWhenPresent(record::getEnableReason))
                .and(enableDatetime, isEqualToWhenPresent(record::getEnableDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(RacDisableLogMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId))
                .and(realmId, isEqualToWhenPresent(record::getRealmId))
                .and(disableOpAgentId, isEqualToWhenPresent(record::getDisableOpAgentId))
                .and(enableOpAgentId, isEqualToWhenPresent(record::getEnableOpAgentId))
                .and(enableOpId, isEqualToWhenPresent(record::getEnableOpId))
                .and(accountId, isEqualToWhenPresent(record::getAccountId))
                .and(disableOpId, isEqualToWhenPresent(record::getDisableOpId))
                .and(disableReason, isEqualToWhenPresent(record::getDisableReason))
                .and(disableDatetime, isEqualToWhenPresent(record::getDisableDatetime))
                .and(enableReason, isEqualToWhenPresent(record::getEnableReason))
                .and(enableDatetime, isEqualToWhenPresent(record::getEnableDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existByPrimaryKey(Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existSelective(RacDisableLogMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDisableLogMo> selectSelective(RacDisableLogMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId))
                .and(realmId, isEqualToWhenPresent(record::getRealmId))
                .and(disableOpAgentId, isEqualToWhenPresent(record::getDisableOpAgentId))
                .and(enableOpAgentId, isEqualToWhenPresent(record::getEnableOpAgentId))
                .and(enableOpId, isEqualToWhenPresent(record::getEnableOpId))
                .and(accountId, isEqualToWhenPresent(record::getAccountId))
                .and(disableOpId, isEqualToWhenPresent(record::getDisableOpId))
                .and(disableReason, isEqualToWhenPresent(record::getDisableReason))
                .and(disableDatetime, isEqualToWhenPresent(record::getDisableDatetime))
                .and(enableReason, isEqualToWhenPresent(record::getEnableReason))
                .and(enableDatetime, isEqualToWhenPresent(record::getEnableDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDisableLogMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * 启用日志
     *
     * @param record
     *
     * @return
     */
    @Update("update RAC_DISABLE_LOG lo set lo.ENABLE_REASON=#{record.enableReason}, lo.ENABLE_DATETIME=#{record.enableDatetime}, lo.ENABLE_OP_ID=#{record.enableOpId}  "
            + " where lo.ACCOUNT_ID=#{record.accountId} and lo.ENABLE_OP_ID is null  " + " order by  lo.DISABLE_DATETIME desc limit 1")
    int updateDisableLogEx(@Param(value = "record") RacDisableLogModifyTo qo);

    /**
     * 查询日志/条件/分页
     *
     * @param record
     *
     * @return
     */
    @Select({ "<script>"
            + "SELECT lo.*, a.SIGN_IN_NAME signInName, a.SIGN_IN_MOBILE signInMobile, a.SIGN_IN_EMAIL signInEmail, a.WX_NICKNAME wxNickname, a.QQ_NICKNAME qqNickname, a.SIGN_IN_NICKNAME signInNickname,"
            + "  b.SIGN_IN_NAME disableSignInName, b.SIGN_IN_MOBILE disableSignInMobile, b.SIGN_IN_EMAIL disableSignInEmail, b.WX_NICKNAME disablewxNickname, b.QQ_NICKNAME disableqqNickname, b.SIGN_IN_NICKNAME disableSignInNickname,"
            + "  c.SIGN_IN_NAME enableSignInName, c.SIGN_IN_MOBILE enableSignInMobile, c.SIGN_IN_EMAIL enableSignInEmail, c.WX_NICKNAME enablewxNickname, c.QQ_NICKNAME enableqqNickname, c.SIGN_IN_NICKNAME enableSignInNickname, "
            + "  d.SIGN_IN_NAME disableAgentSignInName, d.SIGN_IN_MOBILE disableAgentSignInMobile, d.SIGN_IN_EMAIL disableAgentSignInEmail, d.WX_NICKNAME disableAgentwxNickname, d.QQ_NICKNAME disableAgentqqNickname, d.SIGN_IN_NICKNAME disableAgentSignInNickname, "
            + "  e.SIGN_IN_NAME enableAgentSignInName, e.SIGN_IN_MOBILE enableAgentSignInMobile, e.SIGN_IN_EMAIL enableAgentSignInEmail, e.WX_NICKNAME enableAgentwxNickname, e.QQ_NICKNAME enableAgentqqNickname, e.SIGN_IN_NICKNAME enableAgentSignInNickname "
            + "  FROM RAC_DISABLE_LOG lo " + "  left join RAC_ACCOUNT a ON lo.ACCOUNT_ID=a.ID " + "  left join RAC_ACCOUNT b ON lo.DISABLE_OP_ID=b.ID "
            + "  left join RAC_ACCOUNT c ON lo.ENABLE_OP_ID=c.ID " + "  left join RAC_ACCOUNT d ON lo.DISABLE_OP_AGENT_ID=d.ID "
            + "  left join RAC_ACCOUNT e ON lo.ENABLE_OP_AGENT_ID=e.ID " + "  where 1=1 and a.realm_Id=#{record.realmId} " + "<if test='record.keywords!=null'> " + "  and ("
            + "    a.ID like '%${record.keywords}%' or a.SIGN_IN_NAME like '%${record.keywords}%' or a.SIGN_IN_MOBILE like '%${record.keywords}%' or a.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or a.WX_NICKNAME like '%${record.keywords}%' or a.QQ_NICKNAME like '%${record.keywords}%' or a.SIGN_IN_NICKNAME like '%${record.keywords}%' "
            + " or b.ID like '%${record.keywords}%' or b.SIGN_IN_NAME like '%${record.keywords}%' or b.SIGN_IN_MOBILE like '%${record.keywords}%' or b.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or b.WX_NICKNAME like '%${record.keywords}%' or b.QQ_NICKNAME like '%${record.keywords}%' or b.SIGN_IN_NICKNAME like '%${record.keywords}%' "
            + " or c.ID like '%${record.keywords}%' or c.SIGN_IN_NAME like '%${record.keywords}%' or c.SIGN_IN_MOBILE like '%${record.keywords}%' or c.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or c.WX_NICKNAME like '%${record.keywords}%' or c.QQ_NICKNAME like '%${record.keywords}%' or c.SIGN_IN_NICKNAME like '%${record.keywords}%' "
            + " or d.ID like '%${record.keywords}%' or d.SIGN_IN_NAME like '%${record.keywords}%' or d.SIGN_IN_MOBILE like '%${record.keywords}%' or d.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or d.WX_NICKNAME like '%${record.keywords}%' or d.QQ_NICKNAME like '%${record.keywords}%' or d.SIGN_IN_NICKNAME like '%${record.keywords}%' "
            + " or e.ID like '%${record.keywords}%' or e.SIGN_IN_NAME like '%${record.keywords}%' or e.SIGN_IN_MOBILE like '%${record.keywords}%' or e.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or e.WX_NICKNAME like '%${record.keywords}%' or e.QQ_NICKNAME like '%${record.keywords}%' or e.SIGN_IN_NICKNAME like '%${record.keywords}%' " + "   ) </if>"
            + "<if test='record.startDate!=null and record.endDate!=null'>"
            + "  and (lo.DISABLE_DATETIME between  '${record.startDate}' and  '${record.endDate}'  or lo.ENABLE_DATETIME between '${record.startDate}' and  '${record.endDate}') </if>"
            + "</script>"
    })
    List<RacDisableLogExMo> selectEx(@Param(value = "record") RacDisableLogPageTo qo);
}