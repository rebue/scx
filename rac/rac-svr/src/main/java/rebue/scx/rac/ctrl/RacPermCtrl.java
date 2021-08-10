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
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.RacPermApi;
import rebue.scx.rac.mo.RacPermMo;
import rebue.scx.rac.ra.PermListWithGroupRa;
import rebue.scx.rac.to.RacPermAddTo;
import rebue.scx.rac.to.RacPermModifyTo;
import rebue.scx.rac.to.RacPermPageTo;

/**
 * 权限
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacPermCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermApi api;

    /**
     * 添加权限
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加权限", opTitle = "添加权限: #{#p0.name}")
    @PostMapping("/rac/perm")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacPermAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改权限的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改权限", opTitle = "修改权限: #{#p0.name}")
    @PutMapping("/rac/perm")
    public Mono<Ro<?>> modify(@RequestBody final RacPermModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除权限
     *
     * @mbg.dontOverWriteAnnotation
     * @param id 权限ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "删除权限", opTitle = "删除权限: #{#p0}")
    @DeleteMapping("/rac/perm")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个权限的信息
     *
     * @param id 权限ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm/get-by-id")
    public Mono<Ro<PojoRa<RacPermMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断权限是否存在
     *
     * @param id 权限ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询权限的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm/page")
    public Mono<Ro<PageRa<RacPermMo>>> page(final RacPermPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询带分组的权限列表
     *
     * @param realmId 领域ID
     */
    @GetMapping("/rac/perm/list-with-group")
    public Mono<Ro<PermListWithGroupRa>> listWithGroup(@RequestParam("realmId") final String realmId) {
        return Mono.create(callback -> callback.success(api.listWithGroup(realmId)));
    }

    /**
     * 上移动权限的信息
     *
     * @param qo
     */
    @PostMapping("/rac/perm/move-up")
    public Mono<Ro<?>> moveUp(@RequestBody final RacPermModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveUp(qo)));
    }

    /**
     * 下移动权限的信息
     *
     * @param qo
     */
    @PostMapping("/rac/perm/move-down")
    public Mono<Ro<?>> moveDown(@RequestBody final RacPermModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveDown(qo)));
    }

    /**
     * 启用权限
     *
     * @param to 启用的具体数据
     */
    @RacOpLog(opType = "启用权限", opTitle = "启用权限: #{#p0.id}")
    @PostMapping("/rac/perm/enable")
    public Mono<Ro<?>> enable(@RequestBody final RacPermModifyTo qo) {
        return Mono.create(callback -> callback.success(api.enable(qo)));
    }

    /**
     * 禁用权限
     *
     * @param to 禁用的具体数据
     */
    @RacOpLog(opType = "禁用权限", opTitle = "禁用权限: #{#p0.id}")
    @PostMapping("/rac/perm/disable")
    public Mono<Ro<?>> disable(@RequestBody final RacPermModifyTo qo) {
        return Mono.create(callback -> callback.success(api.disable(qo)));
    }
}
