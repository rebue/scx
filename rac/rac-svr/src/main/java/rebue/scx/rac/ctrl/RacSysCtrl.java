package rebue.scx.rac.ctrl;

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
import rebue.robotech.ra.ListRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacSysApi;
import rebue.scx.rac.mo.RacSysMo;
import rebue.scx.rac.to.RacSysAddTo;
import rebue.scx.rac.to.RacSysListTo;
import rebue.scx.rac.to.RacSysModifyTo;
import rebue.scx.rac.to.RacSysPageTo;

/**
 * 系统控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacSysCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacSysApi api;

    /**
     * 添加系统
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/sys")
    public Mono<Ro<IdRa<java.lang.String>>> add(@RequestBody final RacSysAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改系统的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/sys")
    public Mono<Ro<?>> modify(@RequestBody final RacSysModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除系统
     *
     * @param id 系统ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/sys")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个系统的信息
     *
     * @param id 系统ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/sys/get-by-id")
    public Mono<Ro<PojoRa<RacSysMo>>> getById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断系统是否存在
     *
     * @param id 系统ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/sys/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询系统的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/sys/page")
    public Mono<Ro<PageRa<RacSysMo>>> page(final RacSysPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询系统的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/sys/list")
    public Mono<Ro<ListRa<RacSysMo>>> list(final RacSysListTo qo) {
        return Mono.create(callback -> callback.success(api.list(qo)));
    }
}
