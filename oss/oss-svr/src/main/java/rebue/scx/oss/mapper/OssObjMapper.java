package rebue.scx.oss.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.createDatetime;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.creatorId;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.creatorOrgId;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.id;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.objGroup;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.objName;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.objSize;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.objType;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.ossObj;
import static rebue.scx.oss.mapper.OssObjDynamicSqlSupport.url;

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
import rebue.scx.oss.mo.OssObjMo;

@Mapper
public interface OssObjMapper extends MapperRootInterface<OssObjMo, Long> {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	BasicColumn[] selectList = BasicColumn.columnList(id, objName, objGroup, objType, objSize, url, creatorId, creatorOrgId, createDatetime);

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
	int insert(InsertStatementProvider<OssObjMo> insertStatement);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<OssObjMo> multipleInsertStatement);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("OssObjMoResult")
	Optional<OssObjMo> selectOne(SelectStatementProvider selectStatement);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "OssObjMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "OBJ_NAME", property = "objName", jdbcType = JdbcType.VARCHAR), @Result(column = "OBJ_GROUP", property = "objGroup", jdbcType = JdbcType.VARCHAR),
			@Result(column = "OBJ_TYPE", property = "objType", jdbcType = JdbcType.VARCHAR), @Result(column = "OBJ_SIZE", property = "objSize", jdbcType = JdbcType.BIGINT),
			@Result(column = "URL", property = "url", jdbcType = JdbcType.VARCHAR), @Result(column = "CREATOR_ID", property = "creatorId", jdbcType = JdbcType.BIGINT),
			@Result(column = "CREATOR_ORG_ID", property = "creatorOrgId", jdbcType = JdbcType.BIGINT),
			@Result(column = "CREATE_DATETIME", property = "createDatetime", jdbcType = JdbcType.TIMESTAMP)
	})
	List<OssObjMo> selectMany(SelectStatementProvider selectStatement);

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
		return MyBatis3Utils.countFrom(this::count, ossObj, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, ossObj, completer);
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
	default int insert(OssObjMo record) {
		return MyBatis3Utils.insert(this::insert, record, ossObj,
				c -> c.map(id).toProperty("id").map(objName).toProperty("objName").map(objGroup).toProperty("objGroup").map(objType).toProperty("objType").map(objSize)
						.toProperty("objSize").map(url).toProperty("url").map(creatorId).toProperty("creatorId").map(creatorOrgId).toProperty("creatorOrgId").map(createDatetime)
						.toProperty("createDatetime"));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int insertMultiple(Collection<OssObjMo> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ossObj,
				c -> c.map(id).toProperty("id").map(objName).toProperty("objName").map(objGroup).toProperty("objGroup").map(objType).toProperty("objType").map(objSize)
						.toProperty("objSize").map(url).toProperty("url").map(creatorId).toProperty("creatorId").map(creatorOrgId).toProperty("creatorOrgId").map(createDatetime)
						.toProperty("createDatetime"));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int insertSelective(OssObjMo record) {
		return MyBatis3Utils.insert(this::insert, record, ossObj,
				c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(objName).toPropertyWhenPresent("objName", record::getObjName).map(objGroup)
						.toPropertyWhenPresent("objGroup", record::getObjGroup).map(objType).toPropertyWhenPresent("objType", record::getObjType).map(objSize)
						.toPropertyWhenPresent("objSize", record::getObjSize).map(url).toPropertyWhenPresent("url", record::getUrl).map(creatorId)
						.toPropertyWhenPresent("creatorId", record::getCreatorId).map(creatorOrgId).toPropertyWhenPresent("creatorOrgId", record::getCreatorOrgId)
						.map(createDatetime)
						.toPropertyWhenPresent("createDatetime", record::getCreateDatetime));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default Optional<OssObjMo> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, ossObj, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<OssObjMo> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, ossObj, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<OssObjMo> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ossObj, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default Optional<OssObjMo> selectByPrimaryKey(Long id_) {
		return selectOne(c -> c.where(id, isEqualTo(id_)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, ossObj, completer);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	static UpdateDSL<UpdateModel> updateAllColumns(OssObjMo record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalTo(record::getId).set(objName).equalTo(record::getObjName).set(objGroup).equalTo(record::getObjGroup).set(objType).equalTo(record::getObjType)
				.set(objSize).equalTo(record::getObjSize).set(url).equalTo(record::getUrl).set(creatorId).equalTo(record::getCreatorId).set(creatorOrgId)
				.equalTo(record::getCreatorOrgId).set(createDatetime).equalTo(record::getCreateDatetime);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	static UpdateDSL<UpdateModel> updateSelectiveColumns(OssObjMo record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalToWhenPresent(record::getId).set(objName).equalToWhenPresent(record::getObjName).set(objGroup).equalToWhenPresent(record::getObjGroup).set(objType)
				.equalToWhenPresent(record::getObjType).set(objSize).equalToWhenPresent(record::getObjSize).set(url).equalToWhenPresent(record::getUrl).set(creatorId)
				.equalToWhenPresent(record::getCreatorId).set(creatorOrgId).equalToWhenPresent(record::getCreatorOrgId).set(createDatetime)
				.equalToWhenPresent(record::getCreateDatetime);
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int updateByPrimaryKey(OssObjMo record) {
		return update(c -> c.set(objName).equalTo(record::getObjName).set(objGroup).equalTo(record::getObjGroup).set(objType).equalTo(record::getObjType).set(objSize)
				.equalTo(record::getObjSize).set(url).equalTo(record::getUrl).set(creatorId).equalTo(record::getCreatorId).set(creatorOrgId).equalTo(record::getCreatorOrgId)
				.set(createDatetime).equalTo(record::getCreateDatetime).where(id, isEqualTo(record::getId)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int updateByPrimaryKeySelective(OssObjMo record) {
		return update(c -> c.set(objName).equalToWhenPresent(record::getObjName).set(objGroup).equalToWhenPresent(record::getObjGroup).set(objType)
				.equalToWhenPresent(record::getObjType).set(objSize).equalToWhenPresent(record::getObjSize).set(url).equalToWhenPresent(record::getUrl).set(creatorId)
				.equalToWhenPresent(record::getCreatorId).set(creatorOrgId).equalToWhenPresent(record::getCreatorOrgId).set(createDatetime)
				.equalToWhenPresent(record::getCreateDatetime).where(id, isEqualTo(record::getId)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default int deleteSelective(OssObjMo record) {
		return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(objName, isEqualToWhenPresent(record::getObjName))
				.and(objGroup, isEqualToWhenPresent(record::getObjGroup)).and(objType, isEqualToWhenPresent(record::getObjType))
				.and(objSize, isEqualToWhenPresent(record::getObjSize))
				.and(url, isEqualToWhenPresent(record::getUrl)).and(creatorId, isEqualToWhenPresent(record::getCreatorId))
				.and(creatorOrgId, isEqualToWhenPresent(record::getCreatorOrgId)).and(createDatetime, isEqualToWhenPresent(record::getCreateDatetime)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default Optional<OssObjMo> selectOne(OssObjMo record) {
		return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(objName, isEqualToWhenPresent(record::getObjName))
				.and(objGroup, isEqualToWhenPresent(record::getObjGroup)).and(objType, isEqualToWhenPresent(record::getObjType))
				.and(objSize, isEqualToWhenPresent(record::getObjSize))
				.and(url, isEqualToWhenPresent(record::getUrl)).and(creatorId, isEqualToWhenPresent(record::getCreatorId))
				.and(creatorOrgId, isEqualToWhenPresent(record::getCreatorOrgId)).and(createDatetime, isEqualToWhenPresent(record::getCreateDatetime)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default long countSelective(OssObjMo record) {
		return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(objName, isEqualToWhenPresent(record::getObjName))
				.and(objGroup, isEqualToWhenPresent(record::getObjGroup)).and(objType, isEqualToWhenPresent(record::getObjType))
				.and(objSize, isEqualToWhenPresent(record::getObjSize))
				.and(url, isEqualToWhenPresent(record::getUrl)).and(creatorId, isEqualToWhenPresent(record::getCreatorId))
				.and(creatorOrgId, isEqualToWhenPresent(record::getCreatorOrgId)).and(createDatetime, isEqualToWhenPresent(record::getCreateDatetime)));
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default boolean existByPrimaryKey(Long id_) {
		return count(c -> c.where(id, isEqualTo(id_))) > 0;
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default boolean existSelective(OssObjMo record) {
		return countSelective(record) > 0;
	}

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	default List<OssObjMo> selectSelective(OssObjMo record) {
		return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(objName, isEqualToWhenPresent(record::getObjName))
				.and(objGroup, isEqualToWhenPresent(record::getObjGroup)).and(objType, isEqualToWhenPresent(record::getObjType))
				.and(objSize, isEqualToWhenPresent(record::getObjSize))
				.and(url, isEqualToWhenPresent(record::getUrl)).and(creatorId, isEqualToWhenPresent(record::getCreatorId))
				.and(creatorOrgId, isEqualToWhenPresent(record::getCreatorOrgId)).and(createDatetime, isEqualToWhenPresent(record::getCreateDatetime)));
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
	default List<OssObjMo> selectIn(List<Long> ids) {
		return select(c -> c.where(id, isIn(ids)));
	}
}
