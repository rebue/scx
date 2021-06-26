package rebue.wxx.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.appSecret;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.encodeingAesKey;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.id;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.loginCallbackMethodType;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.loginCallbackSignkey;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.loginCallbackUrl;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.mchId;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.menu;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.name;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.subscribeAutoReply;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.token;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.wxpayNotifyUrl;
import static rebue.wxx.mapper.WxxAppDynamicSqlSupport.wxxApp;

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
import rebue.wxx.mo.WxxAppMo;

@Mapper
public interface WxxAppMapper extends MapperRootInterface<WxxAppMo, String> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, mchId, appSecret, token, encodeingAesKey, subscribeAutoReply, menu, loginCallbackUrl, loginCallbackMethodType,
        loginCallbackSignkey, wxpayNotifyUrl);

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
    int insert(InsertStatementProvider<WxxAppMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<WxxAppMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("WxxAppMoResult")
    Optional<WxxAppMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "WxxAppMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "MCH_ID", property = "mchId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "APP_SECRET", property = "appSecret", jdbcType = JdbcType.VARCHAR), @Result(column = "TOKEN", property = "token", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ENCODEING_AES_KEY", property = "encodeingAesKey", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SUBSCRIBE_AUTO_REPLY", property = "subscribeAutoReply", jdbcType = JdbcType.VARCHAR),
        @Result(column = "MENU", property = "menu", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOGIN_CALLBACK_URL", property = "loginCallbackUrl", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOGIN_CALLBACK_METHOD_TYPE", property = "loginCallbackMethodType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "LOGIN_CALLBACK_SIGNKEY", property = "loginCallbackSignkey", jdbcType = JdbcType.VARCHAR),
        @Result(column = "WXPAY_NOTIFY_URL", property = "wxpayNotifyUrl", jdbcType = JdbcType.VARCHAR)
    })
    List<WxxAppMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, wxxApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, wxxApp, completer);
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
    default int insert(WxxAppMo record) {
        return MyBatis3Utils.insert(this::insert, record, wxxApp,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(mchId).toProperty("mchId").map(appSecret).toProperty("appSecret").map(token).toProperty("token")
                .map(encodeingAesKey).toProperty("encodeingAesKey").map(subscribeAutoReply).toProperty("subscribeAutoReply").map(menu).toProperty("menu").map(loginCallbackUrl)
                .toProperty("loginCallbackUrl").map(loginCallbackMethodType).toProperty("loginCallbackMethodType").map(loginCallbackSignkey).toProperty("loginCallbackSignkey")
                .map(wxpayNotifyUrl).toProperty("wxpayNotifyUrl"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<WxxAppMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, wxxApp,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(mchId).toProperty("mchId").map(appSecret).toProperty("appSecret").map(token).toProperty("token")
                .map(encodeingAesKey).toProperty("encodeingAesKey").map(subscribeAutoReply).toProperty("subscribeAutoReply").map(menu).toProperty("menu").map(loginCallbackUrl)
                .toProperty("loginCallbackUrl").map(loginCallbackMethodType).toProperty("loginCallbackMethodType").map(loginCallbackSignkey).toProperty("loginCallbackSignkey")
                .map(wxpayNotifyUrl).toProperty("wxpayNotifyUrl"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(WxxAppMo record) {
        return MyBatis3Utils.insert(this::insert, record, wxxApp, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName)
            .map(mchId).toPropertyWhenPresent("mchId", record::getMchId).map(appSecret).toPropertyWhenPresent("appSecret", record::getAppSecret).map(token)
            .toPropertyWhenPresent("token", record::getToken).map(encodeingAesKey).toPropertyWhenPresent("encodeingAesKey", record::getEncodeingAesKey).map(subscribeAutoReply)
            .toPropertyWhenPresent("subscribeAutoReply", record::getSubscribeAutoReply).map(menu).toPropertyWhenPresent("menu", record::getMenu).map(loginCallbackUrl)
            .toPropertyWhenPresent("loginCallbackUrl", record::getLoginCallbackUrl).map(loginCallbackMethodType)
            .toPropertyWhenPresent("loginCallbackMethodType", record::getLoginCallbackMethodType).map(loginCallbackSignkey)
            .toPropertyWhenPresent("loginCallbackSignkey", record::getLoginCallbackSignkey).map(wxpayNotifyUrl).toPropertyWhenPresent("wxpayNotifyUrl", record::getWxpayNotifyUrl));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<WxxAppMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, wxxApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<WxxAppMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, wxxApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<WxxAppMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, wxxApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<WxxAppMo> selectByPrimaryKey(String id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, wxxApp, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(WxxAppMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(mchId).equalTo(record::getMchId).set(appSecret).equalTo(record::getAppSecret).set(token)
            .equalTo(record::getToken).set(encodeingAesKey).equalTo(record::getEncodeingAesKey).set(subscribeAutoReply).equalTo(record::getSubscribeAutoReply).set(menu)
            .equalTo(record::getMenu).set(loginCallbackUrl).equalTo(record::getLoginCallbackUrl).set(loginCallbackMethodType).equalTo(record::getLoginCallbackMethodType)
            .set(loginCallbackSignkey).equalTo(record::getLoginCallbackSignkey).set(wxpayNotifyUrl).equalTo(record::getWxpayNotifyUrl);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(WxxAppMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(mchId).equalToWhenPresent(record::getMchId).set(appSecret)
            .equalToWhenPresent(record::getAppSecret).set(token).equalToWhenPresent(record::getToken).set(encodeingAesKey).equalToWhenPresent(record::getEncodeingAesKey)
            .set(subscribeAutoReply).equalToWhenPresent(record::getSubscribeAutoReply).set(menu).equalToWhenPresent(record::getMenu).set(loginCallbackUrl)
            .equalToWhenPresent(record::getLoginCallbackUrl).set(loginCallbackMethodType).equalToWhenPresent(record::getLoginCallbackMethodType).set(loginCallbackSignkey)
            .equalToWhenPresent(record::getLoginCallbackSignkey).set(wxpayNotifyUrl).equalToWhenPresent(record::getWxpayNotifyUrl);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(WxxAppMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(mchId).equalTo(record::getMchId).set(appSecret).equalTo(record::getAppSecret).set(token)
            .equalTo(record::getToken).set(encodeingAesKey).equalTo(record::getEncodeingAesKey).set(subscribeAutoReply).equalTo(record::getSubscribeAutoReply).set(menu)
            .equalTo(record::getMenu).set(loginCallbackUrl).equalTo(record::getLoginCallbackUrl).set(loginCallbackMethodType).equalTo(record::getLoginCallbackMethodType)
            .set(loginCallbackSignkey).equalTo(record::getLoginCallbackSignkey).set(wxpayNotifyUrl).equalTo(record::getWxpayNotifyUrl).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(WxxAppMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(mchId).equalToWhenPresent(record::getMchId).set(appSecret).equalToWhenPresent(record::getAppSecret)
            .set(token).equalToWhenPresent(record::getToken).set(encodeingAesKey).equalToWhenPresent(record::getEncodeingAesKey).set(subscribeAutoReply)
            .equalToWhenPresent(record::getSubscribeAutoReply).set(menu).equalToWhenPresent(record::getMenu).set(loginCallbackUrl).equalToWhenPresent(record::getLoginCallbackUrl)
            .set(loginCallbackMethodType).equalToWhenPresent(record::getLoginCallbackMethodType).set(loginCallbackSignkey).equalToWhenPresent(record::getLoginCallbackSignkey)
            .set(wxpayNotifyUrl).equalToWhenPresent(record::getWxpayNotifyUrl).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(WxxAppMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(mchId, isEqualToWhenPresent(record::getMchId))
            .and(appSecret, isEqualToWhenPresent(record::getAppSecret)).and(token, isEqualToWhenPresent(record::getToken))
            .and(encodeingAesKey, isEqualToWhenPresent(record::getEncodeingAesKey)).and(subscribeAutoReply, isEqualToWhenPresent(record::getSubscribeAutoReply))
            .and(menu, isEqualToWhenPresent(record::getMenu)).and(loginCallbackUrl, isEqualToWhenPresent(record::getLoginCallbackUrl))
            .and(loginCallbackMethodType, isEqualToWhenPresent(record::getLoginCallbackMethodType)).and(loginCallbackSignkey, isEqualToWhenPresent(record::getLoginCallbackSignkey))
            .and(wxpayNotifyUrl, isEqualToWhenPresent(record::getWxpayNotifyUrl)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<WxxAppMo> selectOne(WxxAppMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(mchId, isEqualToWhenPresent(record::getMchId))
            .and(appSecret, isEqualToWhenPresent(record::getAppSecret)).and(token, isEqualToWhenPresent(record::getToken))
            .and(encodeingAesKey, isEqualToWhenPresent(record::getEncodeingAesKey)).and(subscribeAutoReply, isEqualToWhenPresent(record::getSubscribeAutoReply))
            .and(menu, isEqualToWhenPresent(record::getMenu)).and(loginCallbackUrl, isEqualToWhenPresent(record::getLoginCallbackUrl))
            .and(loginCallbackMethodType, isEqualToWhenPresent(record::getLoginCallbackMethodType)).and(loginCallbackSignkey, isEqualToWhenPresent(record::getLoginCallbackSignkey))
            .and(wxpayNotifyUrl, isEqualToWhenPresent(record::getWxpayNotifyUrl)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(WxxAppMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(mchId, isEqualToWhenPresent(record::getMchId))
            .and(appSecret, isEqualToWhenPresent(record::getAppSecret)).and(token, isEqualToWhenPresent(record::getToken))
            .and(encodeingAesKey, isEqualToWhenPresent(record::getEncodeingAesKey)).and(subscribeAutoReply, isEqualToWhenPresent(record::getSubscribeAutoReply))
            .and(menu, isEqualToWhenPresent(record::getMenu)).and(loginCallbackUrl, isEqualToWhenPresent(record::getLoginCallbackUrl))
            .and(loginCallbackMethodType, isEqualToWhenPresent(record::getLoginCallbackMethodType)).and(loginCallbackSignkey, isEqualToWhenPresent(record::getLoginCallbackSignkey))
            .and(wxpayNotifyUrl, isEqualToWhenPresent(record::getWxpayNotifyUrl)));
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
    default boolean existSelective(WxxAppMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<WxxAppMo> selectSelective(WxxAppMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(mchId, isEqualToWhenPresent(record::getMchId))
            .and(appSecret, isEqualToWhenPresent(record::getAppSecret)).and(token, isEqualToWhenPresent(record::getToken))
            .and(encodeingAesKey, isEqualToWhenPresent(record::getEncodeingAesKey)).and(subscribeAutoReply, isEqualToWhenPresent(record::getSubscribeAutoReply))
            .and(menu, isEqualToWhenPresent(record::getMenu)).and(loginCallbackUrl, isEqualToWhenPresent(record::getLoginCallbackUrl))
            .and(loginCallbackMethodType, isEqualToWhenPresent(record::getLoginCallbackMethodType)).and(loginCallbackSignkey, isEqualToWhenPresent(record::getLoginCallbackSignkey))
            .and(wxpayNotifyUrl, isEqualToWhenPresent(record::getWxpayNotifyUrl)));
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
    default List<WxxAppMo> selectIn(List<String> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
