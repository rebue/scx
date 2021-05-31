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
import rebue.scx.rac.api.RacDomainApi;
import rebue.scx.rac.mo.RacDomainMo;
import rebue.scx.rac.to.RacDomainAddTo;
import rebue.scx.rac.to.RacDomainModifyTo;
import rebue.scx.rac.to.RacDomainPageTo;

/**
 * 领域控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacDomainCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacDomainApi api;

    /**
     * 添加领域
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加领域", opTitle = "添加领域: #{#p0.name}")
    @PostMapping("/rac/domain")
    public Mono<Ro<IdRa<java.lang.String>>> add(@RequestBody final RacDomainAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改领域的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改领域", opTitle = "修改领域: #{#p0.name}")
    @PutMapping("/rac/domain")
    public Mono<Ro<?>> modify(@RequestBody final RacDomainModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除领域
     *
     * @mbg.dontOverWriteAnnotation
     * @param id 领域ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "删除领域", opTitle = "删除领域: #{#p0}")
    @DeleteMapping("/rac/domain")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个领域的信息
     *
     * @param id 领域ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/domain/get-by-id")
    public Mono<Ro<PojoRa<RacDomainMo>>> getById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断领域是否存在
     *
     * @param id 领域ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/domain/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询领域的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/domain/page")
    public Mono<Ro<PageRa<RacDomainMo>>> page(final RacDomainPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询领域的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/domain/list-all")
    public Mono<Ro<ListRa<RacDomainMo>>> listAll() {
        return Mono.create(callback -> callback.success(api.listAll()));
    }
}
