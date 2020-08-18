package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacUserRoleDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacUserRoleMo;

@Mapper
public interface RacUserRoleMapper extends MapperRootInterface<RacUserRoleMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, sysId, roleId, userId);

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
    int insert(InsertStatementProvider<RacUserRoleMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacUserRoleMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacUserRoleMoResult")
    Optional<RacUserRoleMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacUserRoleMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SYS_ID", property="sysId", jdbcType=JdbcType.VARCHAR),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT)
    })
    List<RacUserRoleMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racUserRole, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racUserRole, completer);
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
    default int insert(RacUserRoleMo record) {
        return MyBatis3Utils.insert(this::insert, record, racUserRole, c ->
            c.map(id).toProperty("id")
            .map(sysId).toProperty("sysId")
            .map(roleId).toProperty("roleId")
            .map(userId).toProperty("userId")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacUserRoleMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racUserRole, c ->
            c.map(id).toProperty("id")
            .map(sysId).toProperty("sysId")
            .map(roleId).toProperty("roleId")
            .map(userId).toProperty("userId")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacUserRoleMo record) {
        return MyBatis3Utils.insert(this::insert, record, racUserRole, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(sysId).toPropertyWhenPresent("sysId", record::getSysId)
            .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserRoleMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racUserRole, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserRoleMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racUserRole, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserRoleMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racUserRole, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserRoleMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racUserRole, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacUserRoleMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(sysId).equalTo(record::getSysId)
                .set(roleId).equalTo(record::getRoleId)
                .set(userId).equalTo(record::getUserId);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacUserRoleMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(sysId).equalToWhenPresent(record::getSysId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(userId).equalToWhenPresent(record::getUserId);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacUserRoleMo record) {
        return update(c ->
            c.set(sysId).equalTo(record::getSysId)
            .set(roleId).equalTo(record::getRoleId)
            .set(userId).equalTo(record::getUserId)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacUserRoleMo record) {
        return update(c ->
            c.set(sysId).equalToWhenPresent(record::getSysId)
            .set(roleId).equalToWhenPresent(record::getRoleId)
            .set(userId).equalToWhenPresent(record::getUserId)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacUserRoleMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(roleId, isEqualToWhenPresent(record::getRoleId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserRoleMo> selectOne(RacUserRoleMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(roleId, isEqualToWhenPresent(record::getRoleId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacUserRoleMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(roleId, isEqualToWhenPresent(record::getRoleId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
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
    default boolean existSelective(RacUserRoleMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserRoleMo> selectSelective(RacUserRoleMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(roleId, isEqualToWhenPresent(record::getRoleId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
        );
    }
}