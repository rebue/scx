package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isInWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.or;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.dicKey;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.racDic;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.sysId;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.updateDatetime;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
import rebue.scx.rac.mo.RacDicMo;
import rebue.scx.rac.to.ex.DicListWithItemTo;
import rebue.wheel.core.NumberUtils;

@Mapper
public interface RacDicMapper extends MapperRootInterface<RacDicMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, dicKey, name, realmId, sysId, remark, updateDatetime);

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
    int insert(InsertStatementProvider<RacDicMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDicMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacDicMoResult")
    Optional<RacDicMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacDicMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "DIC_KEY", property = "dicKey", jdbcType = JdbcType.VARCHAR), @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR), @Result(column = "SYS_ID", property = "sysId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UPDATE_DATETIME", property = "updateDatetime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<RacDicMo> selectMany(SelectStatementProvider selectStatement);

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
    default long count(final CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(final DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insert(final RacDicMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDic, c -> c.map(id).toProperty("id").map(dicKey).toProperty("dicKey").map(name).toProperty("name").map(realmId)
                .toProperty("realmId").map(sysId).toProperty("sysId").map(remark).toProperty("remark").map(updateDatetime).toProperty("updateDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(final Collection<RacDicMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDic, c -> c.map(id).toProperty("id").map(dicKey).toProperty("dicKey").map(name).toProperty("name")
                .map(realmId).toProperty("realmId").map(sysId).toProperty("sysId").map(remark).toProperty("remark").map(updateDatetime).toProperty("updateDatetime"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(final RacDicMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDic,
                c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(dicKey).toPropertyWhenPresent("dicKey", record::getDicKey).map(name)
                        .toPropertyWhenPresent("name", record::getName).map(realmId).toPropertyWhenPresent("realmId", record::getRealmId).map(sysId)
                        .toPropertyWhenPresent("sysId", record::getSysId).map(remark).toPropertyWhenPresent("remark", record::getRemark).map(updateDatetime)
                        .toPropertyWhenPresent("updateDatetime", record::getUpdateDatetime));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDicMo> selectOne(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicMo> select(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicMo> selectDistinct(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(final UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(final RacDicMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(dicKey).equalTo(record::getDicKey).set(name).equalTo(record::getName).set(realmId).equalTo(record::getRealmId).set(sysId)
                .equalTo(record::getSysId).set(remark).equalTo(record::getRemark).set(updateDatetime).equalTo(record::getUpdateDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(final RacDicMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(dicKey).equalToWhenPresent(record::getDicKey).set(name).equalToWhenPresent(record::getName).set(realmId)
                .equalToWhenPresent(record::getRealmId).set(sysId).equalToWhenPresent(record::getSysId).set(remark).equalToWhenPresent(record::getRemark).set(updateDatetime)
                .equalToWhenPresent(record::getUpdateDatetime);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(final RacDicMo record) {
        return update(c -> c.set(dicKey).equalTo(record::getDicKey).set(name).equalTo(record::getName).set(realmId).equalTo(record::getRealmId).set(sysId).equalTo(record::getSysId)
                .set(remark).equalTo(record::getRemark).set(updateDatetime).equalTo(record::getUpdateDatetime).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(final RacDicMo record) {
        return update(c -> c.set(dicKey).equalToWhenPresent(record::getDicKey).set(name).equalToWhenPresent(record::getName).set(realmId).equalToWhenPresent(record::getRealmId)
                .set(sysId).equalToWhenPresent(record::getSysId).set(remark).equalToWhenPresent(record::getRemark).set(updateDatetime).equalToWhenPresent(record::getUpdateDatetime)
                .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(final RacDicMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(realmId, isEqualToWhenPresent(record::getRealmId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDicMo> selectOne(final RacDicMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(realmId, isEqualToWhenPresent(record::getRealmId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(final RacDicMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(realmId, isEqualToWhenPresent(record::getRealmId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existSelective(final RacDicMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicMo> selectSelective(final RacDicMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(realmId, isEqualToWhenPresent(record::getRealmId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark))
                .and(updateDatetime, isEqualToWhenPresent(record::getUpdateDatetime)));
    }

    default List<RacDicMo> selectPageOrKeywords(final DicListWithItemTo record) {
        final String       keywords = StringUtils.isBlank(record.getKeywords()) ? null : "%" + record.getKeywords() + "%";
        final List<String> realmIds = (record.getRealmIds() != null) ? record.getRealmIds() : null;
        final List<String> sysIds   = (record.getSysIds() != null) ? record.getSysIds() : null;
        return select(c -> {
            if (realmIds == null && sysIds == null) {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords)));
            }
            else if (realmIds != null && sysIds == null) {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords))).and(realmId, isInWhenPresent(realmIds));
            }
            else if (realmIds == null && sysIds != null) {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords))).and(sysId, isInWhenPresent(sysIds));
            }
            else {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords))).and(realmId, isInWhenPresent(realmIds)).and(sysId, isInWhenPresent(sysIds));
            }
        });
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteByPrimaryKey(final Long id_) {
        return delete(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacDicMo> selectByPrimaryKey(final Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default boolean existByPrimaryKey(final Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacDicMo> selectIn(final List<Long> ids) {
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
