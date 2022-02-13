package rebue.scx.etl.ctrl;

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
import rebue.scx.etl.api.EtlSyncStrategyDetailApi;
import rebue.scx.etl.mo.EtlSyncStrategyDetailMo;
import rebue.scx.etl.to.EtlSyncStrategyDetailAddTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailModifyTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailOneTo;
import rebue.scx.etl.to.EtlSyncStrategyDetailPageTo;

/**
 * 同步策略详情
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class EtlSyncStrategyDetailCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private EtlSyncStrategyDetailApi api;

    /**
     * 添加同步策略详情
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/etl/sync-strategy-detail")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final EtlSyncStrategyDetailAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改同步策略详情的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/etl/sync-strategy-detail")
    public Mono<Ro<?>> modify(@RequestBody final EtlSyncStrategyDetailModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除同步策略详情
     *
     * @param id 同步策略详情ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/etl/sync-strategy-detail")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 通过ID获取单个同步策略详情的信息
     *
     * @param id 同步策略详情ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/sync-strategy-detail/get-by-id")
    public Mono<Ro<PojoRa<EtlSyncStrategyDetailMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断同步策略详情是否存在
     *
     * @param id 同步策略详情ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/sync-strategy-detail/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询同步策略详情的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/sync-strategy-detail/page")
    public Mono<Ro<PageRa<EtlSyncStrategyDetailMo>>> page(final EtlSyncStrategyDetailPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 通过条件获取单个同步策略详情的信息
     *
     * @param id 同步策略详情ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/sync-strategy-detail/get-one")
    public Mono<Ro<PojoRa<EtlSyncStrategyDetailMo>>> getOne(final EtlSyncStrategyDetailOneTo qo) {
        return Mono.create(callback -> callback.success(api.getOne(qo)));
    }
}
