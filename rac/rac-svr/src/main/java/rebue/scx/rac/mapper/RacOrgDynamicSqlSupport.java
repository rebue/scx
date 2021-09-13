package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacOrgDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacOrg racOrg = new RacOrg();

    /**
    * 组织ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racOrg.id;

    /**
    * 组织名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = racOrg.name;

    /**
    * 上级组织ID(根组织填0)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> parentId = racOrg.parentId;

    /**
    * 组织编码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> code = racOrg.code;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> realmId = racOrg.realmId;

    /**
    * 组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> orgType = racOrg.orgType;

    /**
    * 树编码
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> treeCode = racOrg.treeCode;

    /**
    * 组织全名
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> fullName = racOrg.fullName;

    /**
    * 组织简介
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> introduction = racOrg.introduction;

    /**
    * 组织备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racOrg.remark;

    /**
    * 组织属性类型(字典项KEY)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> attrType = racOrg.attrType;

    /**
    * 地址
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> addr = racOrg.addr;

    /**
    * 联系人
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> contactPerson = racOrg.contactPerson;

    /**
    * 联系方式
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> contactWay = racOrg.contactWay;

    /**
    * 邮箱
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> email = racOrg.email;

    public static final class RacOrg extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<Long> parentId = column("PARENT_ID", JDBCType.BIGINT);

        public final SqlColumn<String> code = column("CODE", JDBCType.VARCHAR);

        public final SqlColumn<String> realmId = column("REALM_ID", JDBCType.VARCHAR);

        public final SqlColumn<Byte> orgType = column("ORG_TYPE", JDBCType.TINYINT);

        public final SqlColumn<String> treeCode = column("TREE_CODE", JDBCType.VARCHAR);

        public final SqlColumn<String> fullName = column("FULL_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> introduction = column("INTRODUCTION", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public final SqlColumn<String> attrType = column("ATTR_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> addr = column("ADDR", JDBCType.VARCHAR);

        public final SqlColumn<String> contactPerson = column("CONTACT_PERSON", JDBCType.VARCHAR);

        public final SqlColumn<String> contactWay = column("CONTACT_WAY", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("EMAIL", JDBCType.VARCHAR);

        public RacOrg() {
            super("RAC_ORG");
        }
    }
}