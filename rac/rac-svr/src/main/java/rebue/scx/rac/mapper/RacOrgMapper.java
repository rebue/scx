package rebue.scx.rac.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.or;
import static rebue.scx.rac.mapper.RacOrgAccountDynamicSqlSupport.racOrgAccount;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.addr;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.attrType;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.code;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.contactPerson;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.contactWay;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.email;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.fullName;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.id;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.introduction;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.name;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.orgType;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.parentId;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.racOrg;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.realmId;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.remark;
import static rebue.scx.rac.mapper.RacOrgDynamicSqlSupport.treeCode;

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
import org.mybatis.dynamic.sql.SqlBuilder;
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
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.mo.ex.RacOrgExMo;
import rebue.scx.rac.to.ex.RacOrgListByAccountIdTo;
import rebue.wheel.core.NumberUtils;

@Mapper
public interface RacOrgMapper extends MapperRootInterface<RacOrgMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, name, parentId, code, realmId, orgType, treeCode, fullName, introduction, remark, attrType, addr, contactPerson,
        contactWay, email);

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
    int insert(InsertStatementProvider<RacOrgMo> insertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RacOrgMo> multipleInsertStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RacOrgMoResult")
    Optional<RacOrgMo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RacOrgMoResult", value = { @Result(column = "ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR), @Result(column = "PARENT_ID", property = "parentId", jdbcType = JdbcType.BIGINT),
        @Result(column = "CODE", property = "code", jdbcType = JdbcType.VARCHAR), @Result(column = "REALM_ID", property = "realmId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ORG_TYPE", property = "orgType", jdbcType = JdbcType.TINYINT), @Result(column = "TREE_CODE", property = "treeCode", jdbcType = JdbcType.VARCHAR),
        @Result(column = "FULL_NAME", property = "fullName", jdbcType = JdbcType.VARCHAR), @Result(column = "INTRODUCTION", property = "introduction", jdbcType = JdbcType.VARCHAR),
        @Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR), @Result(column = "ATTR_TYPE", property = "attrType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "ADDR", property = "addr", jdbcType = JdbcType.VARCHAR), @Result(column = "CONTACT_PERSON", property = "contactPerson", jdbcType = JdbcType.VARCHAR),
        @Result(column = "CONTACT_WAY", property = "contactWay", jdbcType = JdbcType.VARCHAR), @Result(column = "EMAIL", property = "email", jdbcType = JdbcType.VARCHAR)
    })
    List<RacOrgMo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, racOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, racOrg, completer);
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
    default int insert(RacOrgMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOrg,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(parentId).toProperty("parentId").map(code).toProperty("code").map(realmId).toProperty("realmId")
                .map(orgType).toProperty("orgType").map(treeCode).toProperty("treeCode").map(fullName).toProperty("fullName").map(introduction).toProperty("introduction")
                .map(remark).toProperty("remark").map(attrType).toProperty("attrType").map(addr).toProperty("addr").map(contactPerson).toProperty("contactPerson").map(contactWay)
                .toProperty("contactWay").map(email).toProperty("email"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertMultiple(Collection<RacOrgMo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, racOrg,
            c -> c.map(id).toProperty("id").map(name).toProperty("name").map(parentId).toProperty("parentId").map(code).toProperty("code").map(realmId).toProperty("realmId")
                .map(orgType).toProperty("orgType").map(treeCode).toProperty("treeCode").map(fullName).toProperty("fullName").map(introduction).toProperty("introduction")
                .map(remark).toProperty("remark").map(attrType).toProperty("attrType").map(addr).toProperty("addr").map(contactPerson).toProperty("contactPerson").map(contactWay)
                .toProperty("contactWay").map(email).toProperty("email"));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int insertSelective(RacOrgMo record) {
        return MyBatis3Utils.insert(this::insert, record, racOrg,
            c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name).toPropertyWhenPresent("name", record::getName).map(parentId)
                .toPropertyWhenPresent("parentId", record::getParentId).map(code).toPropertyWhenPresent("code", record::getCode).map(realmId)
                .toPropertyWhenPresent("realmId", record::getRealmId).map(orgType).toPropertyWhenPresent("orgType", record::getOrgType).map(treeCode)
                .toPropertyWhenPresent("treeCode", record::getTreeCode).map(fullName).toPropertyWhenPresent("fullName", record::getFullName).map(introduction)
                .toPropertyWhenPresent("introduction", record::getIntroduction).map(remark).toPropertyWhenPresent("remark", record::getRemark).map(attrType)
                .toPropertyWhenPresent("attrType", record::getAttrType).map(addr).toPropertyWhenPresent("addr", record::getAddr).map(contactPerson)
                .toPropertyWhenPresent("contactPerson", record::getContactPerson).map(contactWay).toPropertyWhenPresent("contactWay", record::getContactWay).map(email)
                .toPropertyWhenPresent("email", record::getEmail));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgMo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, racOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, racOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, racOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgMo> selectByPrimaryKey(Long id_) {
        return selectOne(c -> c.where(id, isEqualTo(id_)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, racOrg, completer);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateAllColumns(RacOrgMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(parentId).equalTo(record::getParentId).set(code).equalTo(record::getCode).set(realmId)
            .equalTo(record::getRealmId).set(orgType).equalTo(record::getOrgType).set(treeCode).equalTo(record::getTreeCode).set(fullName).equalTo(record::getFullName)
            .set(introduction).equalTo(record::getIntroduction).set(remark).equalTo(record::getRemark).set(attrType).equalTo(record::getAttrType).set(addr).equalTo(record::getAddr)
            .set(contactPerson).equalTo(record::getContactPerson).set(contactWay).equalTo(record::getContactWay).set(email).equalTo(record::getEmail);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RacOrgMo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName).set(parentId).equalToWhenPresent(record::getParentId).set(code)
            .equalToWhenPresent(record::getCode).set(realmId).equalToWhenPresent(record::getRealmId).set(orgType).equalToWhenPresent(record::getOrgType).set(treeCode)
            .equalToWhenPresent(record::getTreeCode).set(fullName).equalToWhenPresent(record::getFullName).set(introduction).equalToWhenPresent(record::getIntroduction).set(remark)
            .equalToWhenPresent(record::getRemark).set(attrType).equalToWhenPresent(record::getAttrType).set(addr).equalToWhenPresent(record::getAddr).set(contactPerson)
            .equalToWhenPresent(record::getContactPerson).set(contactWay).equalToWhenPresent(record::getContactWay).set(email).equalToWhenPresent(record::getEmail);
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKey(RacOrgMo record) {
        return update(c -> c.set(name).equalTo(record::getName).set(parentId).equalTo(record::getParentId).set(code).equalTo(record::getCode).set(realmId)
            .equalTo(record::getRealmId).set(orgType).equalTo(record::getOrgType).set(treeCode).equalTo(record::getTreeCode).set(fullName).equalTo(record::getFullName)
            .set(introduction).equalTo(record::getIntroduction).set(remark).equalTo(record::getRemark).set(attrType).equalTo(record::getAttrType).set(addr).equalTo(record::getAddr)
            .set(contactPerson).equalTo(record::getContactPerson).set(contactWay).equalTo(record::getContactWay).set(email).equalTo(record::getEmail)
            .where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int updateByPrimaryKeySelective(RacOrgMo record) {
        return update(c -> c.set(name).equalToWhenPresent(record::getName).set(parentId).equalToWhenPresent(record::getParentId).set(code).equalToWhenPresent(record::getCode)
            .set(realmId).equalToWhenPresent(record::getRealmId).set(orgType).equalToWhenPresent(record::getOrgType).set(treeCode).equalToWhenPresent(record::getTreeCode)
            .set(fullName).equalToWhenPresent(record::getFullName).set(introduction).equalToWhenPresent(record::getIntroduction).set(remark).equalToWhenPresent(record::getRemark)
            .set(attrType).equalToWhenPresent(record::getAttrType).set(addr).equalToWhenPresent(record::getAddr).set(contactPerson).equalToWhenPresent(record::getContactPerson)
            .set(contactWay).equalToWhenPresent(record::getContactWay).set(email).equalToWhenPresent(record::getEmail).where(id, isEqualTo(record::getId)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default int deleteSelective(RacOrgMo record) {
        return delete(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(parentId, isEqualToWhenPresent(record::getParentId)).and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(orgType, isEqualToWhenPresent(record::getOrgType)).and(treeCode, isEqualToWhenPresent(record::getTreeCode))
            .and(fullName, isEqualToWhenPresent(record::getFullName)).and(introduction, isEqualToWhenPresent(record::getIntroduction))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(attrType, isEqualToWhenPresent(record::getAttrType)).and(addr, isEqualToWhenPresent(record::getAddr))
            .and(contactPerson, isEqualToWhenPresent(record::getContactPerson)).and(contactWay, isEqualToWhenPresent(record::getContactWay))
            .and(email, isEqualToWhenPresent(record::getEmail)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default Optional<RacOrgMo> selectOne(RacOrgMo record) {
        return selectOne(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(parentId, isEqualToWhenPresent(record::getParentId)).and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(orgType, isEqualToWhenPresent(record::getOrgType)).and(treeCode, isEqualToWhenPresent(record::getTreeCode))
            .and(fullName, isEqualToWhenPresent(record::getFullName)).and(introduction, isEqualToWhenPresent(record::getIntroduction))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(attrType, isEqualToWhenPresent(record::getAttrType)).and(addr, isEqualToWhenPresent(record::getAddr))
            .and(contactPerson, isEqualToWhenPresent(record::getContactPerson)).and(contactWay, isEqualToWhenPresent(record::getContactWay))
            .and(email, isEqualToWhenPresent(record::getEmail)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default long countSelective(RacOrgMo record) {
        return count(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(parentId, isEqualToWhenPresent(record::getParentId))
            .and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId)).and(orgType, isEqualToWhenPresent(record::getOrgType))
            .and(treeCode, isEqualToWhenPresent(record::getTreeCode)).and(fullName, isEqualToWhenPresent(record::getFullName))
            .and(introduction, isEqualToWhenPresent(record::getIntroduction)).and(remark, isEqualToWhenPresent(record::getRemark))
            .and(attrType, isEqualToWhenPresent(record::getAttrType)).and(addr, isEqualToWhenPresent(record::getAddr))
            .and(contactPerson, isEqualToWhenPresent(record::getContactPerson)).and(contactWay, isEqualToWhenPresent(record::getContactWay))
            .and(email, isEqualToWhenPresent(record::getEmail)));
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
    default boolean existSelective(RacOrgMo record) {
        return countSelective(record) > 0;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> selectSelective(RacOrgMo record) {
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName))
            .and(parentId, isEqualToWhenPresent(record::getParentId)).and(code, isEqualToWhenPresent(record::getCode)).and(realmId, isEqualToWhenPresent(record::getRealmId))
            .and(orgType, isEqualToWhenPresent(record::getOrgType)).and(treeCode, isEqualToWhenPresent(record::getTreeCode))
            .and(fullName, isEqualToWhenPresent(record::getFullName)).and(introduction, isEqualToWhenPresent(record::getIntroduction))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(attrType, isEqualToWhenPresent(record::getAttrType)).and(addr, isEqualToWhenPresent(record::getAddr))
            .and(contactPerson, isEqualToWhenPresent(record::getContactPerson)).and(contactWay, isEqualToWhenPresent(record::getContactWay))
            .and(email, isEqualToWhenPresent(record::getEmail)));
    }

    /**
     * 查询parentId为空的记录数
     */
    default long getCount(final RacOrgMo record) {
        return count(c -> c.where(parentId, SqlBuilder.isNull()));
    }

    /**
     * 查询根组织
     */
    default List<RacOrgMo> selectByRealmId(final RacOrgExMo record) {
        final String keywords = StringUtils.isBlank(record.getKeywords()) ? null : "%" + record.getKeywords() + "%";
        return select(c -> c.where(id, isEqualToWhenPresent(record::getId)).and(name, isEqualToWhenPresent(record::getName)).and(parentId, SqlBuilder.isNull())
            .and(realmId, isEqualToWhenPresent(record::getRealmId)).and(orgType, isEqualToWhenPresent(record::getOrgType)).and(treeCode, isEqualToWhenPresent(record::getTreeCode))
            .and(fullName, isEqualToWhenPresent(record::getFullName)).and(introduction, isEqualToWhenPresent(record::getIntroduction))
            .and(remark, isEqualToWhenPresent(record::getRemark)).and(name, isLikeWhenPresent(keywords),
                or(id, isLikeWhenPresent(NumberUtils.isValidLong(keywords) ? Long.parseLong(keywords) : null)), or(remark, isLikeWhenPresent(keywords))));
    }

    /**
     * 查询当前账户所在的组织的信息
     *
     * @param qo 查询的具体条件
     */
    default List<RacOrgMo> listByAccountId(final RacOrgListByAccountIdTo qo) {
        return select(c -> c.join(racOrgAccount).on(racOrg.id, equalTo(racOrgAccount.orgId)).where(racOrgAccount.accountId, isEqualToWhenPresent(qo.getAccountId())));
    }

    /**
     * 查询当前账户所在的组织的信息
     *
     * @param qo 查询的具体条件
     */
    default List<RacOrgMo> listPageOrgLikeTreeCode(final RacOrgMo record) {
        final String tree_Code = record.getTreeCode() + "%";
        return select(c -> c.where(treeCode, isLike(tree_Code)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default List<RacOrgMo> selectIn(List<Long> ids) {
        return select(c -> c.where(id, isIn(ids)));
    }

    /**
     * 查询组织
     */
    default List<RacOrgMo> selectInAndByKeywordsList(final List<Long> ids, final RacOrgMo record) {
        final String keywords = StringUtils.isBlank(record.getKeywords()) ? null : "%" + record.getKeywords() + "%";
        return select(c -> c.where(id, isIn(ids)).and(attrType, isEqualToWhenPresent(record.getAttrType())).and(name, isLikeWhenPresent(keywords)));
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    default BasicColumn[] getColumns() {
        return selectList;
    }
}
