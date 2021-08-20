package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacPermMenuDynamicSqlSupport.appId;
import static rebue.scx.rac.mapper.RacPermMenuDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacPermMenuDynamicSqlSupport.menuUrn;
import static rebue.scx.rac.mapper.RacPermMenuDynamicSqlSupport.permId;
import static rebue.scx.rac.mapper.RacPermMenuDynamicSqlSupport.racPermMenu;

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
import rebue.scx.rac.mo.RacPermMenuMo;

@Mapper
public interface RacPermMenuMapper extends MapperRootInterface<RacPermMenuMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, appId, permId, menuUrn);

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
    int insert(InsertStatementProvider<RacPermMenuMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacPermMenuMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacPermMenuMoResult")
    Optional<RacPermMenuMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacPermMenuMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "APP_ID", property = "appId", jdbcType = JdbcType.VARCHAR), @Result(column = "PERM_ID", property = "permId", jdbcType = JdbcType.BIGINT),
        @Result(column = "MENU_URN", property = "menuUrn", jdbcType = JdbcType.VARCHAR)
    })
    List<RacPermMenuMo> selectMany(SelectStatementProvider selectStatement);

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
        return MyBatis3Utils.countFrom(this::count, racPermMenu, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int delete(final DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racPermMenu, completer);
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
    default int insert(final RacPermMenuMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPermMenu,
            c -> c.map(id).toProperty("id").map(appId).toProperty("appId").map(permId).toProperty("permId").map(menuUrn).toProperty("menuUrn"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertMultiple(final Collection<RacPermMenuMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racPermMenu,
            c -> c.map(id).toProperty("id").map(appId).toProperty("appId").map(permId).toProperty("permId").map(menuUrn).toProperty("menuUrn"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int insertSelective(final RacPermMenuMo record) {
        return MyBatis3Utils.insert(this::insert, record, racPermMenu,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(appId).toPropertyWhenPresent("appId", record::getAppId).map(permId)
                .toPropertyWhenPresent("permId", record::getPermId).map(menuUrn).toPropertyWhenPresent("menuUrn", record::getMenuUrn));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacPermMenuMo> selectOne(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racPermMenu, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacPermMenuMo> select(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racPermMenu, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacPermMenuMo> selectDistinct(final SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racPermMenu, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacPermMenuMo> selectByPrimaryKey(final Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int update(final UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racPermMenu, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(final RacPermMenuMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(appId).equalTo(record::getAppId).set(permId).equalTo(record::getPermId).set(menuUrn).equalTo(record::getMenuUrn);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(final RacPermMenuMo record, final UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(appId).equalToWhenPresent(record::getAppId).set(permId).equalToWhenPresent(record::getPermId).set(menuUrn)
            .equalToWhenPresent(record::getMenuUrn);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKey(final RacPermMenuMo record) {
        return update(
            c -> c.set(appId).equalTo(record::getAppId).set(permId).equalTo(record::getPermId).set(menuUrn).equalTo(record::getMenuUrn).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int updateByPrimaryKeySelective(final RacPermMenuMo record) {
        return update(c -> c.set(appId).equalToWhenPresent(record::getAppId).set(permId).equalToWhenPresent(record::getPermId).set(menuUrn).equalToWhenPresent(record::getMenuUrn)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default int deleteSelective(final RacPermMenuMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(permId, isEqualToWhenPresent(record::getPermId))
            .and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default Optional<RacPermMenuMo> selectOne(final RacPermMenuMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId))
            .and(permId, isEqualToWhenPresent(record::getPermId)).and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default long countSelective(final RacPermMenuMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(permId, isEqualToWhenPresent(record::getPermId))
            .and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)));
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
    default boolean existSelective(final RacPermMenuMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacPermMenuMo> selectSelective(final RacPermMenuMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(appId, isEqualToWhenPresent(record::getAppId)).and(permId, isEqualToWhenPresent(record::getPermId))
            .and(menuUrn, isEqualToWhenPresent(record::getMenuUrn)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    default List<RacPermMenuMo> selectIn(final List<Long> ids) {
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
