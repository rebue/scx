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
import rebue.scx.rac.api.RacPermUrnApi;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnListTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URN控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacPermUrnCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermUrnApi api;

    /**
     * 添加权限URN
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/perm-urn")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacPermUrnAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 添加修改URN
     *
     * @param to 添加的具体信息
     */
    @RacOpLog(opType = "添加/修改URN", opTitle = "添加/修改URN: #{#p0.permId}")
    @PostMapping("/rac/perm-urn/modifyByPermId")
    public Mono<Ro<?>> modifyByPermId(@RequestBody final RacPermUrnAddTo to) {
        return Mono.create(callback -> callback.success(api.modifyByPermId(to)));
    }

    /**
     * 修改权限URN的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/perm-urn")
    public Mono<Ro<?>> modify(@RequestBody final RacPermUrnModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除权限URN
     *
     * @param id 权限URNID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/perm-urn")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个权限URN的信息
     *
     * @param id 权限URNID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-urn/get-by-id")
    public Mono<Ro<PojoRa<RacPermUrnMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断权限URN是否存在
     *
     * @param id 权限URNID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-urn/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询权限URN的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/perm-urn/page")
    public Mono<Ro<PageRa<RacPermUrnMo>>> page(final RacPermUrnPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 通过permId查询权限URN的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/perm-urn/list")
    public Mono<Ro<ListRa<RacPermUrnMo>>> list(final RacPermUrnListTo qo) {
        return Mono.create(callback -> callback.success(api.list(qo)));
    }
}
