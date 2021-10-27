package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThan;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacAccountRoleDynamicSqlSupport.racAccountRole;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.authnType;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.imgUrl;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.isCertified;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.isEnabled;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.menu;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.racApp;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.seqNo;
import static rebue.scx.rac.mapper.RacAppDynamicSqlSupport.url;
import static rebue.scx.rac.mapper.RacRoleAppDynamicSqlSupport.racRoleApp;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.racRole;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
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
import rebue.scx.rac.mo.RacAppMo;

@Mapper
public interface RacAppMapper extends MapperRootInterface<RacAppMo, String> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, realmId, url, menu, remark, isEnabled, imgUrl, seqNo, isCertified, authnType);

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
    int insert(InsertStatementProvider<RacAppMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacAppMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacAppMoResult")
    Optional<RacAppMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacAppMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "URL", property = "url", jdbcType = JdbcType.VARCHAR), @Result(column = "MENU", property = "menu", jdbcType = JdbcType.VARCHAR),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT),
        @Result(column = "IMG_URL", property = "imgUrl", jdbcType = JdbcType.VARCHAR), @Result(column = "SEQ_NO", property = "seqNo", jdbcType = JdbcType.TINYINT),
        @Result(column = "IS_CERTIFIED", property = "isCertified", jdbcType = JdbcType.BIT), @Result(column = "AUTHN_TYPE", property = "authnType", jdbcType = JdbcType.TINYINT)
    })
    List<RacAppMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(RacAppMo record) {
        return MyBatis3Utils.insert(this::insert, record, racApp,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(realmId).toProperty("realmId").map(url).toProperty("url").map(menu).toProperty("menu").map(remark)
                .toProperty("remark").map(isEnabled).toProperty("isEnabled").map(imgUrl).toProperty("imgUrl").map(seqNo).toProperty("seqNo").map(isCertified)
                .toProperty("isCertified").map(authnType).toProperty("authnType"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacAppMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racApp,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(realmId).toProperty("realmId").map(url).toProperty("url").map(menu).toProperty("menu").map(remark)
                .toProperty("remark").map(isEnabled).toProperty("isEnabled").map(imgUrl).toProperty("imgUrl").map(seqNo).toProperty("seqNo").map(isCertified)
                .toProperty("isCertified").map(authnType).toProperty("authnType"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacAppMo record) {
        return MyBatis3Utils.insert(this::insert, record, racApp,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName).map(realmId)
                .toPropertyWhenPresent("realmId", record::getRealmId).map(url).toPropertyWhenPresent("url", record::getUrl).map(menu).toPropertyWhenPresent("menu", record::getMenu)
                .map(remark).toPropertyWhenPresent("remark", record::getRemark).map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(imgUrl)
                .toPropertyWhenPresent("imgUrl", record::getImgUrl).map(seqNo).toPropertyWhenPresent("seqNo", record::getSeqNo).map(isCertified)
                .toPropertyWhenPresent("isCertified", record::getIsCertified).map(authnType).toPropertyWhenPresent("authnType", record::getAuthnType));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacAppMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAppMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAppMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacAppMo> selectByPrimaryKey(String id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacAppMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(realmId).equalTo(record::getRealmId).set(url).equalTo(record::getUrl).set(menu)
            .equalTo(record::getMenu).set(remark).equalTo(record::getRemark).set(isEnabled).equalTo(record::getIsEnabled).set(imgUrl).equalTo(record::getImgUrl).set(seqNo)
            .equalTo(record::getSeqNo).set(isCertified).equalTo(record::getIsCertified).set(authnType).equalTo(record::getAuthnType);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacAppMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(realmId).equalToWhenPresent(record::getRealmId).set(url)
            .equalToWhenPresent(record::getUrl).set(menu).equalToWhenPresent(record::getMenu).set(remark).equalToWhenPresent(record::getRemark).set(isEnabled)
            .equalToWhenPresent(record::getIsEnabled).set(imgUrl).equalToWhenPresent(record::getImgUrl).set(seqNo).equalToWhenPresent(record::getSeqNo).set(isCertified)
            .equalToWhenPresent(record::getIsCertified).set(authnType).equalToWhenPresent(record::getAuthnType);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacAppMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(realmId).equalTo(record::getRealmId).set(url).equalTo(record::getUrl).set(menu).equalTo(record::getMenu)
            .set(remark).equalTo(record::getRemark).set(isEnabled).equalTo(record::getIsEnabled).set(imgUrl).equalTo(record::getImgUrl).set(seqNo).equalTo(record::getSeqNo)
            .set(isCertified).equalTo(record::getIsCertified).set(authnType).equalTo(record::getAuthnType).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacAppMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(realmId).equalToWhenPresent(record::getRealmId).set(url).equalToWhenPresent(record::getUrl).set(menu)
            .equalToWhenPresent(record::getMenu).set(remark).equalToWhenPresent(record::getRemark).set(isEnabled).equalToWhenPresent(record::getIsEnabled).set(imgUrl)
            .equalToWhenPresent(record::getImgUrl).set(seqNo).equalToWhenPresent(record::getSeqNo).set(isCertified).equalToWhenPresent(record::getIsCertified).set(authnType)
            .equalToWhenPresent(record::getAuthnType).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacAppMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(url, isEqualToWhenPresent(record::getUrl)).and(menu, isEqualToWhenPresent(record::getMenu)).and(remark, isEqualToWhenPresent(record::getRemark))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(imgUrl, isEqualToWhenPresent(record::getImgUrl)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(isCertified, isEqualToWhenPresent(record::getIsCertified)).and(authnType, isEqualToWhenPresent(record::getAuthnType)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacAppMo> selectOne(RacAppMo record) {
        return selectOne(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(realmId, isEqualToWhenPresent(record::getRealmId))
                .and(url, isEqualToWhenPresent(record::getUrl)).and(menu, isEqualToWhenPresent(record::getMenu)).and(remark, isEqualToWhenPresent(record::getRemark))
                .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(imgUrl, isEqualToWhenPresent(record::getImgUrl)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
                .and(isCertified, isEqualToWhenPresent(record::getIsCertified)).and(authnType, isEqualToWhenPresent(record::getAuthnType)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacAppMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(url, isEqualToWhenPresent(record::getUrl)).and(menu, isEqualToWhenPresent(record::getMenu)).and(remark, isEqualToWhenPresent(record::getRemark))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(imgUrl, isEqualToWhenPresent(record::getImgUrl)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(isCertified, isEqualToWhenPresent(record::getIsCertified)).and(authnType, isEqualToWhenPresent(record::getAuthnType)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existByPrimaryKey(String id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existSelective(RacAppMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAppMo> selectSelective(RacAppMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(url, isEqualToWhenPresent(record::getUrl)).and(menu, isEqualToWhenPresent(record::getMenu)).and(remark, isEqualToWhenPresent(record::getRemark))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(imgUrl, isEqualToWhenPresent(record::getImgUrl)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(isCertified, isEqualToWhenPresent(record::getIsCertified)).and(authnType, isEqualToWhenPresent(record::getAuthnType)));
    }

    /**
     * 根据顺序号seqNo排序查询
     */
    default List<RacAppMo> selectSelectiveOrderBySeqNo(RacAppMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(url, isEqualToWhenPresent(record::getUrl)).and(menu, isEqualToWhenPresent(record::getMenu)).and(remark, isEqualToWhenPresent(record::getRemark))
            .and(isEnabled, isEqualToWhenPresent(true)).and(imgUrl, isEqualToWhenPresent(record::getImgUrl)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(isCertified, isEqualToWhenPresent(record::getIsCertified)).orderBy(realmId, seqNo));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacAppMo> selectIn(List<String> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }

    /**
     * 根据顺序号排序查询
     */
    default List<RacAppMo> selectListOrderBySeqNo(RacAppMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(url, isEqualToWhenPresent(record::getUrl)).and(menu, isEqualToWhenPresent(record::getMenu)).and(remark, isEqualToWhenPresent(record::getRemark))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(imgUrl, isEqualToWhenPresent(record::getImgUrl)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(isCertified, isEqualToWhenPresent(record::getIsCertified)).orderBy(seqNo));
    }

    /**
     * 因删除应用而进行的应用顺序号更新
     *
     * @param record
     *
     * @return
     */
    default int updateSeqNoByDeleteAfter(@Param(value = "record") RacAppMo record) {
        return update(c -> c.set(seqNo).equalToConstant("SEQ_NO-1").where(realmId, isEqualTo(record::getRealmId)).and(seqNo, isGreaterThan(record::getSeqNo)));
    }

    /**
     * 根据帐号ID查询他可以看到的应用
     *
     * @param accountIds 账户ID集合
     *
     * @return
     */
    default List<RacAppMo> selectAppByAccountIds(List<Long> accountIds) {
        SelectStatementProvider select = null;
        select = SqlBuilder.select(racApp.allColumns()).from(racApp).leftJoin(racRoleApp).on(racApp.id, equalTo(racRoleApp.appId)).leftJoin(racRole)
            .on(racRole.id, equalTo(racRoleApp.roleId)).leftJoin(racAccountRole).on(racAccountRole.roleId, equalTo(racRole.id)).where(racAccountRole.accountId, isIn(accountIds))
            .groupBy(racApp.id).orderBy(racApp.seqNo).build().render(RenderingStrategies.MYBATIS3);
        return this.selectMany(select);
    }
}
