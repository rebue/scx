package rebue.scx.rac.ctrl.ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.config.RacNacosProperties;
import rebue.scx.rac.to.ex.NacosModifyTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.YamlUtils;

/***
 * 等保配置信息
 * 
 * @author yuanman
 *
 */
@RestController
public class RacNacosCtrl {

    /**
     * 读取nacos配置文件的超时时间
     */
    private Long                       ReadTimeOut = 4000L;

    @Resource
    private RacNacosProperties         racNacosProperties;
    /**
     * 读取数据源的Key,不可修改
     */
    private static Map<String, String> maps        = new HashMap<String, String>();
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

    /**
     * 获取配置信息
     */
    @GetMapping("/rac/nacos/level-protect/get/config")
    public Mono<Ro<?>> getNacosConfig() {
        String ymlStr = getConfig(racNacosProperties.getNacosConfigName(),
                racNacosProperties.getNacosConfig().getGroup(), ReadTimeOut);
        if (ymlStr == null) {
            Ro<?> ro = new Ro<>(ResultDic.FAIL, "请联系管理员配置nacos");
            return Mono.create(callback -> callback.success(ro));
        }
        Map<String, String> map = getMap(ymlStr);
        Ro<?>               ro  = new Ro<>(ResultDic.SUCCESS, "查询成功", map);
        return Mono.create(callback -> callback.success(ro));
    }

    /**
     * 修改配置信息
     *
     * @param to
     */
    @RacOpLog(opType = "修改配置信息", opTitle = "修改配置类型: #{#p0.name}")
    @PostMapping("/rac/nacos/level-protect/publish/config")
    public Mono<Ro<?>> publishConfig(@RequestBody final NacosModifyTo to) {
        String key = maps.get(to.getName());
        if (key == null) {
            throw new RuntimeExceptionX("该配置不存在--" + to.getName());
        }
        String type   = ConfigType.YAML.getType();
        String ymlStr = getConfig(racNacosProperties.getNacosConfigName(),
                racNacosProperties.getNacosConfig().getGroup(), ReadTimeOut);
        ymlStr = YamlUtils.setAsString(ymlStr, key, to.getValue());
        boolean config = publishConfig(racNacosProperties.getNacosConfigName(),
                racNacosProperties.getNacosConfig().getGroup(), ymlStr, type);
        if (config) {
            Ro<Boolean> ro = new Ro<>(ResultDic.SUCCESS, "提交成功", config);
            return Mono.create(callback -> callback.success(ro));
        }
        else {
            Ro<Boolean> ro = new Ro<>(ResultDic.FAIL, "提交失败", config);
            return Mono.create(callback -> callback.success(ro));
        }
    }

    /**
     * 获取配置信息
     *
     * @param dataId    文件名
     * @param group     分组名
     * @param timeoutMs 读取的最大时间
     *
     * @return 配置内容
     */
    public String getConfig(String dataId, String group, long timeoutMs) {
        String content = null;
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", racNacosProperties.getNacosConfig().getServerAddr());
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, group, timeoutMs);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 发布配置信息
     *
     * @param dataId  文件名
     * @param group   分组名
     * @param content 配置信息
     *
     * @return 是否成功boolean
     */
    public boolean publishConfig(String dataId, String group, String content, String type) {
        boolean isPublishOk = false;
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            Properties properties = new Properties();
            properties.put("serverAddr", racNacosProperties.getNacosConfig().getServerAddr());
            ConfigService configService = NacosFactory.createConfigService(properties);
            isPublishOk = configService.publishConfig(dataId, group, content, type);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return isPublishOk;

    }

    private Map<String, String> getMap(String content) {
        Map<String, String> hashedMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String value = YamlUtils.getAsString(content, entry.getValue());
            hashedMap.put(entry.getKey(), value);
        }
        return hashedMap;
    }

    public static Map<String, String> getMaps() {
        return maps;
    }

    public static void setMaps(Map<String, String> maps) {
        RacNacosCtrl.maps = maps;
    }
}
