package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacDomainDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacDomainDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacDomainDynamicSqlSupport.racDomain;
import static rebue.scx.rac.mapper.RacDomainDynamicSqlSupport.remark;

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
import rebue.scx.rac.mo.RacDomainMo;

@Mapper
public interface RacDomainMapper extends MapperRootInterface<RacDomainMo, String> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, remark);

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
    int insert(InsertStatementProvider<RacDomainMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDomainMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacDomainMoResult")
    Optional<RacDomainMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacDomainMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<RacDomainMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDomain, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDomain, completer);
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
    default int insert(RacDomainMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDomain, c -> c.map(id).toProperty("id").map(name).toProperty("name").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacDomainMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDomain,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacDomainMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDomain, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name)
            .toPropertyWhenPresent("name", record::getName).map(remark).toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDomain, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDomain, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDomain, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainMo> selectByPrimaryKey(String id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDomain, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacDomainMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacDomainMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacDomainMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(remark).equalTo(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacDomainMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacDomainMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDomainMo> selectOne(RacDomainMo record) {
        return selectOne(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacDomainMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(remark, isEqualToWhenPresent(record::getRemark)));
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
    default boolean existSelective(RacDomainMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainMo> selectSelective(RacDomainMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDomainMo> selectIn(List<String> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }
}
