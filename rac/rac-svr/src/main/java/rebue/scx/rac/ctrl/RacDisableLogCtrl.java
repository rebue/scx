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
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacDisableLogApi;
import rebue.scx.rac.mo.RacDisableLogMo;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.RacDisableLogPageTo;

/**
 * 账户启/禁用日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacDisableLogCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacDisableLogApi api;

    /**
     * 添加账户启/禁用日志
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/disable-log")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacDisableLogAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改账户启/禁用日志的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/disable-log")
    public Mono<Ro<?>> modify(@RequestBody final RacDisableLogModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除账户启/禁用日志
     *
     * @param id 账户启/禁用日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/disable-log")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个账户启/禁用日志的信息
     *
     * @param id 账户启/禁用日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/disable-log/get-by-id")
    public Mono<Ro<PojoRa<RacDisableLogMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断账户启/禁用日志是否存在
     *
     * @param id 账户启/禁用日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/disable-log/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询账户启/禁用日志的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/disable-log/page")
    public Mono<Ro<PageRa<RacDisableLogMo>>> page(final RacDisableLogPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
