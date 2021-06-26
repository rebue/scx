package rebue.wxx.mapper;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class WxxAppDynamicSqlSupport {
    /**
    * @mbg.generated 自动生成，如需修改，请删除本行
     */
    public static final WxxApp wxxApp = new WxxApp();

    /**
    * 公众号的appid
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> id = wxxApp.id;

    /**
    * APP名称
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> name = wxxApp.name;

    /**
    * 商户号
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> mchId = wxxApp.mchId;

    /**
    * 公众号的app secret
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> appSecret = wxxApp.appSecret;

    /**
    * 公众号的token(微信公众号绑定网站的域名时，会通过此token进行校验)
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> token = wxxApp.token;

    /**
    * 微信加解密消息用的key
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> encodeingAesKey = wxxApp.encodeingAesKey;

    /**
    * 用户关注后自动回复的文本
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> subscribeAutoReply = wxxApp.subscribeAutoReply;

    /**
    * 自定义菜单
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> menu = wxxApp.menu;

    /**
    * 登录回调链接
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> loginCallbackUrl = wxxApp.loginCallbackUrl;

    /**
    * 登录回调方法类型
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> loginCallbackMethodType = wxxApp.loginCallbackMethodType;

    /**
    * 登录回调签名密钥
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> loginCallbackSignkey = wxxApp.loginCallbackSignkey;

    /**
    * 微信支付完成通知的URL
    *
    * @mbg.generated 自动生成，如需修改，请删除本行
    */
    public static final SqlColumn<String> wxpayNotifyUrl = wxxApp.wxpayNotifyUrl;

    public static final class WxxApp extends SqlTable {
        public final SqlColumn<String> id = column("ID", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> mchId = column("MCH_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> appSecret = column("APP_SECRET", JDBCType.VARCHAR);

        public final SqlColumn<String> token = column("TOKEN", JDBCType.VARCHAR);

        public final SqlColumn<String> encodeingAesKey = column("ENCODEING_AES_KEY", JDBCType.VARCHAR);

        public final SqlColumn<String> subscribeAutoReply = column("SUBSCRIBE_AUTO_REPLY", JDBCType.VARCHAR);

        public final SqlColumn<String> menu = column("MENU", JDBCType.VARCHAR);

        public final SqlColumn<String> loginCallbackUrl = column("LOGIN_CALLBACK_URL", JDBCType.VARCHAR);

        public final SqlColumn<String> loginCallbackMethodType = column("LOGIN_CALLBACK_METHOD_TYPE", JDBCType.VARCHAR);

        public final SqlColumn<String> loginCallbackSignkey = column("LOGIN_CALLBACK_SIGNKEY", JDBCType.VARCHAR);

        public final SqlColumn<String> wxpayNotifyUrl = column("WXPAY_NOTIFY_URL", JDBCType.VARCHAR);

        public WxxApp() {
            super("WXX_APP");
        }
    }
}