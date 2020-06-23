package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacSignInLogDynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.scx.rac.mo.RacSignInLogMo;

@Mapper
public interface RacSignInLogMapper extends MybatisBaseMapper<RacSignInLogMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, sysId, loginWay, loginTime);

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
    int insert(InsertStatementProvider<RacSignInLogMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacSignInLogMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacSignInLogMoResult")
    Optional<RacSignInLogMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacSignInLogMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="SYS_ID", property="sysId", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_WAY", property="loginWay", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_TIME", property="loginTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RacSignInLogMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racSignInLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racSignInLog, completer);
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
    default int insert(RacSignInLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racSignInLog, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(sysId).toProperty("sysId")
            .map(loginWay).toProperty("loginWay")
            .map(loginTime).toProperty("loginTime")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacSignInLogMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racSignInLog, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(sysId).toProperty("sysId")
            .map(loginWay).toProperty("loginWay")
            .map(loginTime).toProperty("loginTime")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacSignInLogMo record) {
        return MyBatis3Utils.insert(this::insert, record, racSignInLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(sysId).toPropertyWhenPresent("sysId", record::getSysId)
            .map(loginWay).toPropertyWhenPresent("loginWay", record::getLoginWay)
            .map(loginTime).toPropertyWhenPresent("loginTime", record::getLoginTime)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacSignInLogMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racSignInLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacSignInLogMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racSignInLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacSignInLogMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racSignInLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacSignInLogMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racSignInLog, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacSignInLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(sysId).equalTo(record::getSysId)
                .set(loginWay).equalTo(record::getLoginWay)
                .set(loginTime).equalTo(record::getLoginTime);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacSignInLogMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(sysId).equalToWhenPresent(record::getSysId)
                .set(loginWay).equalToWhenPresent(record::getLoginWay)
                .set(loginTime).equalToWhenPresent(record::getLoginTime);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacSignInLogMo record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(sysId).equalTo(record::getSysId)
            .set(loginWay).equalTo(record::getLoginWay)
            .set(loginTime).equalTo(record::getLoginTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacSignInLogMo record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(sysId).equalToWhenPresent(record::getSysId)
            .set(loginWay).equalToWhenPresent(record::getLoginWay)
            .set(loginTime).equalToWhenPresent(record::getLoginTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacSignInLogMo> selectOne(RacSignInLogMo record) {
        return selectOne(c ->
            c.where(id, isEqualTo(record::getId).when(Objects::nonNull))
            .and(userId, isEqualTo(record::getUserId).when(Objects::nonNull))
            .and(sysId, isEqualTo(record::getSysId).when(Objects::nonNull))
            .and(loginWay, isEqualTo(record::getLoginWay).when(Objects::nonNull))
            .and(loginTime, isEqualTo(record::getLoginTime).when(Objects::nonNull))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacSignInLogMo record) {
        return count(c ->
            c.where(id, isEqualTo(record::getId).when(Objects::nonNull))
            .and(userId, isEqualTo(record::getUserId).when(Objects::nonNull))
            .and(sysId, isEqualTo(record::getSysId).when(Objects::nonNull))
            .and(loginWay, isEqualTo(record::getLoginWay).when(Objects::nonNull))
            .and(loginTime, isEqualTo(record::getLoginTime).when(Objects::nonNull))
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
    default boolean existSelective(RacSignInLogMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacSignInLogMo> selectSelective(RacSignInLogMo record) {
        return select(c ->
            c.where(id, isEqualTo(record::getId).when(Objects::nonNull))
            .and(userId, isEqualTo(record::getUserId).when(Objects::nonNull))
            .and(sysId, isEqualTo(record::getSysId).when(Objects::nonNull))
            .and(loginWay, isEqualTo(record::getLoginWay).when(Objects::nonNull))
            .and(loginTime, isEqualTo(record::getLoginTime).when(Objects::nonNull))
        );
    }
}