package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacDomainUserDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacDomainUserMo;

@Mapper
public interface RacDomainUserMapper extends MapperRootInterface<RacDomainUserMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, domainId, userId, isEnabled);

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
    int insert(InsertStatementProvider<RacDomainUserMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDomainUserMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacDomainUserMoResult")
    Optional<RacDomainUserMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacDomainUserMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="DOMAIN_ID", property="domainId", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="IS_ENABLED", property="isEnabled", jdbcType=JdbcType.BIT)
    })
    List<RacDomainUserMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDomainUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDomainUser, completer);
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
    default int insert(RacDomainUserMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDomainUser, c ->
            c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(userId).toProperty("userId")
            .map(isEnabled).toProperty("isEnabled")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacDomainUserMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDomainUser, c ->
            c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(userId).toProperty("userId")
            .map(isEnabled).toProperty("isEnabled")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacDomainUserMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDomainUser, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(domainId).toPropertyWhenPresent("domainId", record::getDomainId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainUserMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDomainUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainUserMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDomainUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainUserMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDomainUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainUserMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDomainUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacDomainUserMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(domainId).equalTo(record::getDomainId)
                .set(userId).equalTo(record::getUserId)
                .set(isEnabled).equalTo(record::getIsEnabled);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacDomainUserMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(domainId).equalToWhenPresent(record::getDomainId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(isEnabled).equalToWhenPresent(record::getIsEnabled);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacDomainUserMo record) {
        return update(c ->
            c.set(domainId).equalTo(record::getDomainId)
            .set(userId).equalTo(record::getUserId)
            .set(isEnabled).equalTo(record::getIsEnabled)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacDomainUserMo record) {
        return update(c ->
            c.set(domainId).equalToWhenPresent(record::getDomainId)
            .set(userId).equalToWhenPresent(record::getUserId)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacDomainUserMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainUserMo> selectOne(RacDomainUserMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacDomainUserMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
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
    default boolean existSelective(RacDomainUserMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainUserMo> selectSelective(RacDomainUserMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
        );
    }
}