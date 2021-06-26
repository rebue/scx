package rebue.wxx.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class WxxMchDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final WxxMch wxxMch = new WxxMch();

    /**
    * 商户号(MCH_ID)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> id = wxxMch.id;

    /**
    * 商户名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = wxxMch.name;

    /**
    * API密钥，签名用的key，在商户平台设置（微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> apiKey = wxxMch.apiKey;

    public static final class WxxMch extends SqlTable {
        public final SqlColumn<String> id = column("ID", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> apiKey = column("API_KEY", JDBCType.VARCHAR);

        public WxxMch() {
            super("WXX_MCH");
        }
    }
}