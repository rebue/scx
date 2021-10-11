package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.or;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.code;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.createTimestamp;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.ddAvatar;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.ddNickname;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.ddOpenId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.ddUnionId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.ddUserId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.isEnabled;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.isTester;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.orgId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.payPswd;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.payPswdSalt;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.qqAvatar;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.qqNickname;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.qqOpenId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.qqUnionId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.racAccount;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInAvatar;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInEmail;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInMobile;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInName;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInNickname;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInPswd;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInPswdSalt;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.unionId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.updateTimestamp;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.userId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxAvatar;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxNickname;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxOpenId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxUnionId;
import static rebue.scx.rac.mapper.RacLockLogDynamicSqlSupport.racLockLog;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.racOrgAccount;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.racOrg;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.racUser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlCriterion;
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
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.ex.RacAccountExMo;
import rebue.scx.rac.mo.ex.RacLockLogAndAccountMo;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.wheel.core.NumberUtils;

@Mapper
public interface RacAccountMapper extends MapperRootInterface<RacAccountMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, unionId, userId, remark, orgId, code, realmId, isEnabled, signInName, signInMobile, signInEmail, signInPswd,
            signInPswdSalt, payPswd, payPswdSalt, signInNickname, signInAvatar, wxOpenId, wxUnionId, wxNickname, wxAvatar, qqOpenId, qqUnionId, qqNickname, qqAvatar, ddOpenId,
            ddUnionId, ddUserId, ddNickname, ddAvatar, isTester, createTimestamp, updateTimestamp);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RacAccountMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacAccountMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacAccountMoResult")
    Optional<RacAccountMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacAccountMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "UNION_ID", property = "unionId", jdbcType = JdbcType.BIGINT), @Result(column = "USER_ID", property = "userId", jdbcType = JdbcType.BIGINT),
            @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR), @Result(column = "ORG_ID", property = "orgId", jdbcType = JdbcType.BIGINT),
            @Result(column = "CODE", property = "code", jdbcType = JdbcType.VARCHAR), @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT), @Result(column = "SIGN_IN_NAME", property = "signInName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_MOBILE", property = "signInMobile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_EMAIL", property = "signInEmail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_PSWD", property = "signInPswd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_PSWD_SALT", property = "signInPswdSalt", jdbcType = JdbcType.CHAR),
            @Result(column = "PAY_PSWD", property = "payPswd", jdbcType = JdbcType.VARCHAR), @Result(column = "PAY_PSWD_SALT", property = "payPswdSalt", jdbcType = JdbcType.CHAR),
            @Result(column = "SIGN_IN_NICKNAME", property = "signInNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_AVATAR", property = "signInAvatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_OPEN_ID", property = "wxOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_UNION_ID", property = "wxUnionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_NICKNAME", property = "wxNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_AVATAR", property = "wxAvatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_OPEN_ID", property = "qqOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_UNION_ID", property = "qqUnionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_NICKNAME", property = "qqNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_AVATAR", property = "qqAvatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_OPEN_ID", property = "ddOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_UNION_ID", property = "ddUnionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_USER_ID", property = "ddUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_NICKNAME", property = "ddNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_AVATAR", property = "ddAvatar", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_TESTER", property = "isTester", jdbcType = JdbcType.BIT),
            @Result(column = "CREATE_TIMESTAMP", property = "createTimestamp", jdbcType = JdbcType.BIGINT),
            @Result(column = "UPDATE_TIMESTAMP", property = "updateTimestamp", jdbcType = JdbcType.BIGINT),
            @Result(column = "REAL_NAME", property = "realName", jdbcType = JdbcType.VARCHAR)
    })
    List<RacAccountMo> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacAccountMoAndLockResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "USER_ID", property = "userId", jdbcType = JdbcType.BIGINT), @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORG_ID", property = "orgId", jdbcType = JdbcType.BIGINT), @Result(column = "CODE", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT),
            @Result(column = "SIGN_IN_NAME", property = "signInName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_MOBILE", property = "signInMobile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_EMAIL", property = "signInEmail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_NICKNAME", property = "signInNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SIGN_IN_AVATAR", property = "signInAvatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_OPEN_ID", property = "wxOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_UNION_ID", property = "wxUnionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_NICKNAME", property = "wxNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WX_AVATAR", property = "wxAvatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_OPEN_ID", property = "qqOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_UNION_ID", property = "qqUnionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_NICKNAME", property = "qqNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "QQ_AVATAR", property = "qqAvatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_OPEN_ID", property = "ddOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_UNION_ID", property = "ddUnionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_USER_ID", property = "ddUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_NICKNAME", property = "ddNickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DD_AVATAR", property = "ddAvatar", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_TESTER", property = "isTester", jdbcType = JdbcType.BIT),
            @Result(column = "CREATE_TIMESTAMP", property = "createTimestamp", jdbcType = JdbcType.BIGINT),
            @Result(column = "UPDATE_TIMESTAMP", property = "updateTimestamp", jdbcType = JdbcType.BIGINT),
            @Result(column = "lockLogId", property = "lockLogId", jdbcType = JdbcType.BIGINT),
            @Result(column = "LOCK_ACCOUNT_ID", property = "lockAccountId", jdbcType = JdbcType.BIGINT),
            @Result(column = "LOCK_DATETIME", property = "lockDatetime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "AUTO_UNLOCK_DATETIME", property = "autoUnlockDatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RacLockLogAndAccountMo> selectManyAndLock(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insert(RacAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccount,
                c -> c.map(id).toProperty("id").map(unionId).toProperty("unionId").map(userId).toProperty("userId").map(remark).toProperty("remark").map(orgId).toProperty("orgId")
                        .map(code).toProperty("code").map(realmId).toProperty("realmId").map(isEnabled).toProperty("isEnabled").map(signInName).toProperty("signInName")
                        .map(signInMobile)
                        .toProperty("signInMobile").map(signInEmail).toProperty("signInEmail").map(signInPswd).toProperty("signInPswd").map(signInPswdSalt)
                        .toProperty("signInPswdSalt")
                        .map(payPswd).toProperty("payPswd").map(payPswdSalt).toProperty("payPswdSalt").map(signInNickname).toProperty("signInNickname").map(signInAvatar)
                        .toProperty("signInAvatar").map(wxOpenId).toProperty("wxOpenId").map(wxUnionId).toProperty("wxUnionId").map(wxNickname).toProperty("wxNickname")
                        .map(wxAvatar)
                        .toProperty("wxAvatar").map(qqOpenId).toProperty("qqOpenId").map(qqUnionId).toProperty("qqUnionId").map(qqNickname).toProperty("qqNickname").map(qqAvatar)
                        .toProperty("qqAvatar").map(ddOpenId).toProperty("ddOpenId").map(ddUnionId).toProperty("ddUnionId").map(ddUserId).toProperty("ddUserId").map(ddNickname)
                        .toProperty("ddNickname").map(ddAvatar).toProperty("ddAvatar").map(isTester).toProperty("isTester").map(createTimestamp).toProperty("createTimestamp")
                        .map(updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(Collection<RacAccountMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racAccount,
                c -> c.map(id).toProperty("id").map(unionId).toProperty("unionId").map(userId).toProperty("userId").map(remark).toProperty("remark").map(orgId).toProperty("orgId")
                        .map(code).toProperty("code").map(realmId).toProperty("realmId").map(isEnabled).toProperty("isEnabled").map(signInName).toProperty("signInName")
                        .map(signInMobile)
                        .toProperty("signInMobile").map(signInEmail).toProperty("signInEmail").map(signInPswd).toProperty("signInPswd").map(signInPswdSalt)
                        .toProperty("signInPswdSalt")
                        .map(payPswd).toProperty("payPswd").map(payPswdSalt).toProperty("payPswdSalt").map(signInNickname).toProperty("signInNickname").map(signInAvatar)
                        .toProperty("signInAvatar").map(wxOpenId).toProperty("wxOpenId").map(wxUnionId).toProperty("wxUnionId").map(wxNickname).toProperty("wxNickname")
                        .map(wxAvatar)
                        .toProperty("wxAvatar").map(qqOpenId).toProperty("qqOpenId").map(qqUnionId).toProperty("qqUnionId").map(qqNickname).toProperty("qqNickname").map(qqAvatar)
                        .toProperty("qqAvatar").map(ddOpenId).toProperty("ddOpenId").map(ddUnionId).toProperty("ddUnionId").map(ddUserId).toProperty("ddUserId").map(ddNickname)
                        .toProperty("ddNickname").map(ddAvatar).toProperty("ddAvatar").map(isTester).toProperty("isTester").map(createTimestamp).toProperty("createTimestamp")
                        .map(updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(RacAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccount,
                c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(unionId).toPropertyWhenPresent("unionId", record::getUnionId).map(userId)
                        .toPropertyWhenPresent("userId", record::getUserId).map(remark).toPropertyWhenPresent("remark", record::getRemark).map(orgId)
                        .toPropertyWhenPresent("orgId", record::getOrgId).map(code).toPropertyWhenPresent("code", record::getCode).map(realmId)
                        .toPropertyWhenPresent("realmId", record::getRealmId).map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(signInName)
                        .toPropertyWhenPresent("signInName", record::getSignInName).map(signInMobile).toPropertyWhenPresent("signInMobile", record::getSignInMobile)
                        .map(signInEmail)
                        .toPropertyWhenPresent("signInEmail", record::getSignInEmail).map(signInPswd).toPropertyWhenPresent("signInPswd", record::getSignInPswd).map(signInPswdSalt)
                        .toPropertyWhenPresent("signInPswdSalt", record::getSignInPswdSalt).map(payPswd).toPropertyWhenPresent("payPswd", record::getPayPswd).map(payPswdSalt)
                        .toPropertyWhenPresent("payPswdSalt", record::getPayPswdSalt).map(signInNickname).toPropertyWhenPresent("signInNickname", record::getSignInNickname)
                        .map(signInAvatar).toPropertyWhenPresent("signInAvatar", record::getSignInAvatar).map(wxOpenId).toPropertyWhenPresent("wxOpenId", record::getWxOpenId)
                        .map(wxUnionId).toPropertyWhenPresent("wxUnionId", record::getWxUnionId).map(wxNickname).toPropertyWhenPresent("wxNickname", record::getWxNickname)
                        .map(wxAvatar)
                        .toPropertyWhenPresent("wxAvatar", record::getWxAvatar).map(qqOpenId).toPropertyWhenPresent("qqOpenId", record::getQqOpenId).map(qqUnionId)
                        .toPropertyWhenPresent("qqUnionId", record::getQqUnionId).map(qqNickname).toPropertyWhenPresent("qqNickname", record::getQqNickname).map(qqAvatar)
                        .toPropertyWhenPresent("qqAvatar", record::getQqAvatar).map(ddOpenId).toPropertyWhenPresent("ddOpenId", record::getDdOpenId).map(ddUnionId)
                        .toPropertyWhenPresent("ddUnionId", record::getDdUnionId).map(ddUserId).toPropertyWhenPresent("ddUserId", record::getDdUserId).map(ddNickname)
                        .toPropertyWhenPresent("ddNickname", record::getDdNickname).map(ddAvatar).toPropertyWhenPresent("ddAvatar", record::getDdAvatar).map(isTester)
                        .toPropertyWhenPresent("isTester", record::getIsTester).map(createTimestamp).toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp)
                        .map(updateTimestamp).toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacAccountMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(unionId).equalTo(record::getUnionId).set(userId).equalTo(record::getUserId).set(remark).equalTo(record::getRemark).set(orgId)
                .equalTo(record::getOrgId).set(code).equalTo(record::getCode).set(realmId).equalTo(record::getRealmId).set(isEnabled).equalTo(record::getIsEnabled).set(signInName)
                .equalTo(record::getSignInName).set(signInMobile).equalTo(record::getSignInMobile).set(signInEmail).equalTo(record::getSignInEmail).set(signInPswd)
                .equalTo(record::getSignInPswd).set(signInPswdSalt).equalTo(record::getSignInPswdSalt).set(payPswd).equalTo(record::getPayPswd).set(payPswdSalt)
                .equalTo(record::getPayPswdSalt).set(signInNickname).equalTo(record::getSignInNickname).set(signInAvatar).equalTo(record::getSignInAvatar).set(wxOpenId)
                .equalTo(record::getWxOpenId).set(wxUnionId).equalTo(record::getWxUnionId).set(wxNickname).equalTo(record::getWxNickname).set(wxAvatar).equalTo(record::getWxAvatar)
                .set(qqOpenId).equalTo(record::getQqOpenId).set(qqUnionId).equalTo(record::getQqUnionId).set(qqNickname).equalTo(record::getQqNickname).set(qqAvatar)
                .equalTo(record::getQqAvatar).set(ddOpenId).equalTo(record::getDdOpenId).set(ddUnionId).equalTo(record::getDdUnionId).set(ddUserId).equalTo(record::getDdUserId)
                .set(ddNickname).equalTo(record::getDdNickname).set(ddAvatar).equalTo(record::getDdAvatar).set(isTester).equalTo(record::getIsTester).set(createTimestamp)
                .equalTo(record::getCreateTimestamp).set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacAccountMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(unionId).equalToWhenPresent(record::getUnionId).set(userId).equalToWhenPresent(record::getUserId).set(remark)
                .equalToWhenPresent(record::getRemark).set(orgId).equalToWhenPresent(record::getOrgId).set(code).equalToWhenPresent(record::getCode).set(realmId)
                .equalToWhenPresent(record::getRealmId).set(isEnabled).equalToWhenPresent(record::getIsEnabled).set(signInName).equalToWhenPresent(record::getSignInName)
                .set(signInMobile).equalToWhenPresent(record::getSignInMobile).set(signInEmail).equalToWhenPresent(record::getSignInEmail).set(signInPswd)
                .equalToWhenPresent(record::getSignInPswd).set(signInPswdSalt).equalToWhenPresent(record::getSignInPswdSalt).set(payPswd).equalToWhenPresent(record::getPayPswd)
                .set(payPswdSalt).equalToWhenPresent(record::getPayPswdSalt).set(signInNickname).equalToWhenPresent(record::getSignInNickname).set(signInAvatar)
                .equalToWhenPresent(record::getSignInAvatar).set(wxOpenId).equalToWhenPresent(record::getWxOpenId).set(wxUnionId).equalToWhenPresent(record::getWxUnionId)
                .set(wxNickname).equalToWhenPresent(record::getWxNickname).set(wxAvatar).equalToWhenPresent(record::getWxAvatar).set(qqOpenId)
                .equalToWhenPresent(record::getQqOpenId)
                .set(qqUnionId).equalToWhenPresent(record::getQqUnionId).set(qqNickname).equalToWhenPresent(record::getQqNickname).set(qqAvatar)
                .equalToWhenPresent(record::getQqAvatar)
                .set(ddOpenId).equalToWhenPresent(record::getDdOpenId).set(ddUnionId).equalToWhenPresent(record::getDdUnionId).set(ddUserId).equalToWhenPresent(record::getDdUserId)
                .set(ddNickname).equalToWhenPresent(record::getDdNickname).set(ddAvatar).equalToWhenPresent(record::getDdAvatar).set(isTester)
                .equalToWhenPresent(record::getIsTester)
                .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp).set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(RacAccountMo record) {
        return update(c -> c.set(unionId).equalTo(record::getUnionId).set(userId).equalTo(record::getUserId).set(remark).equalTo(record::getRemark).set(orgId)
                .equalTo(record::getOrgId).set(code).equalTo(record::getCode).set(realmId).equalTo(record::getRealmId).set(isEnabled).equalTo(record::getIsEnabled).set(signInName)
                .equalTo(record::getSignInName).set(signInMobile).equalTo(record::getSignInMobile).set(signInEmail).equalTo(record::getSignInEmail).set(signInPswd)
                .equalTo(record::getSignInPswd).set(signInPswdSalt).equalTo(record::getSignInPswdSalt).set(payPswd).equalTo(record::getPayPswd).set(payPswdSalt)
                .equalTo(record::getPayPswdSalt).set(signInNickname).equalTo(record::getSignInNickname).set(signInAvatar).equalTo(record::getSignInAvatar).set(wxOpenId)
                .equalTo(record::getWxOpenId).set(wxUnionId).equalTo(record::getWxUnionId).set(wxNickname).equalTo(record::getWxNickname).set(wxAvatar).equalTo(record::getWxAvatar)
                .set(qqOpenId).equalTo(record::getQqOpenId).set(qqUnionId).equalTo(record::getQqUnionId).set(qqNickname).equalTo(record::getQqNickname).set(qqAvatar)
                .equalTo(record::getQqAvatar).set(ddOpenId).equalTo(record::getDdOpenId).set(ddUnionId).equalTo(record::getDdUnionId).set(ddUserId).equalTo(record::getDdUserId)
                .set(ddNickname).equalTo(record::getDdNickname).set(ddAvatar).equalTo(record::getDdAvatar).set(isTester).equalTo(record::getIsTester).set(createTimestamp)
                .equalTo(record::getCreateTimestamp).set(updateTimestamp).equalTo(record::getUpdateTimestamp).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(RacAccountMo record) {
        return update(c -> c.set(unionId).equalToWhenPresent(record::getUnionId).set(userId).equalToWhenPresent(record::getUserId).set(remark).equalToWhenPresent(record::getRemark)
                .set(orgId).equalToWhenPresent(record::getOrgId).set(code).equalToWhenPresent(record::getCode).set(realmId).equalToWhenPresent(record::getRealmId).set(isEnabled)
                .equalToWhenPresent(record::getIsEnabled).set(signInName).equalToWhenPresent(record::getSignInName).set(signInMobile).equalToWhenPresent(record::getSignInMobile)
                .set(signInEmail).equalToWhenPresent(record::getSignInEmail).set(signInPswd).equalToWhenPresent(record::getSignInPswd).set(signInPswdSalt)
                .equalToWhenPresent(record::getSignInPswdSalt).set(payPswd).equalToWhenPresent(record::getPayPswd).set(payPswdSalt).equalToWhenPresent(record::getPayPswdSalt)
                .set(signInNickname).equalToWhenPresent(record::getSignInNickname).set(signInAvatar).equalToWhenPresent(record::getSignInAvatar).set(wxOpenId)
                .equalToWhenPresent(record::getWxOpenId).set(wxUnionId).equalToWhenPresent(record::getWxUnionId).set(wxNickname).equalToWhenPresent(record::getWxNickname)
                .set(wxAvatar)
                .equalToWhenPresent(record::getWxAvatar).set(qqOpenId).equalToWhenPresent(record::getQqOpenId).set(qqUnionId).equalToWhenPresent(record::getQqUnionId)
                .set(qqNickname)
                .equalToWhenPresent(record::getQqNickname).set(qqAvatar).equalToWhenPresent(record::getQqAvatar).set(ddOpenId).equalToWhenPresent(record::getDdOpenId)
                .set(ddUnionId)
                .equalToWhenPresent(record::getDdUnionId).set(ddUserId).equalToWhenPresent(record::getDdUserId).set(ddNickname).equalToWhenPresent(record::getDdNickname)
                .set(ddAvatar)
                .equalToWhenPresent(record::getDdAvatar).set(isTester).equalToWhenPresent(record::getIsTester).set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
                .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(RacAccountMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(unionId, isEqualToWhenPresent(record::getUnionId))
                .and(userId, isEqualToWhenPresent(record::getUserId)).and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(signInName, isEqualToWhenPresent(record::getSignInName)).and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
                .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail)).and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
                .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt)).and(payPswd, isEqualToWhenPresent(record::getPayPswd))
                .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt)).and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
                .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar)).and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
                .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId)).and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
                .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar)).and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
                .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId)).and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
                .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar)).and(ddOpenId, isEqualToWhenPresent(record::getDdOpenId))
                .and(ddUnionId, isEqualToWhenPresent(record::getDdUnionId)).and(ddUserId, isEqualToWhenPresent(record::getDdUserId))
                .and(ddNickname, isEqualToWhenPresent(record::getDdNickname)).and(ddAvatar, isEqualToWhenPresent(record::getDdAvatar))
                .and(isTester, isEqualToWhenPresent(record::getIsTester)).and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
                .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountMo> selectOne(RacAccountMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(unionId, isEqualToWhenPresent(record::getUnionId))
                .and(userId, isEqualToWhenPresent(record::getUserId)).and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(signInName, isEqualToWhenPresent(record::getSignInName)).and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
                .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail)).and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
                .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt)).and(payPswd, isEqualToWhenPresent(record::getPayPswd))
                .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt)).and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
                .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar)).and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
                .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId)).and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
                .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar)).and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
                .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId)).and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
                .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar)).and(ddOpenId, isEqualToWhenPresent(record::getDdOpenId))
                .and(ddUnionId, isEqualToWhenPresent(record::getDdUnionId)).and(ddUserId, isEqualToWhenPresent(record::getDdUserId))
                .and(ddNickname, isEqualToWhenPresent(record::getDdNickname)).and(ddAvatar, isEqualToWhenPresent(record::getDdAvatar))
                .and(isTester, isEqualToWhenPresent(record::getIsTester)).and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
                .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(RacAccountMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(unionId, isEqualToWhenPresent(record::getUnionId))
                .and(userId, isEqualToWhenPresent(record::getUserId)).and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(signInName, isEqualToWhenPresent(record::getSignInName)).and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
                .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail)).and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
                .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt)).and(payPswd, isEqualToWhenPresent(record::getPayPswd))
                .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt)).and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
                .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar)).and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
                .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId)).and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
                .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar)).and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
                .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId)).and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
                .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar)).and(ddOpenId, isEqualToWhenPresent(record::getDdOpenId))
                .and(ddUnionId, isEqualToWhenPresent(record::getDdUnionId)).and(ddUserId, isEqualToWhenPresent(record::getDdUserId))
                .and(ddNickname, isEqualToWhenPresent(record::getDdNickname)).and(ddAvatar, isEqualToWhenPresent(record::getDdAvatar))
                .and(isTester, isEqualToWhenPresent(record::getIsTester)).and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
                .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existByPrimaryKey(Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existSelective(RacAccountMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> selectSelective(RacAccountMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(unionId, isEqualToWhenPresent(record::getUnionId))
                .and(userId, isEqualToWhenPresent(record::getUserId)).and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
                .and(signInName, isEqualToWhenPresent(record::getSignInName)).and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
                .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail)).and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
                .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt)).and(payPswd, isEqualToWhenPresent(record::getPayPswd))
                .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt)).and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
                .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar)).and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
                .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId)).and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
                .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar)).and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
                .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId)).and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
                .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar)).and(ddOpenId, isEqualToWhenPresent(record::getDdOpenId))
                .and(ddUnionId, isEqualToWhenPresent(record::getDdUnionId)).and(ddUserId, isEqualToWhenPresent(record::getDdUserId))
                .and(ddNickname, isEqualToWhenPresent(record::getDdNickname)).and(ddAvatar, isEqualToWhenPresent(record::getDdAvatar))
                .and(isTester, isEqualToWhenPresent(record::getIsTester)).and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
                .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * 查询当前组织下存在的账户
     *
     * @param record #{record.orgId}
     *
     * @return
     */
    default List<RacAccountMo> list(final RacAccountListTo qo) {
        final String               keywords     = StringUtils.isBlank(qo.getKeywords()) ? null : "%" + qo.getKeywords() + "%";
        final String //
                                   hierarchical = ((qo.getOrgId() != null) && qo.getHierarchical() != null && qo.getHierarchical()) ? qo.getOrgTreeCode() + "%" : null;
        final SqlCriterion<String> sqlCriterion = and(racAccount.signInNickname, isLikeWhenPresent(keywords), or(racAccount.signInName, isLikeWhenPresent(keywords)),
                or(racAccount.id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
                or(racAccount.signInEmail, isLikeWhenPresent(keywords)), or(racAccount.signInMobile, isLikeWhenPresent(keywords)),
                or(racAccount.qqNickname, isLikeWhenPresent(keywords)), or(racAccount.qqOpenId, isLikeWhenPresent(keywords)), or(racAccount.qqUnionId, isLikeWhenPresent(keywords)),
                or(racAccount.wxNickname, isLikeWhenPresent(keywords)), or(racAccount.wxOpenId, isLikeWhenPresent(keywords)), or(racAccount.wxUnionId, isLikeWhenPresent(keywords)),
                or(racAccount.remark, isLikeWhenPresent(keywords)));
        if (qo.getOrgId() != null) {
            if (hierarchical != null) {
                SelectStatementProvider select = SqlBuilder.select(racAccount.allColumns(), racUser.realName).from(racAccount)
                        .join(racOrgAccount).on(racAccount.id, equalTo(racOrgAccount.accountId)).leftJoin(racOrg).on(racOrg.id, equalTo(racOrgAccount.orgId))
                        .leftJoin(racUser).on(racUser.id, equalTo(racAccount.userId))
                        .where(racOrg.treeCode, isLike(hierarchical), and(racAccount.realmId, isEqualToWhenPresent(qo.getRealmId()), sqlCriterion)).groupBy(racAccount.id)
                        .build().render(RenderingStrategies.MYBATIS3);
                return this.selectMany(select);
            }
            else {
                SelectStatementProvider select = SqlBuilder.select(racAccount.allColumns(), racUser.realName).from(racAccount)
                        .join(racOrgAccount).on(racAccount.id, equalTo(racOrgAccount.accountId))
                        .leftJoin(racUser).on(racUser.id, equalTo(racAccount.userId))
                        .where(racOrgAccount.orgId, isEqualTo(qo::getOrgId), and(racAccount.realmId, isEqualToWhenPresent(qo.getRealmId()), sqlCriterion))
                        .groupBy(racAccount.id)
                        .build().render(RenderingStrategies.MYBATIS3);
                return this.selectMany(select);
            }
        }
        else {
            SelectStatementProvider select = SqlBuilder.select(racAccount.allColumns(), racUser.realName).from(racAccount)
                    .leftJoin(racUser).on(racUser.id, equalTo(racAccount.userId))
                    .where(racAccount.realmId, isEqualToWhenPresent(qo.getRealmId()), sqlCriterion).groupBy(racAccount.id)
                    .build().render(RenderingStrategies.MYBATIS3);
            return this.selectMany(select);
            // return c.where(racAccount.realmId, isEqualToWhenPresent(qo.getRealmId()), sqlCriterion).groupBy(racAccount.id);
        }
        // });
    }

    /**
     * 查询当前组织下未存在的账户
     *
     * @param record #{record.orgId}
     *
     * @return
     */
    @Select({ "<script>" + "SELECT     ac.* FROM    RAC_ACCOUNT ac   LEFT JOIN " + "    RAC_ORG_ACCOUNT oac ON ac.ID = oac.ACCOUNT_ID " + "WHERE " + "   "
            + " ac.REALM_ID = #{record.realmId}" + "<if test='record.keywords!=null'> " + " and (ac.SIGN_IN_NAME like '%${record.keywords}%' "
            + " or ac.SIGN_IN_MOBILE like '%${record.keywords}%' " + " or ac.SIGN_IN_EMAIL like '%${record.keywords}%' " + " or ac.REMARK like '%${record.keywords}%' "
            + " or ac.SIGN_IN_NICKNAME like '%${record.keywords}%' " + " or ac.WX_NICKNAME like '%${record.keywords}%' " + " or ac.QQ_NICKNAME like '%${record.keywords}%' "
            + " or ac.ID like '%${record.keywords}%' ) " + "</if> " + "        AND ((ac.ORG_ID IS NULL " + "        OR NOT EXISTS( SELECT  " + "            a.ID " + "        FROM "
            + "            RAC_ORG_ACCOUNT a " + "        WHERE " + "            a.ORG_ID = #{record.orgId} " + "                AND ac.ID = a.ACCOUNT_ID))) " + "GROUP BY ac.ID "
            + "</script>"
    })
    List<RacAccountMo> getAddablAccountList(@Param(value = "record") RacAccountExMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * 根据关键字查询登录被锁定的账户
     */
    default List<RacAccountMo> selectIn(List<Long> ids, String keywords) {
        final SqlCriterion<String> sqlCriterion = and(racAccount.signInNickname, isLikeWhenPresent(keywords), or(racAccount.signInName, isLikeWhenPresent(keywords)),
                or(racAccount.id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
                or(racAccount.signInEmail, isLikeWhenPresent(keywords)),
                or(racAccount.signInMobile, isLikeWhenPresent(keywords)), or(racAccount.qqNickname, isLikeWhenPresent(keywords)),
                or(racAccount.qqOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.qqUnionId, isLikeWhenPresent(keywords)), or(racAccount.wxNickname, isLikeWhenPresent(keywords)), or(racAccount.wxOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.wxUnionId, isLikeWhenPresent(keywords)), or(racAccount.remark, isLikeWhenPresent(keywords)));
        return select(c -> c.where(id, isIn(ids), sqlCriterion));
    }

    /**
     * 根据关键字查询登录被锁定的账户
     */
    default List<RacLockLogAndAccountMo> selectLockAccount(RacAccountPageTo qo, String likeDate) {
        final String               keywords     = StringUtils.isBlank(qo.getKeywords()) ? null : "%" + qo.getKeywords() + "%";
        final String //
                                   hierarchical = ((qo.getOrgId() != null) && qo.getHierarchical() != null && qo.getHierarchical()) ? qo.getOrgTreeCode() + "%" : null;
        final SqlCriterion<String> sqlCriterion = and(racAccount.signInNickname, isLikeWhenPresent(keywords), or(racAccount.signInName, isLikeWhenPresent(keywords)),
                or(racAccount.id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
                or(racAccount.signInEmail, isLikeWhenPresent(keywords)),
                or(racAccount.signInMobile, isLikeWhenPresent(keywords)), or(racAccount.qqNickname, isLikeWhenPresent(keywords)),
                or(racAccount.qqOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.qqUnionId, isLikeWhenPresent(keywords)), or(racAccount.wxNickname, isLikeWhenPresent(keywords)), or(racAccount.wxOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.wxUnionId, isLikeWhenPresent(keywords)), or(racAccount.remark, isLikeWhenPresent(keywords)));
        SelectStatementProvider    select       = null;
        if (qo.getOrgId() != null) {
            if (hierarchical != null) {
                select = SqlBuilder.select(racAccount.allColumns(), racLockLog.allColumns(), racLockLog.id.as("lockLogId")).from(racAccount)
                        .rightJoin(racLockLog)
                        .on(racAccount.id, equalTo(racLockLog.lockAccountId)).rightJoin(racOrgAccount).on(racAccount.id, equalTo(racOrgAccount.accountId)).leftJoin(racOrg)
                        .on(racOrg.id, equalTo(racOrgAccount.orgId))
                        .where(racAccount.id, isEqualTo(racLockLog.lockAccountId), and(racAccount.realmId, isEqualToWhenPresent(qo::getRealmId)),
                                and(racLockLog.unlockDatetime, SqlBuilder.isNull()),// and(racOrgAccount.orgId, isEqualToWhenPresent(qo::getOrgId)),
                                and(racLockLog.column("LOCK_DATETIME"), isLikeWhenPresent(likeDate)), and(racOrg.treeCode, isLike(hierarchical)),
                                sqlCriterion)
                        // .groupBy(racOrgAccount.accountId)
                        .build().render(RenderingStrategies.MYBATIS3);
            }
            else {
                select = SqlBuilder.select(racAccount.allColumns(), racLockLog.allColumns(), racLockLog.id.as("lockLogId")).from(racAccount)
                        .rightJoin(racLockLog)
                        .on(racAccount.id, equalTo(racLockLog.lockAccountId)).rightJoin(racOrgAccount).on(racAccount.id, equalTo(racOrgAccount.accountId))
                        .where(racAccount.id, isEqualTo(racLockLog.lockAccountId), and(racAccount.realmId, isEqualToWhenPresent(qo::getRealmId)),
                                and(racLockLog.unlockDatetime, SqlBuilder.isNull()), and(racOrgAccount.orgId, isEqualToWhenPresent(qo::getOrgId)),
                                and(racLockLog.column("LOCK_DATETIME"), isLikeWhenPresent(likeDate)), sqlCriterion)
                        .build().render(RenderingStrategies.MYBATIS3);
            }
        }
        else {
            select = SqlBuilder.select(racAccount.allColumns(), racLockLog.allColumns(), racLockLog.id.as("lockLogId")).from(racAccount)
                    .rightJoin(racLockLog)
                    .on(racAccount.id, equalTo(racLockLog.lockAccountId))
                    .where(racAccount.id, isEqualTo(racLockLog.lockAccountId), and(racAccount.realmId, isEqualToWhenPresent(qo::getRealmId)),
                            and(racLockLog.unlockDatetime, SqlBuilder.isNull()),
                            and(racLockLog.column("LOCK_DATETIME"), isLikeWhenPresent(likeDate)), sqlCriterion)
                    .build().render(RenderingStrategies.MYBATIS3);
        }

        return this.selectManyAndLock(select);
    }

    /**
     * 查询带有用户的账户列表
     *
     * @param qo 查询条件
     *
     * @return 查询到的分页信息
     */
    @Select({
            "<script>" + "SELECT \n" + "    ac.ID accountId,ac.*,us.*\n" + "FROM\n" + "    rac.RAC_ACCOUNT ac\n" + "        LEFT JOIN\n" + "    RAC_USER us ON ac.USER_ID = us.ID "
                    + "WHERE 1=1 " + "<if test='record.orgId!=null'>" + " and ac.ORG_ID = #{record.orgId}" + "</if>" + "<if test='record.keywords!=null'>"
                    + "  and( us.ID_CARD like '%${record.keywords}%' or us.MOBILE like '%${record.keywords}%' or us.REAL_NAME like '%${record.keywords}%')" + "</if>" + "</script>"
    })
    List<RacUserAccountMo> pageAccountMos(@Param(value = "record") RacAccountPageTo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default BasicColumn[] getColumns() {
        return selectList;
    }

    /**
     * 解除钉钉绑定
     *
     * @param id
     *
     * @return
     */
    default int unbindDingTalk(Long id) {
        UpdateStatementProvider update = SqlBuilder.update(racAccount).set(ddAvatar).equalToNull().set(ddNickname).equalToNull().set(ddOpenId).equalToNull().set(ddUnionId)
                .equalToNull().set(ddUserId).equalToNull().where(racAccount.id, isEqualTo(id)).build().render(RenderingStrategies.MYBATIS3);
        return this.update(update);
    }

    /**
     * 解除微信绑定
     *
     * @param id
     *
     * @return
     */
    default int unbindWechatOpen(Long id) {
        UpdateStatementProvider update = SqlBuilder.update(racAccount).set(wxAvatar).equalToNull().set(wxNickname).equalToNull().set(wxOpenId).equalToNull().set(wxUnionId)
                .equalToNull().where(racAccount.id, isEqualTo(id)).build().render(RenderingStrategies.MYBATIS3);
        return this.update(update);
    }

    /**
     * 根据用户ID查询用户下的账户的信息
     *
     * @param id
     *
     * @return
     */
    default List<RacAccountMo> getByUserId(Long id) {
        SelectStatementProvider select = SqlBuilder.select(racAccount.allColumns()).from(racAccount).where(racAccount.userId, isEqualTo(id)).build()
                .render(RenderingStrategies.MYBATIS3);
        return this.selectMany(select);
    }

    /**
     * 根据用户ID领域ID关键字查询该领域下账户(用户的下帐号)的信息
     *
     * @param to 查询的具体条件
     */
    default List<RacAccountMo> getAccountByUser(RacAccountPageTo qo) {
        final String               keywords     = StringUtils.isBlank(qo.getKeywords()) ? null : "%" + qo.getKeywords() + "%";
        final SqlCriterion<String> sqlCriterion = and(racAccount.signInNickname, isLikeWhenPresent(keywords), or(racAccount.signInName, isLikeWhenPresent(keywords)),
                or(racAccount.id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
                or(racAccount.signInEmail, isLikeWhenPresent(keywords)),
                or(racAccount.signInMobile, isLikeWhenPresent(keywords)), or(racAccount.qqNickname, isLikeWhenPresent(keywords)),
                or(racAccount.qqOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.qqUnionId, isLikeWhenPresent(keywords)), or(racAccount.wxNickname, isLikeWhenPresent(keywords)), or(racAccount.wxOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.wxUnionId, isLikeWhenPresent(keywords)), or(racAccount.remark, isLikeWhenPresent(keywords)));

        SelectStatementProvider    select       = SqlBuilder.select(racAccount.allColumns()).from(racAccount)
                .rightJoin(racUser)
                .on(racAccount.userId, equalTo(racUser.id))
                .where(racAccount.realmId, isEqualToWhenPresent(qo.getKeywords()), and(racUser.id, isEqualToWhenPresent(qo::getUserId)), sqlCriterion)
                .build().render(RenderingStrategies.MYBATIS3);

        return this.selectMany(select);
    }
}
