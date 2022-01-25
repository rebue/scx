package rebue.scx.orp.ctrl;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.orp.ra.YamlRa;
import rebue.scx.orp.to.OrpNacosAddTo;
import rebue.scx.orp.to.OrpNacosDelTo;
import rebue.scx.orp.to.OrpNacosModifyTo;
import rebue.scx.rac.ann.RacOpLog;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.YamlUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/***
 * 公众号钉钉号配置信息
 * 
 * @author yuanman
 *
 */
@RestController
@RefreshScope
public class OrpNacosCtrl {

    @Value("${spring.cloud.nacos.config.server-addr:127.0.0.1:8848}")
    private String serverAddr;
    @Value("${spring.application.name:orp-svr}")
    private String name;
    @Value("${spring.profiles.active:dev}")
    private String active;
    @Value("${spring.cloud.nacos.config.file-extension:yaml}")
    private String fileExtension;
    @Value("${spring.cloud.nacos.config.group:REBUE}")
    private String group;

    private ConfigService configService = null;

    @EventListener
    public void onRefreshScopeRefreshed(final RefreshScopeRefreshedEvent event)
    {
        getClass();
    }

    @SneakyThrows
    @PostConstruct
    void init()
    {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        configService = NacosFactory.createConfigService(properties);
    }

    /**
     * 读取nacos配置文件的超时时间
     */
    private Long   ReadTimeOut = 4000L;

    // @DubboReference
    // private NacosApi<OrpNacosAddTo, OrpNacosModifyTo, OrpNacosDelTo> nacosApi;

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
    @GetMapping("/orp/get/nacos/config")
    public Mono<Ro<?>> getNacosConfig() {
        // Ro<?> nacosConfig = nacosApi.getNacosConfig("orp-svr");
        List<Object>              list              = new ArrayList<Object>();
        String                    ymlStr            = getConfig(getNacosConfigName(), group, ReadTimeOut);
        List<Map<String, String>> dingTalkMapList   = YamlUtils.getAsMapList(ymlStr, "orp.strategies.ding-talk.clients");
        List<Map<String, String>> wechatOpenMapList = YamlUtils.getAsMapList(ymlStr, "orp.strategies.wechat-open.clients");
        dingTalkMapList.stream().map(item -> {
            item.remove("secret");
            return item;
        }).collect(Collectors.toList());
        wechatOpenMapList.stream().map(item -> {
            item.remove("secret");
            return item;
        }).collect(Collectors.toList());
        YamlRa ra = new YamlRa();
        ra.setDingTalkMapList(dingTalkMapList);
        ra.setWechatOpenMapList(wechatOpenMapList);
        list.add(dingTalkMapList);
        list.add(wechatOpenMapList);
        Ro<?> ro = new Ro<>(ResultDic.SUCCESS, "查询成功", ra);
        return Mono.create(callback -> callback.success(ro));
    }

