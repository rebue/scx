package rebue.scx.rac.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacPermMenuDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacPermMenu racPermMenu = new RacPermMenu();

    /**
    * 权限菜单ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racPermMenu.id;

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> permId = racPermMenu.permId;

    /**
    * 菜单ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> menuId = racPermMenu.menuId;

    public static final class RacPermMenu extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<String> permId = column("PERM_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> menuId = column("MENU_ID", JDBCType.VARCHAR);

        public RacPermMenu() {
            super("RAC_PERM_MENU");
        }
    }
}