package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacPersonDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacPersonMo;

@Mapper
public interface RacPersonMapper extends MapperRootInterface<RacPersonMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, mobile, isVerifiedMobile, email, isVerifiedEmail, realName, isVerifiedRealname, idCard, isVerifiedIdcard, sex, createrTimestamp, updateTimestamp);

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
    int insert(InsertStatementProvider<RacPersonMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacPersonMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacPersonMoResult")
    Optional<RacPersonMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacPersonMoResult", value = {
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
        @Result(column="CREATER_TIMESTAMP", property="createrTimestamp", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATE_TIMESTAMP", property="updateTimestamp", jdbcType=JdbcType.BIGINT)
    })
    List<RacPersonMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racPerson, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racPerson, completer);
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
    default int insert(RacPersonMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPerson, c ->
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
            .map(createrTimestamp).toProperty("createrTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacPersonMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racPerson, c ->
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
            .map(createrTimestamp).toProperty("createrTimestamp")
            .map(updateTimestamp).toProperty("updateTimestamp")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacPersonMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPerson, c ->
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
            .map(createrTimestamp).toPropertyWhenPresent("createrTimestamp", record::getCreaterTimestamp)
            .map(updateTimestamp).toPropertyWhenPresent("updateTimestamp", record::getUpdateTimestamp)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPersonMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racPerson, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPersonMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racPerson, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPersonMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racPerson, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPersonMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racPerson, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacPersonMo record, UpdateDSL<UpdateModel> dsl) {
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
                .set(createrTimestamp).equalTo(record::getCreaterTimestamp)
                .set(updateTimestamp).equalTo(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacPersonMo record, UpdateDSL<UpdateModel> dsl) {
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
                .set(createrTimestamp).equalToWhenPresent(record::getCreaterTimestamp)
                .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacPersonMo record) {
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
            .set(createrTimestamp).equalTo(record::getCreaterTimestamp)
            .set(updateTimestamp).equalTo(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacPersonMo record) {
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
            .set(createrTimestamp).equalToWhenPresent(record::getCreaterTimestamp)
            .set(updateTimestamp).equalToWhenPresent(record::getUpdateTimestamp)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacPersonMo record) {
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPersonMo> selectOne(RacPersonMo record) {
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacPersonMo record) {
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
    default boolean existSelective(RacPersonMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPersonMo> selectSelective(RacPersonMo record) {
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
            .and(createrTimestamp, isEqualToWhenPresent(record::getCreaterTimestamp))
            .and(updateTimestamp, isEqualToWhenPresent(record::getUpdateTimestamp))
        );
    }
}