    /**
     * 添加配置信息
     *
     * @param to
     */
    @RacOpLog(opType = "添加配置信息", opTitle = "添加配置类型: #{#p0.configType}")
    @PostMapping("/orp/nacos/publish/add-config")
    public Mono<Ro<?>> addPublishConfig(@RequestBody final OrpNacosAddTo to) {
        // Ro<?> addPublishConfig = nacosApi.addPublishConfig("orp-svr", to);
        Map<String, String> hashedMap = new HashMap<String, String>();
        hashedMap.put("id", to.getNewAppKey());
        hashedMap.put("name", to.getNewName());
        hashedMap.put("secret", to.getNewAppSecret());
        if (!(to.getConfigType().equals("ding-talk") || to.getConfigType().equals("wechat-open"))) {
            throw new RuntimeExceptionX("该类型配置不支持修改！--" + to.getConfigType());
        }
        String                    key             = "orp.strategies." + to.getConfigType() + ".clients";
        String                    type            = ConfigType.YAML.getType();
        String                    ymlStr          = getConfig(getNacosConfigName(), group, ReadTimeOut);
        List<Map<String, String>> dingTalkMapList = YamlUtils.getAsMapList(ymlStr, key);
        dingTalkMapList.stream().map(item -> {
            String str    = item.get("id");
            String newstr = hashedMap.get("id");
            if (str.equals(newstr)) {
                throw new RuntimeExceptionX("该配置已存在！--" + newstr);
            }
            return item;
        }).collect(Collectors.toList());
        dingTalkMapList.add(hashedMap);
        ymlStr = YamlUtils.setAsMapList(ymlStr, key, dingTalkMapList);

        boolean config = publishConfig(getNacosConfigName(), group, ymlStr, type);
        if (config) {
            Ro<Boolean> ro = new Ro<>(ResultDic.SUCCESS, "提交成功", config);
            return Mono.create(callback -> callback.success(ro));
        }
        else {
            Ro<Boolean> ro = new Ro<>(ResultDic.FAIL, "提交失败", config);
            return Mono.create(callback -> callback.success(ro));
        }
        // return Mono.create(callback -> callback.success(addPublishConfig));
    }

    /**
     * 修改配置信息
     *
     * @param to
     */
    @RacOpLog(opType = "修改配置信息", opTitle = "修改配置类型: #{#p0.configType}")
    @PostMapping("/orp/nacos/publish/modify-config")
    public Mono<Ro<?>> publishConfig(@RequestBody final OrpNacosModifyTo to) {
        // Ro<?> updatePublishConfig = nacosApi.updatePublishConfig("orp-svr", to);
        if (!(to.getConfigType().equals("ding-talk") || to.getConfigType().equals("wechat-open"))) {
            throw new RuntimeExceptionX("该类型配置不支持修改！--" + to.getConfigType());
        }
        String                    key             = "orp.strategies." + to.getConfigType() + ".clients";
        String                    type            = ConfigType.YAML.getType();
        String                    ymlStr          = getConfig(getNacosConfigName(), group, ReadTimeOut);
        List<Map<String, String>> dingTalkMapList = YamlUtils.getAsMapList(ymlStr, key);
        dingTalkMapList.stream().map(item -> {
            Iterator<Entry<String, String>> entrySet   = item.entrySet().iterator();
            boolean                         idFlag     = false;
            boolean                         secretFlag = false;
            while (entrySet.hasNext()) {
                Entry<String, String> map = entrySet.next();
                if (map.getKey().equals("id") && map.getValue().equals(to.getOldAppKey())) {
                    idFlag     = true;
                    secretFlag = true;
                }
            }
            if (idFlag) {
                item.put("id", to.getNewAppKey());
                item.put("name", to.getNewName());
            }
            if (secretFlag && to.getNewAppSecret() != null) {
                item.put("secret", to.getNewAppSecret());
            }
            return item;
        }).collect(Collectors.toList());
        ymlStr = YamlUtils.setAsMapList(ymlStr, key, dingTalkMapList);

        boolean config = publishConfig(getNacosConfigName(), group, ymlStr, type);
        if (config) {
            Ro<Boolean> ro = new Ro<>(ResultDic.SUCCESS, "提交成功", config);
            return Mono.create(callback -> callback.success(ro));
        }
        else {
            Ro<Boolean> ro = new Ro<>(ResultDic.FAIL, "提交失败", config);
            return Mono.create(callback -> callback.success(ro));
        }
        // return Mono.create(callback -> callback.success(updatePublishConfig));
    }

