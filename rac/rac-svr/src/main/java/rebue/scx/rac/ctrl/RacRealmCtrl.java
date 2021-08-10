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
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.RacRealmApi;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.to.RacRealmAddTo;
import rebue.scx.rac.to.RacRealmModifyTo;
import rebue.scx.rac.to.RacRealmPageTo;

/**
 * 领域
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacRealmCtrl {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacRealmApi api;

    /**
     * 添加领域
     *
     * @param to 添加的具体信息
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/realm")
    @RacOpLog(opType = "添加领域", opTitle = "添加领域: #{#p0.name}")
    public Mono<Ro<IdRa<java.lang.String>>> add(@RequestBody final RacRealmAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改领域的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/realm")
    @RacOpLog(opType = "修改领域", opTitle = "修改领域: #{#p0.name}")
    public Mono<Ro<?>> modify(@RequestBody final RacRealmModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除领域
     *
     * @param id 领域ID
     * 
     * @mbg.dontOverWriteAnnotation
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/realm")
    @RacOpLog(opType = "删除领域", opTitle = "删除领域: #{#p0}")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个领域的信息
     *
     * @param id 领域ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/realm/get-by-id")
    public Mono<Ro<PojoRa<RacRealmMo>>> getById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断领域是否存在
     *
     * @param id 领域ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/realm/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询领域的信息
     *
     * @param qo 查询的具体条件
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/realm/page")
    public Mono<Ro<PageRa<RacRealmMo>>> page(final RacRealmPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询领域的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/realm/list-all")
    public Mono<Ro<ListRa<RacRealmMo>>> listAll() {
        return Mono.create(callback -> callback.success(api.listAll()));
    }
}
