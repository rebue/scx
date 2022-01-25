package rebue.scx.rac.strategy.nacos;

import java.util.Properties;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.base.CaseFormat;

import lombok.SneakyThrows;
import rebue.scx.rac.config.AllNacosConfig.NacosStrategyProperties;
import rebue.scx.rac.dic.NacosTypeDic;
import rebue.wheel.api.exception.RuntimeExceptionX;

/**
 * 
 * @author yuanman
 *
 * @param <RESULT_RO>
 * @param <ADD_TO>
 * @param <MODIFY_TO>
 * @param <DEL_TO>
 */
public abstract class AbstractStrategy<RESULT_RO, ADD_TO, MODIFY_TO, DEL_TO> implements NacosStrategy<ADD_TO, MODIFY_TO, DEL_TO> {

    protected ConfigService configService;

    /**
     * 读取nacos配置文件的超时时间
     */
    protected Long                    ReadTimeOut = 4000L;
    protected String                  serverAddr;
    protected String                  active;
    protected NacosStrategyProperties strategyProperies;
    protected NacosTypeDic            nameType;

    @SneakyThrows
    public AbstractStrategy(NacosTypeDic nameType, String serverAddr, String active, NacosStrategyProperties strategyProperies) {
        this();
        this.nameType          = nameType;
        this.serverAddr        = serverAddr;
        this.active            = active;
        this.strategyProperies = strategyProperies;
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        this.configService = NacosFactory.createConfigService(properties);
    }

    public AbstractStrategy() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 获取配置信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public RESULT_RO getNacosConfig() {
        String content = getConfig(getNacosConfigName(), strategyProperies.getGroup(), ReadTimeOut);
        if (content == null) {
            return null;
        }
        RESULT_RO map = getResult(content);
        return map;
    }

    @Override
    public boolean addPublishConfig(ADD_TO to) {
        String content = getConfig(getNacosConfigName(), strategyProperies.getGroup(), ReadTimeOut);
        return addPublishConfig(content, to);
    }

    protected abstract RESULT_RO getResult(String content);

    protected boolean modifyPublishConfig(String config, MODIFY_TO to) {
        throw new RuntimeExceptionX("该配置不存在修改");
    };

    protected boolean addPublishConfig(String content, ADD_TO to) {
        throw new RuntimeExceptionX("该配置不存在添加");
    }

    protected boolean delPublishConfig(String content, DEL_TO to) {
        throw new RuntimeExceptionX("该配置不存在删除");
    }

    /**
     * 修改配置信息
     */
    @Override
    public boolean updatePublishConfig(MODIFY_TO to) {
        String content = getConfig(getNacosConfigName(), strategyProperies.getGroup(), ReadTimeOut);
        return modifyPublishConfig(content, to);
    }

    /**
     * 删除配置信息
     */
    @Override
    public boolean delPublishConfig(DEL_TO to) {
        String content = getConfig(getNacosConfigName(), strategyProperies.getGroup(), ReadTimeOut);
        return delPublishConfig(content, to);
    }

    /**
     * 获取配置文件内容信息
     *
     * @param dataId    文件名
     * @param group     分组名
     * @param timeoutMs 读取的最大时间
     *
     * @return 配置内容
     */
    private String getConfig(String nacosConfigName, String group, Long timeoutMs) {
        String content = null;
        try {
            content = this.configService.getConfig(nacosConfigName, group, timeoutMs);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 获取配置文件名
     * 
     * @return 文件名
     */
    public String getNacosConfigName() {
        String serverName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, nameType.name());
        return serverName + "-" + active + "." + strategyProperies.getFileExtension();
    }

    protected boolean updateConfig(String content) {
        String type     = strategyProperies.getFileExtension();
        String group    = strategyProperies.getGroup();
        String fileName = getNacosConfigName();
        return publishConfig(fileName, group, content, type);
    }

    /**
     * 提交发布配置信息
     *
     * @param dataId  文件名
     * @param group   分组名
     * @param content 配置信息
     * @param type    文件类型
     * 
     * @return 是否成功boolean
     */
    protected boolean publishConfig(String dataId, String group, String content, String type) {
        boolean isPublishOk = false;
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数
            isPublishOk = this.configService.publishConfig(dataId, group, content, type);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return isPublishOk;
    }
}
