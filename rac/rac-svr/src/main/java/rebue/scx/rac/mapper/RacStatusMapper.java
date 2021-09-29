package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.homeUrl;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.isEnabled;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.racStatus;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacStatusDynamicSqlSupport.seqNo;

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
import rebue.scx.rac.mo.RacStatusMo;

@Mapper
public interface RacStatusMapper extends MapperRootInterface<RacStatusMo, Long> {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, realmId, name, homeUrl, isEnabled, seqNo, remark);

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
    int insert(InsertStatementProvider<RacStatusMo> insertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacStatusMo> multipleInsertStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RacStatusMoResult")
    Optional<RacStatusMo> selectOne(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RacStatusMoResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="REALM_ID", property="realmId", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOME_URL", property="homeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_ENABLED", property="isEnabled", jdbcType=JdbcType.BIT),
        @Result(column="SEQ_NO", property="seqNo", jdbcType=JdbcType.TINYINT),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<RacStatusMo> selectMany(SelectStatementProvider selectStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racStatus, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racStatus, completer);
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
    default int insert(RacStatusMo record) {
        return MyBatis3Utils.insert(this::insert, record, racStatus, c ->
            c.map(id).toProperty("id")
            .map(realmId).toProperty("realmId")
            .map(name).toProperty("name")
            .map(homeUrl).toProperty("homeUrl")
            .map(isEnabled).toProperty("isEnabled")
            .map(seqNo).toProperty("seqNo")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacStatusMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racStatus, c ->
            c.map(id).toProperty("id")
            .map(realmId).toProperty("realmId")
            .map(name).toProperty("name")
            .map(homeUrl).toProperty("homeUrl")
            .map(isEnabled).toProperty("isEnabled")
            .map(seqNo).toProperty("seqNo")
            .map(remark).toProperty("remark")
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacStatusMo record) {
        return MyBatis3Utils.insert(this::insert, record, racStatus, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(realmId).toPropertyWhenPresent("realmId", record::getRealmId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(homeUrl).toPropertyWhenPresent("homeUrl", record::getHomeUrl)
            .map(isEnabled).toPropertyWhenPresent("isEnabled", record::getIsEnabled)
            .map(seqNo).toPropertyWhenPresent("seqNo", record::getSeqNo)
            .map(remark).toPropertyWhenPresent("remark", record::getRemark)
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacStatusMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racStatus, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacStatusMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racStatus, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacStatusMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racStatus, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacStatusMo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racStatus, completer);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacStatusMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(realmId).equalTo(record::getRealmId)
                .set(name).equalTo(record::getName)
                .set(homeUrl).equalTo(record::getHomeUrl)
                .set(isEnabled).equalTo(record::getIsEnabled)
                .set(seqNo).equalTo(record::getSeqNo)
                .set(remark).equalTo(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacStatusMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(realmId).equalToWhenPresent(record::getRealmId)
                .set(name).equalToWhenPresent(record::getName)
                .set(homeUrl).equalToWhenPresent(record::getHomeUrl)
                .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
                .set(seqNo).equalToWhenPresent(record::getSeqNo)
                .set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacStatusMo record) {
        return update(c ->
            c.set(realmId).equalTo(record::getRealmId)
            .set(name).equalTo(record::getName)
            .set(homeUrl).equalTo(record::getHomeUrl)
            .set(isEnabled).equalTo(record::getIsEnabled)
            .set(seqNo).equalTo(record::getSeqNo)
            .set(remark).equalTo(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacStatusMo record) {
        return update(c ->
            c.set(realmId).equalToWhenPresent(record::getRealmId)
            .set(name).equalToWhenPresent(record::getName)
            .set(homeUrl).equalToWhenPresent(record::getHomeUrl)
            .set(isEnabled).equalToWhenPresent(record::getIsEnabled)
            .set(seqNo).equalToWhenPresent(record::getSeqNo)
            .set(remark).equalToWhenPresent(record::getRemark)
            .where(id, isEqualTo(record::getId))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacStatusMo record) {
        return delete(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(homeUrl, isEqualToWhenPresent(record::getHomeUrl))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacStatusMo> selectOne(RacStatusMo record) {
        return selectOne(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(homeUrl, isEqualToWhenPresent(record::getHomeUrl))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacStatusMo record) {
        return count(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(homeUrl, isEqualToWhenPresent(record::getHomeUrl))
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
    default boolean existSelective(RacStatusMo record) {
        return countSelective(record) > 0;
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacStatusMo> selectSelective(RacStatusMo record) {
        return select(c ->
            c.where(id, isEqualToWhenPresent(record::getId))
            .and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(name, isEqualToWhenPresent(record::getName))
            .and(homeUrl, isEqualToWhenPresent(record::getHomeUrl))
            .and(isEnabled, isEqualToWhenPresent(record::getIsEnabled))
            .and(seqNo, isEqualToWhenPresent(record::getSeqNo))
            .and(remark, isEqualToWhenPresent(record::getRemark))
        );
    }

    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacStatusMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}