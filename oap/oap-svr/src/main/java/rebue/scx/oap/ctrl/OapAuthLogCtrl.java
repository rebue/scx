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
import rebue.scx.oap.api.OapAuthLogApi;
import rebue.scx.oap.mo.OapAuthLogMo;
import rebue.scx.oap.to.OapAuthLogAddTo;
import rebue.scx.oap.to.OapAuthLogModifyTo;
import rebue.scx.oap.to.OapAuthLogPageTo;

/**
 * 认证记录
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OapAuthLogCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OapAuthLogApi api;

    /**
     * 添加认证记录
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oap/auth-log")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OapAuthLogAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改认证记录的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oap/auth-log")
    public Mono<Ro<?>> modify(@RequestBody final OapAuthLogModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除认证记录
     *
     * @param id 认证记录ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oap/auth-log")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个认证记录的信息
     *
     * @param id 认证记录ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/auth-log/get-by-id")
    public Mono<Ro<PojoRa<OapAuthLogMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断认证记录是否存在
     *
     * @param id 认证记录ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/auth-log/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询认证记录的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/auth-log/page")
    public Mono<Ro<PageRa<OapAuthLogMo>>> page(final OapAuthLogPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
