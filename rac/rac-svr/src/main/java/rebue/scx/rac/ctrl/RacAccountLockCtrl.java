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
import rebue.scx.rac.api.RacAccountLockApi;
import rebue.scx.rac.mo.RacAccountLockMo;
import rebue.scx.rac.to.RacAccountLockAddTo;
import rebue.scx.rac.to.RacAccountLockModifyTo;
import rebue.scx.rac.to.RacAccountLockPageTo;

/**
 * 账户锁定
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacAccountLockCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacAccountLockApi api;

    /**
     * 添加账户锁定
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/account-lock")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacAccountLockAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改账户锁定的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/account-lock")
    public Mono<Ro<?>> modify(@RequestBody final RacAccountLockModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除账户锁定
     *
     * @param id 账户锁定ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/account-lock")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个账户锁定的信息
     *
     * @param id 账户锁定ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/account-lock/get-by-id")
    public Mono<Ro<PojoRa<RacAccountLockMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断账户锁定是否存在
     *
     * @param id 账户锁定ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/account-lock/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询账户锁定的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/account-lock/page")
    public Mono<Ro<PageRa<RacAccountLockMo>>> page(final RacAccountLockPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
