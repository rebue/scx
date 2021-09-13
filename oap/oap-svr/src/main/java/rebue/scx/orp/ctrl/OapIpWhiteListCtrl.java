package rebue.scx.orp.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rebue.scx.orp.api.OapIpWhiteListApi;
import rebue.scx.orp.mo.OapIpWhiteListMo;
import rebue.scx.orp.to.OapIpWhiteListAddTo;
import rebue.scx.orp.to.OapIpWhiteListModifyTo;
import rebue.scx.orp.to.OapIpWhiteListPageTo;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;

/**
 * 第三方应用IP白名单
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OapIpWhiteListCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OapIpWhiteListApi api;

    /**
     * 添加第三方应用IP白名单
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oap/ip-white-list")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OapIpWhiteListAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改第三方应用IP白名单的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oap/ip-white-list")
    public Mono<Ro<?>> modify(@RequestBody final OapIpWhiteListModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除第三方应用IP白名单
     *
     * @param id 第三方应用IP白名单ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oap/ip-white-list")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个第三方应用IP白名单的信息
     *
     * @param id 第三方应用IP白名单ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/ip-white-list/get-by-id")
    public Mono<Ro<PojoRa<OapIpWhiteListMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断第三方应用IP白名单是否存在
     *
     * @param id 第三方应用IP白名单ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/ip-white-list/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询第三方应用IP白名单的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/ip-white-list/page")
    public Mono<Ro<PageRa<OapIpWhiteListMo>>> page(final OapIpWhiteListPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
