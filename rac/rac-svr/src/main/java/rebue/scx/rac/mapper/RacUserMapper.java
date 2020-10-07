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
    BasicColumn[] selectList = BasicColumn.columnList(id, personId, isEnabled, signInName, signInMobile, signInEmail, signInPswd, signInPswdSalt, signInNickname, signInAvatar, wxOpenId, wxUnionId, wxNickname, wxAvatar, qqOpenId, qqUnionId, qqNickname, qqAvatar, isTester, createrTimestamp, updateTimestamp);

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
        @Result(column="PERSON_ID", property="personId", jdbcType=JdbcType.BIGINT),
        @Result(column="IS_ENABLED", property="isEnabled", jdbcType=JdbcType.BIT),
        @Result(column="SIGN_IN_NAME", property="signInName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_MOBILE", property="signInMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_EMAIL", property="signInEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_PSWD", property="signInPswd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_PSWD_SALT", property="signInPswdSalt", jdbcType=JdbcType.CHAR),
        @Result(column="SIGN_IN_NICKNAME", property="signInNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="SIGN_IN_AVATAR", property="signInAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_OPEN_ID", property="wxOpenId", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_UNION_ID", property="wxUnionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_NICKNAME", property="wxNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="WX_AVATAR", property="wxAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_OPEN_ID", property="qqOpenId", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_UNION_ID", property="qqUnionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_NICKNAME", property="qqNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ_AVATAR", property="qqAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_TESTER", property="isTester", jdbcType=JdbcType.BIT),
        @Result(column="CREATER_TIMESTAMP", property="createrTimestamp", jdbcType=JdbcType.BIGINT),
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
            .map(personId).toProperty("personId")
            .map(isEnabled).toProperty("isEnabled")
            .map(signInName).toProperty("signInName")
            .map(signInMobile).toProperty("signInMobile")
            .map(signInEmail).toProperty("signInEmail")
            .map(signInPswd).toProperty("signInPswd")
            .map(signInPswdSalt).toProperty("signInPswdSalt")
            .map(signInNickname).toProperty("signInNickname")
            .map(signInAvatar).toProperty("signInAvatar")
            .map(wxOpenId).toProperty("wxOpenId")
            .map(wxUnionId).toProperty("wxUnionId")
            .map(wxNickname).toProperty("wxNickname")
            .map(wxAvatar).toProperty("wxAvatar")
            .map(qqOpenId).toProperty("qqOpenId")
            .map(qqUnionId).toProperty("qqUnionId")
            .map(qqNickname).toProperty("qqNickname")
            .map(qqAvatar).toProperty("qqAvatar")
            .map(isTester).toProperty("isTester")
            .map(createrTimestamp).toProperty("createrTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacUserMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racUser, c ->
            c.map(id).toProperty("id")
            .map(personId).toProperty("personId")
            .map(isEnabled).toProperty("isEnabled")
            .map(signInName).toProperty("signInName")
            .map(signInMobile).toProperty("signInMobile")
            .map(signInEmail).toProperty("signInEmail")
            .map(signInPswd).toProperty("signInPswd")
            .map(signInPswdSalt).toProperty("signInPswdSalt")
            .map(signInNickname).toProperty("signInNickname")
            .map(signInAvatar).toProperty("signInAvatar")
            .map(wxOpenId).toProperty("wxOpenId")
            .map(wxUnionId).toProperty("wxUnionId")
            .map(wxNickname).toProperty("wxNickname")
            .map(wxAvatar).toProperty("wxAvatar")
            .map(qqOpenId).toProperty("qqOpenId")
            .map(qqUnionId).toProperty("qqUnionId")
            .map(qqNickname).toProperty("qqNickname")
            .map(qqAvatar).toProperty("qqAvatar")
            .map(isTester).toProperty("isTester")
            .map(createrTimestamp).toProperty("createrTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacUserMo record) {
        return MyBatis3Utils.insert(this::insert, record, racUser, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(personId).toPropertyWhenPresent("personId", record::getPersonId)
            .map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled)
            .map(signInName).toPropertyWhenPresent("signInName", record::getSignInName)
            .map(signInMobile).toPropertyWhenPresent("signInMobile", record::getSignInMobile)
            .map(signInEmail).toPropertyWhenPresent("signInEmail", record::getSignInEmail)
            .map(signInPswd).toPropertyWhenPresent("signInPswd", record::getSignInPswd)
            .map(signInPswdSalt).toPropertyWhenPresent("signInPswdSalt", record::getSignInPswdSalt)
            .map(signInNickname).toPropertyWhenPresent("signInNickname", record::getSignInNickname)
            .map(signInAvatar).toPropertyWhenPresent("signInAvatar", record::getSignInAvatar)
            .map(wxOpenId).toPropertyWhenPresent("wxOpenId", record::getWxOpenId)
            .map(wxUnionId).toPropertyWhenPresent("wxUnionId", record::getWxUnionId)
            .map(wxNickname).toPropertyWhenPresent("wxNickname", record::getWxNickname)
            .map(wxAvatar).toPropertyWhenPresent("wxAvatar", record::getWxAvatar)
            .map(qqOpenId).toPropertyWhenPresent("qqOpenId", record::getQqOpenId)
            .map(qqUnionId).toPropertyWhenPresent("qqUnionId", record::getQqUnionId)
            .map(qqNickname).toPropertyWhenPresent("qqNickname", record::getQqNickname)
            .map(qqAvatar).toPropertyWhenPresent("qqAvatar", record::getQqAvatar)
            .map(isTester).toPropertyWhenPresent("isTester", record::getIsTester)
            .map(createrTimestamp).toPropertyWhenPresent("createrTimestamp", record::getCreaterTimestamp)
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
                .set(personId).equalTo(record::getPersonId)
                .set(isEnabled).equalTo(record::getIsEnabled)
                .set(signInName).equalTo(record::getSignInName)
                .set(signInMobile).equalTo(record::getSignInMobile)
                .set(signInEmail).equalTo(record::getSignInEmail)
                .set(signInPswd).equalTo(record::getSignInPswd)
                .set(signInPswdSalt).equalTo(record::getSignInPswdSalt)
                .set(signInNickname).equalTo(record::getSignInNickname)
                .set(signInAvatar).equalTo(record::getSignInAvatar)
                .set(wxOpenId).equalTo(record::getWxOpenId)
                .set(wxUnionId).equalTo(record::getWxUnionId)
                .set(wxNickname).equalTo(record::getWxNickname)
                .set(wxAvatar).equalTo(record::getWxAvatar)
                .set(qqOpenId).equalTo(record::getQqOpenId)
                .set(qqUnionId).equalTo(record::getQqUnionId)
                .set(qqNickname).equalTo(record::getQqNickname)
                .set(qqAvatar).equalTo(record::getQqAvatar)
                .set(isTester).equalTo(record::getIsTester)
                .set(createrTimestamp).equalTo(record::getCreaterTimestamp)
                .set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacUserMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(personId).equalToWhenPresent(record::getPersonId)
                .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
                .set(signInName).equalToWhenPresent(record::getSignInName)
                .set(signInMobile).equalToWhenPresent(record::getSignInMobile)
                .set(signInEmail).equalToWhenPresent(record::getSignInEmail)
                .set(signInPswd).equalToWhenPresent(record::getSignInPswd)
                .set(signInPswdSalt).equalToWhenPresent(record::getSignInPswdSalt)
                .set(signInNickname).equalToWhenPresent(record::getSignInNickname)
                .set(signInAvatar).equalToWhenPresent(record::getSignInAvatar)
                .set(wxOpenId).equalToWhenPresent(record::getWxOpenId)
                .set(wxUnionId).equalToWhenPresent(record::getWxUnionId)
                .set(wxNickname).equalToWhenPresent(record::getWxNickname)
                .set(wxAvatar).equalToWhenPresent(record::getWxAvatar)
                .set(qqOpenId).equalToWhenPresent(record::getQqOpenId)
                .set(qqUnionId).equalToWhenPresent(record::getQqUnionId)
                .set(qqNickname).equalToWhenPresent(record::getQqNickname)
                .set(qqAvatar).equalToWhenPresent(record::getQqAvatar)
                .set(isTester).equalToWhenPresent(record::getIsTester)
                .set(createrTimestamp).equalToWhenPresent(record::getCreaterTimestamp)
                .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacUserMo record) {
        return update(c ->
            c.set(personId).equalTo(record::getPersonId)
            .set(isEnabled).equalTo(record::getIsEnabled)
            .set(signInName).equalTo(record::getSignInName)
            .set(signInMobile).equalTo(record::getSignInMobile)
            .set(signInEmail).equalTo(record::getSignInEmail)
            .set(signInPswd).equalTo(record::getSignInPswd)
            .set(signInPswdSalt).equalTo(record::getSignInPswdSalt)
            .set(signInNickname).equalTo(record::getSignInNickname)
            .set(signInAvatar).equalTo(record::getSignInAvatar)
            .set(wxOpenId).equalTo(record::getWxOpenId)
            .set(wxUnionId).equalTo(record::getWxUnionId)
            .set(wxNickname).equalTo(record::getWxNickname)
            .set(wxAvatar).equalTo(record::getWxAvatar)
            .set(qqOpenId).equalTo(record::getQqOpenId)
            .set(qqUnionId).equalTo(record::getQqUnionId)
            .set(qqNickname).equalTo(record::getQqNickname)
            .set(qqAvatar).equalTo(record::getQqAvatar)
            .set(isTester).equalTo(record::getIsTester)
            .set(createrTimestamp).equalTo(record::getCreaterTimestamp)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacUserMo record) {
        return update(c ->
            c.set(personId).equalToWhenPresent(record::getPersonId)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .set(signInName).equalToWhenPresent(record::getSignInName)
            .set(signInMobile).equalToWhenPresent(record::getSignInMobile)
            .set(signInEmail).equalToWhenPresent(record::getSignInEmail)
            .set(signInPswd).equalToWhenPresent(record::getSignInPswd)
            .set(signInPswdSalt).equalToWhenPresent(record::getSignInPswdSalt)
            .set(signInNickname).equalToWhenPresent(record::getSignInNickname)
            .set(signInAvatar).equalToWhenPresent(record::getSignInAvatar)
            .set(wxOpenId).equalToWhenPresent(record::getWxOpenId)
            .set(wxUnionId).equalToWhenPresent(record::getWxUnionId)
            .set(wxNickname).equalToWhenPresent(record::getWxNickname)
            .set(wxAvatar).equalToWhenPresent(record::getWxAvatar)
            .set(qqOpenId).equalToWhenPresent(record::getQqOpenId)
            .set(qqUnionId).equalToWhenPresent(record::getQqUnionId)
            .set(qqNickname).equalToWhenPresent(record::getQqNickname)
            .set(qqAvatar).equalToWhenPresent(record::getQqAvatar)
            .set(isTester).equalToWhenPresent(record::getIsTester)
            .set(createrTimestamp).equalToWhenPresent(record::getCreaterTimestamp)
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
            .and(personId, isEqualToWhenPresent(record::getPersonId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserMo> selectOne(RacUserMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(personId, isEqualToWhenPresent(record::getPersonId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacUserMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(personId, isEqualToWhenPresent(record::getPersonId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
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
            .and(personId, isEqualToWhenPresent(record::getPersonId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(signInName, isEqualToWhenPresent(record::getSignInName))
            .and(signInMobile, isEqualToWhenPresent(record::getSignInMobile))
            .and(signInEmail, isEqualToWhenPresent(record::getSignInEmail))
            .and(signInPswd, isEqualToWhenPresent(record::getSignInPswd))
            .and(signInPswdSalt, isEqualToWhenPresent(record::getSignInPswdSalt))
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }
}