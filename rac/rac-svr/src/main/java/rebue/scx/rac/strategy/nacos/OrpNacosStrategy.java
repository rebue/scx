package rebue.scx.rac.strategy.nacos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import rebue.scx.rac.config.AllNacosConfig.NacosStrategyProperties;
import rebue.scx.rac.dic.NacosTypeDic;
import rebue.scx.rac.ra.YamlRa;
import rebue.scx.rac.to.nacos.NacosAddTo;
import rebue.scx.rac.to.nacos.NacosDelTo;
import rebue.scx.rac.to.nacos.NacosModifyTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.YamlUtils;

public class OrpNacosStrategy extends AbstractStrategy<List<Object>, NacosAddTo, NacosModifyTo, NacosDelTo> {

    public OrpNacosStrategy(NacosTypeDic nameType, String serverAddr, String active, NacosStrategyProperties strategyProperies) {
        super(nameType, serverAddr, active, strategyProperies);
    }

    /**
     * 获取配置信息
     */
    @Override
    protected List<Object> getResult(String ymlStr) {
        List<Object>              list              = new ArrayList<Object>();
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
        return list;
    }

    /**
     * 修改配置
     */
    @Override
    protected boolean modifyPublishConfig(String content, NacosModifyTo to) {
        if (!("ding-talk".equals(to.getConfigType()) || "wechat-open".equals(to.getConfigType()))) {
            throw new RuntimeExceptionX("该类型配置不支持修改！--" + to.getConfigType());
        }
        String                    key             = "orp.strategies." + to.getConfigType() + ".clients";
        List<Map<String, String>> dingTalkMapList = YamlUtils.getAsMapList(content, key);
        dingTalkMapList.stream().map(item -> {
            Iterator<Entry<String, String>> entrySet   = item.entrySet().iterator();
            boolean                         idFlag     = false;
            boolean                         secretFlag = false;
            while (entrySet.hasNext()) {
                Entry<String, String> map = entrySet.next();
                if ("id".equals(map.getKey()) && map.getValue().equals(to.getOldAppKey())) {
                    idFlag     = true;
                    secretFlag = true;
                }
            }
            if (idFlag) {
                item.put("id", to.getNewAppKey());
                item.put("name", to.getNewName());
            }
            if (secretFlag && StringUtils.isNotBlank(to.getNewAppSecret())) {
                item.put("secret", to.getNewAppSecret());
            }
            return item;
        }).collect(Collectors.toList());
        content = YamlUtils.setAsMapList(content, key, dingTalkMapList);

        return super.updateConfig(content);
    }

    /**
     * 添加配置
     */
    @Override
    protected boolean addPublishConfig(String content, NacosAddTo to) {
        Map<String, String> hashedMap = new HashMap<String, String>();
        hashedMap.put("id", to.getNewAppKey());
        hashedMap.put("name", to.getNewName());
        hashedMap.put("secret", to.getNewAppSecret());
        if (!("ding-talk".equals(to.getConfigType()) || "wechat-open".equals(to.getConfigType()))) {
            throw new RuntimeExceptionX("该类型配置不支持修改！--" + to.getConfigType());
        }
        String                    key             = "orp.strategies." + to.getConfigType() + ".clients";
        List<Map<String, String>> dingTalkMapList = YamlUtils.getAsMapList(content, key);
        dingTalkMapList.stream().map(item -> {
            String str    = item.get("id");
            String newstr = hashedMap.get("id");
            if (str.equals(newstr)) {
                throw new RuntimeExceptionX("该配置已存在！--" + newstr);
            }
            return item;
        }).collect(Collectors.toList());
        dingTalkMapList.add(hashedMap);
        content = YamlUtils.setAsMapList(content, key, dingTalkMapList);
        return super.updateConfig(content);
    }

    /**
     * 删除配置
     */
    @Override
    protected boolean delPublishConfig(String content, NacosDelTo to) {
        if (!("ding-talk".equals(to.getConfigType()) || "wechat-open".equals(to.getConfigType()))) {
            throw new RuntimeExceptionX("该类型配置不支持修改！--" + to.getConfigType());
        }
        String                    key             = "orp.strategies." + to.getConfigType() + ".clients";
        List<Map<String, String>> dingTalkMapList = YamlUtils.getAsMapList(content, key);
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
        content = YamlUtils.setAsMapList(content, key, dingTalkMapList);
        return super.updateConfig(content);
    }

}
