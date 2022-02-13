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
import rebue.scx.rac.api.RacPermGroupApi;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermGroupOneTo;
import rebue.scx.rac.to.RacPermGroupPageTo;

/**
 * 权限分组
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacPermGroupCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermGroupApi api;

    /**
     * 添加权限分组
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加权限分组", opTitle = "添加权限分组: #{#p0.name}")
    @PostMapping("/rac/perm-group")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacPermGroupAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改权限分组的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改权限分组", opTitle = "修改权限分组: #{#p0.name}")
    @PutMapping("/rac/perm-group")
    public Mono<Ro<?>> modify(@RequestBody final RacPermGroupModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除权限分组
     *
     * @mbg.dontOverWriteAnnotation
     * @param id 权限分组ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "删除权限分组", opTitle = "删除权限分组: #{#p0}")
    @DeleteMapping("/rac/perm-group")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 通过ID获取单个权限分组的信息
     *
     * @param id 权限分组ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-group/get-by-id")
    public Mono<Ro<PojoRa<RacPermGroupMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断权限分组是否存在
     *
     * @param id 权限分组ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-group/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询权限分组的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-group/page")
    public Mono<Ro<PageRa<RacPermGroupMo>>> page(final RacPermGroupPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 上移动权限分组的信息，传入ID
     *
     * @param qo
     */
    @PostMapping("/rac/perm-group/move-up")
    public Mono<Ro<?>> moveUp(@RequestBody final RacPermGroupModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveUp(qo)));
    }

    /**
     * 下移动权限分组的信息，传入ID
     *
     * @param qo
     */
    @PostMapping("/rac/perm-group/move-down")
    public Mono<Ro<?>> moveDown(@RequestBody final RacPermGroupModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveDown(qo)));
    }

    /**
     * 启用权限分组
     *
     * @param to 启用的具体数据
     */
    @RacOpLog(opType = "启用权限分组", opTitle = "启用权限分组: #{#p0.id}")
    @PostMapping("/rac/perm-group/enable")
    public Mono<Ro<?>> enable(@RequestBody final RacPermGroupModifyTo qo) {
        return Mono.create(callback -> callback.success(api.enable(qo)));
    }

    /**
     * 禁用权限分组
     *
     * @param to 禁用的具体数据
     */
    @RacOpLog(opType = "禁用权限分组", opTitle = "禁用权限分组: #{#p0.id}")
    @PostMapping("/rac/perm-group/disable")
    public Mono<Ro<?>> disable(@RequestBody final RacPermGroupModifyTo qo) {
        return Mono.create(callback -> callback.success(api.disable(qo)));
    }

    /**
     * 通过条件获取单个权限分组的信息
     *
     * @param id 权限分组ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-group/get-one")
    public Mono<Ro<PojoRa<RacPermGroupMo>>> getOne(final RacPermGroupOneTo qo) {
        return Mono.create(callback -> callback.success(api.getOne(qo)));
    }
}
