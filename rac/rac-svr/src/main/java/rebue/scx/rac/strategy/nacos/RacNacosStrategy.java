package rebue.scx.rac.strategy.nacos;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import rebue.scx.rac.config.AllNacosConfig.NacosStrategyProperties;
import rebue.scx.rac.dic.NacosTypeDic;
import rebue.scx.rac.to.nacos.NacosModifyTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.YamlUtils;

public class RacNacosStrategy extends AbstractStrategy<Map<String, String>, Void, NacosModifyTo, Void> {
    /**
     * 读取数据源的Key,不可修改
     */
    private static Map<String, String> maps = new HashMap<String, String>();
    static {
        Map<String, String> hashedMap = new HashMap<String, String>();
        hashedMap.put("lockDuration", "level-protect.lock-duration");
        hashedMap.put("passwordMinLength", "level-protect.password-minLength");
        hashedMap.put("passwordCharacter", "level-protect.password-character");
        hashedMap.put("passwordTips", "level-protect.password-tips");
        hashedMap.put("passwordErrors", "level-protect.password-errors");
        hashedMap.put("passwordDoverdue", "level-protect.password-doverdue");
        setMaps(hashedMap);
    }

    private static Map<String, String> getMaps() {
        return maps;
    }

    private static void setMaps(Map<String, String> maps) {
        RacNacosStrategy.maps = maps;
    }

    public RacNacosStrategy(NacosTypeDic nameType, String serverAddr, String active, NacosStrategyProperties strategyProperies) {
        super(nameType, serverAddr, active, strategyProperies);
    }

    /**
     * 获取具体配置信息
     */
    @Override
    protected Map<String, String> getResult(String content) {
        Map<String, String> hashedMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String value = YamlUtils.getAsString(content, entry.getValue());
            hashedMap.put(entry.getKey(), value);
        }
        return hashedMap;
    }

    /**
     * 修改配置
     */
    @Override
    protected boolean modifyPublishConfig(String content, NacosModifyTo to) {
        String name  = to.getName();
        String value = to.getValue();
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(name)) {
            throw new RuntimeExceptionX("name和value不能为空");
        }
        String key = maps.get(name);
        if (key == null) {
            throw new RuntimeExceptionX("该配置不存在--" + to.getName());
        }
        content = YamlUtils.setAsString(content, key, value);
        return super.updateConfig(content);

    }

}
