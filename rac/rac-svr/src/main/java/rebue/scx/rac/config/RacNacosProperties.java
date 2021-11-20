package rebue.scx.rac.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class RacNacosProperties {
    @Value("${spring.application.name:rac-svr}")
    private String name;
    @Value("${spring.profiles.active:dev}")
    private String active;

    /**
     * 获取 NacosConfig 配置
     * 
     */
    @ConfigurationProperties(prefix = "spring.cloud.nacos.config")
    @Bean
    public NacosConfig getNacosConfig() {
        return new NacosConfig();
    }

    /**
     * 获取配置文件名
     * 
     * @return 文件名
     */
    public String getNacosConfigName() {
        return name + "-" + active + "." + this.getNacosConfig().getFileExtension();
    }
}
