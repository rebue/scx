package rebue.scx.rac.api.ex;

import rebue.robotech.ro.Ro;

/**
 * Nacos配置 相关API
 * 
 * @author yuanman
 *
 * @param <ADD_TO>
 * @param <MODIFY_TO>
 * @param <DEL_TO>
 */
public interface NacosApi<ADD_TO, MODIFY_TO, DEL_TO> {

    /**
     * 获取配置信息
     * 
     * @param tpye
     * 
     * @return
     */
    Ro<?> getNacosConfig(String tpye);

    /**
     * 添加配置信息
     * 
     * @param tpye
     * @param to
     * 
     * @return
     */
    Ro<?> addPublishConfig(String tpye, ADD_TO to);

    /**
     * 修改配置信息
     * 
     * @param tpye
     * @param to
     * 
     * @return
     */
    Ro<?> updatePublishConfig(String tpye, MODIFY_TO to);

    /**
     * 删除配置信息
     * 
     * @param tpye
     * @param to
     * 
     * @return
     */
    Ro<?> delPublishConfig(String tpye, DEL_TO to);
}
