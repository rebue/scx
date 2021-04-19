package rebue.scx.rrl.ctrl;

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
import rebue.scx.rrl.api.RrlFilterApi;
import rebue.scx.rrl.mo.RrlFilterMo;
import rebue.scx.rrl.to.RrlFilterAddTo;
import rebue.scx.rrl.to.RrlFilterModifyTo;
import rebue.scx.rrl.to.RrlFilterPageTo;

/**
 * 过滤器控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RrlFilterCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RrlFilterApi api;

    /**
     * 添加过滤器
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rrl/filter")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RrlFilterAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改过滤器的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rrl/filter")
    public Mono<Ro<?>> modify(@RequestBody final RrlFilterModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除过滤器
     *
     * @param id 过滤器ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rrl/filter")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个过滤器的信息
     *
     * @param id 过滤器ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/filter/get-by-id")
    public Mono<Ro<PojoRa<RrlFilterMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断过滤器是否存在
     *
     * @param id 过滤器ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/filter/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询过滤器的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rrl/filter/page")
    public Mono<Ro<PageRa<RrlFilterMo>>> page(final RrlFilterPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
