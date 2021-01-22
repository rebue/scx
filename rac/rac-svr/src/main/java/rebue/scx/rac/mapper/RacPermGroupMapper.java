package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.domainId;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.isEnabled;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.seqNo;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.racPermGroup;
import static rebue.scx.rac.mapper.RacPermGroupDynamicSqlSupport.remark;

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
import rebue.scx.rac.mo.RacPermGroupMo;

@Mapper
public interface RacPermGroupMapper extends MapperRootInterface<RacPermGroupMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, domainId, name, isEnabled, seqNo, remark);

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
    int insert(InsertStatementProvider<RacPermGroupMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacPermGroupMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacPermGroupMoResult")
    Optional<RacPermGroupMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacPermGroupMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="DOMAIN_ID", property="domainId", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_ENABLED", property="isEnabled", jdbcType=JdbcType.BIT),
        @Result(column="ORDER_NO", property="seqNo", jdbcType=JdbcType.TINYINT),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<RacPermGroupMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racPermGroup, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racPermGroup, completer);
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
    default int insert(RacPermGroupMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPermGroup, c ->
            c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(name).toProperty("name")
            .map(isEnabled).toProperty("isEnabled")
            .map(seqNo).toProperty("seqNo")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacPermGroupMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racPermGroup, c ->
            c.map(id).toProperty("id")
            .map(domainId).toProperty("domainId")
            .map(name).toProperty("name")
            .map(isEnabled).toProperty("isEnabled")
            .map(seqNo).toProperty("seqNo")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacPermGroupMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPermGroup, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(domainId).toPropertyWhenPresent("domainId", record::getDomainId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled)
            .map(seqNo).toPropertyWhenPresent("seqNo", record::getSeqNo)
            .map(remark).toPropertyWhenPresent("remark", record::getRemark)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermGroupMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racPermGroup, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermGroupMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racPermGroup, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermGroupMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racPermGroup, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermGroupMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racPermGroup, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacPermGroupMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(domainId).equalTo(record::getDomainId)
                .set(name).equalTo(record::getName)
                .set(isEnabled).equalTo(record::getIsEnabled)
                .set(seqNo).equalTo(record::getSeqNo)
                .set(remark).equalTo(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacPermGroupMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(domainId).equalToWhenPresent(record::getDomainId)
                .set(name).equalToWhenPresent(record::getName)
                .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
                .set(seqNo).equalToWhenPresent(record::getSeqNo)
                .set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacPermGroupMo record) {
        return update(c ->
            c.set(domainId).equalTo(record::getDomainId)
            .set(name).equalTo(record::getName)
            .set(isEnabled).equalTo(record::getIsEnabled)
            .set(seqNo).equalTo(record::getSeqNo)
            .set(remark).equalTo(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacPermGroupMo record) {
        return update(c ->
            c.set(domainId).equalToWhenPresent(record::getDomainId)
            .set(name).equalToWhenPresent(record::getName)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .set(seqNo).equalToWhenPresent(record::getSeqNo)
            .set(remark).equalToWhenPresent(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacPermGroupMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacPermGroupMo> selectOne(RacPermGroupMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacPermGroupMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
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
    default boolean existSelective(RacPermGroupMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacPermGroupMo> selectSelective(RacPermGroupMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(domainId, isEqualToWhenPresent(record::getDomainId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }
}