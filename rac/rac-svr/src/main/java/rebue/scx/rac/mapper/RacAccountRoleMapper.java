package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.accountId;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.racAccountRole;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.roleId;

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
import rebue.scx.rac.mo.RacAccountRoleMo;

@Mapper
public interface RacAccountRoleMapper extends MapperRootInterface<RacAccountRoleMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, roleId, accountId);

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
    int insert(InsertStatementProvider<RacAccountRoleMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacAccountRoleMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacAccountRoleMoResult")
    Optional<RacAccountRoleMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacAccountRoleMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "ROLE_ID", property = "roleId", jdbcType = JdbcType.BIGINT), @Result(column = "ACCOUNT_ID", property = "accountId", jdbcType = JdbcType.BIGINT)
    })
    List<RacAccountRoleMo> selectMany(SelectStatementProvider selectStatement);

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
        return MyBatis3Utils.countFrom(this::count, racAccountRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racAccountRole, completer);
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
    default int insert(RacAccountRoleMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccountRole, c -> c.map(id).toProperty("id").map(roleId).toProperty("roleId").map(accountId).toProperty("accountId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(Collection<RacAccountRoleMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racAccountRole,
                c -> c.map(id).toProperty("id").map(roleId).toProperty("roleId").map(accountId).toProperty("accountId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(RacAccountRoleMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccountRole, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(roleId)
                .toPropertyWhenPresent("roleId", record::getRoleId).map(accountId).toPropertyWhenPresent("accountId", record::getAccountId));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行O
     */
    @Override
    default Optional<RacAccountRoleMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racAccountRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountRoleMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racAccountRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountRoleMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racAccountRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountRoleMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racAccountRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacAccountRoleMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(roleId).equalTo(record::getRoleId).set(accountId).equalTo(record::getAccountId);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacAccountRoleMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(roleId).equalToWhenPresent(record::getRoleId).set(accountId).equalToWhenPresent(record::getAccountId);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(RacAccountRoleMo record) {
        return update(c -> c.set(roleId).equalTo(record::getRoleId).set(accountId).equalTo(record::getAccountId).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(RacAccountRoleMo record) {
        return update(c -> c.set(roleId).equalToWhenPresent(record::getRoleId).set(accountId).equalToWhenPresent(record::getAccountId).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(RacAccountRoleMo record) {
        return delete(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(roleId, isEqualToWhenPresent(record::getRoleId)).and(accountId,
                        isEqualToWhenPresent(record::getAccountId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountRoleMo> selectOne(RacAccountRoleMo record) {
        return selectOne(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(roleId, isEqualToWhenPresent(record::getRoleId)).and(accountId,
                        isEqualToWhenPresent(record::getAccountId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(RacAccountRoleMo record) {
        return count(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(roleId, isEqualToWhenPresent(record::getRoleId)).and(accountId,
                        isEqualToWhenPresent(record::getAccountId)));
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
    default boolean existSelective(RacAccountRoleMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountRoleMo> selectSelective(RacAccountRoleMo record) {
        return select(
                c -> c.where(id, isEqualToWhenPresent(record::getId)).and(roleId, isEqualToWhenPresent(record::getRoleId)).and(accountId,
                        isEqualToWhenPresent(record::getAccountId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountRoleMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default BasicColumn[] getColumns() {
        return selectList;
    }
}
