package rebue.scx.oap.ctrl;

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
import rebue.scx.oap.api.OapGrantApi;
import rebue.scx.oap.mo.OapGrantMo;
import rebue.scx.oap.to.OapGrantAddTo;
import rebue.scx.oap.to.OapGrantModifyTo;
import rebue.scx.oap.to.OapGrantPageTo;

/**
 * 三方应用账户信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OapGrantCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OapGrantApi api;

    /**
     * 添加三方应用账户信息
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oap/grant")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OapGrantAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改三方应用账户信息的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oap/grant")
    public Mono<Ro<?>> modify(@RequestBody final OapGrantModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除三方应用账户信息
     *
     * @param id 三方应用账户信息ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oap/grant")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个三方应用账户信息的信息
     *
     * @param id 三方应用账户信息ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/grant/get-by-id")
    public Mono<Ro<PojoRa<OapGrantMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断三方应用账户信息是否存在
     *
     * @param id 三方应用账户信息ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/grant/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询三方应用账户信息的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/grant/page")
    public Mono<Ro<PageRa<OapGrantMo>>> page(final OapGrantPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
