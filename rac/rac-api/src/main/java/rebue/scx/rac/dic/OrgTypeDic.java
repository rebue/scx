package rebue.scx.rac.dic;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rebue.robotech.dic.Dic;
import rebue.robotech.dic.DicUtils;

/**
 * 组织级别组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
 * 1: 1
 * 2: 20
 * 3: 21
 * 4: 80
 * 5: 90
 */
@AllArgsConstructor
@Getter
public enum OrgTypeDic implements Dic {
    /**
     * 1: 集团
     */
    one((byte) 1, "1"),
    /**
     * 2: 政府单位
     */
    two((byte) 2, "20"),
    /**
     * 3: 公司
     */
    three((byte) 3, "21"),
    /**
     * 4: 部门
     */
    four((byte) 4, "80"),
    /**
     * 5: 小组
     */
    five((byte) 5, "90");

    private final byte   code;
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
     * 通过code得到枚举的实例(Jackson反序列化时会调用此方法)
     * 注意：此方法必须是static的方法，且返回类型必须是本枚举类，而不能是接口Dic
     * 否则Jackson将调用默认的反序列化方法，而不会调用本方法
     */
    @JsonCreator // Jackson在反序列化时，调用 @JsonCreator 标注的构造器或者工厂方法来创建对象
    public static OrgTypeDic getItem(final byte pcode) {
        final OrgTypeDic result = (OrgTypeDic) DicUtils.getItem(OrgTypeDic.class, pcode);
        if (result == null) {
            throw new IllegalArgumentException("输入的code(" + pcode + ")不在枚举的取值范围内");
        }
        return result;
    }
}
