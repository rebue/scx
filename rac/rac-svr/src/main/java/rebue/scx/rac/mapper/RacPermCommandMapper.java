package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.rac.mapper.RacPermCommandDynamicSqlSupport.commandKey;
import static rebue.scx.rac.mapper.RacPermCommandDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacPermCommandDynamicSqlSupport.permId;
import static rebue.scx.rac.mapper.RacPermCommandDynamicSqlSupport.racPermCommand;
import static rebue.scx.rac.mapper.RacPermCommandDynamicSqlSupport.remark;

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
import org.apache.ibatis.annotations.Select;
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
import rebue.scx.rac.mo.RacPermCommandMo;

@Mapper
public interface RacPermCommandMapper extends MapperRootInterface<RacPermCommandMo, Long> {
	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	BasicColumn[] selectList = BasicColumn.columnList(id, permId, commandKey, remark);

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
	int insert(InsertStatementProvider<RacPermCommandMo> insertStatement);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<RacPermCommandMo> multipleInsertStatement);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("RacPermCommandMoResult")
	Optional<RacPermCommandMo> selectOne(SelectStatementProvider selectStatement);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "RacPermCommandMoResult", value = {
			@Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "PERM_ID", property = "permId", jdbcType = JdbcType.BIGINT),
			@Result(column = "COMMAND_KEY", property = "commandKey", jdbcType = JdbcType.VARCHAR),
			@Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
	})
	List<RacPermCommandMo> selectMany(SelectStatementProvider selectStatement);

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
		return MyBatis3Utils.countFrom(this::count, racPermCommand, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int delete(final DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, racPermCommand, completer);
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
	default int insert(final RacPermCommandMo record) {
		return MyBatis3Utils.insert(this::insert, record, racPermCommand, c -> c.map(id).toProperty("id")
				.map(permId).toProperty("permId")
				.map(commandKey).toProperty("commandKey")
				.map(remark).toProperty("remark"));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int insertMultiple(final Collection<RacPermCommandMo> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racPermCommand, c -> c.map(id).toProperty("id")
				.map(permId).toProperty("permId")
				.map(commandKey).toProperty("commandKey")
				.map(remark).toProperty("remark"));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int insertSelective(final RacPermCommandMo record) {
		return MyBatis3Utils.insert(this::insert, record, racPermCommand, c -> c.map(id).toPropertyWhenPresent("id", record::getId)
				.map(permId).toPropertyWhenPresent("permId", record::getPermId)
				.map(commandKey).toPropertyWhenPresent("commandKey", record::getCommandKey)
				.map(remark).toPropertyWhenPresent("remark", record::getRemark));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default Optional<RacPermCommandMo> selectOne(final SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, racPermCommand, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<RacPermCommandMo> select(final SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, racPermCommand, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<RacPermCommandMo> selectDistinct(final SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racPermCommand, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default Optional<RacPermCommandMo> selectByPrimaryKey(final Long id_) {
		return selectOne(c -> c.where(id, isEqualTo(id_)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int update(final UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, racPermCommand, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	static UpdateDSL<UpdateModel> updateAllColumns(final RacPermCommandMo record, final UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalTo(record::getId)
				.set(permId).equalTo(record::getPermId)
				.set(commandKey).equalTo(record::getCommandKey)
				.set(remark).equalTo(record::getRemark);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	static UpdateDSL<UpdateModel> updateSelectiveColumns(final RacPermCommandMo record, final UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalToWhenPresent(record::getId)
				.set(permId).equalToWhenPresent(record::getPermId)
				.set(commandKey).equalToWhenPresent(record::getCommandKey)
				.set(remark).equalToWhenPresent(record::getRemark);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int updateByPrimaryKey(final RacPermCommandMo record) {
		return update(c -> c.set(permId).equalTo(record::getPermId)
				.set(commandKey).equalTo(record::getCommandKey)
				.set(remark).equalTo(record::getRemark)
				.where(id, isEqualTo(record::getId)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int updateByPrimaryKeySelective(final RacPermCommandMo record) {
		return update(c -> c.set(permId).equalToWhenPresent(record::getPermId)
				.set(commandKey).equalToWhenPresent(record::getCommandKey)
				.set(remark).equalToWhenPresent(record::getRemark)
				.where(id, isEqualTo(record::getId)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default BasicColumn[] getColumns() {
		return selectList;
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int deleteSelective(final RacPermCommandMo record) {
		return delete(c -> c.where(id, isEqualToWhenPresent(record::getId))
				.and(permId, isEqualToWhenPresent(record::getPermId))
				.and(commandKey, isEqualToWhenPresent(record::getCommandKey))
				.and(remark, isEqualToWhenPresent(record::getRemark)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default Optional<RacPermCommandMo> selectOne(final RacPermCommandMo record) {
		return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId))
				.and(permId, isEqualToWhenPresent(record::getPermId))
				.and(commandKey, isEqualToWhenPresent(record::getCommandKey))
				.and(remark, isEqualToWhenPresent(record::getRemark)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default long countSelective(final RacPermCommandMo record) {
		return count(c -> c.where(id, isEqualToWhenPresent(record::getId))
				.and(permId, isEqualToWhenPresent(record::getPermId))
				.and(commandKey, isEqualToWhenPresent(record::getCommandKey))
				.and(remark, isEqualToWhenPresent(record::getRemark)));
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
	default boolean existSelective(final RacPermCommandMo record) {
		return countSelective(record) > 0;
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<RacPermCommandMo> selectSelective(final RacPermCommandMo record) {
		return select(c -> c.where(id, isEqualToWhenPresent(record::getId))
				.and(permId, isEqualToWhenPresent(record::getPermId))
				.and(commandKey, isEqualToWhenPresent(record::getCommandKey))
				.and(remark, isEqualToWhenPresent(record::getRemark)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<RacPermCommandMo> selectIn(final List<Long> ids) {
		return select(c -> c.where(id, isIn(ids)));
	}

	@Select("SELECT \n"
			+ "    d.*\n"
			+ "FROM\n"
			+ "    rac.RAC_ACCOUNT a\n"
			+ "        LEFT JOIN\n"
			+ "    RAC_ACCOUNT_ROLE b ON a.id = b.ACCOUNT_ID\n"
			+ "        LEFT JOIN\n"
			+ "    RAC_ROLE_PERM c ON b.ROLE_ID = c.ROLE_ID\n"
			+ "   LEFT JOIN\n"
			+ "    RAC_PERM_COMMAND d ON c.PERM_ID = d.PERM_ID\n"
			+ "WHERE\n"
			+ "    a.id =#{id} and d.id is not null;")
	List<RacPermCommandMo> selectByAccountId(@Param("id") Long id);
}