    /**
     * 删除配置信息
     *
     * @param to
     */
    @RacOpLog(opType = "删除配置信息", opTitle = "删除配置类型: #{#p0.configType}")
    @PostMapping("/orp/nacos/publish/del-config")
    public Mono<Ro<?>> delPublishConfig(@RequestBody final OrpNacosDelTo to) {
        // Ro<?> delPublishConfig = nacosApi.delPublishConfig("orp-svr", to);
        if (!(to.getConfigType().equals("ding-talk") || to.getConfigType().equals("wechat-open"))) {
            throw new RuntimeExceptionX("该类型配置不支持修改！--" + to.getConfigType());
        }
        String                    key             = "orp.strategies." + to.getConfigType() + ".clients";
        String                    type            = ConfigType.YAML.getType();
        String                    ymlStr          = getConfig(getNacosConfigName(), group, ReadTimeOut);
        List<Map<String, String>> dingTalkMapList = YamlUtils.getAsMapList(ymlStr, key);
        dingTalkMapList.stream().map(item -> {
            Iterator<Entry<String, String>> entrySet  = item.entrySet().iterator();
            boolean                         clearFlag = false;
            while (entrySet.hasNext()) {
                Entry<String, String> map = entrySet.next();
                if (map.getKey().equals("id") && map.getValue().equals(to.getOldAppKey()))
                    clearFlag = true;
            }
            if (clearFlag) {
                item.clear();
            }
            return item;
        }).collect(Collectors.toList());
        ymlStr = YamlUtils.setAsMapList(ymlStr, key, dingTalkMapList);

        boolean config = publishConfig(getNacosConfigName(), group, ymlStr, type);
        if (config) {
            Ro<Boolean> ro = new Ro<>(ResultDic.SUCCESS, "提交成功", config);
            return Mono.create(callback -> callback.success(ro));
        }
        else {
            Ro<Boolean> ro = new Ro<>(ResultDic.FAIL, "提交失败", config);
            return Mono.create(callback -> callback.success(ro));
        }
        // return Mono.create(callback -> callback.success(delPublishConfig));
    }

    /**
     * 修改yaml文件字符串格式把注解带上
     *
     */
    public String modifyYamlStr(String ymlStr) {
        Yaml yaml;
        yaml = new Yaml();
        Object                          ymlProperties  = yaml.load(ymlStr);
        String[]                        split          = ymlStr.split("\\n");
        Map<String, Object>             strArrayToMap  = StrArrayToMap(split);
        String                          ymlProperties1 = yaml.dumpAsMap(ymlProperties);
        Iterator<Entry<String, Object>> iterator       = strArrayToMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry   = iterator.next();
            boolean                   isEmpty = entry.getKey().isEmpty();
            if (isEmpty) {
                break;
            }
            String key   = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<String> valist  = (List<String>) value;
                String       collect = valist.stream().map(str -> {
                                         return str;
                                     }).collect(Collectors.joining("\n")) + "\n";
                ymlProperties1 = ymlProperties1.replace(key, collect + key);
            }
        }
        return ymlProperties1;
    }

    /**
     * 找出注解的行和被注解的行
     *
     * @param split
     *
     * @return Map<被注解行,注解行>
     */
    public Map<String, Object> StrArrayToMap(String[] split) {
        List<String>        list       = new ArrayList<String>();
        @SuppressWarnings("unchecked")
        Map<String, Object> map        = new HashMap<String, Object>();
        boolean             searchFalg = false;
        boolean             setMapFalg = false;
        for (String str : split) {
            List<String> keyValue = new ArrayList<String>();
            int          indexOf  = str.indexOf("#");
            if (indexOf != -1) {
                // 去除空格
                String  replaceAll = str.trim();
                // 判断是否是#开头
                boolean startsWith = replaceAll.startsWith("#");
                if (startsWith) {
                    list.add(str);
                    searchFalg = true;
                }
            }
            if (searchFalg && indexOf == -1) {
                String key = str;
                searchFalg = false;
                keyValue.addAll(list);
                map.put(key, keyValue);
                list = new ArrayList<String>();
            }
        }
        return map;
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
            content = configService.getConfig(dataId, group, 5000);
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
            isPublishOk = configService.publishConfig(dataId, group, content, type);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isPublishOk;

    }

}