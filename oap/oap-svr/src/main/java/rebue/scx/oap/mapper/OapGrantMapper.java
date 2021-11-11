package rebue.scx.oap.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.accessToken;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.accessTokenExpireTimestamp;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.accessTokenJson;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.accountId;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.createTimestamp;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.id;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.oapGrant;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.refreshToken;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.refreshTokenExpiresTimestamp;
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
import rebue.scx.oap.mo.OapGrantMo;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.oap.mapper.OapGrantDynamicSqlSupport.*;

@Mapper
public interface OapGrantMapper extends MapperRootInterface<OapGrantMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, accountId, accessToken, refreshToken, accessTokenJson, accessTokenExpireTimestamp, refreshTokenExpiresTimestamp,
        createTimestamp);

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
    int insert(InsertStatementProvider<OapGrantMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OapGrantMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("OapGrantMoResult")
    Optional<OapGrantMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "OapGrantMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "ACCOUNT_ID", property = "accountId", jdbcType = JdbcType.BIGINT), @Result(column = "ACCESS_TOKEN", property = "accessToken", jdbcType = JdbcType.VARCHAR),
        @Result(column = "REFRESH_TOKEN", property = "refreshToken", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ACCESS_TOKEN_JSON", property = "accessTokenJson", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ACCESS_TOKEN_EXPIRE_TIMESTAMP", property = "accessTokenExpireTimestamp", jdbcType = JdbcType.BIGINT),
        @Result(column = "REFRESH_TOKEN_EXPIRES_TIMESTAMP", property = "refreshTokenExpiresTimestamp", jdbcType = JdbcType.BIGINT),
        @Result(column = "CREATE_TIMESTAMP", property = "createTimestamp", jdbcType = JdbcType.BIGINT)
    })
    List<OapGrantMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, oapGrant, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, oapGrant, completer);
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
    default int insert(OapGrantMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapGrant,
            c -> c.map(id).toProperty("id").map(accountId).toProperty("accountId").map(accessToken).toProperty("accessToken").map(refreshToken).toProperty("refreshToken")
                .map(accessTokenJson).toProperty("accessTokenJson").map(accessTokenExpireTimestamp).toProperty("accessTokenExpireTimestamp").map(refreshTokenExpiresTimestamp)
                .toProperty("refreshTokenExpiresTimestamp").map(createTimestamp).toProperty("createTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<OapGrantMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, oapGrant,
            c -> c.map(id).toProperty("id").map(accountId).toProperty("accountId").map(accessToken).toProperty("accessToken").map(refreshToken).toProperty("refreshToken")
                .map(accessTokenJson).toProperty("accessTokenJson").map(accessTokenExpireTimestamp).toProperty("accessTokenExpireTimestamp").map(refreshTokenExpiresTimestamp)
                .toProperty("refreshTokenExpiresTimestamp").map(createTimestamp).toProperty("createTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(OapGrantMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapGrant,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(accountId).toPropertyWhenPresent("accountId", record::getAccountId).map(accessToken)
                .toPropertyWhenPresent("accessToken", record::getAccessToken).map(refreshToken).toPropertyWhenPresent("refreshToken", record::getRefreshToken).map(accessTokenJson)
                .toPropertyWhenPresent("accessTokenJson", record::getAccessTokenJson).map(accessTokenExpireTimestamp)
                .toPropertyWhenPresent("accessTokenExpireTimestamp", record::getAccessTokenExpireTimestamp).map(refreshTokenExpiresTimestamp)
                .toPropertyWhenPresent("refreshTokenExpiresTimestamp", record::getRefreshTokenExpiresTimestamp).map(createTimestamp)
                .toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapGrantMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, oapGrant, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapGrantMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, oapGrant, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapGrantMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, oapGrant, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapGrantMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, oapGrant, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(OapGrantMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(accountId).equalTo(record::getAccountId).set(accessToken).equalTo(record::getAccessToken).set(refreshToken)
            .equalTo(record::getRefreshToken).set(accessTokenJson).equalTo(record::getAccessTokenJson).set(accessTokenExpireTimestamp)
            .equalTo(record::getAccessTokenExpireTimestamp).set(refreshTokenExpiresTimestamp).equalTo(record::getRefreshTokenExpiresTimestamp).set(createTimestamp)
            .equalTo(record::getCreateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OapGrantMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(accountId).equalToWhenPresent(record::getAccountId).set(accessToken).equalToWhenPresent(record::getAccessToken)
            .set(refreshToken).equalToWhenPresent(record::getRefreshToken).set(accessTokenJson).equalToWhenPresent(record::getAccessTokenJson).set(accessTokenExpireTimestamp)
            .equalToWhenPresent(record::getAccessTokenExpireTimestamp).set(refreshTokenExpiresTimestamp).equalToWhenPresent(record::getRefreshTokenExpiresTimestamp)
            .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(OapGrantMo record) {
        return update(c -> c.set(accountId).equalTo(record::getAccountId).set(accessToken).equalTo(record::getAccessToken).set(refreshToken).equalTo(record::getRefreshToken)
            .set(accessTokenJson).equalTo(record::getAccessTokenJson).set(accessTokenExpireTimestamp).equalTo(record::getAccessTokenExpireTimestamp)
            .set(refreshTokenExpiresTimestamp).equalTo(record::getRefreshTokenExpiresTimestamp).set(createTimestamp).equalTo(record::getCreateTimestamp)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(OapGrantMo record) {
        return update(c -> c.set(accountId).equalToWhenPresent(record::getAccountId).set(accessToken).equalToWhenPresent(record::getAccessToken).set(refreshToken)
            .equalToWhenPresent(record::getRefreshToken).set(accessTokenJson).equalToWhenPresent(record::getAccessTokenJson).set(accessTokenExpireTimestamp)
            .equalToWhenPresent(record::getAccessTokenExpireTimestamp).set(refreshTokenExpiresTimestamp).equalToWhenPresent(record::getRefreshTokenExpiresTimestamp)
            .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp).where(id, isEqualTo(record::getId)));
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
    default int deleteSelective(OapGrantMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
            .and(accessToken, isEqualToWhenPresent(record::getAccessToken)).and(refreshToken, isEqualToWhenPresent(record::getRefreshToken))
            .and(accessTokenJson, isEqualToWhenPresent(record::getAccessTokenJson)).and(accessTokenExpireTimestamp, isEqualToWhenPresent(record::getAccessTokenExpireTimestamp))
            .and(refreshTokenExpiresTimestamp, isEqualToWhenPresent(record::getRefreshTokenExpiresTimestamp))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapGrantMo> selectOne(OapGrantMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
            .and(accessToken, isEqualToWhenPresent(record::getAccessToken)).and(refreshToken, isEqualToWhenPresent(record::getRefreshToken))
            .and(accessTokenJson, isEqualToWhenPresent(record::getAccessTokenJson)).and(accessTokenExpireTimestamp, isEqualToWhenPresent(record::getAccessTokenExpireTimestamp))
            .and(refreshTokenExpiresTimestamp, isEqualToWhenPresent(record::getRefreshTokenExpiresTimestamp))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(OapGrantMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
            .and(accessToken, isEqualToWhenPresent(record::getAccessToken)).and(refreshToken, isEqualToWhenPresent(record::getRefreshToken))
            .and(accessTokenJson, isEqualToWhenPresent(record::getAccessTokenJson)).and(accessTokenExpireTimestamp, isEqualToWhenPresent(record::getAccessTokenExpireTimestamp))
            .and(refreshTokenExpiresTimestamp, isEqualToWhenPresent(record::getRefreshTokenExpiresTimestamp))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)));
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
    default boolean existSelective(OapGrantMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapGrantMo> selectSelective(OapGrantMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(accountId, isEqualToWhenPresent(record::getAccountId))
            .and(accessToken, isEqualToWhenPresent(record::getAccessToken)).and(refreshToken, isEqualToWhenPresent(record::getRefreshToken))
            .and(accessTokenJson, isEqualToWhenPresent(record::getAccessTokenJson)).and(accessTokenExpireTimestamp, isEqualToWhenPresent(record::getAccessTokenExpireTimestamp))
            .and(refreshTokenExpiresTimestamp, isEqualToWhenPresent(record::getRefreshTokenExpiresTimestamp))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapGrantMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
