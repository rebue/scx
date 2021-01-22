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
    * 组织名称(简称)
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
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> domainId = racOrg.domainId;

    /**
    * 组织类型(1.集团;20.政府单位;21.公司;80.部门)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Byte> orgType = racOrg.orgType;

    /**
    * 左值
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Integer> leftValue = racOrg.leftValue;

    /**
    * 右值
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Integer> rightValue = racOrg.rightValue;

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

    public static final class RacOrg extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<Long> parentId = column("PARENT_ID", JDBCType.BIGINT);

        public final SqlColumn<String> domainId = column("DOMAIN_ID", JDBCType.VARCHAR);

        public final SqlColumn<Byte> orgType = column("ORG_TYPE", JDBCType.TINYINT);

        public final SqlColumn<Integer> leftValue = column("LEFT_VALUE", JDBCType.INTEGER);

        public final SqlColumn<Integer> rightValue = column("RIGHT_VALUE", JDBCType.INTEGER);

        public final SqlColumn<String> fullName = column("FULL_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> introduction = column("INTRODUCTION", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacOrg() {
            super("RAC_ORG");
        }
    }
}