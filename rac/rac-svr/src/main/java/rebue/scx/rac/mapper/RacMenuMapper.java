package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static rebue.scx.rac.mapper.RacMenuDynamicSqlSupport.*;

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
import rebue.scx.rac.mo.RacMenuMo;

@Mapper
public interface RacMenuMapper extends MapperRootInterface<RacMenuMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, sysId, code, name, title, path, isEnabled, icon, remark);

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
    int insert(InsertStatementProvider<RacMenuMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacMenuMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacMenuMoResult")
    Optional<RacMenuMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacMenuMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SYS_ID", property="sysId", jdbcType=JdbcType.VARCHAR),
        @Result(column="CODE", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="PATH", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_ENABLED", property="isEnabled", jdbcType=JdbcType.BIT),
        @Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<RacMenuMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racMenu, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racMenu, completer);
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
    default int insert(RacMenuMo record) {
        return MyBatis3Utils.insert(this::insert, record, racMenu, c ->
            c.map(id).toProperty("id")
            .map(sysId).toProperty("sysId")
            .map(code).toProperty("code")
            .map(name).toProperty("name")
            .map(title).toProperty("title")
            .map(path).toProperty("path")
            .map(isEnabled).toProperty("isEnabled")
            .map(icon).toProperty("icon")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacMenuMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racMenu, c ->
            c.map(id).toProperty("id")
            .map(sysId).toProperty("sysId")
            .map(code).toProperty("code")
            .map(name).toProperty("name")
            .map(title).toProperty("title")
            .map(path).toProperty("path")
            .map(isEnabled).toProperty("isEnabled")
            .map(icon).toProperty("icon")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacMenuMo record) {
        return MyBatis3Utils.insert(this::insert, record, racMenu, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(sysId).toPropertyWhenPresent("sysId", record::getSysId)
            .map(code).toPropertyWhenPresent("code", record::getCode)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(title).toPropertyWhenPresent("title", record::getTitle)
            .map(path).toPropertyWhenPresent("path", record::getPath)
            .map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled)
            .map(icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(remark).toPropertyWhenPresent("remark", record::getRemark)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacMenuMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racMenu, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacMenuMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racMenu, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacMenuMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racMenu, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacMenuMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racMenu, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacMenuMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(sysId).equalTo(record::getSysId)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(title).equalTo(record::getTitle)
                .set(path).equalTo(record::getPath)
                .set(isEnabled).equalTo(record::getIsEnabled)
                .set(icon).equalTo(record::getIcon)
                .set(remark).equalTo(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacMenuMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(sysId).equalToWhenPresent(record::getSysId)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(title).equalToWhenPresent(record::getTitle)
                .set(path).equalToWhenPresent(record::getPath)
                .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
                .set(icon).equalToWhenPresent(record::getIcon)
                .set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacMenuMo record) {
        return update(c ->
            c.set(sysId).equalTo(record::getSysId)
            .set(code).equalTo(record::getCode)
            .set(name).equalTo(record::getName)
            .set(title).equalTo(record::getTitle)
            .set(path).equalTo(record::getPath)
            .set(isEnabled).equalTo(record::getIsEnabled)
            .set(icon).equalTo(record::getIcon)
            .set(remark).equalTo(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacMenuMo record) {
        return update(c ->
            c.set(sysId).equalToWhenPresent(record::getSysId)
            .set(code).equalToWhenPresent(record::getCode)
            .set(name).equalToWhenPresent(record::getName)
            .set(title).equalToWhenPresent(record::getTitle)
            .set(path).equalToWhenPresent(record::getPath)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .set(icon).equalToWhenPresent(record::getIcon)
            .set(remark).equalToWhenPresent(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacMenuMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(code, isEqualToWhenPresent(record::getCode))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(title, isEqualToWhenPresent(record::getTitle))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(icon, isEqualToWhenPresent(record::getIcon))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacMenuMo> selectOne(RacMenuMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(code, isEqualToWhenPresent(record::getCode))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(title, isEqualToWhenPresent(record::getTitle))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(icon, isEqualToWhenPresent(record::getIcon))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacMenuMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(code, isEqualToWhenPresent(record::getCode))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(title, isEqualToWhenPresent(record::getTitle))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(icon, isEqualToWhenPresent(record::getIcon))
            .and(remark, isEqualToWhenPresent(record::getRemark))
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
    default boolean existSelective(RacMenuMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacMenuMo> selectSelective(RacMenuMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(sysId, isEqualToWhenPresent(record::getSysId))
            .and(code, isEqualToWhenPresent(record::getCode))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(title, isEqualToWhenPresent(record::getTitle))
            .and(path, isEqualToWhenPresent(record::getPath))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(icon, isEqualToWhenPresent(record::getIcon))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }
}