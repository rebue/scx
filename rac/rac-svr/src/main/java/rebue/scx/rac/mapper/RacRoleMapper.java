package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThan;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.domainId;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.isEnabled;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.racRole;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacRoleDynamicSqlSupport.seqNo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
import rebue.scx.rac.mo.RacRoleMo;

@Mapper
public interface RacRoleMapper extends MapperRootInterface<RacRoleMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, domainId, isEnabled, seqNo, remark);

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
    int insert(InsertStatementProvider<RacRoleMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacRoleMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacRoleMoResult")
    Optional<RacRoleMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacRoleMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "DOMAIN_ID", property = "domainId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT), @Result(column = "SEQ_NO", property = "seqNo", jdbcType = JdbcType.TINYINT),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<RacRoleMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racRole, completer);
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
    default int insert(RacRoleMo record) {
        return MyBatis3Utils.insert(this::insert, record, racRole, c -> c.map(id).toProperty("id").map(name).toProperty("name").map(domainId).toProperty("domainId").map(isEnabled)
            .toProperty("isEnabled").map(seqNo).toProperty("seqNo").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacRoleMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racRole, c -> c.map(id).toProperty("id").map(name).toProperty("name").map(domainId)
            .toProperty("domainId").map(isEnabled).toProperty("isEnabled").map(seqNo).toProperty("seqNo").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacRoleMo record) {
        return MyBatis3Utils.insert(this::insert, record, racRole,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName).map(domainId)
                .toPropertyWhenPresent("domainId", record::getDomainId).map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(seqNo)
                .toPropertyWhenPresent("seqNo", record::getSeqNo).map(remark).toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacRoleMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacRoleMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacRoleMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacRoleMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racRole, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacRoleMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(domainId).equalTo(record::getDomainId).set(isEnabled).equalTo(record::getIsEnabled)
            .set(seqNo).equalTo(record::getSeqNo).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacRoleMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(domainId).equalToWhenPresent(record::getDomainId).set(isEnabled)
            .equalToWhenPresent(record::getIsEnabled).set(seqNo).equalToWhenPresent(record::getSeqNo).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacRoleMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(domainId).equalTo(record::getDomainId).set(isEnabled).equalTo(record::getIsEnabled).set(seqNo)
            .equalTo(record::getSeqNo).set(remark).equalTo(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacRoleMo record) {
        return update(
            c -> c.set(name).equalToWhenPresent(record::getName).set(domainId).equalToWhenPresent(record::getDomainId).set(isEnabled).equalToWhenPresent(record::getIsEnabled)
                .set(seqNo).equalToWhenPresent(record::getSeqNo).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacRoleMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacRoleMo> selectOne(RacRoleMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacRoleMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(seqNo, isEqualToWhenPresent(record::getSeqNo)).and(remark, isEqualToWhenPresent(record::getRemark)));
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
    default boolean existSelective(RacRoleMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacRoleMo> selectSelective(RacRoleMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled)).and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * 查询角色并排序
     *
     * @param record
     * @return
     */
    default List<RacRoleMo> selectListOrderByRole(RacRoleMo record) {
        return select(c -> c.where(domainId, isEqualToWhenPresent(record::getDomainId)).orderBy(seqNo));
    }

    /**
     * 因删除角色而进行的角色顺序号更新
     *
     * @param record
     * @return
     */
    // @Update({
    // "<script>  UPDATE RAC_ROLE ro SET ro.SEQ_NO = (ro.SEQ_NO-1) WHERE ro.DOMAIN_ID=#{record.domainId} and ro.SEQ_NO > #{record.seqNo} </script>" })
    // 
    default int updateSeqNoByDeleteAfter(@Param(value = "record") RacRoleMo record) {
        return update(c -> c.set(seqNo).equalToConstant("SEQ_NO-1").where(domainId, isEqualTo(record::getDomainId)).and(seqNo, isGreaterThan(record::getSeqNo)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacRoleMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
