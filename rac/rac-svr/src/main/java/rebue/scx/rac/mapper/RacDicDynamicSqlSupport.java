package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacDicDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacDic racDic = new RacDic();

    /**
    * 字典ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racDic.id;

    /**
    * 字典Key
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dicKey = racDic.dicKey;

    /**
    * 字典名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = racDic.name;

    /**
    * 领域ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> domainId = racDic.domainId;

    /**
    * 系统ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> sysId = racDic.sysId;

    /**
    * 字典备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racDic.remark;

    public static final class RacDic extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> dicKey = column("DIC_KEY", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> domainId = column("DOMAIN_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> sysId = column("SYS_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacDic() {
            super("RAC_DIC");
        }
    }
}