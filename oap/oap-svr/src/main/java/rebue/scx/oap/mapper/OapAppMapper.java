package rebue.scx.oap.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

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

import rebue.scx.oap.mo.OapAppMo;

import rebue.robotech.mybatis.MapperRootInterface;

@Mapper
public interface OapAppMapper extends MapperRootInterface<OapAppMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(OapAppDynamicSqlSupport.id, OapAppDynamicSqlSupport.appId, OapAppDynamicSqlSupport.isEnabled, OapAppDynamicSqlSupport.clientId, OapAppDynamicSqlSupport.secret, OapAppDynamicSqlSupport.objId, OapAppDynamicSqlSupport.createTimestamp, OapAppDynamicSqlSupport.updateTimestamp);

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
    int insert(InsertStatementProvider<OapAppMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OapAppMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("OapAppMoResult")
    Optional<OapAppMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "OapAppMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "APP_ID", property = "appId", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT),
        @Result(column = "CLIENT_ID", property = "clientId", jdbcType = JdbcType.VARCHAR), @Result(column = "SECRET", property = "secret", jdbcType = JdbcType.VARCHAR),
        @Result(column = "OBJ_ID", property = "objId", jdbcType = JdbcType.BIGINT), @Result(column = "CREATE_TIMESTAMP", property = "createTimestamp", jdbcType = JdbcType.BIGINT),
        @Result(column = "UPDATE_TIMESTAMP", property = "updateTimestamp", jdbcType = JdbcType.BIGINT)
    })
    List<OapAppMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OapAppDynamicSqlSupport.oapApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OapAppDynamicSqlSupport.oapApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> c.where(OapAppDynamicSqlSupport.id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(OapAppMo record) {
        return MyBatis3Utils.insert(this::insert, record, OapAppDynamicSqlSupport.oapApp,
            c -> c.map(OapAppDynamicSqlSupport.id).toProperty("id").map(OapAppDynamicSqlSupport.appId).toProperty("appId").map(OapAppDynamicSqlSupport.isEnabled).toProperty("isEnabled").map(OapAppDynamicSqlSupport.clientId).toProperty("clientId").map(OapAppDynamicSqlSupport.secret)
                .toProperty("secret").map(OapAppDynamicSqlSupport.objId).toProperty("objId").map(OapAppDynamicSqlSupport.createTimestamp).toProperty("createTimestamp").map(OapAppDynamicSqlSupport.updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<OapAppMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, OapAppDynamicSqlSupport.oapApp,
            c -> c.map(OapAppDynamicSqlSupport.id).toProperty("id").map(OapAppDynamicSqlSupport.appId).toProperty("appId").map(OapAppDynamicSqlSupport.isEnabled).toProperty("isEnabled").map(OapAppDynamicSqlSupport.clientId).toProperty("clientId").map(OapAppDynamicSqlSupport.secret)
                .toProperty("secret").map(OapAppDynamicSqlSupport.objId).toProperty("objId").map(OapAppDynamicSqlSupport.createTimestamp).toProperty("createTimestamp").map(OapAppDynamicSqlSupport.updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(OapAppMo record) {
        return MyBatis3Utils.insert(this::insert, record, OapAppDynamicSqlSupport.oapApp,
            c -> c.map(OapAppDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId).map(OapAppDynamicSqlSupport.appId).toPropertyWhenPresent("appId", record::getAppId).map(OapAppDynamicSqlSupport.isEnabled)
                .toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(OapAppDynamicSqlSupport.clientId).toPropertyWhenPresent("clientId", record::getClientId).map(OapAppDynamicSqlSupport.secret)
                .toPropertyWhenPresent("secret", record::getSecret).map(OapAppDynamicSqlSupport.objId).toPropertyWhenPresent("objId", record::getObjId).map(OapAppDynamicSqlSupport.createTimestamp)
                .toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp).map(OapAppDynamicSqlSupport.updateTimestamp).toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapAppMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OapAppDynamicSqlSupport.oapApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAppMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OapAppDynamicSqlSupport.oapApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAppMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OapAppDynamicSqlSupport.oapApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapAppMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(OapAppDynamicSqlSupport.id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OapAppDynamicSqlSupport.oapApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(OapAppMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OapAppDynamicSqlSupport.id).equalTo(record::getId).set(OapAppDynamicSqlSupport.appId).equalTo(record::getAppId).set(OapAppDynamicSqlSupport.isEnabled).equalTo(record::getIsEnabled).set(OapAppDynamicSqlSupport.clientId).equalTo(record::getClientId)
            .set(OapAppDynamicSqlSupport.secret).equalTo(record::getSecret).set(OapAppDynamicSqlSupport.objId).equalTo(record::getObjId).set(OapAppDynamicSqlSupport.createTimestamp).equalTo(record::getCreateTimestamp).set(OapAppDynamicSqlSupport.updateTimestamp)
            .equalTo(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OapAppMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OapAppDynamicSqlSupport.id).equalToWhenPresent(record::getId).set(OapAppDynamicSqlSupport.appId).equalToWhenPresent(record::getAppId).set(OapAppDynamicSqlSupport.isEnabled).equalToWhenPresent(record::getIsEnabled).set(OapAppDynamicSqlSupport.clientId)
            .equalToWhenPresent(record::getClientId).set(OapAppDynamicSqlSupport.secret).equalToWhenPresent(record::getSecret).set(OapAppDynamicSqlSupport.objId).equalToWhenPresent(record::getObjId).set(OapAppDynamicSqlSupport.createTimestamp)
            .equalToWhenPresent(record::getCreateTimestamp).set(OapAppDynamicSqlSupport.updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(OapAppMo record) {
        return update(c -> c.set(OapAppDynamicSqlSupport.appId).equalTo(record::getAppId).set(OapAppDynamicSqlSupport.isEnabled).equalTo(record::getIsEnabled).set(OapAppDynamicSqlSupport.clientId).equalTo(record::getClientId).set(OapAppDynamicSqlSupport.secret)
            .equalTo(record::getSecret).set(OapAppDynamicSqlSupport.objId).equalTo(record::getObjId).set(OapAppDynamicSqlSupport.createTimestamp).equalTo(record::getCreateTimestamp).set(OapAppDynamicSqlSupport.updateTimestamp)
            .equalTo(record::getUpdateTimestamp).where(OapAppDynamicSqlSupport.id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(OapAppMo record) {
        return update(c -> c.set(OapAppDynamicSqlSupport.appId).equalToWhenPresent(record::getAppId).set(OapAppDynamicSqlSupport.isEnabled).equalToWhenPresent(record::getIsEnabled).set(OapAppDynamicSqlSupport.clientId)
            .equalToWhenPresent(record::getClientId).set(OapAppDynamicSqlSupport.secret).equalToWhenPresent(record::getSecret).set(OapAppDynamicSqlSupport.objId).equalToWhenPresent(record::getObjId).set(OapAppDynamicSqlSupport.createTimestamp)
            .equalToWhenPresent(record::getCreateTimestamp).set(OapAppDynamicSqlSupport.updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp).where(OapAppDynamicSqlSupport.id, isEqualTo(record::getId)));
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
    default int deleteSelective(OapAppMo record) {
        return delete(
            c -> c.where(OapAppDynamicSqlSupport.id, isEqualToWhenPresent(record::getId)).and(OapAppDynamicSqlSupport.appId, isEqualToWhenPresent(record::getAppId)).and(OapAppDynamicSqlSupport.isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(OapAppDynamicSqlSupport.clientId, isEqualToWhenPresent(record::getClientId)).and(OapAppDynamicSqlSupport.secret, isEqualToWhenPresent(record::getSecret)).and(OapAppDynamicSqlSupport.objId, isEqualToWhenPresent(record::getObjId))
                .and(OapAppDynamicSqlSupport.createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(OapAppDynamicSqlSupport.updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapAppMo> selectOne(OapAppMo record) {
        return selectOne(
            c -> c.where(OapAppDynamicSqlSupport.id, isEqualToWhenPresent(record::getId)).and(OapAppDynamicSqlSupport.appId, isEqualToWhenPresent(record::getAppId)).and(OapAppDynamicSqlSupport.isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(OapAppDynamicSqlSupport.clientId, isEqualToWhenPresent(record::getClientId)).and(OapAppDynamicSqlSupport.secret, isEqualToWhenPresent(record::getSecret)).and(OapAppDynamicSqlSupport.objId, isEqualToWhenPresent(record::getObjId))
                .and(OapAppDynamicSqlSupport.createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(OapAppDynamicSqlSupport.updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(OapAppMo record) {
        return count(
            c -> c.where(OapAppDynamicSqlSupport.id, isEqualToWhenPresent(record::getId)).and(OapAppDynamicSqlSupport.appId, isEqualToWhenPresent(record::getAppId)).and(OapAppDynamicSqlSupport.isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(OapAppDynamicSqlSupport.clientId, isEqualToWhenPresent(record::getClientId)).and(OapAppDynamicSqlSupport.secret, isEqualToWhenPresent(record::getSecret)).and(OapAppDynamicSqlSupport.objId, isEqualToWhenPresent(record::getObjId))
                .and(OapAppDynamicSqlSupport.createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(OapAppDynamicSqlSupport.updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existByPrimaryKey(Long id_) {
        return count(c -> c.where(OapAppDynamicSqlSupport.id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existSelective(OapAppMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAppMo> selectSelective(OapAppMo record) {
        return select(
            c -> c.where(OapAppDynamicSqlSupport.id, isEqualToWhenPresent(record::getId)).and(OapAppDynamicSqlSupport.appId, isEqualToWhenPresent(record::getAppId)).and(OapAppDynamicSqlSupport.isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(OapAppDynamicSqlSupport.clientId, isEqualToWhenPresent(record::getClientId)).and(OapAppDynamicSqlSupport.secret, isEqualToWhenPresent(record::getSecret)).and(OapAppDynamicSqlSupport.objId, isEqualToWhenPresent(record::getObjId))
                .and(OapAppDynamicSqlSupport.createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(OapAppDynamicSqlSupport.updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapAppMo> selectIn(List<Long> ids) {
        return select(c -> c.where(OapAppDynamicSqlSupport.id, isIn(ids)));
    }
}
