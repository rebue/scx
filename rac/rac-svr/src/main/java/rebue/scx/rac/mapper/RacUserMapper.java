package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacUserMo;

@Mapper
public interface RacUserMapper extends MapperRootInterface<RacUserMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, nickname, avatar, signInName, signInPswd, payPswd, salt, mobile, isVerifiedMobile, email, isVerifiedEmail, wxOpenId, wxUnionId, wxNickname, wxAvatar, qqOpenId, qqUnionId, qqNickname, qqAvatar, realName, isVerifiedRealname, idCard, isVerifiedIdcard, sex, age, isTester, isEnabled, updateTimestamp);

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
    int insert(InsertStatementProvider<RacUserMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacUserMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacUserMoResult")
    Optional<RacUserMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacUserMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="NICKNAME", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="AVATAR", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_NAME", property="signInName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_PSWD", property="signInPswd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PAY_PSWD", property="payPswd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SALT", property="salt", jdbcType=JdbcType.CHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_VERIFIED_MOBILE", property="isVerifiedMobile", jdbcType=JdbcType.BIT),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_VERIFIED_EMAIL", property="isVerifiedEmail", jdbcType=JdbcType.BIT),
        @Result(column="WX_OPEN_ID", property="wxOpenId", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_UNION_ID", property="wxUnionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_NICKNAME", property="wxNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_AVATAR", property="wxAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_OPEN_ID", property="qqOpenId", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_UNION_ID", property="qqUnionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_NICKNAME", property="qqNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_AVATAR", property="qqAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_NAME", property="realName", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_VERIFIED_REALNAME", property="isVerifiedRealname", jdbcType=JdbcType.BIT),
        @Result(column="ID_CARD", property="idCard", jdbcType=JdbcType.CHAR),
        @Result(column="IS_VERIFIED_IDCARD", property="isVerifiedIdcard", jdbcType=JdbcType.BIT),
        @Result(column="SEX", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="AGE", property="age", jdbcType=JdbcType.TINYINT),
        @Result(column="IS_TESTER", property="isTester", jdbcType=JdbcType.BIT),
        @Result(column="IS_ENABLED", property="isEnabled", jdbcType=JdbcType.BIT),
        @Result(column="UPDATE_TIMESTAMP", property="updateTimestamp", jdbcType=JdbcType.BIGINT)
    })
    List<RacUserMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racUser, completer);
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
    default int insert(RacUserMo record) {
        return MyBatis3Utils.insert(this::insert, record, racUser, c ->
            c.map(id).toProperty("id")
            .map(nickname).toProperty("nickname")
            .map(avatar).toProperty("avatar")
            .map(signInName).toProperty("signInName")
            .map(signInPswd).toProperty("signInPswd")
            .map(payPswd).toProperty("payPswd")
            .map(salt).toProperty("salt")
            .map(mobile).toProperty("mobile")
            .map(isVerifiedMobile).toProperty("isVerifiedMobile")
            .map(email).toProperty("email")
            .map(isVerifiedEmail).toProperty("isVerifiedEmail")
            .map(wxOpenId).toProperty("wxOpenId")
            .map(wxUnionId).toProperty("wxUnionId")
            .map(wxNickname).toProperty("wxNickname")
            .map(wxAvatar).toProperty("wxAvatar")
            .map(qqOpenId).toProperty("qqOpenId")
            .map(qqUnionId).toProperty("qqUnionId")
            .map(qqNickname).toProperty("qqNickname")
            .map(qqAvatar).toProperty("qqAvatar")
            .map(realName).toProperty("realName")
            .map(isVerifiedRealname).toProperty("isVerifiedRealname")
            .map(idCard).toProperty("idCard")
            .map(isVerifiedIdcard).toProperty("isVerifiedIdcard")
            .map(sex).toProperty("sex")
            .map(age).toProperty("age")
            .map(isTester).toProperty("isTester")
            .map(isEnabled).toProperty("isEnabled")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacUserMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racUser, c ->
            c.map(id).toProperty("id")
            .map(nickname).toProperty("nickname")
            .map(avatar).toProperty("avatar")
            .map(signInName).toProperty("signInName")
            .map(signInPswd).toProperty("signInPswd")
            .map(payPswd).toProperty("payPswd")
            .map(salt).toProperty("salt")
            .map(mobile).toProperty("mobile")
            .map(isVerifiedMobile).toProperty("isVerifiedMobile")
            .map(email).toProperty("email")
            .map(isVerifiedEmail).toProperty("isVerifiedEmail")
            .map(wxOpenId).toProperty("wxOpenId")
            .map(wxUnionId).toProperty("wxUnionId")
            .map(wxNickname).toProperty("wxNickname")
            .map(wxAvatar).toProperty("wxAvatar")
            .map(qqOpenId).toProperty("qqOpenId")
            .map(qqUnionId).toProperty("qqUnionId")
            .map(qqNickname).toProperty("qqNickname")
            .map(qqAvatar).toProperty("qqAvatar")
            .map(realName).toProperty("realName")
            .map(isVerifiedRealname).toProperty("isVerifiedRealname")
            .map(idCard).toProperty("idCard")
            .map(isVerifiedIdcard).toProperty("isVerifiedIdcard")
            .map(sex).toProperty("sex")
            .map(age).toProperty("age")
            .map(isTester).toProperty("isTester")
            .map(isEnabled).toProperty("isEnabled")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacUserMo record) {
        return MyBatis3Utils.insert(this::insert, record, racUser, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
            .map(avatar).toPropertyWhenPresent("avatar", record::getAvatar)
            .map(signInName).toPropertyWhenPresent("signInName", record::getSignInName)
            .map(signInPswd).toPropertyWhenPresent("signInPswd", record::getSignInPswd)
            .map(payPswd).toPropertyWhenPresent("payPswd", record::getPayPswd)
            .map(salt).toPropertyWhenPresent("salt", record::getSalt)
            .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
            .map(isVerifiedMobile).toPropertyWhenPresent("isVerifiedMobile", record::getIsVerifiedMobile)
            .map(email).toPropertyWhenPresent("email", record::getEmail)
            .map(isVerifiedEmail).toPropertyWhenPresent("isVerifiedEmail", record::getIsVerifiedEmail)
            .map(wxOpenId).toPropertyWhenPresent("wxOpenId", record::getWxOpenId)
            .map(wxUnionId).toPropertyWhenPresent("wxUnionId", record::getWxUnionId)
            .map(wxNickname).toPropertyWhenPresent("wxNickname", record::getWxNickname)
            .map(wxAvatar).toPropertyWhenPresent("wxAvatar", record::getWxAvatar)
            .map(qqOpenId).toPropertyWhenPresent("qqOpenId", record::getQqOpenId)
            .map(qqUnionId).toPropertyWhenPresent("qqUnionId", record::getQqUnionId)
            .map(qqNickname).toPropertyWhenPresent("qqNickname", record::getQqNickname)
            .map(qqAvatar).toPropertyWhenPresent("qqAvatar", record::getQqAvatar)
            .map(realName).toPropertyWhenPresent("realName", record::getRealName)
            .map(isVerifiedRealname).toPropertyWhenPresent("isVerifiedRealname", record::getIsVerifiedRealname)
            .map(idCard).toPropertyWhenPresent("idCard", record::getIdCard)
            .map(isVerifiedIdcard).toPropertyWhenPresent("isVerifiedIdcard", record::getIsVerifiedIdcard)
            .map(sex).toPropertyWhenPresent("sex", record::getSex)
            .map(age).toPropertyWhenPresent("age", record::getAge)
            .map(isTester).toPropertyWhenPresent("isTester", record::getIsTester)
            .map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled)
            .map(updateTimestamp).toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racUser, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacUserMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(nickname).equalTo(record::getNickname)
                .set(avatar).equalTo(record::getAvatar)
                .set(signInName).equalTo(record::getSignInName)
                .set(signInPswd).equalTo(record::getSignInPswd)
                .set(payPswd).equalTo(record::getPayPswd)
                .set(salt).equalTo(record::getSalt)
                .set(mobile).equalTo(record::getMobile)
                .set(isVerifiedMobile).equalTo(record::getIsVerifiedMobile)
                .set(email).equalTo(record::getEmail)
                .set(isVerifiedEmail).equalTo(record::getIsVerifiedEmail)
                .set(wxOpenId).equalTo(record::getWxOpenId)
                .set(wxUnionId).equalTo(record::getWxUnionId)
                .set(wxNickname).equalTo(record::getWxNickname)
                .set(wxAvatar).equalTo(record::getWxAvatar)
                .set(qqOpenId).equalTo(record::getQqOpenId)
                .set(qqUnionId).equalTo(record::getQqUnionId)
                .set(qqNickname).equalTo(record::getQqNickname)
                .set(qqAvatar).equalTo(record::getQqAvatar)
                .set(realName).equalTo(record::getRealName)
                .set(isVerifiedRealname).equalTo(record::getIsVerifiedRealname)
                .set(idCard).equalTo(record::getIdCard)
                .set(isVerifiedIdcard).equalTo(record::getIsVerifiedIdcard)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(isTester).equalTo(record::getIsTester)
                .set(isEnabled).equalTo(record::getIsEnabled)
                .set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacUserMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(avatar).equalToWhenPresent(record::getAvatar)
                .set(signInName).equalToWhenPresent(record::getSignInName)
                .set(signInPswd).equalToWhenPresent(record::getSignInPswd)
                .set(payPswd).equalToWhenPresent(record::getPayPswd)
                .set(salt).equalToWhenPresent(record::getSalt)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(isVerifiedMobile).equalToWhenPresent(record::getIsVerifiedMobile)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(isVerifiedEmail).equalToWhenPresent(record::getIsVerifiedEmail)
                .set(wxOpenId).equalToWhenPresent(record::getWxOpenId)
                .set(wxUnionId).equalToWhenPresent(record::getWxUnionId)
                .set(wxNickname).equalToWhenPresent(record::getWxNickname)
                .set(wxAvatar).equalToWhenPresent(record::getWxAvatar)
                .set(qqOpenId).equalToWhenPresent(record::getQqOpenId)
                .set(qqUnionId).equalToWhenPresent(record::getQqUnionId)
                .set(qqNickname).equalToWhenPresent(record::getQqNickname)
                .set(qqAvatar).equalToWhenPresent(record::getQqAvatar)
                .set(realName).equalToWhenPresent(record::getRealName)
                .set(isVerifiedRealname).equalToWhenPresent(record::getIsVerifiedRealname)
                .set(idCard).equalToWhenPresent(record::getIdCard)
                .set(isVerifiedIdcard).equalToWhenPresent(record::getIsVerifiedIdcard)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(isTester).equalToWhenPresent(record::getIsTester)
                .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
                .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacUserMo record) {
        return update(c ->
            c.set(nickname).equalTo(record::getNickname)
            .set(avatar).equalTo(record::getAvatar)
            .set(signInName).equalTo(record::getSignInName)
            .set(signInPswd).equalTo(record::getSignInPswd)
            .set(payPswd).equalTo(record::getPayPswd)
            .set(salt).equalTo(record::getSalt)
            .set(mobile).equalTo(record::getMobile)
            .set(isVerifiedMobile).equalTo(record::getIsVerifiedMobile)
            .set(email).equalTo(record::getEmail)
            .set(isVerifiedEmail).equalTo(record::getIsVerifiedEmail)
            .set(wxOpenId).equalTo(record::getWxOpenId)
            .set(wxUnionId).equalTo(record::getWxUnionId)
            .set(wxNickname).equalTo(record::getWxNickname)
            .set(wxAvatar).equalTo(record::getWxAvatar)
            .set(qqOpenId).equalTo(record::getQqOpenId)
            .set(qqUnionId).equalTo(record::getQqUnionId)
            .set(qqNickname).equalTo(record::getQqNickname)
            .set(qqAvatar).equalTo(record::getQqAvatar)
            .set(realName).equalTo(record::getRealName)
            .set(isVerifiedRealname).equalTo(record::getIsVerifiedRealname)
            .set(idCard).equalTo(record::getIdCard)
            .set(isVerifiedIdcard).equalTo(record::getIsVerifiedIdcard)
            .set(sex).equalTo(record::getSex)
            .set(age).equalTo(record::getAge)
            .set(isTester).equalTo(record::getIsTester)
            .set(isEnabled).equalTo(record::getIsEnabled)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacUserMo record) {
        return update(c ->
            c.set(nickname).equalToWhenPresent(record::getNickname)
            .set(avatar).equalToWhenPresent(record::getAvatar)
            .set(signInName).equalToWhenPresent(record::getSignInName)
            .set(signInPswd).equalToWhenPresent(record::getSignInPswd)
            .set(payPswd).equalToWhenPresent(record::getPayPswd)
            .set(salt).equalToWhenPresent(record::getSalt)
            .set(mobile).equalToWhenPresent(record::getMobile)
            .set(isVerifiedMobile).equalToWhenPresent(record::getIsVerifiedMobile)
            .set(email).equalToWhenPresent(record::getEmail)
            .set(isVerifiedEmail).equalToWhenPresent(record::getIsVerifiedEmail)
            .set(wxOpenId).equalToWhenPresent(record::getWxOpenId)
            .set(wxUnionId).equalToWhenPresent(record::getWxUnionId)
            .set(wxNickname).equalToWhenPresent(record::getWxNickname)
            .set(wxAvatar).equalToWhenPresent(record::getWxAvatar)
            .set(qqOpenId).equalToWhenPresent(record::getQqOpenId)
            .set(qqUnionId).equalToWhenPresent(record::getQqUnionId)
            .set(qqNickname).equalToWhenPresent(record::getQqNickname)
            .set(qqAvatar).equalToWhenPresent(record::getQqAvatar)
            .set(realName).equalToWhenPresent(record::getRealName)
            .set(isVerifiedRealname).equalToWhenPresent(record::getIsVerifiedRealname)
            .set(idCard).equalToWhenPresent(record::getIdCard)
            .set(isVerifiedIdcard).equalToWhenPresent(record::getIsVerifiedIdcard)
            .set(sex).equalToWhenPresent(record::getSex)
            .set(age).equalToWhenPresent(record::getAge)
            .set(isTester).equalToWhenPresent(record::getIsTester)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacUserMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(nickname, isEqualToWhenPresent(record::getNickname))
            .and(avatar, isEqualToWhenPresent(record::getAvatar))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(salt, isEqualToWhenPresent(record::getSalt))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(age, isEqualToWhenPresent(record::getAge))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserMo> selectOne(RacUserMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(nickname, isEqualToWhenPresent(record::getNickname))
            .and(avatar, isEqualToWhenPresent(record::getAvatar))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(salt, isEqualToWhenPresent(record::getSalt))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(age, isEqualToWhenPresent(record::getAge))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacUserMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(nickname, isEqualToWhenPresent(record::getNickname))
            .and(avatar, isEqualToWhenPresent(record::getAvatar))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(salt, isEqualToWhenPresent(record::getSalt))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(age, isEqualToWhenPresent(record::getAge))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
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
    default boolean existSelective(RacUserMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserMo> selectSelective(RacUserMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(nickname, isEqualToWhenPresent(record::getNickname))
            .and(avatar, isEqualToWhenPresent(record::getAvatar))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(payPswd, isEqualToWhenPresent(record::getPayPswd))
            .and(salt, isEqualToWhenPresent(record::getSalt))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(wxOpenId, isEqualToWhenPresent(record::getWxOpenId))
            .and(wxUnionId, isEqualToWhenPresent(record::getWxUnionId))
            .and(wxNickname, isEqualToWhenPresent(record::getWxNickname))
            .and(wxAvatar, isEqualToWhenPresent(record::getWxAvatar))
            .and(qqOpenId, isEqualToWhenPresent(record::getQqOpenId))
            .and(qqUnionId, isEqualToWhenPresent(record::getQqUnionId))
            .and(qqNickname, isEqualToWhenPresent(record::getQqNickname))
            .and(qqAvatar, isEqualToWhenPresent(record::getQqAvatar))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(age, isEqualToWhenPresent(record::getAge))
            .and(isTester, isEqualToWhenPresent(record::getIsTester))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }
}