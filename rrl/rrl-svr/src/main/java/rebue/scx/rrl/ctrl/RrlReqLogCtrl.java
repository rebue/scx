package rebue.scx.rrl.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rrl.api.RrlReqLogApi;
import rebue.scx.rrl.mo.RrlReqLogMo;
import rebue.scx.rrl.to.RrlReqLogPageTo;

/**
 * 请求日志控制器
 *
 * @mbg.removedMember add,modify,del
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RrlReqLogCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RrlReqLogApi api;

    /**
     * 获取单个请求日志的信息
     *
     * @param id 请求日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/req-log/get-by-id")
    public Mono<Ro<PojoRa<RrlReqLogMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断请求日志是否存在
     *
     * @param id 请求日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/req-log/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询请求日志的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/req-log/page")
    public Mono<Ro<PageRa<RrlReqLogMo>>> page(final RrlReqLogPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
