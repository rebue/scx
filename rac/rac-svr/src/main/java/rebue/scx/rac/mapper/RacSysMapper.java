package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.domainId;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.menuUrn;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.racSys;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacSysDynamicSqlSupport.url;

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
import rebue.scx.rac.mo.RacSysMo;

@Mapper
public interface RacSysMapper extends MapperRootInterface<RacSysMo, String> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, domainId, url, menuUrn, remark);

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
    int insert(InsertStatementProvider<RacSysMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacSysMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacSysMoResult")
    Optional<RacSysMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacSysMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "DOMAIN_ID", property = "domainId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "URL", property = "url", jdbcType = JdbcType.VARCHAR), @Result(column = "MENU_URN", property = "menuUrn", jdbcType = JdbcType.VARCHAR),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<RacSysMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racSys, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racSys, completer);
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
    default int insert(RacSysMo record) {
        return MyBatis3Utils.insert(this::insert, record, racSys, c -> c.map(id).toProperty("id").map(name).toProperty("name").map(domainId).toProperty("domainId").map(url)
            .toProperty("url").map(menuUrn).toProperty("menuUrn").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacSysMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racSys, c -> c.map(id).toProperty("id").map(name).toProperty("name").map(domainId).toProperty("domainId")
            .map(url).toProperty("url").map(menuUrn).toProperty("menuUrn").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacSysMo record) {
        return MyBatis3Utils.insert(this::insert, record, racSys,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName).map(domainId)
                .toPropertyWhenPresent("domainId", record::getDomainId).map(url).toPropertyWhenPresent("url", record::getUrl).map(menuUrn)
                .toPropertyWhenPresent("menuUrn", record::getMenuUrn).map(remark).toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacSysMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racSys, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacSysMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racSys, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacSysMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racSys, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacSysMo> selectByPrimaryKey(String id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racSys, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacSysMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(domainId).equalTo(record::getDomainId).set(url).equalTo(record::getUrl).set(menuUrn)
            .equalTo(record::getMenuUrn).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacSysMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(domainId).equalToWhenPresent(record::getDomainId).set(url)
            .equalToWhenPresent(record::getUrl).set(menuUrn).equalToWhenPresent(record::getMenuUrn).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacSysMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(domainId).equalTo(record::getDomainId).set(url).equalTo(record::getUrl).set(menuUrn).equalTo(record::getMenuUrn)
            .set(remark).equalTo(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacSysMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(domainId).equalToWhenPresent(record::getDomainId).set(url).equalToWhenPresent(record::getUrl)
            .set(menuUrn).equalToWhenPresent(record::getMenuUrn).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacSysMo record) {
        return delete(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(domainId, isEqualToWhenPresent(record::getDomainId))
                .and(url, isEqualToWhenPresent(record::getUrl)).and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacSysMo> selectOne(RacSysMo record) {
        return selectOne(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(domainId, isEqualToWhenPresent(record::getDomainId))
                .and(url, isEqualToWhenPresent(record::getUrl)).and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacSysMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(url, isEqualToWhenPresent(record::getUrl)).and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)).and(remark, isEqualToWhenPresent(record::getRemark)));
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
    default boolean existSelective(RacSysMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacSysMo> selectSelective(RacSysMo record) {
        return select(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(domainId, isEqualToWhenPresent(record::getDomainId))
                .and(url, isEqualToWhenPresent(record::getUrl)).and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }
}
