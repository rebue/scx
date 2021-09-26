package rebue.scx.etl.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.dbName;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.dbType;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.etlConn;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.host;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.id;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.name;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.port;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.remark;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.userName;
import static rebue.scx.etl.mapper.EtlConnDynamicSqlSupport.userPswd;

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
import rebue.scx.etl.mo.EtlConnMo;

@Mapper
public interface EtlConnMapper extends MapperRootInterface<EtlConnMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, dbType, dbName, host, port, userName, userPswd, remark);

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
    int insert(InsertStatementProvider<EtlConnMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EtlConnMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("EtlConnMoResult")
    Optional<EtlConnMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "EtlConnMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "DB_TYPE", property = "dbType", jdbcType = JdbcType.TINYINT),
        @Result(column = "DB_NAME", property = "dbName", jdbcType = JdbcType.VARCHAR), @Result(column = "HOST", property = "host", jdbcType = JdbcType.VARCHAR),
        @Result(column = "PORT", property = "port", jdbcType = JdbcType.SMALLINT), @Result(column = "USER_NAME", property = "userName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "USER_PSWD", property = "userPswd", jdbcType = JdbcType.VARCHAR), @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    List<EtlConnMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, etlConn, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, etlConn, completer);
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
    default int insert(EtlConnMo record) {
        return MyBatis3Utils.insert(this::insert, record, etlConn,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(dbType).toProperty("dbType").map(dbName).toProperty("dbName").map(host).toProperty("host").map(port)
                .toProperty("port").map(userName).toProperty("userName").map(userPswd).toProperty("userPswd").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<EtlConnMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, etlConn,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(dbType).toProperty("dbType").map(dbName).toProperty("dbName").map(host).toProperty("host").map(port)
                .toProperty("port").map(userName).toProperty("userName").map(userPswd).toProperty("userPswd").map(remark).toProperty("remark"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(EtlConnMo record) {
        return MyBatis3Utils.insert(this::insert, record, etlConn,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName).map(dbType)
                .toPropertyWhenPresent("dbType", record::getDbType).map(dbName).toPropertyWhenPresent("dbName", record::getDbName).map(host)
                .toPropertyWhenPresent("host", record::getHost).map(port).toPropertyWhenPresent("port", record::getPort).map(userName)
                .toPropertyWhenPresent("userName", record::getUserName).map(userPswd).toPropertyWhenPresent("userPswd", record::getUserPswd).map(remark)
                .toPropertyWhenPresent("remark", record::getRemark));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlConnMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, etlConn, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlConnMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, etlConn, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlConnMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, etlConn, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlConnMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, etlConn, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(EtlConnMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(dbType).equalTo(record::getDbType).set(dbName).equalTo(record::getDbName).set(host)
            .equalTo(record::getHost).set(port).equalTo(record::getPort).set(userName).equalTo(record::getUserName).set(userPswd).equalTo(record::getUserPswd).set(remark)
            .equalTo(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EtlConnMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(dbType).equalToWhenPresent(record::getDbType).set(dbName)
            .equalToWhenPresent(record::getDbName).set(host).equalToWhenPresent(record::getHost).set(port).equalToWhenPresent(record::getPort).set(userName)
            .equalToWhenPresent(record::getUserName).set(userPswd).equalToWhenPresent(record::getUserPswd).set(remark).equalToWhenPresent(record::getRemark);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(EtlConnMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(dbType).equalTo(record::getDbType).set(dbName).equalTo(record::getDbName).set(host).equalTo(record::getHost)
            .set(port).equalTo(record::getPort).set(userName).equalTo(record::getUserName).set(userPswd).equalTo(record::getUserPswd).set(remark).equalTo(record::getRemark)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(EtlConnMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(dbType).equalToWhenPresent(record::getDbType).set(dbName).equalToWhenPresent(record::getDbName)
            .set(host).equalToWhenPresent(record::getHost).set(port).equalToWhenPresent(record::getPort).set(userName).equalToWhenPresent(record::getUserName).set(userPswd)
            .equalToWhenPresent(record::getUserPswd).set(remark).equalToWhenPresent(record::getRemark).where(id, isEqualTo(record::getId)));
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
    default int deleteSelective(EtlConnMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(dbType, isEqualToWhenPresent(record::getDbType))
            .and(dbName, isEqualToWhenPresent(record::getDbName)).and(host, isEqualToWhenPresent(record::getHost)).and(port, isEqualToWhenPresent(record::getPort))
            .and(userName, isEqualToWhenPresent(record::getUserName)).and(userPswd, isEqualToWhenPresent(record::getUserPswd))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<EtlConnMo> selectOne(EtlConnMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(dbType, isEqualToWhenPresent(record::getDbType))
            .and(dbName, isEqualToWhenPresent(record::getDbName)).and(host, isEqualToWhenPresent(record::getHost)).and(port, isEqualToWhenPresent(record::getPort))
            .and(userName, isEqualToWhenPresent(record::getUserName)).and(userPswd, isEqualToWhenPresent(record::getUserPswd))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(EtlConnMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(dbType, isEqualToWhenPresent(record::getDbType))
            .and(dbName, isEqualToWhenPresent(record::getDbName)).and(host, isEqualToWhenPresent(record::getHost)).and(port, isEqualToWhenPresent(record::getPort))
            .and(userName, isEqualToWhenPresent(record::getUserName)).and(userPswd, isEqualToWhenPresent(record::getUserPswd))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
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
    default boolean existSelective(EtlConnMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlConnMo> selectSelective(EtlConnMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(dbType, isEqualToWhenPresent(record::getDbType))
            .and(dbName, isEqualToWhenPresent(record::getDbName)).and(host, isEqualToWhenPresent(record::getHost)).and(port, isEqualToWhenPresent(record::getPort))
            .and(userName, isEqualToWhenPresent(record::getUserName)).and(userPswd, isEqualToWhenPresent(record::getUserPswd))
            .and(remark, isEqualToWhenPresent(record::getRemark)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<EtlConnMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }
}
