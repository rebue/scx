package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacDomainAccountDynamicSqlSupport.accountId;
import static rebue.scx.rac.mapper.RacDomainAccountDynamicSqlSupport.domainId;
import static rebue.scx.rac.mapper.RacDomainAccountDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacDomainAccountDynamicSqlSupport.racDomainAccount;

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
import rebue.scx.rac.mo.RacDomainAccountMo;

@Mapper
public interface RacDomainAccountMapper extends MapperRootInterface<RacDomainAccountMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, domainId, accountId);

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
    int insert(InsertStatementProvider<RacDomainAccountMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDomainAccountMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacDomainAccountMoResult")
    Optional<RacDomainAccountMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacDomainAccountMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="DOMAIN_ID", property="domainId", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT)
    })
    List<RacDomainAccountMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDomainAccount, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDomainAccount, completer);
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
    default int insert(RacDomainAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDomainAccount, c ->
            c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(accountId).toProperty("accountId")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacDomainAccountMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDomainAccount, c ->
            c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(accountId).toProperty("accountId")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacDomainAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDomainAccount, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(domainId).toPropertyWhenPresent("domainId", record::getDomainId)
            .map(accountId).toPropertyWhenPresent("accountId", record::getAccountId)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainAccountMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDomainAccount, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainAccountMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDomainAccount, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainAccountMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDomainAccount, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainAccountMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDomainAccount, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacDomainAccountMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(domainId).equalTo(record::getDomainId)
                .set(accountId).equalTo(record::getAccountId);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacDomainAccountMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(domainId).equalToWhenPresent(record::getDomainId)
                .set(accountId).equalToWhenPresent(record::getAccountId);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacDomainAccountMo record) {
        return update(c ->
            c.set(domainId).equalTo(record::getDomainId)
            .set(accountId).equalTo(record::getAccountId)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacDomainAccountMo record) {
        return update(c ->
            c.set(domainId).equalToWhenPresent(record::getDomainId)
            .set(accountId).equalToWhenPresent(record::getAccountId)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacDomainAccountMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(accountId, isEqualToWhenPresent(record::getAccountId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainAccountMo> selectOne(RacDomainAccountMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(accountId, isEqualToWhenPresent(record::getAccountId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacDomainAccountMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(accountId, isEqualToWhenPresent(record::getAccountId))
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
    default boolean existSelective(RacDomainAccountMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainAccountMo> selectSelective(RacDomainAccountMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(accountId, isEqualToWhenPresent(record::getAccountId))
        );
    }
}