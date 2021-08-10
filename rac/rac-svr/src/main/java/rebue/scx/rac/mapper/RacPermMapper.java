package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThan;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.groupId;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.isEnabled;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.racPerm;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacPermDynamicSqlSupport.seqNo;

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
import rebue.scx.rac.mo.RacPermMo;

@Mapper
public interface RacPermMapper extends MapperRootInterface<RacPermMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, realmId, groupId, name, isEnabled, seqNo, remark);

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
    int insert(InsertStatementProvider<RacPermMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacPermMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacPermMoResult")
    Optional<RacPermMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacPermMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR), @Result(column = "GROUP_ID", property = "groupId", jdbcType = JdbcType.BIGINT),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "IS_ENABLED", property = "isEnabled", jdbcType = JdbcType.BIT),
        @Result(column = "SEQ_NO", property = "seqNo", jdbcType = JdbcType.TINYINT), @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<RacPermMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racPerm, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racPerm, completer);
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
    default int insert(RacPermMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPerm, c -> c.map(id).toProperty("id").map(realmId).toProperty("realmId").map(groupId).toProperty("groupId").map(name)
            .toProperty("name").map(isEnabled).toProperty("isEnabled").map(seqNo).toProperty("seqNo").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacPermMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racPerm, c -> c.map(id).toProperty("id").map(realmId).toProperty("realmId").map(groupId)
            .toProperty("groupId").map(name).toProperty("name").map(isEnabled).toProperty("isEnabled").map(seqNo).toProperty("seqNo").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacPermMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPerm,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(realmId).toPropertyWhenPresent("realmId", record::getRealmId).map(groupId)
                .toPropertyWhenPresent("groupId", record::getGroupId).map(name).toPropertyWhenPresent("name", record::getName).map(isEnabled)
                .toPropertyWhenPresent("isEnabled", record::getIsEnabled).map(seqNo).toPropertyWhenPresent("seqNo", record::getSeqNo).map(remark)
                .toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racPerm, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racPerm, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racPerm, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racPerm, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacPermMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(realmId).equalTo(record::getRealmId).set(groupId).equalTo(record::getGroupId).set(name).equalTo(record::getName)
            .set(isEnabled).equalTo(record::getIsEnabled).set(seqNo).equalTo(record::getSeqNo).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacPermMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(realmId).equalToWhenPresent(record::getRealmId).set(groupId).equalToWhenPresent(record::getGroupId).set(name)
            .equalToWhenPresent(record::getName).set(isEnabled).equalToWhenPresent(record::getIsEnabled).set(seqNo).equalToWhenPresent(record::getSeqNo).set(remark)
            .equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacPermMo record) {
        return update(c -> c.set(realmId).equalTo(record::getRealmId).set(groupId).equalTo(record::getGroupId).set(name).equalTo(record::getName).set(isEnabled)
            .equalTo(record::getIsEnabled).set(seqNo).equalTo(record::getSeqNo).set(remark).equalTo(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacPermMo record) {
        return update(c -> c.set(realmId).equalToWhenPresent(record::getRealmId).set(groupId).equalToWhenPresent(record::getGroupId).set(name).equalToWhenPresent(record::getName)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled).set(seqNo).equalToWhenPresent(record::getSeqNo).set(remark).equalToWhenPresent(record::getRemark)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacPermMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(groupId, isEqualToWhenPresent(record::getGroupId)).and(name, isEqualToWhenPresent(record::getName)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermMo> selectOne(RacPermMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(groupId, isEqualToWhenPresent(record::getGroupId)).and(name, isEqualToWhenPresent(record::getName)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacPermMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(groupId, isEqualToWhenPresent(record::getGroupId)).and(name, isEqualToWhenPresent(record::getName)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo)).and(remark, isEqualToWhenPresent(record::getRemark)));
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
    default boolean existSelective(RacPermMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermMo> selectSelective(RacPermMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(groupId, isEqualToWhenPresent(record::getGroupId)).and(name, isEqualToWhenPresent(record::getName)).and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * 根据groupId 修改是否启用/禁用权限
     *
     * @param record
     * @return
     */
    default int updateByGroupId(RacPermMo record) {
        return update(c -> c.set(isEnabled).equalTo(record::getIsEnabled).where(groupId, isEqualTo(record::getGroupId)));
    }

    /**
     * 因删除权限而进行的权限分组顺序号更新
     *
     * @param record
     * @return
     */
    default int updateSeqNoByDeleteAfter(@Param(value = "record") RacPermMo record) {
        return update(c -> c.set(seqNo).equalToConstant("SEQ_NO-1").where(realmId, isEqualTo(record::getRealmId)).and(groupId, isEqualTo(record::getGroupId)).and(seqNo,
            isGreaterThan(record::getSeqNo)));
    }

    /**
     * 查询权限并排序
     *
     * @param record
     * @return
     */
    default List<RacPermMo> selectOrderByPerm(RacPermMo record) {
        return select(c -> c.where(realmId, isEqualToWhenPresent(record::getRealmId)).orderBy(seqNo));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }
}
