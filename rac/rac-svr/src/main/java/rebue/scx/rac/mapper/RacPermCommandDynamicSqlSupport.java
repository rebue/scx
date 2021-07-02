package rebue.scx.rac.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RacPermCommandDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final RacPermCommand racPermCommand = new RacPermCommand();

    /**
    * 权限命令的ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> id = racPermCommand.id;

    /**
    * 权限ID
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<Long> permId = racPermCommand.permId;

    /**
    * 命令KEY
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> commandKey = racPermCommand.commandKey;

    /**
    * 命令备注
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> remark = racPermCommand.remark;

    public static final class RacPermCommand extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> permId = column("PERM_ID", JDBCType.BIGINT);

        public final SqlColumn<String> commandKey = column("COMMAND_KEY", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("REMARK", JDBCType.VARCHAR);

        public RacPermCommand() {
            super("RAC_PERM_COMMAND");
        }
    }
}