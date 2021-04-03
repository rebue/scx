package rebue.scx.sgn.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rebue.robotech.dic.Dic;

/**
 * 签名算法的字典
 * 1: 通用签名
 * 2: 微信支付签名
 */
@AllArgsConstructor
@Getter
public enum SignAlgorithmDic implements Dic {
    /**
     * 1: 通用签名
     */
    COMMON(1, "通用签名"),
    /**
     * 2: 微信支付签名
     */
    WX_PAY(2, "微信支付签名");

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
    private static final Map<Integer, Dic> valueMap = new HashMap<>();

    // 初始化map，保存枚举的所有项到map中以方便通过code查找
    static {
        for (final Dic item : values()) {
            valueMap.put(item.getCode(), item);
        }
    }

    /**
     * 通过code得到枚举的实例(Jackson反序列化时会调用此方法)
     * 注意：此方法必须是static的方法，且返回类型必须是本枚举类，而不能是接口Dic
     * 否则Jackson将调用默认的反序列化方法，而不会调用本方法
     */
    @JsonCreator // Jackson在反序列化时，调用 @JsonCreator 标注的构造器或者工厂方法来创建对象
    public static SignAlgorithmDic getItem(final int code) {
        final SignAlgorithmDic result = (SignAlgorithmDic) valueMap.get(code);
        if (result == null) {
            throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
        }
        return result;
    }
}
