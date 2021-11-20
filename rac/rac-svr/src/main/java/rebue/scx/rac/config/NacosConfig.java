package rebue.scx.rac.config;

import lombok.Data;

@Data
public class NacosConfig {
    /**
     * nacos服务IP
     */
    private String serverAddr;
    /**
     * 配置文件格式
     */
    private String fileExtension;
    /**
     * nacos分组名
     */
    private String group;
}