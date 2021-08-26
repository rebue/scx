package com.github.rebue.scx.ctrl;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.to.OapAppAddTo;
import com.github.rebue.scx.to.OapAppModifyTo;
import com.github.rebue.scx.to.OapAppPageTo;
import com.github.rebue.scx.api.OapAppApi;
import reactor.core.publisher.Mono;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;

/**
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OapAppCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OapAppApi api;

    /**
     * 添加
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oap/app")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OapAppAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oap/app")
    public Mono<Ro<?>> modify(@RequestBody final OapAppModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除
     *
     * @param id ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oap/app")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个的信息
     *
     * @param id ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/app/get-by-id")
    public Mono<Ro<PojoRa<OapAppMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断是否存在
     *
     * @param id ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/app/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/app/page")
    public Mono<Ro<PageRa<OapAppMo>>> page(final OapAppPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}