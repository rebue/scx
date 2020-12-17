package rebue.scx.rac.ctrl;

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

import rebue.scx.rac.mo.RacPersonMo;
import rebue.scx.rac.to.RacPersonAddTo;
import rebue.scx.rac.to.RacPersonModifyTo;
import rebue.scx.rac.to.RacPersonPageTo;
import rebue.scx.rac.api.RacPersonApi;

import rebue.robotech.dic.ResultDic;
import reactor.core.publisher.Mono;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;

/**
 * 个人控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacPersonCtrl {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPersonApi api;

    /**
     * 添加个人
     *
     * @param to 添加的具体信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/person")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacPersonAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改个人的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/person")
    public Mono<Ro<?>> modify(@RequestBody final RacPersonModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除个人
     *
     * @param id 个人ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/person")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个个人的信息
     *
     * @param id 个人ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/person/get-by-id")
    public Mono<Ro<PojoRa<RacPersonMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断个人是否存在
     *
     * @param id 个人ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/person/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询个人的信息
     *
     * @param qo 查询的具体条件
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/person/page")
    public Mono<Ro<PageRa<RacPersonMo>>> page(final RacPersonPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

}
