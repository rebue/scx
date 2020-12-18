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
import rebue.scx.rac.api.RacDomainUserApi;
import rebue.scx.rac.mo.RacDomainUserMo;
import rebue.scx.rac.to.RacDomainUserAddTo;
import rebue.scx.rac.to.RacDomainUserModifyTo;
import rebue.scx.rac.to.RacDomainUserPageTo;

/**
 * 领域用户控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacDomainUserCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacDomainUserApi api;

    /**
     * 添加领域用户
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/domain-user")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacDomainUserAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改领域用户的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/domain-user")
    public Mono<Ro<?>> modify(@RequestBody final RacDomainUserModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除领域用户
     *
     * @param id 领域用户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/domain-user")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个领域用户的信息
     *
     * @param id 领域用户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/domain-user/get-by-id")
    public Mono<Ro<PojoRa<RacDomainUserMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断领域用户是否存在
     *
     * @param id 领域用户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/domain-user/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询领域用户的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/domain-user/page")
    public Mono<Ro<PageRa<RacDomainUserMo>>> page(final RacDomainUserPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
