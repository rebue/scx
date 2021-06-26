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
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.RacDicApi;
import rebue.scx.rac.mo.RacDicMo;
import rebue.scx.rac.ra.DicListWithItemRa;
import rebue.scx.rac.to.RacDicAddTo;
import rebue.scx.rac.to.RacDicModifyTo;
import rebue.scx.rac.to.RacDicPageTo;
import rebue.scx.rac.to.ex.DicListWithItemTo;

/**
 * 字典
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacDicCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacDicApi api;

    /**
     * 添加字典
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加字典", opTitle = "添加字典: #{#p0.id}")
    @PostMapping("/rac/dic")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacDicAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改字典的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改字典", opTitle = "修改字典: #{#p0.id}")
    @PutMapping("/rac/dic")
    public Mono<Ro<?>> modify(@RequestBody final RacDicModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 查询字典的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/dic/page")
    public Mono<Ro<PageRa<RacDicMo>>> page(final RacDicPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询字典的信息
     *
     * @param qo 查询的具体条件
     */
    @GetMapping("/rac/dic/list-with-dic")
    public Mono<Ro<DicListWithItemRa>> listWithDic(final DicListWithItemTo to) {
        return Mono.create(callback -> callback.success(api.listWithDic(to)));
    }

    /**
     * 删除字典
     *
     * @param id 字典ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/dic")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个字典的信息
     *
     * @param id 字典ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/dic/get-by-id")
    public Mono<Ro<PojoRa<RacDicMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断字典是否存在
     *
     * @param id 字典ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/dic/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }
}
