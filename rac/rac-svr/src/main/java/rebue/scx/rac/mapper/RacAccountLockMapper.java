package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacAccountLockDynamicSqlSupport.accountId;
import static rebue.scx.rac.mapper.RacAccountLockDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacAccountLockDynamicSqlSupport.lockDatetime;
import static rebue.scx.rac.mapper.RacAccountLockDynamicSqlSupport.racAccountLock;

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
import rebue.scx.rac.mo.RacAccountLockMo;

@Mapper
public interface RacAccountLockMapper extends MapperRootInterface<RacAccountLockMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, accountId, lockDatetime);

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
    int insert(InsertStatementProvider<RacAccountLockMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacAccountLockMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacAccountLockMoResult")
    Optional<RacAccountLockMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacAccountLockMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "ACCOUNT_ID", property = "accountId", jdbcType = JdbcType.BIGINT),
        @Result(column = "LOCK_DATETIME", property = "lockDatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RacAccountLockMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racAccountLock, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racAccountLock, completer);
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
    default int insert(RacAccountLockMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccountLock,
            c -> c.map(id).toProperty("id").map(accountId).toProperty("accountId").map(lockDatetime).toProperty("lockDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacAccountLockMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racAccountLock,
            c -> c.map(id).toProperty("id").map(accountId).toProperty("accountId").map(lockDatetime).toProperty("lockDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacAccountLockMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccountLock, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(accountId)
            .toPropertyWhenPresent("accountId", record::getAccountId).map(lockDatetime).toPropertyWhenPresent("lockDatetime", record::getLockDatetime));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacAccountLockMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racAccountLock, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAccountLockMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racAccountLock, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAccountLockMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racAccountLock, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacAccountLockMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racAccountLock, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacAccountLockMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(accountId).equalTo(record::getAccountId).set(lockDatetime).equalTo(record::getLockDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacAccountLockMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(accountId).equalToWhenPresent(record::getAccountId).set(lockDatetime).equalToWhenPresent(record::getLockDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacAccountLockMo record) {
        return update(c -> c.set(accountId).equalTo(record::getAccountId).set(lockDatetime).equalTo(record::getLockDatetime).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacAccountLockMo record) {
        return update(
            c -> c.set(accountId).equalToWhenPresent(record::getAccountId).set(lockDatetime).equalToWhenPresent(record::getLockDatetime).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacAccountLockMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId)).and(lockDatetime,
            isEqualToWhenPresent(record::getLockDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacAccountLockMo> selectOne(RacAccountLockMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId)).and(lockDatetime,
            isEqualToWhenPresent(record::getLockDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacAccountLockMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId)).and(lockDatetime,
            isEqualToWhenPresent(record::getLockDatetime)));
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
    default boolean existSelective(RacAccountLockMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAccountLockMo> selectSelective(RacAccountLockMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId)).and(lockDatetime,
            isEqualToWhenPresent(record::getLockDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAccountLockMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
