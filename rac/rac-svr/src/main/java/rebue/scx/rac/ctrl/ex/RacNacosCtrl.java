package rebue.scx.rac.ctrl.ex;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.CaseFormat;

import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.ex.NacosApi;
import rebue.scx.rac.dic.NacosTypeDic;
import rebue.scx.rac.to.nacos.NacosAddTo;
import rebue.scx.rac.to.nacos.NacosDelTo;
import rebue.scx.rac.to.nacos.NacosModifyTo;

/***
 * 等保配置信息
 * 
 * @author yuanman
 *
 */
@RestController
public class RacNacosCtrl {

    private static String                                   serverName = CaseFormat.UPPER_CAMEL.to(
            CaseFormat.LOWER_HYPHEN, NacosTypeDic.RacSvr.name());
    @Resource
    private NacosApi<NacosAddTo, NacosModifyTo, NacosDelTo> nacosApi;

    /**
     * 获取配置信息
     * 
     * @param type 服务名rac-svr/orp-svr/...
     */
    @GetMapping("/rac/nacos/get/{type}/config")
    public Mono<Ro<?>> getNacosConfig(@PathVariable("type") final String type) {
        Ro<?> config = nacosApi.getNacosConfig(type);
        return Mono.create(callback -> callback.success(config));
    }

    /**
     * 添加配置信息
     *
     * @param to
     * @param type 服务名rac-svr/orp-svr/...
     */
    @RacOpLog(opType = "添加配置信息", opTitle = "添加配置类型: #{#p1}")
    @PostMapping("/rac/nacos/add/publish/{type}/config")
    public Mono<Ro<?>> addPublishConfig(@RequestBody final NacosAddTo to, @PathVariable("type") final String type) {
        Ro<?> updatePublishConfig = nacosApi.addPublishConfig(type, to);
        return Mono.create(callback -> callback.success(updatePublishConfig));
    }

    /**
     * 修改配置信息
     *
     * @param to
     * @param type 服务名rac-svr/orp-svr/...
     */
    @RacOpLog(opType = "修改配置信息", opTitle = "修改配置类型: #{#p1}")
    @PostMapping("/rac/nacos/modify/publish/{type}/config")
    public Mono<Ro<?>> publishConfig(@RequestBody final NacosModifyTo to, @PathVariable("type") final String type) {
        Ro<?> updatePublishConfig = nacosApi.updatePublishConfig(type, to);
        return Mono.create(callback -> callback.success(updatePublishConfig));
    }

    /**
     * 
     * 删除配置信息
     * 
     * @param to
     * @param type 服务名rac-svr/orp-svr/...
     * 
     * @return
     */
    @RacOpLog(opType = "删除配置信息", opTitle = "删除配置类型: #{#p1}")
    @PostMapping("/rac/nacos/del/publish/{type}/config")
    public Mono<Ro<?>> delPublishConfig(@RequestBody final NacosDelTo to, @PathVariable("type") final String type) {
        Ro<?> updatePublishConfig = nacosApi.delPublishConfig(type, to);
        return Mono.create(callback -> callback.success(updatePublishConfig));
    }

}
