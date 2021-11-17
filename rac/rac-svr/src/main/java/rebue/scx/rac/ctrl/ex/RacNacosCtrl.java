package rebue.scx.rac.ctrl.ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.ex.RacSignInSvc;
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

    @Value("${spring.cloud.nacos.config.server-addr:127.0.0.1:8848}")
    private String                     serverAddr;
    @Value("${spring.application.name:rac-svr}")
    private String                     name;
    @Value("${spring.profiles.active:dev}")
    private String                     active;
    @Value("${spring.cloud.nacos.config.file-extension:yaml}")
    private String                     fileExtension;
    @Value("${spring.cloud.nacos.config.group:REBUE}")
    private String                     group;
    /**
     * 读取nacos配置文件的超时时间
     */
    private Long                       ReadTimeOut = 4000L;

    @Resource
    private RacSignInSvc               racSignInSvc;
    @Resource
    private RacAccountSvc              racAccountSvc;
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
     * 获取配置文件名
     * 
     * @return 文件名
     */
    private String getNacosConfigName() {
        return name + "-" + active + "." + fileExtension;
    }

    /**
     * 获取配置信息
     */
    @GetMapping("/rac/nacos/level-protect/get/config")
    public Mono<Ro<?>> getNacosConfig() {
        String              ymlStr = getConfig(getNacosConfigName(), group, ReadTimeOut);
        Map<String, String> map    = getMap(ymlStr);
        Ro<?>               ro     = new Ro<>(ResultDic.SUCCESS, "查询成功", map);
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
        String ymlStr = getConfig(getNacosConfigName(), group, ReadTimeOut);
        ymlStr = YamlUtils.setAsString(ymlStr, key, to.getValue());
        boolean config = publishConfig(getNacosConfigName(), group, ymlStr, type);
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
            // String serverAddr = "127.0.0.1:8848";
            // String dataId = "{dataId}";
            // String group = "{group}";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, group, timeoutMs);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
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
            // String serverAddr = "127.0.0.1:8848";
            // String dataId = "{dataId}";
            // String group = "{group}";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            isPublishOk = configService.publishConfig(dataId, group, content, type);
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    Map<String, String> map = getMap(configInfo);
                    racSignInSvc.refreshUpdateLevelProtect(map);
                    racAccountSvc.refreshUpdateLevelProtect(map);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            // TODO Auto-generated catch block
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
