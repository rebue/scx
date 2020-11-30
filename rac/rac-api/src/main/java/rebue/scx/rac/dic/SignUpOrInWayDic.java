package rebue.scx.rac.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rebue.robotech.dic.EnumBase;

/**
 * 注册或登录方式的字典
 * 1: 登录名称
 * 2: 电子邮箱
 * 3: 手机号码
 * 4: QQ
 * 5: 微信
 */
@AllArgsConstructor
@Getter
public enum SignUpOrInWayDic implements EnumBase {
    /**
     * 1: 登录名
     */
    SIGN_IN_NAME(1, "登录名称"),
    /**
     * 2: 电子邮箱
     */
    EMAIL(2, "电子邮箱"),
    /**
     * 3: 手机号码
     */
    MOBILE(3, "手机号码"),
    /**
     * 4: QQ
     */
    QQ(4, "QQ"),
    /**
     * 5: 微信
     */
    WX(5, "微信");

    private final int    code;
    private final String desc;

    @Override
    public String getName() {
        return name();
    }

    /**
     * springdoc显示枚举说明将会调用此方法
     */
    @Override
    public String toString() {
        return getCode() + "(" + getDesc() + ")";
    }

    /**
     * 枚举的所有项，注意这个变量是静态单例的
     */
    private static final Map<Integer, EnumBase> valueMap = new HashMap<>();

    // 初始化map，保存枚举的所有项到map中以方便通过code查找
    static {
        for (final EnumBase item : values()) {
            valueMap.put(item.getCode(), item);
        }
    }

    /**
     * 通过code得到枚举的实例(Jackson反序列化时会调用此方法)
     * 注意：此方法必须是static的方法，且返回类型必须是本枚举类，而不能是接口EnumBase
     * 否则Jackson将调用默认的反序列化方法，而不会调用本方法
     */
    @JsonCreator // Jackson在反序列化时，调用 @JsonCreator 标注的构造器或者工厂方法来创建对象
    public static SignUpOrInWayDic getItem(final int code) {
        final SignUpOrInWayDic result = (SignUpOrInWayDic) valueMap.get(code);
        if (result == null) {
            throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
        }
        return result;
    }
}
