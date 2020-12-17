package rebue.scx.rac.ctrl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rebue.scx.rac.mo.RacRoleMo;
import rebue.scx.rac.to.RacRoleAddTo;
import rebue.scx.rac.to.RacRoleModifyTo;
import rebue.scx.rac.to.RacRolePageTo;
import rebue.scx.rac.api.RacRoleApi;

import rebue.robotech.dic.ResultDic;
import reactor.core.publisher.Mono;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;

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
     * @param to 添加的具体信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/role")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacRoleAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改角色的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/role")
    public Mono<Ro<?>> modify(@RequestBody final RacRoleModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除角色
     *
     * @param id 角色ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
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

}
