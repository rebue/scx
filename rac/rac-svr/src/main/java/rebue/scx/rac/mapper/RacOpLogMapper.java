package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.accountId;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.agentId;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.opDatetime;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.opDetail;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.opTitle;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.opType;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.racOpLog;
import static rebue.scx.rac.mapper.RacOpLogDynamicSqlSupport.sysId;

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
import rebue.scx.rac.mo.Ex.RacOpLogExMo;
import rebue.scx.rac.to.RacOpLogPageTo;

@Mapper
public interface RacOpLogMapper extends MapperRootInterface<RacOpLogMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, sysId, accountId, agentId, opType, opTitle, opDetail, opDatetime);

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
    int insert(InsertStatementProvider<RacOpLogMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacOpLogMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacOpLogMoResult")
    Optional<RacOpLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacOpLogMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "SYS_ID", property = "sysId", jdbcType = JdbcType.VARCHAR), @Result(column = "ACCOUNT_ID", property = "accountId", jdbcType = JdbcType.BIGINT),
            @Result(column = "AGENT_ID", property = "agentId", jdbcType = JdbcType.BIGINT), @Result(column = "OP_TYPE", property = "opType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "OP_TITLE", property = "opTitle", jdbcType = JdbcType.VARCHAR), @Result(column = "OP_DETAIL", property = "opDetail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "OP_DATETIME", property = "opDatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RacOpLogMo> selectMany(SelectStatementProvider selectStatement);

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
        return MyBatis3Utils.countFrom(this::count, racOpLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(final DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racOpLog, completer);
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
    default int insert(final RacOpLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOpLog,
                c -> c.map(id).toProperty("id").map(sysId).toProperty("sysId").map(accountId).toProperty("accountId").map(agentId).toProperty("agentId").map(opType)
                        .toProperty("opType").map(opTitle).toProperty("opTitle").map(opDetail).toProperty("opDetail").map(opDatetime).toProperty("opDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(final Collection<RacOpLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racOpLog,
                c -> c.map(id).toProperty("id").map(sysId).toProperty("sysId").map(accountId).toProperty("accountId").map(agentId).toProperty("agentId").map(opType)
                        .toProperty("opType").map(opTitle).toProperty("opTitle").map(opDetail).toProperty("opDetail").map(opDatetime).toProperty("opDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(final RacOpLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOpLog,
                c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(sysId).toPropertyWhenPresent("sysId", record::getSysId).map(accountId)
                        .toPropertyWhenPresent("accountId", record::getAccountId).map(agentId).toPropertyWhenPresent("agentId", record::getAgentId).map(opType)
                        .toPropertyWhenPresent("opType", record::getOpType).map(opTitle).toPropertyWhenPresent("opTitle", record::getOpTitle).map(opDetail)
                        .toPropertyWhenPresent("opDetail", record::getOpDetail).map(opDatetime).toPropertyWhenPresent("opDatetime", record::getOpDatetime));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacOpLogMo> selectOne(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racOpLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacOpLogMo> select(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racOpLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacOpLogMo> selectDistinct(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racOpLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacOpLogMo> selectByPrimaryKey(final Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(final UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racOpLog, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(final RacOpLogMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(sysId).equalTo(record::getSysId).set(accountId).equalTo(record::getAccountId).set(agentId).equalTo(record::getAgentId)
                .set(opType).equalTo(record::getOpType).set(opTitle).equalTo(record::getOpTitle).set(opDetail).equalTo(record::getOpDetail).set(opDatetime)
                .equalTo(record::getOpDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(final RacOpLogMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(sysId).equalToWhenPresent(record::getSysId).set(accountId).equalToWhenPresent(record::getAccountId).set(agentId)
                .equalToWhenPresent(record::getAgentId).set(opType).equalToWhenPresent(record::getOpType).set(opTitle).equalToWhenPresent(record::getOpTitle).set(opDetail)
                .equalToWhenPresent(record::getOpDetail).set(opDatetime).equalToWhenPresent(record::getOpDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(final RacOpLogMo record) {
        return update(c -> c.set(sysId).equalTo(record::getSysId).set(accountId).equalTo(record::getAccountId).set(agentId).equalTo(record::getAgentId).set(opType)
                .equalTo(record::getOpType).set(opTitle).equalTo(record::getOpTitle).set(opDetail).equalTo(record::getOpDetail).set(opDatetime).equalTo(record::getOpDatetime)
                .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(final RacOpLogMo record) {
        return update(c -> c.set(sysId).equalToWhenPresent(record::getSysId).set(accountId).equalToWhenPresent(record::getAccountId).set(agentId)
                .equalToWhenPresent(record::getAgentId).set(opType).equalToWhenPresent(record::getOpType).set(opTitle).equalToWhenPresent(record::getOpTitle).set(opDetail)
                .equalToWhenPresent(record::getOpDetail).set(opDatetime).equalToWhenPresent(record::getOpDatetime).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(final RacOpLogMo record) {
        return delete(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
                        .and(agentId, isEqualToWhenPresent(record::getAgentId)).and(opType, isEqualToWhenPresent(record::getOpType))
                        .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
                        .and(opDetail, isEqualToWhenPresent(record::getOpDetail)).and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacOpLogMo> selectOne(final RacOpLogMo record) {
        return selectOne(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
                        .and(agentId, isEqualToWhenPresent(record::getAgentId)).and(opType, isEqualToWhenPresent(record::getOpType))
                        .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
                        .and(opDetail, isEqualToWhenPresent(record::getOpDetail)).and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(final RacOpLogMo record) {
        return count(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
                        .and(agentId, isEqualToWhenPresent(record::getAgentId)).and(opType, isEqualToWhenPresent(record::getOpType))
                        .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
                        .and(opDetail, isEqualToWhenPresent(record::getOpDetail)).and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)));
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
    default boolean existSelective(final RacOpLogMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacOpLogMo> selectSelective(final RacOpLogMo record) {
        return select(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
                        .and(agentId, isEqualToWhenPresent(record::getAgentId)).and(opType, isEqualToWhenPresent(record::getOpType))
                        .and(opTitle, isEqualToWhenPresent(record::getOpTitle))
                        .and(opDetail, isEqualToWhenPresent(record::getOpDetail)).and(opDatetime, isEqualToWhenPresent(record::getOpDatetime)));
    }

    /**
     * 分页查询/条件分页查询
     *
     * @param record
     *
     * @return
     */
    @Select({ "<script>" + "SELECT op.*,a.SIGN_IN_NAME,a.SIGN_IN_MOBILE,a.SIGN_IN_EMAIL,a.WX_NICKNAME,a.QQ_NICKNAME,a.SIGN_IN_NICKNAME, "
            + " b.SIGN_IN_NAME agentSignInName, b.SIGN_IN_MOBILE agentSignInMobile, b.SIGN_IN_EMAIL agentSignInEmail, "
            + " b.WX_NICKNAME agentwxNickname, b.QQ_NICKNAME agentqqNickname, b.SIGN_IN_NICKNAME agentSignInNickname, "
            + " s.NAME sysName,s.MENU menu,s.DOMAIN_ID domainId, s.REMARK remark " + " FROM RAC_OP_LOG op " + " left join  RAC_ACCOUNT a on op.ACCOUNT_ID=a.ID "
            + " left join  RAC_ACCOUNT b on op.AGENT_ID=b.ID " + // + " where 1=1 and a.domain_Id=#{record.domainId} "
            " left join RAC_SYS s on op.sys_id=s.id " + " where a.domain_Id=#{record.domainId} " + "<if test='record.keywords!=null'> "
            + " and (a.ID like '%${record.keywords}%' or a.SIGN_IN_NAME like '%${record.keywords}%' or a.SIGN_IN_MOBILE like '%${record.keywords}%' or a.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or a.WX_NICKNAME like '%${record.keywords}%' or a.QQ_NICKNAME like '%${record.keywords}%' or a.SIGN_IN_NICKNAME like '%${record.keywords}%'  "
            + " or b.ID like '%${record.keywords}%' or b.SIGN_IN_NAME like '%${record.keywords}%' or b.SIGN_IN_MOBILE like '%${record.keywords}%' or b.SIGN_IN_EMAIL like '%${record.keywords}%' "
            + " or b.WX_NICKNAME like '%${record.keywords}%' or b.QQ_NICKNAME like '%${record.keywords}%' or b.SIGN_IN_NICKNAME like '%${record.keywords}%' "
            + " or op.OP_TITLE like '%${record.keywords}%' ) " + "</if> " + "<if test='record.startDate!=null and record.endDate!=null'>"
            + "  and op.OP_DATETIME between  '${record.startDate}' and  '${record.endDate}'  </if>" + "<if test='((record.opType!=null) and (record.opType.length>0))'> and "
            + "<foreach collection='record.opType' open='(' close= ')'  separator='or' item='otype'> " + " op.OP_TYPE like concat('%',#{otype},'%') " + "</foreach>" + "</if>"
            + "</script>"
    })
    List<RacOpLogExMo> selectEx(@Param(value = "record") RacOpLogPageTo record);
}
