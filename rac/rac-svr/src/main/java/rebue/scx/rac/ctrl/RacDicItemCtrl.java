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
import rebue.scx.rac.api.RacDicItemApi;
import rebue.scx.rac.mo.RacDicItemMo;
import rebue.scx.rac.to.RacDicItemAddTo;
import rebue.scx.rac.to.RacDicItemModifyTo;
import rebue.scx.rac.to.RacDicItemPageTo;

/**
 * 字典项
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacDicItemCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacDicItemApi api;

    /**
     * 添加字典项
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加字典项", opTitle = "添加字典项: #{#p0.id}")
    @PostMapping("/rac/dic-item")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacDicItemAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改字典项的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改字典项", opTitle = "修改字典项: #{#p0.id}")
    @PutMapping("/rac/dic-item")
    public Mono<Ro<?>> modify(@RequestBody final RacDicItemModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 上移动字典项的信息，传入ID
     *
     * @param qo
     */
    @PostMapping("/rac/dic-item/move-up")
    public Mono<Ro<?>> moveUp(@RequestBody final RacDicItemModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveUp(qo)));
    }

    /**
     * 下移动字典项的信息，传入ID
     *
     * @param qo
     */
    @PostMapping("/rac/dic-item/move-down")
    public Mono<Ro<?>> moveDown(@RequestBody final RacDicItemModifyTo qo) {
        return Mono.create(callback -> callback.success(api.moveDown(qo)));
    }

    /**
     * 查询字典项的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/dic-item/page")
    public Mono<Ro<PageRa<RacDicItemMo>>> page(final RacDicItemPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 删除字典项
     *
     * @param id 字典项ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/dic-item")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个字典项的信息
     *
     * @param id 字典项ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/dic-item/get-by-id")
    public Mono<Ro<PojoRa<RacDicItemMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断字典项是否存在
     *
     * @param id 字典项ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/dic-item/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }
}
