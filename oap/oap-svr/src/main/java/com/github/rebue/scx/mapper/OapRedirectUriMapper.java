package com.github.rebue.scx.mapper;

import static com.github.rebue.scx.mapper.OapRedirectUriDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.github.rebue.scx.mo.OapRedirectUriMo;
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
import org.mybatis.dynamic.sql.SqlBuilder.isIn;
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

@Mapper
public interface OapRedirectUriMapper extends MapperRootInterface<OapRedirectUriMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, appId, redirectUri, createTimestamp, updateTimestamp);

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
    int insert(InsertStatementProvider<OapRedirectUriMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OapRedirectUriMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OapRedirectUriMoResult")
    Optional<OapRedirectUriMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OapRedirectUriMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="APP_ID", property="appId", jdbcType=JdbcType.BIGINT),
        @Result(column="REDIRECT_URI", property="redirectUri", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIMESTAMP", property="createTimestamp", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATE_TIMESTAMP", property="updateTimestamp", jdbcType=JdbcType.BIGINT)
    })
    List<OapRedirectUriMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, oapRedirectUri, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, oapRedirectUri, completer);
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
    default int insert(OapRedirectUriMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapRedirectUri, c ->
            c.map(id).toProperty("id")
            .map(appId).toProperty("appId")
            .map(redirectUri).toProperty("redirectUri")
            .map(createTimestamp).toProperty("createTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<OapRedirectUriMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, oapRedirectUri, c ->
            c.map(id).toProperty("id")
            .map(appId).toProperty("appId")
            .map(redirectUri).toProperty("redirectUri")
            .map(createTimestamp).toProperty("createTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(OapRedirectUriMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapRedirectUri, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(appId).toPropertyWhenPresent("appId", record::getAppId)
            .map(redirectUri).toPropertyWhenPresent("redirectUri", record::getRedirectUri)
            .map(createTimestamp).toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp)
            .map(updateTimestamp).toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapRedirectUriMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, oapRedirectUri, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapRedirectUriMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, oapRedirectUri, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapRedirectUriMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, oapRedirectUri, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapRedirectUriMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, oapRedirectUri, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(OapRedirectUriMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(appId).equalTo(record::getAppId)
                .set(redirectUri).equalTo(record::getRedirectUri)
                .set(createTimestamp).equalTo(record::getCreateTimestamp)
                .set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OapRedirectUriMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(appId).equalToWhenPresent(record::getAppId)
                .set(redirectUri).equalToWhenPresent(record::getRedirectUri)
                .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
                .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(OapRedirectUriMo record) {
        return update(c ->
            c.set(appId).equalTo(record::getAppId)
            .set(redirectUri).equalTo(record::getRedirectUri)
            .set(createTimestamp).equalTo(record::getCreateTimestamp)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(OapRedirectUriMo record) {
        return update(c ->
            c.set(appId).equalToWhenPresent(record::getAppId)
            .set(redirectUri).equalToWhenPresent(record::getRedirectUri)
            .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
            .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
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
    default int deleteSelective(OapRedirectUriMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(appId, isEqualToWhenPresent(record::getAppId))
            .and(redirectUri, isEqualToWhenPresent(record::getRedirectUri))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapRedirectUriMo> selectOne(OapRedirectUriMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(appId, isEqualToWhenPresent(record::getAppId))
            .and(redirectUri, isEqualToWhenPresent(record::getRedirectUri))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(OapRedirectUriMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(appId, isEqualToWhenPresent(record::getAppId))
            .and(redirectUri, isEqualToWhenPresent(record::getRedirectUri))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
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
    default boolean existSelective(OapRedirectUriMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapRedirectUriMo> selectSelective(OapRedirectUriMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(appId, isEqualToWhenPresent(record::getAppId))
            .and(redirectUri, isEqualToWhenPresent(record::getRedirectUri))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapRedirectUriMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}