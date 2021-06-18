package rebue.scx.oss.ctrl;

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
import rebue.scx.oss.api.OssObjApi;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjPageTo;

/**
 * 对象控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OssObjCtrl {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OssObjApi api;

    /**
     * 添加对象
     *
     * @param to 添加的具体信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oss/obj")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OssObjAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改对象的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oss/obj")
    public Mono<Ro<?>> modify(@RequestBody final OssObjModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除对象
     *
     * @param id 对象ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oss/obj")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个对象的信息
     *
     * @param id 对象ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oss/obj/get-by-id")
    public Mono<Ro<PojoRa<OssObjMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断对象是否存在
     *
     * @param id 对象ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oss/obj/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询对象的信息
     *
     * @param qo 查询的具体条件
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oss/obj/page")
    public Mono<Ro<PageRa<OssObjMo>>> page(final OssObjPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

}
