package rebue.wxx.ctrl;

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
import rebue.wxx.api.WxxAppApi;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.to.WxxAppAddTo;
import rebue.wxx.to.WxxAppModifyTo;
import rebue.wxx.to.WxxAppOneTo;
import rebue.wxx.to.WxxAppPageTo;

/**
 * APP
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class WxxAppCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private WxxAppApi api;

    /**
     * 添加APP
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/wxx/app")
    public Mono<Ro<IdRa<java.lang.String>>> add(@RequestBody final WxxAppAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改APP的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/wxx/app")
    public Mono<Ro<?>> modify(@RequestBody final WxxAppModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除APP
     *
     * @param id APPID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/wxx/app")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 通过ID获取单个APP的信息
     *
     * @param id APPID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/app/get-by-id")
    public Mono<Ro<PojoRa<WxxAppMo>>> getById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断APP是否存在
     *
     * @param id APPID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/app/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询APP的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/app/page")
    public Mono<Ro<PageRa<WxxAppMo>>> page(final WxxAppPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 通过条件获取单个APP的信息
     *
     * @param id APPID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/app/get-one")
    public Mono<Ro<PojoRa<WxxAppMo>>> getOne(final WxxAppOneTo qo) {
        return Mono.create(callback -> callback.success(api.getOne(qo)));
    }
}
