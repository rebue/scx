package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.createTimestamp;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.email;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.idCard;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.isVerifiedEmail;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.isVerifiedIdcard;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.isVerifiedMobile;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.isVerifiedRealname;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.mobile;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.racUser;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.realName;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.sex;
import static rebue.scx.rac.mapper.RacUserDynamicSqlSupport.updateTimestamp;

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
    BasicColumn[] selectList = BasicColumn.columnList(id, mobile, isVerifiedMobile, email, isVerifiedEmail, realName, isVerifiedRealname, idCard, isVerifiedIdcard, sex, createTimestamp, updateTimestamp);

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
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_VERIFIED_MOBILE", property="isVerifiedMobile", jdbcType=JdbcType.BIT),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_VERIFIED_EMAIL", property="isVerifiedEmail", jdbcType=JdbcType.BIT),
        @Result(column="REAL_NAME", property="realName", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_VERIFIED_REALNAME", property="isVerifiedRealname", jdbcType=JdbcType.BIT),
        @Result(column="ID_CARD", property="idCard", jdbcType=JdbcType.CHAR),
        @Result(column="IS_VERIFIED_IDCARD", property="isVerifiedIdcard", jdbcType=JdbcType.BIT),
        @Result(column="SEX", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="CREATE_TIMESTAMP", property="createTimestamp", jdbcType=JdbcType.BIGINT),
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
            .map(mobile).toProperty("mobile")
            .map(isVerifiedMobile).toProperty("isVerifiedMobile")
            .map(email).toProperty("email")
            .map(isVerifiedEmail).toProperty("isVerifiedEmail")
            .map(realName).toProperty("realName")
            .map(isVerifiedRealname).toProperty("isVerifiedRealname")
            .map(idCard).toProperty("idCard")
            .map(isVerifiedIdcard).toProperty("isVerifiedIdcard")
            .map(sex).toProperty("sex")
            .map(createTimestamp).toProperty("createTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacUserMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racUser, c ->
            c.map(id).toProperty("id")
            .map(mobile).toProperty("mobile")
            .map(isVerifiedMobile).toProperty("isVerifiedMobile")
            .map(email).toProperty("email")
            .map(isVerifiedEmail).toProperty("isVerifiedEmail")
            .map(realName).toProperty("realName")
            .map(isVerifiedRealname).toProperty("isVerifiedRealname")
            .map(idCard).toProperty("idCard")
            .map(isVerifiedIdcard).toProperty("isVerifiedIdcard")
            .map(sex).toProperty("sex")
            .map(createTimestamp).toProperty("createTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacUserMo record) {
        return MyBatis3Utils.insert(this::insert, record, racUser, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
            .map(isVerifiedMobile).toPropertyWhenPresent("isVerifiedMobile", record::getIsVerifiedMobile)
            .map(email).toPropertyWhenPresent("email", record::getEmail)
            .map(isVerifiedEmail).toPropertyWhenPresent("isVerifiedEmail", record::getIsVerifiedEmail)
            .map(realName).toPropertyWhenPresent("realName", record::getRealName)
            .map(isVerifiedRealname).toPropertyWhenPresent("isVerifiedRealname", record::getIsVerifiedRealname)
            .map(idCard).toPropertyWhenPresent("idCard", record::getIdCard)
            .map(isVerifiedIdcard).toPropertyWhenPresent("isVerifiedIdcard", record::getIsVerifiedIdcard)
            .map(sex).toPropertyWhenPresent("sex", record::getSex)
            .map(createTimestamp).toPropertyWhenPresent("createTimestamp", record::getCreateTimestamp)
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
                .set(mobile).equalTo(record::getMobile)
                .set(isVerifiedMobile).equalTo(record::getIsVerifiedMobile)
                .set(email).equalTo(record::getEmail)
                .set(isVerifiedEmail).equalTo(record::getIsVerifiedEmail)
                .set(realName).equalTo(record::getRealName)
                .set(isVerifiedRealname).equalTo(record::getIsVerifiedRealname)
                .set(idCard).equalTo(record::getIdCard)
                .set(isVerifiedIdcard).equalTo(record::getIsVerifiedIdcard)
                .set(sex).equalTo(record::getSex)
                .set(createTimestamp).equalTo(record::getCreateTimestamp)
                .set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacUserMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(isVerifiedMobile).equalToWhenPresent(record::getIsVerifiedMobile)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(isVerifiedEmail).equalToWhenPresent(record::getIsVerifiedEmail)
                .set(realName).equalToWhenPresent(record::getRealName)
                .set(isVerifiedRealname).equalToWhenPresent(record::getIsVerifiedRealname)
                .set(idCard).equalToWhenPresent(record::getIdCard)
                .set(isVerifiedIdcard).equalToWhenPresent(record::getIsVerifiedIdcard)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
                .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacUserMo record) {
        return update(c ->
            c.set(mobile).equalTo(record::getMobile)
            .set(isVerifiedMobile).equalTo(record::getIsVerifiedMobile)
            .set(email).equalTo(record::getEmail)
            .set(isVerifiedEmail).equalTo(record::getIsVerifiedEmail)
            .set(realName).equalTo(record::getRealName)
            .set(isVerifiedRealname).equalTo(record::getIsVerifiedRealname)
            .set(idCard).equalTo(record::getIdCard)
            .set(isVerifiedIdcard).equalTo(record::getIsVerifiedIdcard)
            .set(sex).equalTo(record::getSex)
            .set(createTimestamp).equalTo(record::getCreateTimestamp)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacUserMo record) {
        return update(c ->
            c.set(mobile).equalToWhenPresent(record::getMobile)
            .set(isVerifiedMobile).equalToWhenPresent(record::getIsVerifiedMobile)
            .set(email).equalToWhenPresent(record::getEmail)
            .set(isVerifiedEmail).equalToWhenPresent(record::getIsVerifiedEmail)
            .set(realName).equalToWhenPresent(record::getRealName)
            .set(isVerifiedRealname).equalToWhenPresent(record::getIsVerifiedRealname)
            .set(idCard).equalToWhenPresent(record::getIdCard)
            .set(isVerifiedIdcard).equalToWhenPresent(record::getIsVerifiedIdcard)
            .set(sex).equalToWhenPresent(record::getSex)
            .set(createTimestamp).equalToWhenPresent(record::getCreateTimestamp)
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
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacUserMo> selectOne(RacUserMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacUserMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
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
    default boolean existSelective(RacUserMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacUserMo> selectSelective(RacUserMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(mobile, isEqualToWhenPresent(record::getMobile))
            .and(isVerifiedMobile, isEqualToWhenPresent(record::getIsVerifiedMobile))
            .and(email, isEqualToWhenPresent(record::getEmail))
            .and(isVerifiedEmail, isEqualToWhenPresent(record::getIsVerifiedEmail))
            .and(realName, isEqualToWhenPresent(record::getRealName))
            .and(isVerifiedRealname, isEqualToWhenPresent(record::getIsVerifiedRealname))
            .and(idCard, isEqualToWhenPresent(record::getIdCard))
            .and(isVerifiedIdcard, isEqualToWhenPresent(record::getIsVerifiedIdcard))
            .and(sex, isEqualToWhenPresent(record::getSex))
            .and(createTimestamp, isEqualToWhenPresent(record::getCreateTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }
}