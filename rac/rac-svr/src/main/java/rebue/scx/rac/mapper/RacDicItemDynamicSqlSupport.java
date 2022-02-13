package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacDicItemDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacDicItem racDicItem = new RacDicItem();

    /**
    * 字典项ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racDicItem.id;

    /**
    * 字典ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> dicId = racDicItem.dicId;

    /**
    * 组织ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> orgId = racDicItem.orgId;

    /**
    * 字典项Key
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dicItemKey = racDicItem.dicItemKey;

    /**
    * 字典项值
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> dicItemValue = racDicItem.dicItemValue;

    /**
    * 字典项名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = racDicItem.name;

    /**
    * 树编码(每三位为一级)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> treeCode = racDicItem.treeCode;

    /**
    * 修改时间
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<LocalDateTime> updateDatetime = racDicItem.updateDatetime;

    /**
    * 字典备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racDicItem.remark;

    public static final class RacDicItem extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> dicId = column("DIC_ID", JDBCType.BIGINT);

        public final SqlColumn<Long> orgId = column("ORG_ID", JDBCType.BIGINT);

        public final SqlColumn<String> dicItemKey = column("DIC_ITEM_KEY", JDBCType.VARCHAR);

        public final SqlColumn<String> dicItemValue = column("DIC_ITEM_VALUE", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> treeCode = column("TREE_CODE", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> updateDatetime = column("UPDATE_DATETIME", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacDicItem() {
            super("RAC_DIC_ITEM");
        }
    }
}