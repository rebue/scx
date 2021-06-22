package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.accountId;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.orgId;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.racOrgAccount;

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
import rebue.scx.rac.mo.RacOrgAccountMo;

@Mapper
public interface RacOrgAccountMapper extends MapperRootInterface<RacOrgAccountMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, orgId, accountId);

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
    int insert(InsertStatementProvider<RacOrgAccountMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacOrgAccountMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacOrgAccountMoResult")
    Optional<RacOrgAccountMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacOrgAccountMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "ORG_ID", property = "orgId", jdbcType = JdbcType.BIGINT), @Result(column = "ACCOUNT_ID", property = "accountId", jdbcType = JdbcType.BIGINT)
    })
    List<RacOrgAccountMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racOrgAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racOrgAccount, completer);
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
    default int insert(RacOrgAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOrgAccount, c -> c.map(id).toProperty("id").map(orgId).toProperty("orgId").map(accountId).toProperty("accountId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacOrgAccountMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racOrgAccount,
            c -> c.map(id).toProperty("id").map(orgId).toProperty("orgId").map(accountId).toProperty("accountId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacOrgAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOrgAccount, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(orgId)
            .toPropertyWhenPresent("orgId", record::getOrgId).map(accountId).toPropertyWhenPresent("accountId", record::getAccountId));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgAccountMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racOrgAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgAccountMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racOrgAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgAccountMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racOrgAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgAccountMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racOrgAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacOrgAccountMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(orgId).equalTo(record::getOrgId).set(accountId).equalTo(record::getAccountId);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacOrgAccountMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(orgId).equalToWhenPresent(record::getOrgId).set(accountId).equalToWhenPresent(record::getAccountId);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacOrgAccountMo record) {
        return update(c -> c.set(orgId).equalTo(record::getOrgId).set(accountId).equalTo(record::getAccountId).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacOrgAccountMo record) {
        return update(c -> c.set(orgId).equalToWhenPresent(record::getOrgId).set(accountId).equalToWhenPresent(record::getAccountId).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacOrgAccountMo record) {
        return delete(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(orgId, isEqualToWhenPresent(record::getOrgId)).and(accountId, isEqualToWhenPresent(record::getAccountId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgAccountMo> selectOne(RacOrgAccountMo record) {
        return selectOne(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(orgId, isEqualToWhenPresent(record::getOrgId)).and(accountId, isEqualToWhenPresent(record::getAccountId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacOrgAccountMo record) {
        return count(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(orgId, isEqualToWhenPresent(record::getOrgId)).and(accountId, isEqualToWhenPresent(record::getAccountId)));
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
    default boolean existSelective(RacOrgAccountMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgAccountMo> selectSelective(RacOrgAccountMo record) {
        return select(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(orgId, isEqualToWhenPresent(record::getOrgId)).and(accountId, isEqualToWhenPresent(record::getAccountId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgAccountMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }
}
