package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThan;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.dicId;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.dicItemKey;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.orgId;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.racDicItem;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.treeCode;
import static rebue.scx.rac.mapper.RacDicItemDynamicSqlSupport.updateDatetime;

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
import rebue.scx.rac.mo.RacDicItemMo;

@Mapper
public interface RacDicItemMapper extends MapperRootInterface<RacDicItemMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, dicId, orgId, dicItemKey, name, treeCode, updateDatetime, remark);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RacDicItemMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDicItemMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacDicItemMoResult")
    Optional<RacDicItemMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacDicItemMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "DIC_ID", property = "dicId", jdbcType = JdbcType.BIGINT), @Result(column = "ORG_ID", property = "orgId", jdbcType = JdbcType.BIGINT),
            @Result(column = "DIC_ITEM_KEY", property = "dicItemKey", jdbcType = JdbcType.VARCHAR), @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TREE_CODE", property = "treeCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UPDATE_DATETIME", property = "updateDatetime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<RacDicItemMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDicItem, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDicItem, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insert(RacDicItemMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDicItem,
                c -> c.map(id).toProperty("id").map(dicId).toProperty("dicId").map(orgId).toProperty("orgId").map(dicItemKey).toProperty("dicItemKey").map(name).toProperty("name")
                        .map(treeCode).toProperty("treeCode").map(updateDatetime).toProperty("updateDatetime").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(Collection<RacDicItemMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDicItem,
                c -> c.map(id).toProperty("id").map(dicId).toProperty("dicId").map(orgId).toProperty("orgId").map(dicItemKey).toProperty("dicItemKey").map(name).toProperty("name")
                        .map(treeCode).toProperty("treeCode").map(updateDatetime).toProperty("updateDatetime").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(RacDicItemMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDicItem,
                c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(dicId).toPropertyWhenPresent("dicId", record::getDicId).map(orgId)
                        .toPropertyWhenPresent("orgId", record::getOrgId).map(dicItemKey).toPropertyWhenPresent("dicItemKey", record::getDicItemKey).map(name)
                        .toPropertyWhenPresent("name", record::getName).map(treeCode).toPropertyWhenPresent("treeCode", record::getTreeCode).map(updateDatetime)
                        .toPropertyWhenPresent("updateDatetime", record::getUpdateDatetime).map(remark).toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDicItemMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDicItem, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicItemMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDicItem, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicItemMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDicItem, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDicItem, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacDicItemMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(dicId).equalTo(record::getDicId).set(orgId).equalTo(record::getOrgId).set(dicItemKey).equalTo(record::getDicItemKey).set(name)
                .equalTo(record::getName).set(treeCode).equalTo(record::getTreeCode).set(updateDatetime).equalTo(record::getUpdateDatetime).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacDicItemMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(dicId).equalToWhenPresent(record::getDicId).set(orgId).equalToWhenPresent(record::getOrgId).set(dicItemKey)
                .equalToWhenPresent(record::getDicItemKey).set(name).equalToWhenPresent(record::getName).set(treeCode).equalToWhenPresent(record::getTreeCode).set(updateDatetime)
                .equalToWhenPresent(record::getUpdateDatetime).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(RacDicItemMo record) {
        return update(c -> c.set(dicId).equalTo(record::getDicId).set(orgId).equalTo(record::getOrgId).set(dicItemKey).equalTo(record::getDicItemKey).set(name)
                .equalTo(record::getName).set(treeCode).equalTo(record::getTreeCode).set(updateDatetime).equalTo(record::getUpdateDatetime).set(remark).equalTo(record::getRemark)
                .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(RacDicItemMo record) {
        return update(c -> c.set(dicId).equalToWhenPresent(record::getDicId).set(orgId).equalToWhenPresent(record::getOrgId).set(dicItemKey)
                .equalToWhenPresent(record::getDicItemKey).set(name).equalToWhenPresent(record::getName).set(treeCode).equalToWhenPresent(record::getTreeCode).set(updateDatetime)
                .equalToWhenPresent(record::getUpdateDatetime).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(RacDicItemMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicId, isEqualToWhenPresent(record::getDicId)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(dicItemKey, isEqualToWhenPresent(record::getDicItemKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(treeCode, isEqualToWhenPresent(record::getTreeCode))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDicItemMo> selectOne(RacDicItemMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicId, isEqualToWhenPresent(record::getDicId)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(dicItemKey, isEqualToWhenPresent(record::getDicItemKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(treeCode, isEqualToWhenPresent(record::getTreeCode))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(RacDicItemMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicId, isEqualToWhenPresent(record::getDicId)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(dicItemKey, isEqualToWhenPresent(record::getDicItemKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(treeCode, isEqualToWhenPresent(record::getTreeCode))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existSelective(RacDicItemMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicItemMo> selectSelective(RacDicItemMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicId, isEqualToWhenPresent(record::getDicId)).and(orgId, isEqualToWhenPresent(record::getOrgId))
                .and(dicItemKey, isEqualToWhenPresent(record::getDicItemKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(treeCode, isEqualToWhenPresent(record::getTreeCode))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * 通过多个dicId查询字典项
     */
    default List<RacDicItemMo> selectByInDicId(final List<Long> dicIds) {
        return select(c -> c.where(dicId, isIn(dicIds)).orderBy(treeCode));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDicItemMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existByPrimaryKey(Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * 查寻当前字典项下面的字典项记录数
     */
    default long countDicItemSelective(final RacDicItemMo record) {
        final String str = record.getTreeCode() + "___";
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicId, isEqualToWhenPresent(record::getDicId)).and(name, isEqualToWhenPresent(record::getName))
                .and(treeCode, isLikeWhenPresent(str)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * 查寻字典下面的字典项记录数
     */
    default Long countDicSelective(final RacDicItemMo record) {
        // _ 表示通配符 匹配一个字符
        final String str = "___";
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicId, isEqualToWhenPresent(record::getDicId)).and(name, isEqualToWhenPresent(record::getName))
                .and(treeCode, isLikeWhenPresent(str)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * 查询大于当前字典项的treeCode的记录
     */
    default List<RacDicItemMo> selectDicSelective(final RacDicItemMo record) {
        // _ 表示通配符 匹配一个字符
        final String str = "___";
        return select(c -> c.where(dicId, isEqualToWhenPresent(record::getDicId))
                .and(treeCode, isGreaterThan(record::getTreeCode)).and(treeCode, isLikeWhenPresent(str)));
    }

    /**
     * 删除当前字典项下面的字典项记录数
     */
    default int deleteDicItemSelective(RacDicItemMo record) {
        // _ 表示通配符 匹配一个字符
        final String str = record.getTreeCode() + "%";
        return delete(c -> c.where(dicId, isEqualToWhenPresent(record::getDicId))
                .and(treeCode, isLikeWhenPresent(str)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicItemMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default BasicColumn[] getColumns() {
        return selectList;
    }

}
