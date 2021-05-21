package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.or;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.createTimestamp;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.domainId;
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
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInAvatar;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInEmail;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInMobile;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInName;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInNickname;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInPswd;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.signInPswdSalt;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.updateTimestamp;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.userId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxAvatar;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxNickname;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxOpenId;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.wxUnionId;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.racOrgAccount;

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
import org.mybatis.dynamic.sql.SqlCriterion;
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
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.ex.RacAccountExMo;
import rebue.wheel.core.NumberUtils;

@Mapper
public interface RacAccountMapper extends MapperRootInterface<RacAccountMo, Long> {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, remark, orgId, domainId, isEnabled, signInName,
        signInMobile, signInEmail, signInPswd, signInPswdSalt, payPswd, payPswdSalt, signInNickname, signInAvatar,
        wxOpenId, wxUnionId, wxNickname, wxAvatar, qqOpenId, qqUnionId, qqNickname, qqAvatar, isTester,
        createTimestamp, updateTimestamp);

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
    @Results(id = "RacAccountMoResult", value = {
        @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "USER_ID", property = "userId", jdbcType = JdbcType.BIGINT),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ORG_ID", property = "orgId", jdbcType = JdbcType.BIGINT),
        @Result(column = "DOMAIN_ID", property = "domainId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT),
        @Result(column = "SIGN_IN_NAME", property = "signInName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SIGN_IN_MOBILE", property = "signInMobile", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SIGN_IN_EMAIL", property = "signInEmail", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SIGN_IN_PSWD", property = "signInPswd", jdbcType = JdbcType.VARCHAR),
        @Result(column = "SIGN_IN_PSWD_SALT", property = "signInPswdSalt", jdbcType = JdbcType.CHAR),
        @Result(column = "PAY_PSWD", property = "payPswd", jdbcType = JdbcType.VARCHAR),
        @Result(column = "PAY_PSWD_SALT", property = "payPswdSalt", jdbcType = JdbcType.CHAR),
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
        @Result(column = "IS_TESTER", property = "isTester", jdbcType = JdbcType.BIT),
        @Result(column = "CREATE_TIMESTAMP", property = "createTimestamp", jdbcType = JdbcType.BIGINT),
        @Result(column = "UPDATE_TIMESTAMP", property = "updateTimestamp", jdbcType = JdbcType.BIGINT)
    })
    List<RacAccountMo> selectMany(SelectStatementProvider selectStatement);

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
    default long count(final CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(final DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteByPrimaryKey(final Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insert(final RacAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccount, c -> c.map(id).toProperty("id").map(userId)
            .toProperty("userId").map(remark).toProperty("remark").map(orgId).toProperty("orgId").map(domainId)
            .toProperty("domainId").map(isEnabled).toProperty("isEnabled").map(signInName).toProperty("signInName")
            .map(signInMobile).toProperty("signInMobile").map(signInEmail).toProperty("signInEmail").map(signInPswd)
            .toProperty("signInPswd").map(signInPswdSalt).toProperty("signInPswdSalt").map(payPswd)
            .toProperty("payPswd").map(payPswdSalt).toProperty("payPswdSalt").map(signInNickname)
            .toProperty("signInNickname").map(signInAvatar).toProperty("signInAvatar").map(wxOpenId)
            .toProperty("wxOpenId").map(wxUnionId).toProperty("wxUnionId").map(wxNickname).toProperty("wxNickname")
            .map(wxAvatar).toProperty("wxAvatar").map(qqOpenId).toProperty("qqOpenId").map(qqUnionId)
            .toProperty("qqUnionId").map(qqNickname).toProperty("qqNickname").map(qqAvatar).toProperty("qqAvatar")
            .map(isTester).toProperty("isTester").map(createTimestamp).toProperty("createTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(final Collection<RacAccountMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racAccount, c -> c.map(id).toProperty("id")
            .map(userId).toProperty("userId").map(remark).toProperty("remark").map(orgId).toProperty("orgId")
            .map(domainId).toProperty("domainId").map(isEnabled).toProperty("isEnabled").map(signInName)
            .toProperty("signInName").map(signInMobile).toProperty("signInMobile").map(signInEmail)
            .toProperty("signInEmail").map(signInPswd).toProperty("signInPswd").map(signInPswdSalt)
            .toProperty("signInPswdSalt").map(payPswd).toProperty("payPswd").map(payPswdSalt)
            .toProperty("payPswdSalt").map(signInNickname).toProperty("signInNickname").map(signInAvatar)
            .toProperty("signInAvatar").map(wxOpenId).toProperty("wxOpenId").map(wxUnionId).toProperty("wxUnionId")
            .map(wxNickname).toProperty("wxNickname").map(wxAvatar).toProperty("wxAvatar").map(qqOpenId)
            .toProperty("qqOpenId").map(qqUnionId).toProperty("qqUnionId").map(qqNickname).toProperty("qqNickname")
            .map(qqAvatar).toProperty("qqAvatar").map(isTester).toProperty("isTester").map(createTimestamp)
            .toProperty("createTimestamp").map(updateTimestamp).toProperty("updateTimestamp"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(final RacAccountMo record) {
        return MyBatis3Utils.insert(this::insert, record, racAccount,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(userId)
                .toPropertyWhenPresent("userId", record::getUserId).map(remark)
                .toPropertyWhenPresent("remark", record::getRemark).map(orgId)
                .toPropertyWhenPresent("orgId", record::getOrgId).map(domainId)
                .toPropertyWhenPresent("domainId", record::getDomainId).map(isEnabled)
                .toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(signInName)
                .toPropertyWhenPresent("signInName", record::getSignInName).map(signInMobile)
                .toPropertyWhenPresent("signInMobile", record::getSignInMobile).map(signInEmail)
                .toPropertyWhenPresent("signInEmail", record::getSignInEmail).map(signInPswd)
                .toPropertyWhenPresent("signInPswd", record::getSignInPswd).map(signInPswdSalt)
                .toPropertyWhenPresent("signInPswdSalt", record::getSignInPswdSalt).map(payPswd)
                .toPropertyWhenPresent("payPswd", record::getPayPswd).map(payPswdSalt)
                .toPropertyWhenPresent("payPswdSalt", record::getPayPswdSalt).map(signInNickname)
                .toPropertyWhenPresent("signInNickname", record::getSignInNickname).map(signInAvatar)
                .toPropertyWhenPresent("signInAvatar", record::getSignInAvatar).map(wxOpenId)
                .toPropertyWhenPresent("wxOpenId", record::getWxOpenId).map(wxUnionId)
                .toPropertyWhenPresent("wxUnionId", record::getWxUnionId).map(wxNickname)
                .toPropertyWhenPresent("wxNickname", record::getWxNickname).map(wxAvatar)
                .toPropertyWhenPresent("wxAvatar", record::getWxAvatar).map(qqOpenId)
                .toPropertyWhenPresent("qqOpenId", record::getQqOpenId).map(qqUnionId)
                .toPropertyWhenPresent("qqUnionId", record::getQqUnionId).map(qqNickname)
                .toPropertyWhenPresent("qqNickname", record::getQqNickname).map(qqAvatar)
                .toPropertyWhenPresent("qqAvatar", record::getQqAvatar).map(isTester)
                .toPropertyWhenPresent("isTester", record::getIsTester).map(createTimestamp)
                .toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp).map(updateTimestamp)
                .toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountMo> selectOne(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> select(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> selectDistinct(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountMo> selectByPrimaryKey(final Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(final UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racAccount, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(final RacAccountMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(userId).equalTo(record::getUserId).set(remark)
            .equalTo(record::getRemark).set(orgId).equalTo(record::getOrgId).set(domainId)
            .equalTo(record::getDomainId).set(isEnabled).equalTo(record::getIsEnabled).set(signInName)
            .equalTo(record::getSignInName).set(signInMobile).equalTo(record::getSignInMobile).set(signInEmail)
            .equalTo(record::getSignInEmail).set(signInPswd).equalTo(record::getSignInPswd).set(signInPswdSalt)
            .equalTo(record::getSignInPswdSalt).set(payPswd).equalTo(record::getPayPswd).set(payPswdSalt)
            .equalTo(record::getPayPswdSalt).set(signInNickname).equalTo(record::getSignInNickname)
            .set(signInAvatar).equalTo(record::getSignInAvatar).set(wxOpenId).equalTo(record::getWxOpenId)
            .set(wxUnionId).equalTo(record::getWxUnionId).set(wxNickname).equalTo(record::getWxNickname)
            .set(wxAvatar).equalTo(record::getWxAvatar).set(qqOpenId).equalTo(record::getQqOpenId).set(qqUnionId)
            .equalTo(record::getQqUnionId).set(qqNickname).equalTo(record::getQqNickname).set(qqAvatar)
            .equalTo(record::getQqAvatar).set(isTester).equalTo(record::getIsTester).set(createTimestamp)
            .equalTo(record::getCreateTimestamp).set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(final RacAccountMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(userId).equalToWhenPresent(record::getUserId)
            .set(remark).equalToWhenPresent(record::getRemark).set(orgId).equalToWhenPresent(record::getOrgId)
            .set(domainId).equalToWhenPresent(record::getDomainId).set(isEnabled)
            .equalToWhenPresent(record::getIsEnabled).set(signInName).equalToWhenPresent(record::getSignInName)
            .set(signInMobile).equalToWhenPresent(record::getSignInMobile).set(signInEmail)
            .equalToWhenPresent(record::getSignInEmail).set(signInPswd).equalToWhenPresent(record::getSignInPswd)
            .set(signInPswdSalt).equalToWhenPresent(record::getSignInPswdSalt).set(payPswd)
            .equalToWhenPresent(record::getPayPswd).set(payPswdSalt).equalToWhenPresent(record::getPayPswdSalt)
            .set(signInNickname).equalToWhenPresent(record::getSignInNickname).set(signInAvatar)
            .equalToWhenPresent(record::getSignInAvatar).set(wxOpenId).equalToWhenPresent(record::getWxOpenId)
            .set(wxUnionId).equalToWhenPresent(record::getWxUnionId).set(wxNickname)
            .equalToWhenPresent(record::getWxNickname).set(wxAvatar).equalToWhenPresent(record::getWxAvatar)
            .set(qqOpenId).equalToWhenPresent(record::getQqOpenId).set(qqUnionId)
            .equalToWhenPresent(record::getQqUnionId).set(qqNickname).equalToWhenPresent(record::getQqNickname)
            .set(qqAvatar).equalToWhenPresent(record::getQqAvatar).set(isTester)
            .equalToWhenPresent(record::getIsTester).set(createTimestamp)
            .equalToWhenPresent(record::getCreateTimestamp).set(updateTimestamp)
            .equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(final RacAccountMo record) {
        return update(c -> c.set(userId).equalTo(record::getUserId).set(remark).equalTo(record::getRemark).set(orgId)
            .equalTo(record::getOrgId).set(domainId).equalTo(record::getDomainId).set(isEnabled)
            .equalTo(record::getIsEnabled).set(signInName).equalTo(record::getSignInName).set(signInMobile)
            .equalTo(record::getSignInMobile).set(signInEmail).equalTo(record::getSignInEmail).set(signInPswd)
            .equalTo(record::getSignInPswd).set(signInPswdSalt).equalTo(record::getSignInPswdSalt).set(payPswd)
            .equalTo(record::getPayPswd).set(payPswdSalt).equalTo(record::getPayPswdSalt).set(signInNickname)
            .equalTo(record::getSignInNickname).set(signInAvatar).equalTo(record::getSignInAvatar).set(wxOpenId)
            .equalTo(record::getWxOpenId).set(wxUnionId).equalTo(record::getWxUnionId).set(wxNickname)
            .equalTo(record::getWxNickname).set(wxAvatar).equalTo(record::getWxAvatar).set(qqOpenId)
            .equalTo(record::getQqOpenId).set(qqUnionId).equalTo(record::getQqUnionId).set(qqNickname)
            .equalTo(record::getQqNickname).set(qqAvatar).equalTo(record::getQqAvatar).set(isTester)
            .equalTo(record::getIsTester).set(createTimestamp).equalTo(record::getCreateTimestamp)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(final RacAccountMo record) {
        return update(c -> c.set(userId).equalToWhenPresent(record::getUserId).set(remark)
            .equalToWhenPresent(record::getRemark).set(orgId).equalToWhenPresent(record::getOrgId).set(domainId)
            .equalToWhenPresent(record::getDomainId).set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .set(signInName).equalToWhenPresent(record::getSignInName).set(signInMobile)
            .equalToWhenPresent(record::getSignInMobile).set(signInEmail).equalToWhenPresent(record::getSignInEmail)
            .set(signInPswd).equalToWhenPresent(record::getSignInPswd).set(signInPswdSalt)
            .equalToWhenPresent(record::getSignInPswdSalt).set(payPswd).equalToWhenPresent(record::getPayPswd)
            .set(payPswdSalt).equalToWhenPresent(record::getPayPswdSalt).set(signInNickname)
            .equalToWhenPresent(record::getSignInNickname).set(signInAvatar)
            .equalToWhenPresent(record::getSignInAvatar).set(wxOpenId).equalToWhenPresent(record::getWxOpenId)
            .set(wxUnionId).equalToWhenPresent(record::getWxUnionId).set(wxNickname)
            .equalToWhenPresent(record::getWxNickname).set(wxAvatar).equalToWhenPresent(record::getWxAvatar)
            .set(qqOpenId).equalToWhenPresent(record::getQqOpenId).set(qqUnionId)
            .equalToWhenPresent(record::getQqUnionId).set(qqNickname).equalToWhenPresent(record::getQqNickname)
            .set(qqAvatar).equalToWhenPresent(record::getQqAvatar).set(isTester)
            .equalToWhenPresent(record::getIsTester).set(createTimestamp)
            .equalToWhenPresent(record::getCreateTimestamp).set(updateTimestamp)
            .equalToWhenPresent(record::getUpdateTimestamp).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(final RacAccountMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt))
            .and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
            .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacAccountMo> selectOne(final RacAccountMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt))
            .and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
            .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(final RacAccountMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt))
            .and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
            .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existByPrimaryKey(final Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existSelective(final RacAccountMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacAccountMo> selectSelective(final RacAccountMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId))
            .and(userId, isEqualToWhenPresent(record::getUserId))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(orgId, isEqualToWhenPresent(record::getOrgId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(payPswdSalt, isEqualToWhenPresent(record::getPayPswdSalt))
            .and(signInNickname, isEqualToWhenPresent(record::getSignInNickname))
            .and(signInAvatar, isEqualToWhenPresent(record::getSignInAvatar))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
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
        final String keywords = StringUtils.isBlank(qo.getKeywords()) ? null : "%" + qo.getKeywords() + "%";

        return select(c -> {
            final SqlCriterion<String> sqlCriterion = and(racAccount.signInNickname, isLikeWhenPresent(keywords),
                or(racAccount.signInName, isLikeWhenPresent(keywords)),
                or(racAccount.id,
                    isEqualToWhenPresent(
                        NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)),
                or(racAccount.signInEmail, isLikeWhenPresent(keywords)),
                or(racAccount.signInMobile, isLikeWhenPresent(keywords)),
                or(racAccount.qqNickname, isLikeWhenPresent(keywords)),
                or(racAccount.qqOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.qqUnionId, isLikeWhenPresent(keywords)),
                or(racAccount.wxNickname, isLikeWhenPresent(keywords)),
                or(racAccount.wxOpenId, isLikeWhenPresent(keywords)),
                or(racAccount.wxUnionId, isLikeWhenPresent(keywords)),
                or(racAccount.remark, isLikeWhenPresent(keywords)));

            if (qo.getOrgId() != null) {
                return c.join(racOrgAccount).on(racAccount.id, equalTo(racOrgAccount.accountId))
                    .where(racOrgAccount.orgId, isEqualTo(qo::getOrgId),
                        and(racAccount.domainId, isEqualTo(qo.getDomainId()), sqlCriterion));
            }
            else {
                return c
                    .where(racAccount.domainId, isEqualTo(qo.getDomainId()), sqlCriterion);
            }
        });
    }

    /**
     * 查询当前组织下未存在的账户
     *
     * @param record #{record.orgId}
     *
     * @return
     */
    @Select({ "<script>" + "SELECT     ac.* FROM    RAC_ACCOUNT ac   LEFT JOIN "
        + "    RAC_ORG_ACCOUNT oac ON ac.ID = oac.ACCOUNT_ID " + "WHERE " + "   "
        + " ac.DOMAIN_ID = #{record.domainId}" + "<if test='record.keywords!=null'> "
        + " and (ac.SIGN_IN_NAME like '%${record.keywords}%' "
        + " or ac.SIGN_IN_MOBILE like '%${record.keywords}%' " + " or ac.SIGN_IN_EMAIL like '%${record.keywords}%' "
        + " or ac.REMARK like '%${record.keywords}%' " + " or ac.SIGN_IN_NICKNAME like '%${record.keywords}%' "
        + " or ac.WX_NICKNAME like '%${record.keywords}%' " + " or ac.QQ_NICKNAME like '%${record.keywords}%' "
        + " or ac.ID like '%${record.keywords}%' ) " + "</if> " + "        AND ((ac.ORG_ID IS NULL "
        + "        OR NOT EXISTS( SELECT  " + "            a.ID " + "        FROM "
        + "            RAC_ORG_ACCOUNT a " + "        WHERE " + "            a.ORG_ID = #{record.orgId} "
        + "                AND ac.ID = a.ACCOUNT_ID))) " + "GROUP BY ac.ID " + "</script>"
    })
    List<RacAccountMo> getAddablAccountList(@Param(value = "record") RacAccountExMo record);
}