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
import rebue.scx.rac.api.RacPermCommandApi;
import rebue.scx.rac.mo.RacPermCommandMo;
import rebue.scx.rac.to.RacPermCommandAddTo;
import rebue.scx.rac.to.RacPermCommandListTo;
import rebue.scx.rac.to.RacPermCommandModifyTo;
import rebue.scx.rac.to.RacPermCommandOneTo;
import rebue.scx.rac.to.RacPermCommandPageTo;

/**
 * 权限命令
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacPermCommandCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermCommandApi api;

    /**
     * 添加权限命令
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/perm-command")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacPermCommandAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改权限命令的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/perm-command")
    public Mono<Ro<?>> modify(@RequestBody final RacPermCommandModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除权限命令
     *
     * @param id 权限命令ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/perm-command")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 通过ID获取单个权限命令的信息
     *
     * @param id 权限命令ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-command/get-by-id")
    public Mono<Ro<PojoRa<RacPermCommandMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断权限命令是否存在
     *
     * @param id 权限命令ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-command/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询权限命令的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-command/page")
    public Mono<Ro<PageRa<RacPermCommandMo>>> page(final RacPermCommandPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询权限命令的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/perm-command/list")
    public Mono<Ro<ListRa<RacPermCommandMo>>> list(final RacPermCommandListTo qo) {
        return Mono.create(callback -> callback.success(api.list(qo)));
    }

    /**
     * 通过条件获取单个权限命令的信息
     *
     * @param id 权限命令ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-command/get-one")
    public Mono<Ro<PojoRa<RacPermCommandMo>>> getOne(final RacPermCommandOneTo qo) {
        return Mono.create(callback -> callback.success(api.getOne(qo)));
    }
}
