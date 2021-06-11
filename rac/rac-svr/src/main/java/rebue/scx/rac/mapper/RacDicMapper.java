package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isInWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.or;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.dicKey;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.domainId;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.racDic;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacDicDynamicSqlSupport.sysId;

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
    BasicColumn[] selectList = BasicColumn.columnList(id, dicKey, name, domainId, sysId, remark);

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
    int insert(InsertStatementProvider<RacDicMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacDicMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacDicMoResult")
    Optional<RacDicMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacDicMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "DIC_KEY", property = "dicKey", jdbcType = JdbcType.VARCHAR), @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DOMAIN_ID", property = "domainId", jdbcType = JdbcType.VARCHAR), @Result(column = "SYS_ID", property = "sysId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<RacDicMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insert(RacDicMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDic, c -> c.map(id).toProperty("id").map(dicKey).toProperty("dicKey").map(name).toProperty("name").map(domainId)
                .toProperty("domainId").map(sysId).toProperty("sysId").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacDicMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racDic, c -> c.map(id).toProperty("id").map(dicKey).toProperty("dicKey").map(name).toProperty("name")
                .map(domainId).toProperty("domainId").map(sysId).toProperty("sysId").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacDicMo record) {
        return MyBatis3Utils.insert(this::insert, record, racDic,
                c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(dicKey).toPropertyWhenPresent("dicKey", record::getDicKey).map(name)
                        .toPropertyWhenPresent("name", record::getName).map(domainId).toPropertyWhenPresent("domainId", record::getDomainId).map(sysId)
                        .toPropertyWhenPresent("sysId", record::getSysId).map(remark).toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDicMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDicMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDicMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racDic, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacDicMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(dicKey).equalTo(record::getDicKey).set(name).equalTo(record::getName).set(domainId).equalTo(record::getDomainId).set(sysId)
                .equalTo(record::getSysId).set(remark).equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacDicMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(dicKey).equalToWhenPresent(record::getDicKey).set(name).equalToWhenPresent(record::getName).set(domainId)
                .equalToWhenPresent(record::getDomainId).set(sysId).equalToWhenPresent(record::getSysId).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacDicMo record) {
        return update(c -> c.set(dicKey).equalTo(record::getDicKey).set(name).equalTo(record::getName).set(domainId).equalTo(record::getDomainId).set(sysId)
                .equalTo(record::getSysId).set(remark).equalTo(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacDicMo record) {
        return update(c -> c.set(dicKey).equalToWhenPresent(record::getDicKey).set(name).equalToWhenPresent(record::getName).set(domainId).equalToWhenPresent(record::getDomainId)
                .set(sysId).equalToWhenPresent(record::getSysId).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacDicMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacDicMo> selectOne(RacDicMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacDicMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existSelective(RacDicMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacDicMo> selectSelective(RacDicMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(dicKey, isEqualToWhenPresent(record::getDicKey)).and(name, isEqualToWhenPresent(record::getName))
                .and(domainId, isEqualToWhenPresent(record::getDomainId)).and(sysId, isEqualToWhenPresent(record::getSysId)).and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    default List<RacDicMo> selectPageOrKeywords(DicListWithItemTo record) {
        final String       keywords  = StringUtils.isBlank(record.getKeywords()) ? null : "%" + record.getKeywords() + "%";
        final List<String> domainIds = (record.getDomainIds() != null) ? record.getDomainIds() : null;
        final List<String> sysIds    = (record.getSysIds() != null) ? record.getSysIds() : null;
        return select(c -> {
            if (domainIds == null && sysIds == null) {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords)));
            }
            else if (domainIds != null && sysIds == null) {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords))).and(domainId,
                                isInWhenPresent(domainIds));
            }
            else if (domainIds == null && sysIds != null) {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords))).and(sysId, isInWhenPresent(sysIds));
            }
            else {
                return c.where(id, isEqualToWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null), or(name, isLikeWhenPresent(keywords)),
                        or(remark, isLikeWhenPresent(keywords)))
                        .and(domainId, isInWhenPresent(domainIds)).and(sysId, isInWhenPresent(sysIds));
            }
        });
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
    default Optional<RacDicMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default boolean existByPrimaryKey(Long id_) {
        return count(c -> c.where(id, isEqualTo(id_))) > 0;
    }
}
