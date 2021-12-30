package rebue.scx.rac.strategy.nacos;

/**
 * nacos配置策略
 * 
 * @author yuanman
 *
 */
public interface NacosStrategy<ADD_TO, MODIFY_TO, DEL_TO> {
    /**
     * 获取配置信息
     * 
     * @param <H>
     * 
     * @return
     */
    <H> H getNacosConfig();

    /**
     * 添加配置信息
     */
    boolean addPublishConfig(final ADD_TO to);

    /**
     * 修改配置信息
     * 
     * @param to
     * 
     * @return
     */
    boolean updatePublishConfig(final MODIFY_TO to);

    /**
     * 删除配置信息
     */
    boolean delPublishConfig(final DEL_TO to);
}
