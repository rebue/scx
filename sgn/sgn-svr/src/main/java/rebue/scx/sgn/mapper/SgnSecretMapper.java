package rebue.scx.sgn.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.sgn.mapper.SgnSecretDynamicSqlSupport.algorithm;
import static rebue.scx.sgn.mapper.SgnSecretDynamicSqlSupport.id;
import static rebue.scx.sgn.mapper.SgnSecretDynamicSqlSupport.secret;
import static rebue.scx.sgn.mapper.SgnSecretDynamicSqlSupport.sgnSecret;

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
import rebue.scx.sgn.mo.SgnSecretMo;

@Mapper
public interface SgnSecretMapper extends MapperRootInterface<SgnSecretMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, secret, algorithm);

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
    int insert(InsertStatementProvider<SgnSecretMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SgnSecretMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("SgnSecretMoResult")
    Optional<SgnSecretMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "SgnSecretMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "SECRET", property = "secret", jdbcType = JdbcType.VARCHAR), @Result(column = "ALGORITHM", property = "algorithm", jdbcType = JdbcType.TINYINT)
    })
    List<SgnSecretMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sgnSecret, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sgnSecret, completer);
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
    default int insert(SgnSecretMo record) {
        return MyBatis3Utils.insert(this::insert, record, sgnSecret, c -> c.map(id).toProperty("id").map(secret).toProperty("secret").map(algorithm).toProperty("algorithm"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<SgnSecretMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sgnSecret,
            c -> c.map(id).toProperty("id").map(secret).toProperty("secret").map(algorithm).toProperty("algorithm"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(SgnSecretMo record) {
        return MyBatis3Utils.insert(this::insert, record, sgnSecret, c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(secret)
            .toPropertyWhenPresent("secret", record::getSecret).map(algorithm).toPropertyWhenPresent("algorithm", record::getAlgorithm));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<SgnSecretMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sgnSecret, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<SgnSecretMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sgnSecret, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<SgnSecretMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sgnSecret, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<SgnSecretMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sgnSecret, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(SgnSecretMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(secret).equalTo(record::getSecret).set(algorithm).equalTo(record::getAlgorithm);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SgnSecretMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(secret).equalToWhenPresent(record::getSecret).set(algorithm).equalToWhenPresent(record::getAlgorithm);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(SgnSecretMo record) {
        return update(c -> c.set(secret).equalTo(record::getSecret).set(algorithm).equalTo(record::getAlgorithm).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(SgnSecretMo record) {
        return update(c -> c.set(secret).equalToWhenPresent(record::getSecret).set(algorithm).equalToWhenPresent(record::getAlgorithm).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(SgnSecretMo record) {
        return delete(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(secret, isEqualToWhenPresent(record::getSecret)).and(algorithm, isEqualToWhenPresent(record::getAlgorithm)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<SgnSecretMo> selectOne(SgnSecretMo record) {
        return selectOne(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(secret, isEqualToWhenPresent(record::getSecret)).and(algorithm, isEqualToWhenPresent(record::getAlgorithm)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(SgnSecretMo record) {
        return count(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(secret, isEqualToWhenPresent(record::getSecret)).and(algorithm, isEqualToWhenPresent(record::getAlgorithm)));
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
    default boolean existSelective(SgnSecretMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<SgnSecretMo> selectSelective(SgnSecretMo record) {
        return select(
            c -> c.where(id, isEqualToWhenPresent(record::getId)).and(secret, isEqualToWhenPresent(record::getSecret)).and(algorithm, isEqualToWhenPresent(record::getAlgorithm)));
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
    default List<SgnSecretMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
