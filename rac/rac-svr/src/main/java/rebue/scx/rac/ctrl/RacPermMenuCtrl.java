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
import rebue.scx.rac.api.RacPermMenuApi;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuPageTo;

/**
 * 权限菜单控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacPermMenuCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermMenuApi api;

    /**
     * 添加权限菜单
     *
     * @param to 添加的具体信息
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/perm-menu")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacPermMenuAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 添加/修改权限菜单
     *
     * @param to 添加的具体信息
     * 
     */
    @PostMapping("/rac/perm-menu/addPermMenuUrn")
    public Mono<Ro<IdRa<java.lang.Long>>> addPermMenuUrn(@RequestBody final RacPermMenuAddTo to) {
        return Mono.create(callback -> callback.success(null));
    }

    /**
     * 修改权限菜单的信息
     *
     * @param to 修改的具体数据
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/perm-menu")
    public Mono<Ro<?>> modify(@RequestBody final RacPermMenuModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除权限菜单
     *
     * @param id 权限菜单ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/perm-menu")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个权限菜单的信息
     *
     * @param id 权限菜单ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-menu/get-by-id")
    public Mono<Ro<PojoRa<RacPermMenuMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断权限菜单是否存在
     *
     * @param id 权限菜单ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-menu/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询权限菜单的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/perm-menu/listPermMenu")
    public Mono<Ro<ListRa<RacPermMenuMo>>> listPermMenu(final RacPermMenuListTo qo) {
        return Mono.create(callback -> callback.success(null));
    }

    /**
     * 查询权限菜单的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-menu/page")
    public Mono<Ro<PageRa<RacPermMenuMo>>> page(final RacPermMenuPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
