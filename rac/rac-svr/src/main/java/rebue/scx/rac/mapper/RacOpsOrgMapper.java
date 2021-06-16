package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacOpsOrgDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacOpsOrgDynamicSqlSupport.masterOrgId;
import static rebue.scx.rac.mapper.RacOpsOrgDynamicSqlSupport.racOpsOrg;
import static rebue.scx.rac.mapper.RacOpsOrgDynamicSqlSupport.slaveOrgId;

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
import rebue.scx.rac.mo.RacOpsOrgMo;

@Mapper
public interface RacOpsOrgMapper extends MapperRootInterface<RacOpsOrgMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, masterOrgId, slaveOrgId);

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
    int insert(InsertStatementProvider<RacOpsOrgMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacOpsOrgMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacOpsOrgMoResult")
    Optional<RacOpsOrgMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacOpsOrgMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "MASTER_ORG_ID", property = "masterOrgId", jdbcType = JdbcType.BIGINT),
        @Result(column = "SLAVE_ORG_ID", property = "slaveOrgId", jdbcType = JdbcType.BIGINT)
    })
    List<RacOpsOrgMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racOpsOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racOpsOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(RacOpsOrgMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOpsOrg,
            c -> c.map(id).toProperty("id").map(masterOrgId).toProperty("masterOrgId").map(slaveOrgId).toProperty("slaveOrgId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacOpsOrgMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racOpsOrg,
            c -> c.map(id).toProperty("id").map(masterOrgId).toProperty("masterOrgId").map(slaveOrgId).toProperty("slaveOrgId"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacOpsOrgMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOpsOrg, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(masterOrgId)
            .toPropertyWhenPresent("masterOrgId", record::getMasterOrgId).map(slaveOrgId).toPropertyWhenPresent("slaveOrgId", record::getSlaveOrgId));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOpsOrgMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racOpsOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpsOrgMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racOpsOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpsOrgMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racOpsOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOpsOrgMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racOpsOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacOpsOrgMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(masterOrgId).equalTo(record::getMasterOrgId).set(slaveOrgId).equalTo(record::getSlaveOrgId);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacOpsOrgMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(masterOrgId).equalToWhenPresent(record::getMasterOrgId).set(slaveOrgId).equalToWhenPresent(record::getSlaveOrgId);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacOpsOrgMo record) {
        return update(c -> c.set(masterOrgId).equalTo(record::getMasterOrgId).set(slaveOrgId).equalTo(record::getSlaveOrgId).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacOpsOrgMo record) {
        return update(
            c -> c.set(masterOrgId).equalToWhenPresent(record::getMasterOrgId).set(slaveOrgId).equalToWhenPresent(record::getSlaveOrgId).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacOpsOrgMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(masterOrgId, isEqualToWhenPresent(record::getMasterOrgId)).and(slaveOrgId,
            isEqualToWhenPresent(record::getSlaveOrgId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOpsOrgMo> selectOne(RacOpsOrgMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(masterOrgId, isEqualToWhenPresent(record::getMasterOrgId)).and(slaveOrgId,
            isEqualToWhenPresent(record::getSlaveOrgId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacOpsOrgMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(masterOrgId, isEqualToWhenPresent(record::getMasterOrgId)).and(slaveOrgId,
            isEqualToWhenPresent(record::getSlaveOrgId)));
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
    default boolean existSelective(RacOpsOrgMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpsOrgMo> selectSelective(RacOpsOrgMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(masterOrgId, isEqualToWhenPresent(record::getMasterOrgId)).and(slaveOrgId,
            isEqualToWhenPresent(record::getSlaveOrgId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOpsOrgMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
