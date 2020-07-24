package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.scx.rac.mo.RacOrgMo;

@Mapper
public interface RacOrgMapper extends MybatisBaseMapper<RacOrgMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, parentId, orgType, leftValue, rightValue, fullName, introduction, remark);

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
    int insert(InsertStatementProvider<RacOrgMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacOrgMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacOrgMoResult")
    Optional<RacOrgMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacOrgMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_ID", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="ORG_TYPE", property="orgType", jdbcType=JdbcType.TINYINT),
        @Result(column="LEFT_VALUE", property="leftValue", jdbcType=JdbcType.INTEGER),
        @Result(column="RIGHT_VALUE", property="rightValue", jdbcType=JdbcType.INTEGER),
        @Result(column="FULL_NAME", property="fullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTRODUCTION", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<RacOrgMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racOrg, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racOrg, completer);
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
    default int insert(RacOrgMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOrg, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(parentId).toProperty("parentId")
            .map(orgType).toProperty("orgType")
            .map(leftValue).toProperty("leftValue")
            .map(rightValue).toProperty("rightValue")
            .map(fullName).toProperty("fullName")
            .map(introduction).toProperty("introduction")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacOrgMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racOrg, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(parentId).toProperty("parentId")
            .map(orgType).toProperty("orgType")
            .map(leftValue).toProperty("leftValue")
            .map(rightValue).toProperty("rightValue")
            .map(fullName).toProperty("fullName")
            .map(introduction).toProperty("introduction")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacOrgMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOrg, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
            .map(orgType).toPropertyWhenPresent("orgType", record::getOrgType)
            .map(leftValue).toPropertyWhenPresent("leftValue", record::getLeftValue)
            .map(rightValue).toPropertyWhenPresent("rightValue", record::getRightValue)
            .map(fullName).toPropertyWhenPresent("fullName", record::getFullName)
            .map(introduction).toPropertyWhenPresent("introduction", record::getIntroduction)
            .map(remark).toPropertyWhenPresent("remark", record::getRemark)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racOrg, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racOrg, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racOrg, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racOrg, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacOrgMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(parentId).equalTo(record::getParentId)
                .set(orgType).equalTo(record::getOrgType)
                .set(leftValue).equalTo(record::getLeftValue)
                .set(rightValue).equalTo(record::getRightValue)
                .set(fullName).equalTo(record::getFullName)
                .set(introduction).equalTo(record::getIntroduction)
                .set(remark).equalTo(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacOrgMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(orgType).equalToWhenPresent(record::getOrgType)
                .set(leftValue).equalToWhenPresent(record::getLeftValue)
                .set(rightValue).equalToWhenPresent(record::getRightValue)
                .set(fullName).equalToWhenPresent(record::getFullName)
                .set(introduction).equalToWhenPresent(record::getIntroduction)
                .set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacOrgMo record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(parentId).equalTo(record::getParentId)
            .set(orgType).equalTo(record::getOrgType)
            .set(leftValue).equalTo(record::getLeftValue)
            .set(rightValue).equalTo(record::getRightValue)
            .set(fullName).equalTo(record::getFullName)
            .set(introduction).equalTo(record::getIntroduction)
            .set(remark).equalTo(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacOrgMo record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(parentId).equalToWhenPresent(record::getParentId)
            .set(orgType).equalToWhenPresent(record::getOrgType)
            .set(leftValue).equalToWhenPresent(record::getLeftValue)
            .set(rightValue).equalToWhenPresent(record::getRightValue)
            .set(fullName).equalToWhenPresent(record::getFullName)
            .set(introduction).equalToWhenPresent(record::getIntroduction)
            .set(remark).equalToWhenPresent(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgMo> selectOne(RacOrgMo record) {
        return selectOne(c ->
            c.where(id, isEqualTo(record::getId).when(Objects::nonNull))
            .and(name, isEqualTo(record::getName).when(Objects::nonNull))
            .and(parentId, isEqualTo(record::getParentId).when(Objects::nonNull))
            .and(orgType, isEqualTo(record::getOrgType).when(Objects::nonNull))
            .and(leftValue, isEqualTo(record::getLeftValue).when(Objects::nonNull))
            .and(rightValue, isEqualTo(record::getRightValue).when(Objects::nonNull))
            .and(fullName, isEqualTo(record::getFullName).when(Objects::nonNull))
            .and(introduction, isEqualTo(record::getIntroduction).when(Objects::nonNull))
            .and(remark, isEqualTo(record::getRemark).when(Objects::nonNull))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacOrgMo record) {
        return count(c ->
            c.where(id, isEqualTo(record::getId).when(Objects::nonNull))
            .and(name, isEqualTo(record::getName).when(Objects::nonNull))
            .and(parentId, isEqualTo(record::getParentId).when(Objects::nonNull))
            .and(orgType, isEqualTo(record::getOrgType).when(Objects::nonNull))
            .and(leftValue, isEqualTo(record::getLeftValue).when(Objects::nonNull))
            .and(rightValue, isEqualTo(record::getRightValue).when(Objects::nonNull))
            .and(fullName, isEqualTo(record::getFullName).when(Objects::nonNull))
            .and(introduction, isEqualTo(record::getIntroduction).when(Objects::nonNull))
            .and(remark, isEqualTo(record::getRemark).when(Objects::nonNull))
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
    default boolean existSelective(RacOrgMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> selectSelective(RacOrgMo record) {
        return select(c ->
            c.where(id, isEqualTo(record::getId).when(Objects::nonNull))
            .and(name, isEqualTo(record::getName).when(Objects::nonNull))
            .and(parentId, isEqualTo(record::getParentId).when(Objects::nonNull))
            .and(orgType, isEqualTo(record::getOrgType).when(Objects::nonNull))
            .and(leftValue, isEqualTo(record::getLeftValue).when(Objects::nonNull))
            .and(rightValue, isEqualTo(record::getRightValue).when(Objects::nonNull))
            .and(fullName, isEqualTo(record::getFullName).when(Objects::nonNull))
            .and(introduction, isEqualTo(record::getIntroduction).when(Objects::nonNull))
            .and(remark, isEqualTo(record::getRemark).when(Objects::nonNull))
        );
    }
}