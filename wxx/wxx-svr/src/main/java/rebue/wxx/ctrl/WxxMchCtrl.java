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
import rebue.wxx.api.WxxMchApi;
import rebue.wxx.mo.WxxMchMo;
import rebue.wxx.to.WxxMchAddTo;
import rebue.wxx.to.WxxMchModifyTo;
import rebue.wxx.to.WxxMchOneTo;
import rebue.wxx.to.WxxMchPageTo;

/**
 * 商户(微信支付账户)
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class WxxMchCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private WxxMchApi api;

    /**
     * 添加商户
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/wxx/mch")
    public Mono<Ro<IdRa<java.lang.String>>> add(@RequestBody final WxxMchAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改商户的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/wxx/mch")
    public Mono<Ro<?>> modify(@RequestBody final WxxMchModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除商户
     *
     * @param id 商户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/wxx/mch")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 通过ID获取单个商户的信息
     *
     * @param id 商户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/mch/get-by-id")
    public Mono<Ro<PojoRa<WxxMchMo>>> getById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断商户是否存在
     *
     * @param id 商户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/mch/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.String id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询商户的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/mch/page")
    public Mono<Ro<PageRa<WxxMchMo>>> page(final WxxMchPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 通过条件获取单个商户的信息
     *
     * @param id 商户ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/wxx/mch/get-one")
    public Mono<Ro<PojoRa<WxxMchMo>>> getOne(final WxxMchOneTo qo) {
        return Mono.create(callback -> callback.success(api.getOne(qo)));
    }
}
