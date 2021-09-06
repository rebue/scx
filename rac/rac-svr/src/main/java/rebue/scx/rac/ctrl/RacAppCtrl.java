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
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppPageTo;

/**
 * 应用
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacAppCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacAppApi api;

    /**
     * 添加应用
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @param to 添加的具体信息
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加应用", opTitle = "添加应用: #{#p0.name}")
    @PostMapping("/rac/app")
    public Mono<Ro<IdRa<java.lang.String>>> add(@RequestBody final RacAppAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改应用的信息
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @param to 修改的具体数据
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改应用", opTitle = "修改应用: #{#p0.name}")
    @PutMapping("/rac/app")
    public Mono<Ro<?>> modify(@RequestBody final RacAppModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 上移动应用的信息，传入ID
     *
     * @param qo
     */
    @PostMapping("/rac/app/move-up")
    public Mono<Ro<?>> moveUp(@RequestBody final RacAppModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveUp(qo)));
    }

    /**
     * 下移动应用的信息，传入ID
     *
     * @param qo
     */
    @PostMapping("/rac/app/move-down")
    public Mono<Ro<?>> moveDown(@RequestBody final RacAppModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveDown(qo)));
    }

    /**
     * 是否启用应用
     *
     * @param to 修改的具体数据
     */
    @RacOpLog(opType = "启/禁用应用", opTitle = "启/禁用应用: #{#p0.id}")
    @PutMapping("/rac/app/enable")
    public Mono<Ro<?>> enable(@RequestBody final RacAppEnabledTo to) {
        return Mono.create(callback -> callback.success(api.enable(to)));
    }

    /**
     * 删除应用
     *
     * @mbg.dontOverWriteAnnotation
     * 
     * @param id 应用ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "删除应用", opTitle = "删除应用: #{#p0}")
    @DeleteMapping("/rac/app")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个应用的信息
     *
     * @param id 应用ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/app/get-by-id")
    public Mono<Ro<PojoRa<RacAppMo>>> getById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断应用是否存在
     *
     * @param id 应用ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/app/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询应用的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/app/page")
    public Mono<Ro<PageRa<RacAppMo>>> page(final RacAppPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询应用的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/app/list")
    public Mono<Ro<ListRa<RacAppMo>>> list(final RacAppListTo qo) {
        return Mono.create(callback -> callback.success(api.list(qo)));
    }
}
