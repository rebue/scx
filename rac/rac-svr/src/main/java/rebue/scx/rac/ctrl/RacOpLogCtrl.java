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

import rebue.scx.rac.mo.RacOpLogMo;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.scx.rac.to.RacOpLogModifyTo;
import rebue.scx.rac.to.RacOpLogPageTo;
import rebue.scx.rac.api.RacOpLogApi;

import rebue.robotech.dic.ResultDic;
import reactor.core.publisher.Mono;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;

/**
 * 操作日志控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacOpLogCtrl {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacOpLogApi api;

    /**
     * 添加操作日志
     *
     * @param to 添加的具体信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/op-log")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacOpLogAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改操作日志的信息
     *
     * @param to 修改的具体数据
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/op-log")
    public Mono<Ro<?>> modify(@RequestBody final RacOpLogModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除操作日志
     *
     * @param id 操作日志ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/op-log")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个操作日志的信息
     *
     * @param id 操作日志ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/op-log/get-by-id")
    public Mono<Ro<PojoRa<RacOpLogMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断操作日志是否存在
     *
     * @param id 操作日志ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/op-log/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询操作日志的信息
     *
     * @param qo 查询的具体条件
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/op-log/page")
    public Mono<Ro<PageRa<RacOpLogMo>>> page(final RacOpLogPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

}
