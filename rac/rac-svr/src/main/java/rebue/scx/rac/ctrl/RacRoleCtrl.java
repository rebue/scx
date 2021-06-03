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
import rebue.scx.rac.api.RacRoleApi;
import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.mo.RacRolePermMo;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleListTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRolePageTo;
import rebue.scx.rac.to.RacRolePermAddTo;

/**
 * 角色控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacRoleCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacRoleApi api;

    /**
     * 添加角色
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @param to 添加的具体信息
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加角色", opTitle = "添加角色: #{#p0.name}")
    @PostMapping("/rac/role")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacRoleAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 添加角色和权限的关系
     *
     * @param to 添加的具体信息
     */
    @RacOpLog(opType = "添加角色权限关系", opTitle = "添加角色权限关系: #{#p0.roleId}")
    @PostMapping("/rac/role/add-role-perm")
    public Mono<Ro<?>> addRolePerm(@RequestBody final RacRolePermAddTo to) {
        return Mono.create(callback -> callback.success(api.addRolePerm(to)));
    }

    /**
     * 修改角色的信息
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @param to 修改的具体数据
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改角色", opTitle = "修改角色: #{#p0.name}")
    @PutMapping("/rac/role")
    public Mono<Ro<?>> modify(@RequestBody final RacRoleModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除角色
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @param id 角色ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "删除角色", opTitle = "删除角色: #{#p0}")
    @DeleteMapping("/rac/role")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个角色的信息
     *
     * @param id 角色ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/role/get-by-id")
    public Mono<Ro<PojoRa<RacRoleMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断角色是否存在
     *
     * @param id 角色ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/role/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询角色的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/role/page")
    public Mono<Ro<PageRa<RacRoleMo>>> page(final RacRolePageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 上移动角色的信息
     *
     * @param qo
     */
    @PostMapping("/rac/role/move-up")
    public Mono<Ro<?>> moveUp(@RequestBody final RacRoleModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveUp(qo)));
    }

    /**
     * 下移动角色的信息
     *
     * @param qo
     */
    @PostMapping("/rac/role/move-down")
    public Mono<Ro<?>> moveDown(@RequestBody final RacRoleModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveDown(qo)));
    }

    /**
     * 启用角色
     *
     * @param to 启用的具体数据
     */
    @RacOpLog(opType = "启用角色", opTitle = "启用角色: #{#p0.id}")
    @PostMapping("/rac/role/enable")
    public Mono<Ro<?>> enable(@RequestBody final RacRoleModifyTo qo) {
        return Mono.create(callback -> callback.success(api.enable(qo)));
    }

    /**
     * 禁用角色
     *
     * @param to 禁用的具体数据
     */
    @RacOpLog(opType = "禁用角色", opTitle = "禁用角色: #{#p0.id}")
    @PostMapping("/rac/role/disable")
    public Mono<Ro<?>> disable(@RequestBody final RacRoleModifyTo qo) {
        return Mono.create(callback -> callback.success(api.disable(qo)));
    }

    /**
     * 查询角色已有的权限的关系
     *
     * @param to 添加的具体信息
     */
    @GetMapping("/rac/role/list-role-perm")
    public Mono<Ro<ListRa<RacRolePermMo>>> listRolePerm(@RequestParam("roleId") final java.lang.Long roleId) {
        return Mono.create(callback -> callback.success(api.listRolePerm(roleId)));
    }

    /**
     * 查询角色的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/role/list")
    public Mono<Ro<ListRa<RacRoleMo>>> list(final RacRoleListTo qo) {
        return Mono.create(callback -> callback.success(api.list(qo)));
    }
}
