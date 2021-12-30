package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.NacosApi;
import rebue.scx.rac.svc.ex.NacosSvc;

/**
 * Nacos配置的实现类
 * 
 * @author yuanman
 *
 * @param <ADD_TO>
 * @param <MODIFY_TO>
 * @param <DEL_TO>
 */
@DubboService
public class NacosApiImpl<ADD_TO, MODIFY_TO, DEL_TO> implements NacosApi<ADD_TO, MODIFY_TO, DEL_TO> {

    @Resource
    private NacosSvc<ADD_TO, MODIFY_TO, DEL_TO> svc;

    @Override
    public Ro<?> getNacosConfig(String tpye) {
        return svc.getNacosConfig(tpye);
    }

    @Override
    public Ro<?> addPublishConfig(String tpye, ADD_TO to) {
        return svc.addPublishConfig(tpye, to);
    }

    @Override
    public Ro<?> updatePublishConfig(String tpye, MODIFY_TO to) {
        return svc.updatePublishConfig(tpye, to);
    }

    @Override
    public Ro<?> delPublishConfig(String tpye, DEL_TO to) {
        return svc.delPublishConfig(tpye, to);
    }

}
