package rebue.scx.rrl.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rrl.api.RrlRespLogApi;
import rebue.scx.rrl.mo.RrlRespLogMo;
import rebue.scx.rrl.to.RrlRespLogAddTo;
import rebue.scx.rrl.to.RrlRespLogModifyTo;
import rebue.scx.rrl.to.RrlRespLogPageTo;

/**
 * 响应日志控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RrlRespLogCtrl {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RrlRespLogApi api;

    /**
     * 添加响应日志
     *
     * @param to 添加的具体信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rrl/resp-log")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RrlRespLogAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改响应日志的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rrl/resp-log")
    public Mono<Ro<?>> modify(@RequestBody final RrlRespLogModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除响应日志
     *
     * @param id 响应日志ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rrl/resp-log")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个响应日志的信息
     *
     * @param id 响应日志ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/resp-log/get-by-id")
    public Mono<Ro<PojoRa<RrlRespLogMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断响应日志是否存在
     *
     * @param id 响应日志ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/resp-log/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询响应日志的信息
     *
     * @param qo 查询的具体条件
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/resp-log/page")
    public Mono<Ro<PageRa<RrlRespLogMo>>> page(final RrlRespLogPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

}
