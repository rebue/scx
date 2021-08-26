package com.github.rebue.scx.ctrl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.rebue.scx.mo.OapRedirectUriMo;
import com.github.rebue.scx.to.OapRedirectUriAddTo;
import com.github.rebue.scx.to.OapRedirectUriModifyTo;
import com.github.rebue.scx.to.OapRedirectUriPageTo;
import com.github.rebue.scx.api.OapRedirectUriApi;

import rebue.robotech.dic.ResultDic;
import reactor.core.publisher.Mono;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;

/**
 * 
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OapRedirectUriCtrl {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OapRedirectUriApi api;

    /**
     * 添加
     *
     * @param to 添加的具体信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oap/redirect-uri")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OapRedirectUriAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oap/redirect-uri")
    public Mono<Ro<?>> modify(@RequestBody final OapRedirectUriModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除
     *
     * @param id ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oap/redirect-uri")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个的信息
     *
     * @param id ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/redirect-uri/get-by-id")
    public Mono<Ro<PojoRa<OapRedirectUriMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断是否存在
     *
     * @param id ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/redirect-uri/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询的信息
     *
     * @param qo 查询的具体条件
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/redirect-uri/page")
    public Mono<Ro<PageRa<OapRedirectUriMo>>> page(final OapRedirectUriPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

}
