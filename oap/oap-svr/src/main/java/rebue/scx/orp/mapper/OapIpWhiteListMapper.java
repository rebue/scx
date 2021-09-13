package rebue.scx.orp.mapper;

import static rebue.scx.orp.mapper.OapIpWhiteListDynamicSqlSupport.appId;
import static rebue.scx.orp.mapper.OapIpWhiteListDynamicSqlSupport.createTimestamp;
import static rebue.scx.orp.mapper.OapIpWhiteListDynamicSqlSupport.id;
import static rebue.scx.orp.mapper.OapIpWhiteListDynamicSqlSupport.ipAddr;
import static rebue.scx.orp.mapper.OapIpWhiteListDynamicSqlSupport.oapIpWhiteList;
import static rebue.scx.orp.mapper.OapIpWhiteListDynamicSqlSupport.updateTimestamp;
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

import rebue.scx.orp.mo.OapIpWhiteListMo;

import rebue.robotech.mybatis.MapperRootInterface;

@Mapper
public interface OapIpWhiteListMapper extends MapperRootInterface<OapIpWhiteListMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, appId, ipAddr, createTimestamp, updateTimestamp);

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
    int insert(InsertStatementProvider<OapIpWhiteListMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OapIpWhiteListMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("OapIpWhiteListMoResult")
    Optional<OapIpWhiteListMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "OapIpWhiteListMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "APP_ID", property = "appId", jdbcType = JdbcType.BIGINT), @Result(column = "IP_ADDR", property = "ipAddr", jdbcType = JdbcType.VARCHAR),
        @Result(column = "CREATE_TIMESTAMP", property = "createTimestamp", jdbcType = JdbcType.BIGINT),
        @Result(column = "UPDATE_TIMESTAMP", property = "updateTimestamp", jdbcType = JdbcType.BIGINT)
    })
    List<OapIpWhiteListMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, oapIpWhiteList, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, oapIpWhiteList, completer);
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
    default int insert(OapIpWhiteListMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapIpWhiteList, c -> c.map(id).toProperty("id").map(appId).toProperty("appId").map(ipAddr).toProperty("ipAddr")
            .map(createTimestamp).toProperty("createTimestamp").map(updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<OapIpWhiteListMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, oapIpWhiteList, c -> c.map(id).toProperty("id").map(appId).toProperty("appId").map(ipAddr)
            .toProperty("ipAddr").map(createTimestamp).toProperty("createTimestamp").map(updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(OapIpWhiteListMo record) {
        return MyBatis3Utils.insert(this::insert, record, oapIpWhiteList,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(appId).toPropertyWhenPresent("appId", record::getAppId).map(ipAddr)
                .toPropertyWhenPresent("ipAddr", record::getIpAddr).map(createTimestamp).toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp).map(updateTimestamp)
                .toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapIpWhiteListMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, oapIpWhiteList, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapIpWhiteListMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, oapIpWhiteList, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapIpWhiteListMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, oapIpWhiteList, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapIpWhiteListMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, oapIpWhiteList, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(OapIpWhiteListMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(appId).equalTo(record::getAppId).set(ipAddr).equalTo(record::getIpAddr).set(createTimestamp)
            .equalTo(record::getCreateTimestamp).set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OapIpWhiteListMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(appId).equalToWhenPresent(record::getAppId).set(ipAddr).equalToWhenPresent(record::getIpAddr).set(createTimestamp)
            .equalToWhenPresent(record::getCreateTimestamp).set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(OapIpWhiteListMo record) {
        return update(c -> c.set(appId).equalTo(record::getAppId).set(ipAddr).equalTo(record::getIpAddr).set(createTimestamp).equalTo(record::getCreateTimestamp)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(OapIpWhiteListMo record) {
        return update(c -> c.set(appId).equalToWhenPresent(record::getAppId).set(ipAddr).equalToWhenPresent(record::getIpAddr).set(createTimestamp)
            .equalToWhenPresent(record::getCreateTimestamp).set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp).where(id, isEqualTo(record::getId)));
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
    default int deleteSelective(OapIpWhiteListMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(ipAddr, isEqualToWhenPresent(record::getIpAddr))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<OapIpWhiteListMo> selectOne(OapIpWhiteListMo record) {
        return selectOne(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(ipAddr, isEqualToWhenPresent(record::getIpAddr))
                .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(OapIpWhiteListMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(ipAddr, isEqualToWhenPresent(record::getIpAddr))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
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
    default boolean existSelective(OapIpWhiteListMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapIpWhiteListMo> selectSelective(OapIpWhiteListMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(ipAddr, isEqualToWhenPresent(record::getIpAddr))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp)).and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<OapIpWhiteListMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
